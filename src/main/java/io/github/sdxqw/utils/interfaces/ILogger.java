package io.github.sdxqw.utils.interfaces;

import io.github.sdxqw.SeaCore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ILogger {
    Logger log = LogManager.getLogger( SeaCore.class.getName() );

    default void error(Object message, Object... parameters) {
        log.error( (String) message, parameters );
    }

    default void info(Object message, Object... parameters) {
        log.info( (String) message, parameters );
    }
}
