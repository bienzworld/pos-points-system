package jp.co.pos.apidemo.services;

import jp.co.pos.apidemo.entities.POSEntity;
import jp.co.pos.apidemo.exception.POSException;
import jp.co.pos.apidemo.repositories.POSRepository;
import jp.co.pos.apidemo.response.POSHistroyResponse;
import jp.co.pos.apidemo.response.POSResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jp.co.pos.apidemo.util.Constants.*;

@Service
public class POSServiceImpl implements POSService{


    @Autowired
    private POSRepository posRepository;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Service for payment api
     * HTTP POST Request
     * @param stringPrice
     * @param priceModifier
     * @param paymentMethod
     * @param datetime
     * @author Bien Carlo San Jose
     * @return final price and points
     */
    public POSResponse payment(String stringPrice, double priceModifier, String paymentMethod, String datetime) {

        POSResponse response;
        double price = Double.valueOf(stringPrice);
        double finalPrice;
        double points;

        try {
            switch(paymentMethod.toUpperCase()) {
                case "CASH":
                    priceModifier = CASH_MODIFIER;
                    finalPrice = price * priceModifier;
                    points = price * CASH_POINTS;
                    response = new POSResponse(formatDecimals(finalPrice), Double.valueOf(points).intValue());
                    break;
                case "CASH_ON_DELIVERY":
                    priceModifier = CASH_ON_DELIVERY_MODIFIER;
                    finalPrice = price * priceModifier;
                    points = price * CASH_ON_DELIVERY_POINTS;
                    response = new POSResponse(formatDecimals(finalPrice), Double.valueOf(points).intValue());
                    break;
                case "VISA":
                    priceModifier = VISA_MODIFIER;
                    finalPrice = priceModifier * price;
                    points = price * VISA_POINTS;
                    response = new POSResponse(formatDecimals(finalPrice), Double.valueOf(points).intValue());
                    break;
                case "MASTERCARD":
                    priceModifier = MASTERCARD_MODIFIER;
                    finalPrice = price * priceModifier;
                    points = price * MASTERCARD_POINTS;
                    response = new POSResponse(formatDecimals(finalPrice), Double.valueOf(points).intValue());
                    break;
                case "AMEX":
                    priceModifier = AMEX_MODIFIER;
                    finalPrice = price * priceModifier;
                    points = price * AMEX_POINTS;
                    response = new POSResponse(formatDecimals(finalPrice), Double.valueOf(points).intValue());
                    break;
                case "JCB":
                    priceModifier = JCB_MODIFIER;
                    finalPrice = price * priceModifier;
                    points = price * JCB_POINTS;
                    response = new POSResponse(formatDecimals(finalPrice), Double.valueOf(points).intValue());
                    break;
                default:
                    // Handle invalid payment method
                    throw new POSException(POSException.Type.NOT_FOUND);
            }
            Timestamp formattedDate = formatDate(datetime);
            POSEntity posEntity = new POSEntity(formattedDate, formatDecimals(finalPrice), Double.valueOf(points).intValue(), paymentMethod.toUpperCase());
        } catch (Exception e) {
            POSException exception = getPOSException(e);
            response = new POSResponse(exception);
        }
        return response;
    }

    /**
     * API for POS payment history
     * HTTP POST Request
     * @param startDate
     * @param endDate
     * @author Bien Carlo San Jose
     * @return List of payment history base on the indicated duration
     */
    public POSHistroyResponse history(String startDate, String endDate) throws POSException {
        POSHistroyResponse response;
        try {
            List<POSEntity> salesHistory = new ArrayList<>();
            Timestamp start = formatDate(startDate);
            Timestamp end = formatDate(endDate);
            List<POSEntity> data = posRepository.findAll();

            for (POSEntity salesData: data) {
                if ((salesData.getDateTime().getTime() >= start.getTime()) && (salesData.getDateTime().getTime() <= end.getTime())) {
                    System.out.println(salesData.getDateTime());
                    salesHistory.add(salesData);
                }
            }
            response = new POSHistroyResponse(salesHistory);
        } catch (Exception e) {
            POSException exception = getPOSException(e);
            response = new POSHistroyResponse(exception);
        }

        return response;
    }

    private double formatDecimals(double num){
        return Double.valueOf(df.format(num));
    }

    private Timestamp formatDate(String datetime) throws Exception {

        // Create a SimpleDateFormat object with the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        // Parse the String as a Date object
        Date date = dateFormat.parse(datetime);

        // Create a Timestamp object from the Date object
        Timestamp timestamp = new Timestamp(date.getTime());

        // Print the Timestamp object
        return timestamp;
    }

    /**
     * POS exception
     * @author    Bien Carlo San Jose
     */
    private POSException getPOSException(@NonNull Exception e) {
        if (e instanceof POSException) {
            return (POSException) e;
        } else if (e instanceof IllegalArgumentException) {
            return new POSException(POSException.Type.BAD_ARGUMENTS, e);
        } else {
            return new POSException(POSException.Type.UNKNOWN);
        }
    }
}
