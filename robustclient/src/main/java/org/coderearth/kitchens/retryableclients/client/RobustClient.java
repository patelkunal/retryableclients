package org.coderearth.kitchens.retryableclients.client;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kunal_patel on 10/13/16.
 */
public class RobustClient implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(RobustClient.class);

    private static final String URL_TEMPLATE = "http://localhost:8080/rest/unreliable/%d";

    private final RetryTemplate retryTemplate;

    private final RestTemplate restTemplate;

    public RobustClient(final RetryTemplate retryTemplate, final RestTemplate restTemplate) {
        this.retryTemplate = retryTemplate;
        this.restTemplate = restTemplate;
    }

    public void gatherInfoRobustly() {
        final int statusCode = retryTemplate.execute(
                context -> {
                    final String uri = String.format(URL_TEMPLATE, context.getRetryCount());
                    LOGGER.trace("Requesting from uri = {}", uri);
                    final ResponseEntity<String> resp = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
                    LOGGER.trace("Response = {}", resp);
                    return resp.getStatusCodeValue();
                }
        );
        Preconditions.checkState(statusCode == 200, "Failure in remote webservice call, status = %d", statusCode);
    }

    public void gatherInfoFromRemote() {
        this.gatherInfoFromRemote(10);
    }

    public void gatherInfoFromRemote(final int id) {
        final String uri = String.format(URL_TEMPLATE, id);
        try {
            final ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
            Preconditions.checkState(responseEntity.getStatusCode().is2xxSuccessful(), "Failure in remote webservice call, status = %d, body = %s", responseEntity.getStatusCodeValue(), responseEntity.getBody());
        } catch (HttpClientErrorException e) {
            LOGGER.error("Because of 4xx", e);
        } catch (HttpServerErrorException e) {
            LOGGER.error("Because of 5xx", e);
        }
    }

    public void afterPropertiesSet() throws Exception {
        Preconditions.checkState(this.retryTemplate != null, "%s cannot be null after initialization !!", "retryTemplate");
        Preconditions.checkState(this.restTemplate != null, "%s cannot be null after initialization !!", "restTemplate");
    }
}
