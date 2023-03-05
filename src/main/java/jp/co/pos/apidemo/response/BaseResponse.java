package jp.co.pos.apidemo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jp.co.pos.apidemo.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class BaseResponse {

    @JsonIgnore
    protected HttpStatus statusCode;

    @JsonProperty("errorCode")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Integer errorCode;

    @JsonProperty("message")
    protected String message;

    public BaseResponse() { this.statusCode = HttpStatus.OK; }

    public void setStatusCode(HttpStatus statusCode) { this.statusCode = statusCode; }

    public void setMessage(@NonNull String message) { this.message = message; }

    @NonNull
    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public <T extends BaseException> BaseResponse(@NonNull T e) {
        this.statusCode = e.getStatus();
        this.errorCode = e.getErrorCode();
    }

    @Nullable
    public Integer getErrorCode() {
        return errorCode;
    }

    @NonNull
    public String getMessage() {
        return message;
    }
}
