package io.github.sdxqw.utils.interfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ILogger {
    Logger log = LogManager.getLogger();

    static void error(Object message, Object... parameters) {
        log.error( (String) message, parameters );
    }

    static void info(Object message, Object... parameters) {
        log.info( (String) message, parameters );
    }
}
