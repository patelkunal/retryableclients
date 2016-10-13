package org.coderearth.kitchens.retryableclients.webservice.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kunal_patel on 10/13/16.
 */
@RestController
public class UnreliableServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnreliableServiceController.class);

    @RequestMapping(value = "/rest/unreliable/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    protected ResponseEntity processRequest(@PathVariable final int id) {
        switch (id) {
            case 1:
                LOGGER.info("INCOMING REQUEST, id = {}, responding BAD_REQUEST(400)", id);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            case 2:
                LOGGER.info("INCOMING REQUEST, id = {}, responding UNAUTHORIZED(401)", id);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            case 3:
                LOGGER.info("INCOMING REQUEST, id = {}, responding FORBIDDEN(403)", id);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            case 4:
                LOGGER.info("INCOMING REQUEST, id = {}, responding METHOD_NOT_ALLOWED(405)", id);
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
            case 5:
                LOGGER.info("INCOMING REQUEST, id = {}, responding REQUEST_TIMEOUT(408)", id);
                return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
            case 6:
                LOGGER.info("INCOMING REQUEST, id = {}, responding UNSUPPORTED_MEDIA_TYPE(415)", id);
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
            case 7:
                LOGGER.info("INCOMING REQUEST, id = {}, responding TOO_MANY_REQUESTS(429)", id);
                return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
            case 8:
                LOGGER.info("INCOMING REQUEST, id = {}, responding INTERNAL_SERVER_ERROR(500)", id);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            case 9:
                LOGGER.info("INCOMING REQUEST, id = {}, responding SERVICE_UNAVAILABLE(503)", id);
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
            default:
                LOGGER.info("INCOMING REQUEST, id = {}, responding OK(200)", id);
                return ResponseEntity.ok(null);
        }
    }

}
