package jp.co.pos.apidemo.util;

import jp.co.pos.apidemo.exception.BaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.spi.ExtendedLoggerWrapper;
import org.springframework.boot.logging.LogLevel;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

public class POSLogger {
    private static final Map<String, POSLogger> loggerMap = new HashMap<>();

    private final ExtendedLoggerWrapper log;

    private POSLogger(@NonNull final Logger log) {
        this.log = new ExtendedLoggerWrapper((AbstractLogger) log, log.getName(), log.getMessageFactory());
    }

    public static synchronized POSLogger getLogger(final Class<?> clazz) {
        String className = clazz.getName();
        POSLogger posLogger = loggerMap.get(className);
        if (posLogger == null) {
            final Logger wrapped = LogManager.getLogger();
            posLogger = new POSLogger(wrapped);
            loggerMap.put(className, posLogger);
        }
        return posLogger;
    }

    public void info(@NonNull Object message) {
        if (log.isInfoEnabled()) {
            log.info(message);
        }
    }

    public void warn(@NonNull Object message, @Nullable Throwable throwable) {
        if (log.isWarnEnabled()) {
            log.warn(message, throwable);
        }
    }

    public void error(@NonNull Object message, @Nullable Throwable throwable) {
        if (log.isErrorEnabled()) {
            log.error(message, throwable);
        }
    }

    public void fatal(@NonNull Object message, @Nullable Throwable throwable) {
        if (log.isFatalEnabled()) {
            log.fatal(message, throwable);
        }
    }

    public void log(@NonNull Object message, @NonNull BaseException exception) {
        log(message, exception, exception.getLogLevel());
    }

    public void log(@NonNull Object message, @Nullable Throwable throwable, LogLevel logLevel) {
        switch (logLevel) {
            case INFO:
                info(message);
                break;
            case WARN:
                warn(message, throwable);
                break;
            case ERROR:
                error(message, throwable);
                break;
            case FATAL:
                fatal(message, throwable);
                break;
            case OFF:
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
