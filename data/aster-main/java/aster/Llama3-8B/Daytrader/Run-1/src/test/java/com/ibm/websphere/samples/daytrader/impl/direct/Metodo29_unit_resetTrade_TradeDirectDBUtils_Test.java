/**
 * Filtered unit tests for method: resetTrade(boolean deleteAll)
 * Original class: TradeDirectDBUtils
 * Tests that actually call the target method
 */
package com.ibm.websphere.samples.daytrader.impl.direct;

import com.ibm.websphere.samples.daytrader.beans.RunStatsDataBean;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;
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

public class Metodo29_unit_resetTrade_TradeDirectDBUtils_Test {



    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeDeleteAll_sDcr0_1() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(true);
    assertEquals(0, runStatsData.getTradeUserCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeDeleteAll_sDcr0_2() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(true);
    assertEquals(0, runStatsData.getNewUserCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeDeleteAll_sDcr0_3() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(true);
    assertEquals(0, runStatsData.getTradeStockCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeDeleteAll_sDcr0_4() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(true);
    assertEquals(0, runStatsData.getSumLoginCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeDeleteAll_sDcr0_5() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(true);
    assertEquals(0, runStatsData.getSumLogoutCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeDeleteAll_sDcr0_6() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(true);
    assertEquals(0, runStatsData.getHoldingCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeDeleteAll_sDcr0_7() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(true);
    assertEquals(0, runStatsData.getOrderCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeDeleteAll_sDcr0_8() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(true);
    assertEquals(0, runStatsData.getBuyOrderCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeDeleteAll_sDcr0_9() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(true);
    assertEquals(0, runStatsData.getSellOrderCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeDeleteAll_sDcr0_10() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(true);
    assertEquals(0, runStatsData.getCancelledOrderCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeDeleteAll_sDcr0_11() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(true);
    assertEquals(0, runStatsData.getOpenOrderCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeDeleteAll_sDcr0_12() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(true);
    assertEquals(0, runStatsData.getDeletedOrderCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeNoDeleteAll_CIFi1_1() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(false);
    assertEquals(0, runStatsData.getTradeUserCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeNoDeleteAll_CIFi1_2() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(false);
    assertEquals(0, runStatsData.getNewUserCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeNoDeleteAll_CIFi1_3() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(false);
    assertEquals(0, runStatsData.getTradeStockCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeNoDeleteAll_CIFi1_4() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(false);
    assertEquals(0, runStatsData.getSumLoginCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeNoDeleteAll_CIFi1_5() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(false);
    assertEquals(0, runStatsData.getSumLogoutCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeNoDeleteAll_CIFi1_6() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(false);
    assertEquals(0, runStatsData.getHoldingCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeNoDeleteAll_CIFi1_7() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(false);
    assertEquals(0, runStatsData.getOrderCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeNoDeleteAll_CIFi1_8() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(false);
    assertEquals(0, runStatsData.getBuyOrderCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeNoDeleteAll_CIFi1_9() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(false);
    assertEquals(0, runStatsData.getSellOrderCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeNoDeleteAll_CIFi1_10() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(false);
    assertEquals(0, runStatsData.getCancelledOrderCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeNoDeleteAll_CIFi1_11() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(false);
    assertEquals(0, runStatsData.getOpenOrderCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeNoDeleteAll_CIFi1_12() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(false);
    assertEquals(0, runStatsData.getDeletedOrderCount());
    }



    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeDeleteAll_sDcr0() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(true);
    assertEquals(0, runStatsData.getTradeUserCount());
    assertEquals(0, runStatsData.getNewUserCount());
    assertEquals(0, runStatsData.getTradeStockCount());
    assertEquals(0, runStatsData.getSumLoginCount());
    assertEquals(0, runStatsData.getSumLogoutCount());
    assertEquals(0, runStatsData.getHoldingCount());
    assertEquals(0, runStatsData.getOrderCount());
    assertEquals(0, runStatsData.getBuyOrderCount());
    assertEquals(0, runStatsData.getSellOrderCount());
    assertEquals(0, runStatsData.getCancelledOrderCount());
    assertEquals(0, runStatsData.getOpenOrderCount());
    assertEquals(0, runStatsData.getDeletedOrderCount());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testResetTradeNoDeleteAll_CIFi1() throws Exception {
    TradeDirectDBUtils tradeDirectDBUtils = new TradeDirectDBUtils();
    RunStatsDataBean runStatsData = tradeDirectDBUtils.resetTrade(false);
    assertEquals(0, runStatsData.getTradeUserCount());
    assertEquals(0, runStatsData.getNewUserCount());
    assertEquals(0, runStatsData.getTradeStockCount());
    assertEquals(0, runStatsData.getSumLoginCount());
    assertEquals(0, runStatsData.getSumLogoutCount());
    assertEquals(0, runStatsData.getHoldingCount());
    assertEquals(0, runStatsData.getOrderCount());
    assertEquals(0, runStatsData.getBuyOrderCount());
    assertEquals(0, runStatsData.getSellOrderCount());
    assertEquals(0, runStatsData.getCancelledOrderCount());
    assertEquals(0, runStatsData.getOpenOrderCount());
    assertEquals(0, runStatsData.getDeletedOrderCount());
    }

    
}