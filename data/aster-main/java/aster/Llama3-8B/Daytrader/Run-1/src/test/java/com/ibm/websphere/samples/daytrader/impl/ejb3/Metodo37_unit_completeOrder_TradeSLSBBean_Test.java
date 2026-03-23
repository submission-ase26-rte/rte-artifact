/**
 * Filtered unit tests for method: completeOrder(Integer orderID, boolean twoPhase)
 * Original class: TradeSLSBBean
 * Tests that actually call the target method
 */
package com.ibm.websphere.samples.daytrader.impl.ejb3;

import com.ibm.websphere.samples.daytrader.beans.MarketSummaryDataBean;
import com.ibm.websphere.samples.daytrader.entities.*;
import com.ibm.websphere.samples.daytrader.entities.AccountDataBean;
import com.ibm.websphere.samples.daytrader.entities.AccountProfileDataBean;
import com.ibm.websphere.samples.daytrader.entities.HoldingDataBean;
import com.ibm.websphere.samples.daytrader.entities.OrderDataBean;
import com.ibm.websphere.samples.daytrader.entities.QuoteDataBean;
import com.ibm.websphere.samples.daytrader.util.Log;
import com.ibm.websphere.samples.daytrader.util.TradeConfig;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJBException;
import javax.jms.JMSContext;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;
import static org.mockito.Mockito.mock;

public class Metodo37_unit_completeOrder_TradeSLSBBean_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrderCreateHolding_xzbH0() throws Exception {
    TradeSLSBBean tradeSLSBBean = new TradeSLSBBean();
    OrderDataBean order = OrderDataBean.getRandomInstance();
    order.setAccount(AccountDataBean.getRandomInstance());
    order.setQuote(QuoteDataBean.getRandomInstance());
    order.setQuantity(10.0);
    order.setPrice(BigDecimal.valueOf(10.0));
    order.setOrderStatus("open");
    order.setCompletionDate(null);
    order = tradeSLSBBean.completeOrder(order.getOrderID(), true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrderNullOrder_cSOT0_fid1() {
    TradeSLSBBean tradeSLSBBean = new TradeSLSBBean();
    OrderDataBean order = null;
    try {
    OrderDataBean result = tradeSLSBBean.completeOrder(null, false);
    Assertions.assertNull(result);
    } catch (Exception e) {
    Assertions.fail();
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrderBuy_DPxo2_ndhg0_fid1() {
    TradeSLSBBean tradeSLSBBean = new TradeSLSBBean();
    OrderDataBean order = OrderDataBean.getRandomInstance();
    order.setAccount(OrderDataBean.getRandomInstance().getAccount());
    order.setQuote(OrderDataBean.getRandomInstance().getQuote());
    order.setPrice(BigDecimal.valueOf(10));
    order.setQuantity(5);
    try {
    OrderDataBean result = tradeSLSBBean.completeOrder(order.getOrderID(), false);
    Assertions.assertNotNull(result);
    } catch (Exception e) {
    Assertions.fail();
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrderTwoPhase_FWDM5_Lzyq0_fid1() {
    TradeSLSBBean tradeSLSBBean = new TradeSLSBBean();
    OrderDataBean order = OrderDataBean.getRandomInstance();
    order.setAccount(OrderDataBean.getRandomInstance().getAccount());
    order.setQuote(OrderDataBean.getRandomInstance().getQuote());
    order.setPrice(BigDecimal.valueOf(10));
    order.setQuantity(5);
    try {
    OrderDataBean result = tradeSLSBBean.completeOrder(order.getOrderID(), true);
    Assertions.assertNotNull(result);
    } catch (Exception e) {
    Assertions.fail();
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrderNullOrder_cSOT0() {
    TradeSLSBBean tradeSLSBBean = new TradeSLSBBean();
    OrderDataBean order = null;
    try {
    OrderDataBean result = tradeSLSBBean.completeOrder(null, false);
    Assertions.assertNull(result);
    } catch (Exception e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrderBuy_DPxo2_ndhg0() {
    TradeSLSBBean tradeSLSBBean = new TradeSLSBBean();
    OrderDataBean order = OrderDataBean.getRandomInstance();
    order.setAccount(OrderDataBean.getRandomInstance().getAccount());
    order.setQuote(OrderDataBean.getRandomInstance().getQuote());
    order.setPrice(BigDecimal.valueOf(10));
    order.setQuantity(5);
    try {
    OrderDataBean result = tradeSLSBBean.completeOrder(order.getOrderID(), false);
    Assertions.assertNotNull(result);
    } catch (Exception e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testCompleteOrderTwoPhase_FWDM5_Lzyq0() {
    TradeSLSBBean tradeSLSBBean = new TradeSLSBBean();
    OrderDataBean order = OrderDataBean.getRandomInstance();
    order.setAccount(OrderDataBean.getRandomInstance().getAccount());
    order.setQuote(OrderDataBean.getRandomInstance().getQuote());
    order.setPrice(BigDecimal.valueOf(10));
    order.setQuantity(5);
    try {
    OrderDataBean result = tradeSLSBBean.completeOrder(order.getOrderID(), true);
    Assertions.assertNotNull(result);
    } catch (Exception e) {
    }
    }
}