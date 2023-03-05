package jp.co.pos.apidemo.controllers;

import jp.co.pos.apidemo.response.POSHistroyResponse;
import jp.co.pos.apidemo.response.POSResponse;
import jp.co.pos.apidemo.services.POSService;
import jp.co.pos.apidemo.util.POSLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/pos")
@PropertySource("classpath:/application.properties")
public class POSController {

    @Autowired
    private POSService posService;

    private static final POSLogger logger = POSLogger.getLogger(POSController.class);

    /**
     * API for POS payment
     * HTTP POST Request
     * @param price
     * @param priceModifier
     * @param paymentMethod
     * @param dateTime
     * @author Bien Carlo San Jose
     * @return final price and points
     */
    @PostMapping("/payment")
    public ResponseEntity<POSResponse> payment(@RequestHeader(name = "price", required = true) @NonNull String price,
                                                     @RequestHeader(name = "priceModifier", required = true) @NonNull double priceModifier,
                                                     @RequestHeader(name = "paymentMethod", required = true) @NonNull String paymentMethod,
                                                     @RequestHeader(name = "datetime", required = true) @NonNull String dateTime) throws Exception {

        logger.info("payment start."
                + " ==>price: " + Optional.ofNullable(price).orElse("<null>")
                + " ==>priceModifier: " + Optional.ofNullable(priceModifier).orElse(0.0)
                + " ==>paymentMethod: " + Optional.ofNullable(paymentMethod).orElse("<null>")
                + " ==>dateTime: " + Optional.ofNullable(dateTime).orElse("<null>"));

        POSResponse response = posService.payment(price, priceModifier, paymentMethod, dateTime);
        String message = response.getStatusCode().toString();
        response.setMessage(message);

        logger.info("payment end." + " ==>status: " + response.getStatusCode()
                + " ==>errorCode: " + Optional.ofNullable(response.getErrorCode()).orElse(0)
                + " ==>message: " + response.getMessage());

        return new ResponseEntity<>(response, response.getStatusCode());
    }

    /**
     * API for POS payment history
     * HTTP POST Request
     * @param startDate
     * @param endDate
     * @author Bien Carlo San Jose
     * @return List of payment history base on the indicated duration
     */
    @GetMapping("/history")
    public ResponseEntity<POSHistroyResponse> history(@RequestHeader(name = "startDate", required = true) @NonNull String startDate,
                                               @RequestHeader(name = "endDate", required = true) @NonNull String endDate) throws Exception {

        logger.info("get history start."
                + " ==>startDate: " + Optional.ofNullable(startDate).orElse("<null>")
                + " ==>endDate: " + Optional.ofNullable(endDate).orElse("<null>"));

        POSHistroyResponse response = posService.history(startDate, endDate);
        String message = response.getStatusCode().toString();
        response.setMessage(message);

        logger.info("get history end." + " ==>status: " + response.getStatusCode()
                + " ==>errorCode: " + Optional.ofNullable(response.getErrorCode()).orElse(0)
                + " ==>message: " + response.getStatusCode().toString());

        return new ResponseEntity<>(response, response.getStatusCode());

    }
}
