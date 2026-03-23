/**
 * Extracted tests for method: completeOrder(Integer orderID, boolean twoPhase)
 * Original class: TradeSLSBBean
 * Source: ASTER GPT-4
 */
package com.ibm.websphere.samples.daytrader.impl.ejb3;

import com.ibm.websphere.samples.daytrader.beans.MarketSummaryDataBean;
import com.ibm.websphere.samples.daytrader.entities.*;
import com.ibm.websphere.samples.daytrader.util.FinancialUtils;
import com.ibm.websphere.samples.daytrader.util.TradeConfig;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;
import static org.mockito.Mockito.*;

public class Metodo37_completeOrder_TradeSLSBBean_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrder_BuyOrder_uRwp2_1() throws Exception {
    TradeSLSBBean tradeBean = new TradeSLSBBean();
    AccountDataBean account = new AccountDataBean();
    QuoteDataBean quote = new QuoteDataBean();
    OrderDataBean order = new OrderDataBean();
    order.setOrderType("buy");
    order.setAccount(account);
    order.setQuote(quote);
    order.setPrice(new BigDecimal("100.00"));
    order.setQuantity(10.0);
    OrderDataBean completedOrder = tradeBean.completeOrder(order.getOrderID(), true);
    assertNotNull(completedOrder.getHolding());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrder_BuyOrder_uRwp2_2() throws Exception {
    TradeSLSBBean tradeBean = new TradeSLSBBean();
    AccountDataBean account = new AccountDataBean();
    QuoteDataBean quote = new QuoteDataBean();
    OrderDataBean order = new OrderDataBean();
    order.setOrderType("buy");
    order.setAccount(account);
    order.setQuote(quote);
    order.setPrice(new BigDecimal("100.00"));
    order.setQuantity(10.0);
    OrderDataBean completedOrder = tradeBean.completeOrder(order.getOrderID(), true);
    assertEquals("closed", completedOrder.getOrderStatus());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrder_BuyOrder_uRwp2_3() throws Exception {
    TradeSLSBBean tradeBean = new TradeSLSBBean();
    AccountDataBean account = new AccountDataBean();
    QuoteDataBean quote = new QuoteDataBean();
    OrderDataBean order = new OrderDataBean();
    order.setOrderType("buy");
    order.setAccount(account);
    order.setQuote(quote);
    order.setPrice(new BigDecimal("100.00"));
    order.setQuantity(10.0);
    OrderDataBean completedOrder = tradeBean.completeOrder(order.getOrderID(), true);
    assertNotNull(completedOrder.getCompletionDate());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrder_NullOrder_bUSJ0_fid1() {
    TradeSLSBBean tradeBean = new TradeSLSBBean();
    try {
    tradeBean.completeOrder(null, true);
    fail("Should have thrown an exception for null orderID");
    } catch (Exception e) {
    assertTrue(e instanceof EJBException);
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrder_OrderAlreadyCompleted_SnuY1_fid1() {
    TradeSLSBBean tradeBean = new TradeSLSBBean();
    OrderDataBean order = new OrderDataBean();
    order.setOrderStatus("closed");
    try {
    tradeBean.completeOrder(order.getOrderID(), true);
    fail("Should have thrown an exception for already completed order");
    } catch (Exception e) {
    assertTrue(e instanceof EJBException);
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrder_SellOrder_NoHolding_dQyx4_fid1() throws Exception {
    TradeSLSBBean tradeBean = new TradeSLSBBean();
    AccountDataBean account = new AccountDataBean();
    QuoteDataBean quote = new QuoteDataBean();
    OrderDataBean order = new OrderDataBean();
    order.setOrderType("sell");
    order.setAccount(account);
    order.setQuote(quote);
    order.setPrice(new BigDecimal("150.00"));
    order.setQuantity(10.0);
    order.setHolding(null);
    try {
    tradeBean.completeOrder(order.getOrderID(), true);
    fail("Should have thrown an exception for selling order without holding");
    } catch (Exception e) {
    assertTrue(e instanceof EJBException);
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrder_BuyOrder_uRwp2() throws Exception {
    TradeSLSBBean tradeBean = new TradeSLSBBean();
    AccountDataBean account = new AccountDataBean();
    QuoteDataBean quote = new QuoteDataBean();
    OrderDataBean order = new OrderDataBean();
    order.setOrderType("buy");
    order.setAccount(account);
    order.setQuote(quote);
    order.setPrice(new BigDecimal("100.00"));
    order.setQuantity(10.0);
    OrderDataBean completedOrder = tradeBean.completeOrder(order.getOrderID(), true);
    assertNotNull(completedOrder.getHolding());
    assertEquals("closed", completedOrder.getOrderStatus());
    assertNotNull(completedOrder.getCompletionDate());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrder_NullOrder_bUSJ0() {
    TradeSLSBBean tradeBean = new TradeSLSBBean();
    try {
    tradeBean.completeOrder(null, true);
    fail("Should have thrown an exception for null orderID");
    } catch (Exception e) {
    assertFalse(e instanceof EJBException);
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrder_OrderAlreadyCompleted_SnuY1() {
    TradeSLSBBean tradeBean = new TradeSLSBBean();
    OrderDataBean order = new OrderDataBean();
    order.setOrderStatus("closed");
    try {
    tradeBean.completeOrder(order.getOrderID(), true);
    fail("Should have thrown an exception for already completed order");
    } catch (Exception e) {
    assertFalse(e instanceof EJBException);
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrder_SellOrder_NoHolding_dQyx4() throws Exception {
    TradeSLSBBean tradeBean = new TradeSLSBBean();
    AccountDataBean account = new AccountDataBean();
    QuoteDataBean quote = new QuoteDataBean();
    OrderDataBean order = new OrderDataBean();
    order.setOrderType("sell");
    order.setAccount(account);
    order.setQuote(quote);
    order.setPrice(new BigDecimal("150.00"));
    order.setQuantity(10.0);
    order.setHolding(null);
    try {
    tradeBean.completeOrder(order.getOrderID(), true);
    fail("Should have thrown an exception for selling order without holding");
    } catch (Exception e) {
    assertFalse(e instanceof EJBException);
    }
    }
}