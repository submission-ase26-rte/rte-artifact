/**
 * Filtered unit tests for method: updateConfig()
 * Original class: TradeConfigJSF
 * Tests that actually call the target method
 */
package com.ibm.websphere.samples.daytrader.web.jsf;

import com.ibm.websphere.samples.daytrader.beans.RunStatsDataBean;
import com.ibm.websphere.samples.daytrader.util.TradeConfig;
import java.util.concurrent.TimeUnit;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo36_unit_updateConfig_TradeConfigJSF_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_RuntimeMode_kAts0() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.updateConfig();
    assertEquals("RunTimeMode: RuntimeMode1", tradeConfigJSF.getResult());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_RuntimeModeNull_cmpa1() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.updateConfig();
    assertEquals("RunTimeMode: RuntimeMode0", tradeConfigJSF.getResult());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_OrderProcessingModeNull_YTez3() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.updateConfig();
    assertEquals("OrderProcessingMode: OrderProcessingMode0", tradeConfigJSF.getResult());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_WebInterfaceNull_QYiy5() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.updateConfig();
    assertEquals("Web Interface: WebInterface0", tradeConfigJSF.getResult());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_MAX_QUOTES_EUAY7_sbCX1() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.updateConfig();
    assertEquals("Trade Quotes: 20", tradeConfigJSF.buildDatabaseTables());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_PrimIterations_oJtA9_TdoD0() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.setPrimIterations(40);
    tradeConfigJSF.updateConfig();
    assertEquals("Primitive Iterations: 40", tradeConfigJSF.getResult());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_LongRun_xKLi12_leCR0() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.updateConfig();
    assertEquals("database", tradeConfigJSF.buildDatabaseTables());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_RuntimeMode_kAts0_fid1() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.updateConfig();
    assertEquals("DayTrader Configuration Updated", tradeConfigJSF.getResult());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_MAX_QUOTES_EUAY7_sbCX1_fid1() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.updateConfig();
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_PrimIterations_oJtA9_TdoD0_fid1() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.setPrimIterations(40);
    tradeConfigJSF.updateConfig();
    assertEquals("DayTrader Configuration Updated", tradeConfigJSF.getResult());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_RuntimeMode_FBlI0() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.updateConfig();
    assertEquals("RuntimeMode: " + TradeConfig.getRunTimeModeNames()[TradeConfig.getRunTimeMode()], tradeConfigJSF.getResult());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_OrderProcessingMode_BUTV1() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.setOrderProcessingMode("OrderProcessingMode1");
    tradeConfigJSF.updateConfig();
    assertEquals("OrderProcessingMode: " + TradeConfig.getOrderProcessingModeNames()[TradeConfig.getOrderProcessingMode()], tradeConfigJSF.getResult());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_WebInterface_FYTl2() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.setWebInterface("WebInterface1");
    tradeConfigJSF.updateConfig();
    assertEquals("Web Interface: " + TradeConfig.getWebInterfaceNames()[TradeConfig.getWebInterface()], tradeConfigJSF.getResult());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_MAX_USERS_hwIS3() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.setMaxUsers(10);
    tradeConfigJSF.updateConfig();
    assertEquals("Trade Users: " + TradeConfig.getMAX_USERS(), tradeConfigJSF.getResult());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_MarketSummaryInterval_fdKw5() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.setMarketSummaryInterval(30);
    tradeConfigJSF.updateConfig();
    assertEquals("Market Summary Interval: " + TradeConfig.getMarketSummaryInterval(), tradeConfigJSF.getResult());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_PrimIterations_xtgH6() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.setPrimIterations(40);
    tradeConfigJSF.updateConfig();
    assertEquals("Primitive Iterations: " + TradeConfig.getPrimIterations(), tradeConfigJSF.getResult());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_MAX_QUOTES_hSyS4_RrkB0_fid1() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.setOrderProcessingMode("MAX_QUOTES");
    tradeConfigJSF.updateConfig();
    assertEquals("Trade Quotes: MAX_QUOTES", tradeConfigJSF.getResult());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testUpdateConfig_MAX_QUOTES_hSyS4_RrkB0() {
    TradeConfigJSF tradeConfigJSF = new TradeConfigJSF();
    tradeConfigJSF.setOrderProcessingMode("MAX_QUOTES");
    tradeConfigJSF.updateConfig();
    assertEquals("DayTrader Configuration Updated", tradeConfigJSF.getResult());
    }
}