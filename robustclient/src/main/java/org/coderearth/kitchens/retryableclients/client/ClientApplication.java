package org.coderearth.kitchens.retryableclients.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by kunal_patel on 10/13/16.
 */
public class ClientApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientApplication.class);

    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
    }

}
