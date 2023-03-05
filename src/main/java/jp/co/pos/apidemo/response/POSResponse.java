package jp.co.pos.apidemo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jp.co.pos.apidemo.exception.POSException;
import org.springframework.lang.NonNull;

public class POSResponse extends BaseResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("finalPrice")
    private double finalPrice;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("points")
    private int points;

    public POSResponse() { super(); }

    public POSResponse(double finalPrice, int points) {
        super();
        this.finalPrice = finalPrice;
        this.points = points;
    }

    public POSResponse(@NonNull POSException e) {
        super(e);
    }
}
