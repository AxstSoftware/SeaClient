package io.github.sdxqw.utils.interfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ILogger {
    private static final Logger log = LogManager.getLogger();

    public static void error(Object message, Object... parameters) {
        log.error( (String) message, parameters );
    }

    public static void info(Object message, Object... parameters) {
        log.info( (String) message, parameters );
    }
}
