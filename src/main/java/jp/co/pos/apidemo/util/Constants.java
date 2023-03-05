package jp.co.pos.apidemo.util;

public class Constants {

    // PAYMENT METHOD
    public static final String CASH = "CASH";
    public static final String CASH_ON_DELIVERY = "CASH_ON_DELIVERY";
    public static final String VISA = "VISA";
    public static final String MASTERCARD = "MASTERCARD";
    public static final String AMEX = "AMEX";
    public static final String JCB = "JCB";

    // PRICE MODIFIER
    public static final double CASH_MODIFIER = 0.90;
    public static final double CASH_ON_DELIVERY_MODIFIER = 1.02;
    public static final double VISA_MODIFIER = 0.95;
    public static final double MASTERCARD_MODIFIER = 0.95;
    public static final double AMEX_MODIFIER = 0.98;
    public static final double JCB_MODIFIER = 0.95;

    // POINTS
    public static final double CASH_POINTS = 0.05;
    public static final double CASH_ON_DELIVERY_POINTS = 0.05;
    public static final double VISA_POINTS = 0.03;
    public static final double MASTERCARD_POINTS = 0.03;
    public static final double AMEX_POINTS = 0.02;
    public static final double JCB_POINTS = 0.05;

}
