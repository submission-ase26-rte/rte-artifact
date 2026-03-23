/**
 * Extracted tests for method: login(String userID, String password)
 * Original class: TradeDirect
 * Source: ASTER GPT-4
 */
package com.ibm.websphere.samples.daytrader.impl.direct;

import com.ibm.websphere.samples.daytrader.beans.MarketSummaryDataBean;
import com.ibm.websphere.samples.daytrader.entities.*;
import com.ibm.websphere.samples.daytrader.entities.HoldingDataBean;
import com.ibm.websphere.samples.daytrader.entities.OrderDataBean;
import com.ibm.websphere.samples.daytrader.entities.QuoteDataBean;
import com.ibm.websphere.samples.daytrader.util.TradeConfig;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.ejb.FinderException;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.jms.JMSException;
import javax.resource.cci.Connection;
import javax.resource.cci.ConnectionFactory;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;
import static org.mockito.Mockito.*;

public class Metodo38_login_TradeDirect_Test {


    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLoginSuccess_vNJa0() throws Exception {
    TradeDirect tradeDirect = new TradeDirect();
    String userID = "validUser";
    String password = "correctPassword";
    AccountDataBean expectedAccountData = AccountDataBean.getRandomInstance();
    AccountDataBean result = tradeDirect.login(userID, password);
    assertNotNull(result);
    assertEquals(expectedAccountData.getAccountID(), result.getAccountID());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLoginFailureIncorrectPassword_ZGrl1() throws Exception {
    TradeDirect tradeDirect = new TradeDirect();
    String userID = "validUser";
    String password = "wrongPassword";
    try {
    tradeDirect.login(userID, password);
    fail("Expected an Exception to be thrown");
    } catch (Exception e) {
    assertEquals("TradeDirect:Login failure for user: validUser\n\tIncorrect password-->validUser:wrongPassword", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLoginFailureUserNotFound_mtOW2() throws Exception {
    TradeDirect tradeDirect = new TradeDirect();
    String userID = "nonExistentUser";
    String password = "anyPassword";
    try {
    tradeDirect.login(userID, password);
    fail("Expected a FinderException to be thrown");
    } catch (FinderException e) {
    assertEquals("Cannot find account fornonExistentUser", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testLoginSQLExceptionHandling_rOvX3() throws Exception {
    TradeDirect tradeDirect = new TradeDirect();
    String userID = "userCausingSQLIssue";
    String password = "anyPassword";
    try {
    tradeDirect.login(userID, password);
    fail("Expected an Exception to be thrown due to SQL issues");
    } catch (Exception e) {
    assertTrue(e instanceof SQLException);
    }
    }
}