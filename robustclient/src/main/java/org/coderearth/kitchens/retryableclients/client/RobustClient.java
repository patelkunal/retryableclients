package org.coderearth.kitchens.retryableclients.client;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.retry.support.RetryTemplate;

/**
 * Created by kunal_patel on 10/13/16.
 */
public class RobustClient implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(RobustClient.class);

    private final RetryTemplate retryTemplate;

    public RobustClient(final RetryTemplate retryTemplate) {
        this.retryTemplate = retryTemplate;
    }

    public void afterPropertiesSet() throws Exception {
        LOGGER.trace("Inside RobustClient.init()");
        Preconditions.checkState(this.retryTemplate != null, "%s cannot be null after initialization !!", "retryTemplate");
        LOGGER.trace("Finishing RobustClient.init()");
    }
}
