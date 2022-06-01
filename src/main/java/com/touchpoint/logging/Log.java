package com.touchpoint.logging;

import org.apache.log4j.Logger;

import java.util.Objects;

public final class Log {
    private static final Logger LOGGER = Logger.getLogger("custom-logger");
    public static final String MESSAGE_CANNOT_BE_NULL = "Message cannot be null.";

    public static void info(Object message) {
        Objects.requireNonNull(message, MESSAGE_CANNOT_BE_NULL);
        LOGGER.info(message);
    }

    public static void error(Object message, Throwable exception) {
        Objects.requireNonNull(message, MESSAGE_CANNOT_BE_NULL);
        Objects.requireNonNull(exception, "Exception cannot be null.");
        LOGGER.error(message, exception);
    }
}
