package jp.co.pos.apidemo.exception;

import lombok.NonNull;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

public class POSException extends BaseException {

    /**
     * type of pos exception
     */
    public enum Type {
        BAD_ARGUMENTS(
                HttpStatus.BAD_REQUEST, BaseException.ERROR_CODE_BAD_ARGUMENTS, LogLevel.ERROR
        ),
        UNKNOWN(
                HttpStatus.INTERNAL_SERVER_ERROR, BaseException.ERROR_CODE_INTERNAL_ERROR, LogLevel.FATAL
        ),
        NOT_FOUND(
                HttpStatus.NOT_FOUND, BaseException.ERROR_CODE_NOT_FOUND, LogLevel.INFO
        );

        private HttpStatus status; // status that should be returned for this exception
        private Integer errorCode; // result code that should be returned for this exception
        private LogLevel logLevel; // log level

        Type(HttpStatus status, Integer errorCode, LogLevel logLevel) {
            this.errorCode = errorCode;
            this.status = status;
            this.logLevel = logLevel;
        }
    }

    private POSException.Type type;

    public POSException(POSException.Type type) {
        super(type);
        this.type = type;
    }

    public POSException(POSException.Type type, Exception e) {
        super(type, e);
        this.type = type;
    }

    @Override
    @NonNull
    public POSException.Type getType() {
        return type;
    }

    @Override
    @NonNull
    public HttpStatus getStatus() {
        return type.status;
    }

    @Override
    @NonNull
    public Integer getErrorCode() {
        return type.errorCode;
    }

    @NonNull
    @Override
    public LogLevel getLogLevel() {
        return type.logLevel;

    }
}
