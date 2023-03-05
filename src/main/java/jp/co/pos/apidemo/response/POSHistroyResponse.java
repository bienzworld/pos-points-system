package jp.co.pos.apidemo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jp.co.pos.apidemo.entities.POSEntity;
import jp.co.pos.apidemo.exception.POSException;
import org.springframework.lang.NonNull;

import java.util.List;

public class POSHistroyResponse extends BaseResponse{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("sales")
    private List<POSEntity> sales;

    private long id;

    public POSHistroyResponse(List<POSEntity> sales) {
        super();
        this.sales = sales;
    }

    public POSHistroyResponse(@NonNull POSException e) {
        super(e);
    }
}
