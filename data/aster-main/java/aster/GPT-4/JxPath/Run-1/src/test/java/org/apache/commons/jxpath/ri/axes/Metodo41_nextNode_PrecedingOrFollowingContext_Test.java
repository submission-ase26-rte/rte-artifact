/**
 * Extracted tests for method: nextNode()
 * Original class: PrecedingOrFollowingContext
 * Source: ASTER GPT-4
 */
package org.apache.commons.jxpath.ri.axes;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

import org.apache.commons.jxpath.ri.EvalContext;
import org.apache.commons.jxpath.ri.compiler.NodeTest;
import org.apache.commons.jxpath.ri.compiler.NodeTypeTest;
import org.apache.commons.jxpath.ri.model.NodeIterator;
import org.apache.commons.jxpath.ri.model.NodePointer;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;
import static org.mockito.Mockito.*;

public class Metodo41_nextNode_PrecedingOrFollowingContext_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode_initialStackEmpty_PMUN0() {
    NamespaceContext parentContext = mock(NamespaceContext.class);
    NodeTypeTest nodeTest = new NodeTypeTest(1);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(parentContext, nodeTest, false);
    when(parentContext.getCurrentNodePointer()).thenReturn(null);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode_initialStackNotEmpty_KDDo1() {
    NamespaceContext parentContext = mock(NamespaceContext.class);
    NodeTypeTest nodeTest = new NodeTypeTest(1);
    NodePointer mockNodePointer = mock(NodePointer.class);
    NodeIterator mockIterator = mock(NodeIterator.class);
    when(parentContext.getCurrentNodePointer()).thenReturn(mockNodePointer);
    when(mockNodePointer.getParent()).thenReturn(mockNodePointer);
    when(mockNodePointer.childIterator(null, false, mockNodePointer)).thenReturn(mockIterator);
    when(mockIterator.setPosition(anyInt())).thenReturn(true);
    when(mockIterator.getNodePointer()).thenReturn(mockNodePointer);
    when(mockNodePointer.isLeaf()).thenReturn(true);
    when(mockNodePointer.testNode(nodeTest)).thenReturn(true);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(parentContext, nodeTest, false);
    boolean result = context.nextNode();
    assertTrue(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode_stackNotEmpty_reverseTrue_rynr2() {
    NamespaceContext parentContext = mock(NamespaceContext.class);
    NodeTypeTest nodeTest = new NodeTypeTest(1);
    NodePointer mockNodePointer = mock(NodePointer.class);
    NodeIterator mockIterator = mock(NodeIterator.class);
    when(parentContext.getCurrentNodePointer()).thenReturn(mockNodePointer);
    when(mockNodePointer.getParent()).thenReturn(mockNodePointer);
    when(mockNodePointer.childIterator(null, true, mockNodePointer)).thenReturn(mockIterator);
    when(mockIterator.setPosition(anyInt())).thenReturn(true);
    when(mockIterator.getNodePointer()).thenReturn(mockNodePointer);
    when(mockNodePointer.isLeaf()).thenReturn(false);
    when(mockNodePointer.childIterator(null, true, null)).thenReturn(mockIterator);
    when(mockNodePointer.testNode(nodeTest)).thenReturn(false);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(parentContext, nodeTest, true);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode_stackNotEmpty_reverseFalse_nodeTestFails_ERNM3() {
    NamespaceContext parentContext = mock(NamespaceContext.class);
    NodeTypeTest nodeTest = new NodeTypeTest(1);
    NodePointer mockNodePointer = mock(NodePointer.class);
    NodeIterator mockIterator = mock(NodeIterator.class);
    when(parentContext.getCurrentNodePointer()).thenReturn(mockNodePointer);
    when(mockNodePointer.getParent()).thenReturn(mockNodePointer);
    when(mockNodePointer.childIterator(null, false, mockNodePointer)).thenReturn(mockIterator);
    when(mockIterator.setPosition(anyInt())).thenReturn(true);
    when(mockIterator.getNodePointer()).thenReturn(mockNodePointer);
    when(mockNodePointer.isLeaf()).thenReturn(true);
    when(mockNodePointer.testNode(nodeTest)).thenReturn(false);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(parentContext, nodeTest, false);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode_currentRootLocationIsRoot_QWeI5() {
    NamespaceContext parentContext = mock(NamespaceContext.class);
    NodeTypeTest nodeTest = new NodeTypeTest(1);
    NodePointer mockNodePointer = mock(NodePointer.class);
    when(parentContext.getCurrentNodePointer()).thenReturn(mockNodePointer);
    when(mockNodePointer.getParent()).thenReturn(null);
    when(mockNodePointer.isRoot()).thenReturn(true);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(parentContext, nodeTest, false);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSetPosition_SuccessfullyAdvancesPosition() throws Exception {
    // Initialize PrecedingOrFollowingContext with mocked dependencies
        EvalContext mockNamespaceContext = mock(EvalContext.class);
        NodeTest mockNodeTypeTest = mock(NodeTest.class);
        PrecedingOrFollowingContext precedingOrFollowingContext = new PrecedingOrFollowingContext(mockNamespaceContext, mockNodeTypeTest, false);
    // Arrange
    int initialPosition = 1;
    int newPosition = 3;
    // Simulate the internal state and behavior
    when(precedingOrFollowingContext.nextNode()).thenReturn(true, true, false);
    // Act
    boolean result = precedingOrFollowingContext.setPosition(newPosition);
    // Assert
    assertTrue(result, "setPosition should return true when it can advance to the requested position");
    verify(precedingOrFollowingContext, times(2)).nextNode();
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSetPosition_FailsToAdvancePosition() throws Exception {
    // Initialize PrecedingOrFollowingContext with mocked dependencies
        EvalContext mockNamespaceContext = null;
        NodeTest mockNodeTypeTest = null;
        PrecedingOrFollowingContext precedingOrFollowingContext = new PrecedingOrFollowingContext(mockNamespaceContext, mockNodeTypeTest, false);
    // Arrange
    int initialPosition = 1;
    int newPosition = 5;
    // Simulate the internal state and behavior
    when(precedingOrFollowingContext.nextNode()).thenReturn(true, true, false);
    // Act
    boolean result = precedingOrFollowingContext.setPosition(newPosition);
    // Assert
    assertFalse(result, "setPosition should return false when it cannot advance to the requested position");
    verify(precedingOrFollowingContext, times(3)).nextNode();
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode1_OyFP0() {
    NodeTypeTest nodeTest = new NodeTypeTest(1);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, false);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode2_uvao1() {
    NodeTypeTest nodeTest = new NodeTypeTest(2);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, true);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode3_vHoK2() {
    NodeTypeTest nodeTest = new NodeTypeTest(3);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, false);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode4_uaAJ3() {
    NodeTypeTest nodeTest = new NodeTypeTest(4);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, true);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode5_KTHc4() {
    NodeTypeTest nodeTest = new NodeTypeTest(5);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, false);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode6_bsoY5() {
    NodeTypeTest nodeTest = new NodeTypeTest(6);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, true);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode7_peJK6() {
    NodeTypeTest nodeTest = new NodeTypeTest(7);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, false);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode8_CJui7() {
    NodeTypeTest nodeTest = new NodeTypeTest(8);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, true);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode9_Rbwj8() {
    NodeTypeTest nodeTest = new NodeTypeTest(9);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, false);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode10_jsRK9() {
    NodeTypeTest nodeTest = new NodeTypeTest(10);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, true);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode11_jvmZ10() {
    NodeTypeTest nodeTest = new NodeTypeTest(11);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, false);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode12_qYdZ11() {
    NodeTypeTest nodeTest = new NodeTypeTest(12);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, true);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode13_hQby12() {
    NodeTypeTest nodeTest = new NodeTypeTest(13);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, false);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode14_YqCL13() {
    NodeTypeTest nodeTest = new NodeTypeTest(14);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, true);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode15_gGra14() {
    NodeTypeTest nodeTest = new NodeTypeTest(15);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, false);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode16_yWwa15() {
    NodeTypeTest nodeTest = new NodeTypeTest(16);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, true);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode17_MWtP16() {
    NodeTypeTest nodeTest = new NodeTypeTest(17);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, false);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testNextNode18_SiZw17() {
    NodeTypeTest nodeTest = new NodeTypeTest(18);
    NamespaceContext namespaceContext = new NamespaceContext(null, nodeTest);
    PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(namespaceContext, nodeTest, true);
    boolean result = context.nextNode();
    assertFalse(result);
    }

    @Test
    public void testNextNode_sCMI0() {
        EvalContext parentContext=mock(EvalContext.class);
        NodeTest nodeTest=mock(NodeTest.class);
        PrecedingOrFollowingContext context = new PrecedingOrFollowingContext(parentContext, nodeTest, true);
    when(parentContext.getCurrentNodePointer()).thenReturn(null); // Mock the behavior as per requirement
    boolean result = context.nextNode();
    }

}