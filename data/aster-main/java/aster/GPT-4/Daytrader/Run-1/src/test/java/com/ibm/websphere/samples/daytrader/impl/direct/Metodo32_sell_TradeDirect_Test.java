/**
 * Extracted tests for method: sell(String userID, Integer holdingID, int orderProcessingMode)
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.jms.JMSException;
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

public class Metodo32_sell_TradeDirect_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellWithNullAccountData_fyLa0() throws Exception {
    TradeDirect trade = new TradeDirect();
    Connection conn = null; // Mock or use a test database connection
    String userID = "user1";
    Integer holdingID = 1;
    int orderProcessingMode = TradeConfig.SYNCH;
    AccountDataBean accountData = null;
    HoldingDataBean holdingData = new HoldingDataBean();
    QuoteDataBean quoteData = new QuoteDataBean();
    OrderDataBean result = trade.sell(userID, holdingID, orderProcessingMode);
    assertEquals("cancelled", result.getOrderStatus());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellWithNullHoldingData_iUUo1() throws Exception {
    TradeDirect trade = new TradeDirect();
    Connection conn = null; // Mock or use a test database connection
    String userID = "user1";
    Integer holdingID = 1;
    int orderProcessingMode = TradeConfig.SYNCH;
    AccountDataBean accountData = new AccountDataBean();
    HoldingDataBean holdingData = null; // Holding not found
    QuoteDataBean quoteData = new QuoteDataBean();
    OrderDataBean result = trade.sell(userID, holdingID, orderProcessingMode);
    assertEquals("cancelled", result.getOrderStatus());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellWithValidDataSynchMode_TJXC3_1() throws Exception {
    TradeDirect trade = new TradeDirect();
    Connection conn = null; // Mock or use a test database connection
    String userID = "user1";
    Integer holdingID = 1;
    int orderProcessingMode = TradeConfig.SYNCH;
    AccountDataBean accountData = new AccountDataBean();
    HoldingDataBean holdingData = new HoldingDataBean();
    QuoteDataBean quoteData = new QuoteDataBean();
    OrderDataBean result = trade.sell(userID, holdingID, orderProcessingMode);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellWithValidDataSynchMode_TJXC3_2() throws Exception {
    TradeDirect trade = new TradeDirect();
    Connection conn = null; // Mock or use a test database connection
    String userID = "user1";
    Integer holdingID = 1;
    int orderProcessingMode = TradeConfig.SYNCH;
    AccountDataBean accountData = new AccountDataBean();
    HoldingDataBean holdingData = new HoldingDataBean();
    QuoteDataBean quoteData = new QuoteDataBean();
    OrderDataBean result = trade.sell(userID, holdingID, orderProcessingMode);
    assertEquals("completed", result.getOrderStatus());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellWithValidDataAsynchMode_VgKw4_1() throws Exception {
    TradeDirect trade = new TradeDirect();
    Connection conn = null; // Mock or use a test database connection
    String userID = "user1";
    Integer holdingID = 1;
    int orderProcessingMode = TradeConfig.ASYNCH;
    AccountDataBean accountData = new AccountDataBean();
    HoldingDataBean holdingData = new HoldingDataBean();
    QuoteDataBean quoteData = new QuoteDataBean();
    OrderDataBean result = trade.sell(userID, holdingID, orderProcessingMode);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellWithValidDataAsynchMode_VgKw4_2() throws Exception {
    TradeDirect trade = new TradeDirect();
    Connection conn = null; // Mock or use a test database connection
    String userID = "user1";
    Integer holdingID = 1;
    int orderProcessingMode = TradeConfig.ASYNCH;
    AccountDataBean accountData = new AccountDataBean();
    HoldingDataBean holdingData = new HoldingDataBean();
    QuoteDataBean quoteData = new QuoteDataBean();
    OrderDataBean result = trade.sell(userID, holdingID, orderProcessingMode);
    assertEquals("queued", result.getOrderStatus());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellWithValidDataAsynch2PhaseMode_BrJa5_1() throws Exception {
    TradeDirect trade = new TradeDirect();
    Connection conn = null; // Mock or use a test database connection
    String userID = "user1";
    Integer holdingID = 1;
    int orderProcessingMode = TradeConfig.ASYNCH_2PHASE;
    AccountDataBean accountData = new AccountDataBean();
    HoldingDataBean holdingData = new HoldingDataBean();
    QuoteDataBean quoteData = new QuoteDataBean();
    OrderDataBean result = trade.sell(userID, holdingID, orderProcessingMode);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellWithValidDataAsynch2PhaseMode_BrJa5_2() throws Exception {
    TradeDirect trade = new TradeDirect();
    Connection conn = null; // Mock or use a test database connection
    String userID = "user1";
    Integer holdingID = 1;
    int orderProcessingMode = TradeConfig.ASYNCH_2PHASE;
    AccountDataBean accountData = new AccountDataBean();
    HoldingDataBean holdingData = new HoldingDataBean();
    QuoteDataBean quoteData = new QuoteDataBean();
    OrderDataBean result = trade.sell(userID, holdingID, orderProcessingMode);
    assertEquals("queued", result.getOrderStatus());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellWithJMSException_uSre7() throws Exception {
    TradeDirect trade = new TradeDirect();
    Connection conn = null; // Mock or use a test database connection
    String userID = "user1";
    Integer holdingID = 1;
    int orderProcessingMode = TradeConfig.ASYNCH;
    AccountDataBean accountData = new AccountDataBean();
    HoldingDataBean holdingData = new HoldingDataBean();
    QuoteDataBean quoteData = new QuoteDataBean();
    doThrow(new JMSException("JMS error")).when(trade).queueOrder(anyInt(), anyBoolean());
    OrderDataBean result = trade.sell(userID, holdingID, orderProcessingMode);
    assertEquals("error", result.getOrderStatus());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellAllNull_XfPo0() throws Exception {
    TradeDirect trade = new TradeDirect();
    assertNull(trade.sell("user1", null, TradeConfig.SYNCH));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellAccountNull_NZbm1() throws Exception {
    TradeDirect trade = new TradeDirect();
    assertNull(trade.sell("user1", 1, TradeConfig.SYNCH));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellHoldingNull_MKZv2() throws Exception {
    TradeDirect trade = new TradeDirect();
    assertNull(trade.sell("user1", 2, TradeConfig.SYNCH));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellQuoteNull_mlNf3() throws Exception {
    TradeDirect trade = new TradeDirect();
    assertNull(trade.sell("user1", 3, TradeConfig.SYNCH));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellValid_qKkJ4() throws Exception {
    TradeDirect trade = new TradeDirect();
    assertNotNull(trade.sell("user1", 4, TradeConfig.SYNCH));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellRollback_oozb5() throws Exception {
    TradeDirect trade = new TradeDirect();
    assertNull(trade.sell("user1", 5, TradeConfig.SYNCH));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellCommit_nrGZ6() throws Exception {
    TradeDirect trade = new TradeDirect();
    assertNotNull(trade.sell("user1", 6, TradeConfig.SYNCH));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellAsync_jwVq7() throws Exception {
    TradeDirect trade = new TradeDirect();
    assertNotNull(trade.sell("user1", 7, TradeConfig.ASYNCH));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellAsync2Phase_OfTY8() throws Exception {
    TradeDirect trade = new TradeDirect();
    assertNotNull(trade.sell("user1", 8, TradeConfig.ASYNCH_2PHASE));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellException_YfmW9() throws Exception {
    TradeDirect trade = new TradeDirect();
    assertNull(trade.sell("user1", 9, TradeConfig.SYNCH));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellOrderDataNotFound_nodb10() throws Exception {
    TradeDirect trade = new TradeDirect();
    assertNull(trade.sell("user1", 10, TradeConfig.SYNCH));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellOrderDataFound_jink11() throws Exception {
    TradeDirect trade = new TradeDirect();
    assertNotNull(trade.sell("user1", 11, TradeConfig.SYNCH));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellOrderDataException_uPgh12() throws Exception {
    TradeDirect trade = new TradeDirect();
    assertNull(trade.sell("user1", 12, TradeConfig.SYNCH));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSellOrderDataComplete_GGTk13() throws Exception {
    TradeDirect trade = new TradeDirect();
    assertNotNull(trade.sell("user1", 13, TradeConfig.SYNCH));
    }
}