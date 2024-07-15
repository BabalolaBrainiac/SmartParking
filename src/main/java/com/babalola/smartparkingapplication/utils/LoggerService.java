package com.babalola.smartparkingapplication.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerService {

    private static final Logger logger = LoggerFactory.getLogger(LoggerService.class);

    public void info(String message) {
        logger.info(message);
    }

    public void debug(String message) {
        logger.debug(message);
    }

    public void error(String message, Throwable t) {
        logger.error(message, t);
    }
}
