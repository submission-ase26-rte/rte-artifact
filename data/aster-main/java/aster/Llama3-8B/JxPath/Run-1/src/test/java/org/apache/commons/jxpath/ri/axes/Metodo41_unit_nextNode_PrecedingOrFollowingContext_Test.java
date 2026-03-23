/**
 * Filtered unit tests for method: nextNode()
 * Original class: PrecedingOrFollowingContext
 * Tests that actually call the target method
 */
package org.apache.commons.jxpath.ri.axes;

import java.util.Stack;
import java.util.concurrent.TimeUnit;
import org.apache.commons.jxpath.ri.compiler.NodeTypeTest;
import org.apache.commons.jxpath.ri.model.NodeIterator;
import org.apache.commons.jxpath.ri.model.NodePointer;
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
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;
import static org.mockito.Mockito.*;

public class Metodo41_unit_nextNode_PrecedingOrFollowingContext_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testsetPosition_positionGreaterThanCurrentPosition_nextNodeCalled_fGjQ2_CkoK0_1() {
    NodeTypeTest nodeTest = new NodeTypeTest(1);
    SelfContext selfContext = new SelfContext(new NamespaceContext(null, nodeTest), nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(new NamespaceContext(selfContext, nodeTest), nodeTest, false);
    context.setPosition(5);
    assertTrue(context.nextNode());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testsetPosition_positionGreaterThanCurrentPosition_nextNodeReturnsFalse_XOun3_AfNK0_1() {
    NodeTypeTest nodeTest = new NodeTypeTest(0);
    SelfContext selfContext = new SelfContext(null, nodeTest);
    NamespaceContext parentContext = new NamespaceContext(selfContext, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(parentContext, nodeTest, false);
    context.setPosition(5);
    assertFalse(context.nextNode());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testsetPosition_positionGreaterThanCurrentPosition_nextNodeReturnsTrueAndReturnTrue_zyaA5_GHAH0_1() {
    NodeTypeTest nodeTest = new NodeTypeTest(1);
    SelfContext parentContext = new SelfContext(null, nodeTest);
    NamespaceContext namespaceContext = new NamespaceContext(parentContext, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, false);
    context.setPosition(5);
    assertTrue(context.nextNode());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode_currentNodePointerNull_AyVX8_Pkpl0() {
    NamespaceContext parentContext = new NamespaceContext(null, null);
    NodeTypeTest nodeTest = new NodeTypeTest(0);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(parentContext, nodeTest, false);
    context.nextNode();
    org.junit.Assert.assertNull(context.getCurrentNodePointer());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode_nodeTestTrue_fnEa12_nOhx0() {
    SelfContext selfContext = new SelfContext(new NamespaceContext(null, new NodeTypeTest(1)), new NodeTypeTest(1));
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(new NamespaceContext(selfContext, new NodeTypeTest(1)), new NodeTypeTest(1), false);
    context.nextNode();
    Assertions.assertTrue(context.nextNode());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode_nodeTestFalse_YmOD13_JvmR0() {
    SelfContext selfContext = new SelfContext(new NamespaceContext(null, new NodeTypeTest(0)), new NodeTypeTest(0));
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(new NamespaceContext(selfContext, new NodeTypeTest(0)), new NodeTypeTest(0), false);
    context.nextNode();
    Assertions.assertFalse(context.nextNode());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode_setPositionTrue_KlRt14_whzA0() {
    SelfContext parentContext = new SelfContext(null, null);
    NodeTypeTest nodeTest = new NodeTypeTest(0);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(parentContext, nodeTest, false);
    context.setPosition(1);
    context.nextNode();
    Assertions.assertTrue(context.setPosition(2));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode_setPositionFalse_uQtz15_sUiV0() {
    NamespaceContext parentContext = new NamespaceContext(new SelfContext(null, null), new NodeTypeTest(0));
    NodeTypeTest nodeTest = new NodeTypeTest(0);
    SelfContext selfContext = new SelfContext(parentContext, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(selfContext, nodeTest, false);
    context.setPosition(1);
    context.nextNode();
    Assertions.assertFalse(context.setPosition(2));
    }


}