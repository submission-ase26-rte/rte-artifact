/**
 * Filtered unit tests for method: buy(String userID, String symbol, double quantity, int orderProcessingMode)
 * Original class: TradeDirect
 * Tests that actually call the target method
 */
package com.ibm.websphere.samples.daytrader.impl.direct;

import com.ibm.websphere.samples.daytrader.beans.MarketSummaryDataBean;
import com.ibm.websphere.samples.daytrader.beans.RunStatsDataBean;
import com.ibm.websphere.samples.daytrader.entities.*;
import com.ibm.websphere.samples.daytrader.entities.AccountDataBean;
import com.ibm.websphere.samples.daytrader.entities.AccountProfileDataBean;
import com.ibm.websphere.samples.daytrader.entities.HoldingDataBean;
import com.ibm.websphere.samples.daytrader.entities.OrderDataBean;
import com.ibm.websphere.samples.daytrader.entities.QuoteDataBean;
import com.ibm.websphere.samples.daytrader.util.FinancialUtils;
import com.ibm.websphere.samples.daytrader.util.Log;
import com.ibm.websphere.samples.daytrader.util.MDBStats;
import com.ibm.websphere.samples.daytrader.util.TradeConfig;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Metodo34_unit_buy_TradeDirect_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testBuyWithValidInput_voId0() throws Exception {
    TradeDirect tradeDirect = new TradeDirect();
    OrderDataBean orderData = tradeDirect.buy("userID", "symbol", 10.0, TradeConfig.SYNCH);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testBuyWithInvalidUserID_rIFl1() throws Exception {
    TradeDirect tradeDirect = new TradeDirect();
    try {
    tradeDirect.buy(null, "symbol", 10.0, TradeConfig.SYNCH);
    fail("Expected Exception");
    } catch (Exception e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testBuyWithInvalidSymbol_kSLG2() throws Exception {
    TradeDirect tradeDirect = new TradeDirect();
    try {
    tradeDirect.buy("userID", null, 10.0, TradeConfig.SYNCH);
    fail("Expected Exception");
    } catch (Exception e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testBuyWithInvalidQuantity_JRyw3() throws Exception {
    TradeDirect tradeDirect = new TradeDirect();
    try {
    tradeDirect.buy("userID", "symbol", -10.0, TradeConfig.SYNCH);
    fail("Expected Exception");
    } catch (Exception e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testBuyWithAsynchOrderProcessingMode_atGv4() throws Exception {
    TradeDirect tradeDirect = new TradeDirect();
    OrderDataBean orderData = tradeDirect.buy("userID", "symbol", 10.0, TradeConfig.ASYNCH);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testBuyWithAsynch2PhaseOrderProcessingMode_UuOo5() throws Exception {
    TradeDirect tradeDirect = new TradeDirect();
    OrderDataBean orderData = tradeDirect.buy("userID", "symbol", 10.0, TradeConfig.ASYNCH_2PHASE);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testBuyWithRollback_sKRu8() throws Exception {
    TradeDirect tradeDirect = new TradeDirect();
    try {
    tradeDirect.buy("userID", "symbol", 10.0, TradeConfig.ASYNCH_2PHASE);
    fail("Expected Exception");
    } catch (Exception e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testBuyWithOrderProcessingModeInvalidValue_SytJ11() throws Exception {
    TradeDirect tradeDirect = new TradeDirect();
    try {
    tradeDirect.buy("userID", "symbol", 10.0, 100);
    fail("Expected Exception");
    } catch (Exception e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testBuyWithOrderDataBeanInvalidValue_NqCu13() throws Exception {
    TradeDirect tradeDirect = new TradeDirect();
    try {
    tradeDirect.buy("userID", "symbol", 10.0, TradeConfig.SYNCH);
    fail("Expected Exception");
    } catch (Exception e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testBuyZeroQuantity_dyco3() {
    TradeDirect tradeDirect = new TradeDirect();
    try {
    tradeDirect.buy("user1", "AAPL", 0.0, TradeConfig.SYNCH);
    fail("Expected Exception");
    } catch (Exception e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testBuyNegativeQuantity_LhhP4() {
    TradeDirect tradeDirect = new TradeDirect();
    try {
    tradeDirect.buy("user1", "AAPL", -100.0, TradeConfig.SYNCH);
    fail("Expected Exception");
    } catch (Exception e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testBuyInvalidOrderProcessingMode_ndjj5() {
    TradeDirect tradeDirect = new TradeDirect();
    try {
    tradeDirect.buy("user1", "AAPL", 100.0, 5);
    fail("Expected Exception");
    } catch (Exception e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testBuyException_rkeL8() {
    TradeDirect tradeDirect = new TradeDirect();
    try {
    tradeDirect.buy("user1", "AAPL", 100.0, TradeConfig.SYNCH);
    fail("Expected Exception");
    } catch (Exception e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testBuyRollback_BfoX9() {
    TradeDirect tradeDirect = new TradeDirect();
    try {
    tradeDirect.buy("user1", "AAPL", 100.0, TradeConfig.ASYNCH_2PHASE);
    fail("Expected Exception");
    } catch (Exception e) {
    }
    }
}