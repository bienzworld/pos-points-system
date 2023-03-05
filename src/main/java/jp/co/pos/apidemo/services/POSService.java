package jp.co.pos.apidemo.services;

import jp.co.pos.apidemo.exception.POSException;
import jp.co.pos.apidemo.response.POSHistroyResponse;
import jp.co.pos.apidemo.response.POSResponse;

import java.sql.Timestamp;

public interface POSService {

    public POSResponse payment(String price, double priceModifier, String paymentMethod, String datetime) throws POSException;

    public POSHistroyResponse history(String startDate, String endDate) throws POSException;
}
