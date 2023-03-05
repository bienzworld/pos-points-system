package jp.co.pos.apidemo.services;

import jp.co.pos.apidemo.entities.POSEntity;
import jp.co.pos.apidemo.exception.POSException;
import jp.co.pos.apidemo.response.POSHistroyResponse;
import jp.co.pos.apidemo.response.POSResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class POSServiceTest {

    @Autowired
    private POSService posService;


    @Before
    public void initMocks() throws Exception {
        posService.payment("100", 0.95, "AMEX", "2022-09-01T00:00:00Z");
        posService.payment("500", 0.80, "VISA", "2022-09-01T10:00:00Z");

    }

    @Test
    public void returnPaymentHistory() throws POSException {
        POSHistroyResponse response = posService.history("2022-09-01T00:00:00Z", "2022-09-01T20:00:00Z");
        assertTrue("return 200", response.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void returnSuccessPayment() throws POSException {
        POSResponse response = posService.payment("100", 0.95, "AMEX", "2022-09-01T00:00:00Z");
        assertTrue("return 200", response.getStatusCode() == HttpStatus.OK);
    }
}
