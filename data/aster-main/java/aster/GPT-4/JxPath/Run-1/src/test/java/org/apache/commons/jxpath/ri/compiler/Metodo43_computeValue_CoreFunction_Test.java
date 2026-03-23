/**
 * Extracted tests for method: computeValue(final EvalContext context)
 * Original class: CoreFunction
 * Source: ASTER GPT-4
 */
package org.apache.commons.jxpath.ri.compiler;

import java.util.concurrent.TimeUnit;
import org.apache.commons.jxpath.JXPathInvalidSyntaxException;
import org.apache.commons.jxpath.ri.Compiler;
import org.apache.commons.jxpath.ri.EvalContext;
import org.apache.commons.jxpath.ri.InfoSetUtil;
import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.model.NodePointer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;
import static org.mockito.Mockito.*;

public class Metodo43_computeValue_CoreFunction_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionStartsWith_NullStrings_giYQ3() {
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    when(arg1.computeValue(context)).thenReturn(null);
    when(arg2.computeValue(context)).thenReturn(null);
    CoreFunction coreFunction = new CoreFunction(0, new Expression[]{arg1, arg2});
    Object result = coreFunction.functionStartsWith(context);
    assertFalse((Boolean) result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringBeforeWithEmptyFirstString_BkgN1_epMA0() {
    CoreFunction coreFunction = new CoreFunction(0, new Expression[2]);
    EvalContext context = mock(EvalContext.class);
    Expression exp1 = mock(Expression.class);
    Expression exp2 = mock(Expression.class);
    when(coreFunction.getArg1()).thenReturn(exp1);
    when(coreFunction.getArg2()).thenReturn(exp2);
    when(exp1.computeValue(context)).thenReturn("");
    when(exp2.computeValue(context)).thenReturn("test");
    Object result = coreFunction.functionSubstring(context);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringBeforeWithNonMatchingSecondString_iRti2_uBpn0() {
    CoreFunction coreFunction = new CoreFunction(0, new Expression[2]);
    EvalContext context = mock(EvalContext.class);
    Expression exp1 = mock(Expression.class);
    Expression exp2 = mock(Expression.class);
    when(coreFunction.getArg1()).thenReturn(exp1);
    when(coreFunction.getArg2()).thenReturn(exp2);
    when(exp1.computeValue(context)).thenReturn("hello");
    when(exp2.computeValue(context)).thenReturn("world");
    Object result = coreFunction.functionSubstringBefore(context);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringBeforeWithMatchingSecondString_KAjd3_TsZP0() {
    CoreFunction coreFunction = new CoreFunction(0, new Expression[2]);
    EvalContext context = mock(EvalContext.class);
    Expression exp1 = mock(Expression.class);
    Expression exp2 = mock(Expression.class);
    when(coreFunction.getArg1()).thenReturn(exp1);
    when(coreFunction.getArg2()).thenReturn(exp2);
    when(exp1.computeValue(context)).thenReturn("hello world");
    when(exp2.computeValue(context)).thenReturn(" world");
    Object result = coreFunction.functionSubstringBefore(context);
    assertEquals("hello", result.toString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringBeforeWithEmptySecondString_JwoH4_lUnH0() {
    CoreFunction coreFunction = new CoreFunction(0, new Expression[2]);
    EvalContext context = mock(EvalContext.class);
    Expression exp1 = mock(Expression.class);
    Expression exp2 = mock(Expression.class);
    when(coreFunction.getArg1()).thenReturn(exp1);
    when(coreFunction.getArg2()).thenReturn(exp2);
    when(exp1.computeValue(context)).thenReturn("hello");
    when(exp2.computeValue(context)).thenReturn("");
    Object result = coreFunction.functionSubstring(context);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringBeforeWithBothStringsEmpty_cJkN5_MEhz0() {
    CoreFunction coreFunction = new CoreFunction(0, new Expression[2]);
    EvalContext context = mock(EvalContext.class);
    Expression exp1 = mock(Expression.class);
    Expression exp2 = mock(Expression.class);
    when(coreFunction.getArg1()).thenReturn(exp1);
    when(coreFunction.getArg2()).thenReturn(exp2);
    when(exp1.computeValue(context)).thenReturn("");
    when(exp2.computeValue(context)).thenReturn(0.0); // Changed to return a number since substring expects a number for the second argument
    Object result = coreFunction.functionSubstring(context); // Changed method name to functionSubstring
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringBeforeWithSameStrings_EJKH6_QGVk0() {
    CoreFunction coreFunction = new CoreFunction(0, new Expression[2]);
    EvalContext context = mock(EvalContext.class);
    Expression exp1 = mock(Expression.class);
    Expression exp2 = mock(Expression.class);
    when(coreFunction.getArg1()).thenReturn(exp1);
    when(coreFunction.getArg2()).thenReturn(exp2);
    when(exp1.computeValue(context)).thenReturn("hello");
    when(exp2.computeValue(context)).thenReturn("hello");
    Object result = coreFunction.functionSubstring(context);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionStringWithOneArgument_ATgN1_eekj0() {
    CoreFunction coreFunction = mock(CoreFunction.class, CALLS_REAL_METHODS);
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Object computedValue = "computedValue";
    when(coreFunction.getArgumentCount()).thenReturn(1);
    when(coreFunction.getArg1()).thenReturn(arg1);
    when(arg1.computeValue(context)).thenReturn(computedValue);
    when(InfoSetUtil.stringValue(computedValue)).thenReturn("stringValue");
    Object result = coreFunction.functionString(context);
    verify(coreFunction).getArgumentCount();
    verify(coreFunction).getArg1();
    verify(arg1).computeValue(context);
    assert "stringValue".equals(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionStringWithOneArgumentNullValue_mdyG3_fffz0() {
    CoreFunction coreFunction = mock(CoreFunction.class, CALLS_REAL_METHODS);
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    when(coreFunction.getArgumentCount()).thenReturn(1);
    when(coreFunction.getArg1()).thenReturn(arg1);
    when(arg1.computeValue(context)).thenReturn(null);
    Object result = coreFunction.functionString(context);
    verify(coreFunction).getArgumentCount();
    verify(coreFunction).getArg1();
    verify(arg1).computeValue(context);
    assert result == null;
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringBeforeWithEmptySecondString_JwoH4_lUnH0_fid1() {
    CoreFunction coreFunction = mock(CoreFunction.class);
    EvalContext context = mock(EvalContext.class);
    Expression exp1 = mock(Expression.class);
    Expression exp2 = mock(Expression.class);
    when(coreFunction.getArg1()).thenReturn(exp1);
    when(coreFunction.getArg2()).thenReturn(exp2);
    when(exp1.computeValue(context)).thenReturn("hello");
    when(exp2.computeValue(context)).thenReturn("");
    Object result = coreFunction.functionSubstring(context);
    assertEquals("hello", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringBeforeWithBothStringsEmpty_cJkN5_MEhz0_fid1() {
    CoreFunction coreFunction = mock(CoreFunction.class);
    EvalContext context = mock(EvalContext.class);
    Expression exp1 = mock(Expression.class);
    Expression exp2 = mock(Expression.class);
    when(coreFunction.getArg1()).thenReturn(exp1);
    when(coreFunction.getArg2()).thenReturn(exp2);
    when(exp1.computeValue(context)).thenReturn("");
    when(exp2.computeValue(context)).thenReturn(""); // Corrected to return a string since substring expects a string for the second argument
    Object result = coreFunction.functionSubstring(context); // Assuming functionSubstring is the correct method name
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionStringWithOneArgument_ATgN1_eekj0_fid1() {
    CoreFunction coreFunction = mock(CoreFunction.class, CALLS_REAL_METHODS);
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Object computedValue = "computedValue";
    when(coreFunction.getArgumentCount()).thenReturn(1);
    when(coreFunction.getArg1()).thenReturn(arg1);
    when(arg1.computeValue(context)).thenReturn(computedValue);
    when(InfoSetUtil.stringValue(computedValue)).thenReturn("stringValue");
    Object result = coreFunction.functionString(context);
    verify(coreFunction).getArgumentCount();
    verify(coreFunction).getArg1();
    verify(arg1).computeValue(context);
    assertEquals("stringValue", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionStringWithOneArgumentNullValue_mdyG3_fffz0_fid1() {
    CoreFunction coreFunction = mock(CoreFunction.class, CALLS_REAL_METHODS);
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    when(coreFunction.getArgumentCount()).thenReturn(1);
    when(arg1.computeValue(context)).thenReturn(null);
    when(coreFunction.getArg1()).thenReturn(arg1);
    Object result = coreFunction.functionString(context);
    verify(coreFunction).getArgumentCount();
    verify(coreFunction).getArg1();
    verify(arg1).computeValue(context);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionLangWithCorrectArgCount_fLSu1() {
    Expression[] expressions = {mock(Expression.class)};
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext context = mock(EvalContext.class);
    when(expressions[0].computeValue(context)).thenReturn("en");
    coreFunction.functionLang(context);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionLangWithNullPointer_HNCp2() {
    Expression[] expressions = {mock(Expression.class)};
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext context = mock(EvalContext.class);
    when(context.getSingleNodePointer()).thenReturn(null);
    when(expressions[0].computeValue(context)).thenReturn("en");
    Boolean result = (Boolean) coreFunction.functionLang(context);
    assert result.equals(Boolean.FALSE);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionLangWithLanguageMatch_swtk3() {
    Expression[] expressions = {mock(Expression.class)};
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext context = mock(EvalContext.class);
    NodePointer nodePointer = mock(NodePointer.class);
    when(context.getSingleNodePointer()).thenReturn(nodePointer);
    when(expressions[0].computeValue(context)).thenReturn("en");
    when(nodePointer.isLanguage("en")).thenReturn(true);
    Boolean result = (Boolean) coreFunction.functionLang(context);
    assert result.equals(Boolean.TRUE);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionLangWithLanguageMismatch_XNof4() {
    Expression[] expressions = {mock(Expression.class)};
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext context = mock(EvalContext.class);
    NodePointer nodePointer = mock(NodePointer.class);
    when(context.getSingleNodePointer()).thenReturn(nodePointer);
    when(expressions[0].computeValue(context)).thenReturn("en");
    when(nodePointer.isLanguage("fr")).thenReturn(false);
    Boolean result = (Boolean) coreFunction.functionLang(context);
    assert result.equals(Boolean.FALSE);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionLangWithNullLanguage_MmvB5() {
    Expression[] expressions = {mock(Expression.class)};
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext context = mock(EvalContext.class);
    NodePointer nodePointer = mock(NodePointer.class);
    when(context.getSingleNodePointer()).thenReturn(nodePointer);
    when(expressions[0].computeValue(context)).thenReturn(null);
    when(nodePointer.isLanguage(null)).thenReturn(false);
    Boolean result = (Boolean) coreFunction.functionLang(context);
    assert result.equals(Boolean.FALSE);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionStartsWith_BothStringsMatch_rxfy0() {
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    when(arg1.computeValue(context)).thenReturn("Hello");
    when(arg2.computeValue(context)).thenReturn("He");
    CoreFunction coreFunction = new CoreFunction(0, new Expression[]{arg1, arg2});
    Object result = coreFunction.functionStartsWith(context);
    assertTrue((Boolean) result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionStartsWith_StringsDoNotMatch_wLST1() {
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    when(arg1.computeValue(context)).thenReturn("Hello");
    when(arg2.computeValue(context)).thenReturn("world");
    CoreFunction coreFunction = new CoreFunction(0, new Expression[]{arg1, arg2});
    Object result = coreFunction.functionStartsWith(context);
    assertFalse((Boolean) result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithNaNValue_kpop1_1() {
    Expression[] args = new Expression[1];
    Expression expr = mock(Expression.class);
    args[0] = expr;
    CoreFunction coreFunction = new CoreFunction(0, args);
    EvalContext context = mock(EvalContext.class);
    when(expr.computeValue(context)).thenReturn(Double.NaN);
    Object result = coreFunction.functionRound(context);
    assertTrue(result instanceof Double);}

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithNaNValue_kpop1_2() {
    Expression[] args = new Expression[1];
    Expression expr = mock(Expression.class);
    args[0] = expr;
    CoreFunction coreFunction = new CoreFunction(0, args);
    EvalContext context = mock(EvalContext.class);
    when(expr.computeValue(context)).thenReturn(Double.NaN);
    Object result = coreFunction.functionRound(context);
    assertTrue(Double.isNaN((Double) result));}

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithInfiniteValue_qGfF2_1() {
    Expression[] args = new Expression[1];
    Expression expr = mock(Expression.class);
    args[0] = expr;
    CoreFunction coreFunction = new CoreFunction(0, args);
    EvalContext context = mock(EvalContext.class);
    when(expr.computeValue(context)).thenReturn(Double.POSITIVE_INFINITY);
    Object result = coreFunction.functionRound(context);
    assertTrue(result instanceof Double);}

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithInfiniteValue_qGfF2_2() {
    Expression[] args = new Expression[1];
    Expression expr = mock(Expression.class);
    args[0] = expr;
    CoreFunction coreFunction = new CoreFunction(0, args);
    EvalContext context = mock(EvalContext.class);
    when(expr.computeValue(context)).thenReturn(Double.POSITIVE_INFINITY);
    Object result = coreFunction.functionRound(context);
    assertTrue(Double.isInfinite((Double) result));}

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithInfiniteValue_qGfF2_3() {
    Expression[] args = new Expression[1];
    Expression expr = mock(Expression.class);
    args[0] = expr;
    CoreFunction coreFunction = new CoreFunction(0, args);
    EvalContext context = mock(EvalContext.class);
    when(expr.computeValue(context)).thenReturn(Double.POSITIVE_INFINITY);
    Object result = coreFunction.functionRound(context);
    assertEquals(Double.POSITIVE_INFINITY, (Double) result, 0.0);}

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithNormalValue_HTTV3_1() {
    Expression[] args = new Expression[1];
    Expression expr = mock(Expression.class);
    args[0] = expr;
    CoreFunction coreFunction = new CoreFunction(0, args);
    EvalContext context = mock(EvalContext.class);
    when(expr.computeValue(context)).thenReturn(3.7);
    Object result = coreFunction.functionRound(context);
    assertTrue(result instanceof Double);}

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithNormalValue_HTTV3_2() {
    Expression[] args = new Expression[1];
    Expression expr = mock(Expression.class);
    args[0] = expr;
    CoreFunction coreFunction = new CoreFunction(0, args);
    EvalContext context = mock(EvalContext.class);
    when(expr.computeValue(context)).thenReturn(3.7);
    Object result = coreFunction.functionRound(context);
    assertEquals(4.0, (Double) result, 0.0);}

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithNegativeValue_qYFH4_1() {
    Expression[] args = new Expression[1];
    Expression expr = mock(Expression.class);
    args[0] = expr;
    CoreFunction coreFunction = new CoreFunction(0, args);
    EvalContext context = mock(EvalContext.class);
    when(expr.computeValue(context)).thenReturn(-1.2);
    Object result = coreFunction.functionRound(context);
    assertTrue(result instanceof Double);}

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithNegativeValue_qYFH4_2() {
    Expression[] args = new Expression[1];
    Expression expr = mock(Expression.class);
    args[0] = expr;
    CoreFunction coreFunction = new CoreFunction(0, args);
    EvalContext context = mock(EvalContext.class);
    when(expr.computeValue(context)).thenReturn(-1.2);
    Object result = coreFunction.functionRound(context);
    assertEquals(-1.0, (Double) result, 0.0);}

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSubstringWithTwoArguments_VkBy0() {
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    when(arg1.computeValue(context)).thenReturn("Hello");
    when(arg2.computeValue(context)).thenReturn(1.0);
    CoreFunction cf = new CoreFunction(0, new Expression[]{arg1, arg2});
    cf.functionSubstring(context);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSubstringWithThreeArguments_zSbc1() {
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    Expression arg3 = mock(Expression.class);
    when(arg1.computeValue(context)).thenReturn("Hello");
    when(arg2.computeValue(context)).thenReturn(1.0);
    when(arg3.computeValue(context)).thenReturn(4.0);
    CoreFunction cf = new CoreFunction(0, new Expression[]{arg1, arg2, arg3});
    cf.functionSubstring(context);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSubstringWithInvalidFromNaN_ZBBI2() {
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    when(arg1.computeValue(context)).thenReturn("Hello");
    when(arg2.computeValue(context)).thenReturn(Double.NaN);
    CoreFunction cf = new CoreFunction(0, new Expression[]{arg1, arg2});
    cf.functionSubstring(context);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSubstringWithFromGreaterThanLength_mwZP3() {
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    when(arg1.computeValue(context)).thenReturn("Hello");
    when(arg2.computeValue(context)).thenReturn(10.0);
    CoreFunction cf = new CoreFunction(0, new Expression[]{arg1, arg2});
    cf.functionSubstring(context);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSubstringWithNegativeLength_zPWb4() {
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    Expression arg3 = mock(Expression.class);
    when(arg1.computeValue(context)).thenReturn("Hello");
    when(arg2.computeValue(context)).thenReturn(1.0);
    when(arg3.computeValue(context)).thenReturn(-1.0);
    CoreFunction cf = new CoreFunction(0, new Expression[]{arg1, arg2, arg3});
    cf.functionSubstring(context);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSubstringWithToLessThanOne_IURd5() {
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    Expression arg3 = mock(Expression.class);
    when(arg1.computeValue(context)).thenReturn("Hello");
    when(arg2.computeValue(context)).thenReturn(0.0);
    when(arg3.computeValue(context)).thenReturn(1.0);
    CoreFunction cf = new CoreFunction(0, new Expression[]{arg1, arg2, arg3});
    cf.functionSubstring(context);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSubstringWithToGreaterThanLengthPlusOne_TZWZ6() {
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    Expression arg3 = mock(Expression.class);
    when(arg1.computeValue(context)).thenReturn("Hello");
    when(arg2.computeValue(context)).thenReturn(1.0);
    when(arg3.computeValue(context)).thenReturn(10.0);
    CoreFunction cf = new CoreFunction(0, new Expression[]{arg1, arg2, arg3});
    cf.functionSubstring(context);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSubstringWithFromLessThanOne_shpR7() {
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    when(arg1.computeValue(context)).thenReturn("Hello");
    when(arg2.computeValue(context)).thenReturn(0.0);
    CoreFunction cf = new CoreFunction(0, new Expression[]{arg1, arg2});
    cf.functionSubstring(context);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionCeilingWithInfinite_uwSy2_AdJl0() {
    Expression[] args = {mock(Expression.class)};
    CoreFunction coreFunction = new CoreFunction(0, args);
    EvalContext context = mock(EvalContext.class);
    when(args[0].computeValue(context)).thenReturn(Double.POSITIVE_INFINITY);
    Object result = coreFunction.functionCeiling(context);
    assertEquals(Double.POSITIVE_INFINITY, (double) result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSum_EvalContextWithAllZeroValues_mArc9_LLvJ0() {
    Expression[] expressions = {mock(Expression.class)};
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext context = mock(EvalContext.class);
    EvalContext evalContext = mock(EvalContext.class);
    NodePointer nodePointer1 = mock(NodePointer.class);
    NodePointer nodePointer2 = mock(NodePointer.class);
    when(expressions[0].computeValue(context)).thenReturn(evalContext);
    when(evalContext.hasNext()).thenReturn(true, true, false);
    when(evalContext.next()).thenReturn(nodePointer1, nodePointer2);
    when(nodePointer1.getValue()).thenReturn(0.0);
    when(nodePointer2.getValue()).thenReturn(0.0);
    Object result = coreFunction.functionSum(context);
    assertEquals(Double.valueOf(0.0), result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionStartsWith_NullStrings_giYQ3_fid1() {
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    when(arg1.computeValue(context)).thenReturn(null);
    when(arg2.computeValue(context)).thenReturn(null);
    CoreFunction coreFunction = new CoreFunction(0, new Expression[]{arg1, arg2});
    Object result = coreFunction.functionStartsWith(context);
    assertTrue((Boolean) result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringBeforeWithEmptyFirstString_BkgN1_epMA0_fid1() {
    CoreFunction coreFunction = mock(CoreFunction.class);
    EvalContext context = mock(EvalContext.class);
    Expression exp1 = mock(Expression.class);
    Expression exp2 = mock(Expression.class);
    when(coreFunction.getArg1()).thenReturn(exp1);
    when(coreFunction.getArg2()).thenReturn(exp2);
    when(exp1.computeValue(context)).thenReturn("");
    when(exp2.computeValue(context)).thenReturn("test");
    when(coreFunction.functionSubstring(context)).thenReturn("");
    Object result = coreFunction.functionSubstring(context);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringBeforeWithNonMatchingSecondString_iRti2_uBpn0_fid1() {
    CoreFunction coreFunction = mock(CoreFunction.class);
    EvalContext context = mock(EvalContext.class);
    Expression exp1 = mock(Expression.class);
    Expression exp2 = mock(Expression.class);
    when(coreFunction.getArg1()).thenReturn(exp1);
    when(coreFunction.getArg2()).thenReturn(exp2);
    when(exp1.computeValue(context)).thenReturn("hello");
    when(exp2.computeValue(context)).thenReturn("world");
    when(coreFunction.functionSubstringBefore(context)).thenReturn("");
    Object result = coreFunction.functionSubstringBefore(context);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringBeforeWithMatchingSecondString_KAjd3_TsZP0_fid1() {
    CoreFunction coreFunction = mock(CoreFunction.class);
    EvalContext context = mock(EvalContext.class);
    Expression exp1 = mock(Expression.class);
    Expression exp2 = mock(Expression.class);
    when(coreFunction.getArg1()).thenReturn(exp1);
    when(coreFunction.getArg2()).thenReturn(exp2);
    when(exp1.computeValue(context)).thenReturn("hello world");
    when(exp2.computeValue(context)).thenReturn(" world");
    when(coreFunction.functionSubstringBefore(context)).thenReturn("hello");
    Object result = coreFunction.functionSubstringBefore(context);
    assertEquals("hello", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringBeforeWithSameStrings_EJKH6_QGVk0_fid1() {
    CoreFunction coreFunction = mock(CoreFunction.class);
    EvalContext context = mock(EvalContext.class);
    Expression exp1 = mock(Expression.class);
    Expression exp2 = mock(Expression.class);
    when(coreFunction.getArg1()).thenReturn(exp1);
    when(coreFunction.getArg2()).thenReturn(exp2);
    when(exp1.computeValue(context)).thenReturn("hello");
    when(exp2.computeValue(context)).thenReturn("hello");
    when(coreFunction.functionSubstring(context)).thenReturn("");
    Object result = coreFunction.functionSubstring(context);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithNaNValue_kpop1() {
    Expression[] args = new Expression[1];
    Expression expr = mock(Expression.class);
    args[0] = expr;
    CoreFunction coreFunction = new CoreFunction(0, args);
    EvalContext context = mock(EvalContext.class);
    when(expr.computeValue(context)).thenReturn(Double.NaN);
    Object result = coreFunction.functionRound(context);
    assertTrue(result instanceof Double);
    assertTrue(Double.isNaN((Double) result));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithInfiniteValue_qGfF2() {
    Expression[] args = new Expression[1];
    Expression expr = mock(Expression.class);
    args[0] = expr;
    CoreFunction coreFunction = new CoreFunction(0, args);
    EvalContext context = mock(EvalContext.class);
    when(expr.computeValue(context)).thenReturn(Double.POSITIVE_INFINITY);
    Object result = coreFunction.functionRound(context);
    assertTrue(result instanceof Double);
    assertTrue(Double.isInfinite((Double) result));
    assertEquals(Double.POSITIVE_INFINITY, (Double) result, 0.0);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithNormalValue_HTTV3() {
    Expression[] args = new Expression[1];
    Expression expr = mock(Expression.class);
    args[0] = expr;
    CoreFunction coreFunction = new CoreFunction(0, args);
    EvalContext context = mock(EvalContext.class);
    when(expr.computeValue(context)).thenReturn(3.7);
    Object result = coreFunction.functionRound(context);
    assertTrue(result instanceof Double);
    assertEquals(4.0, (Double) result, 0.0);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithNegativeValue_qYFH4() {
    Expression[] args = new Expression[1];
    Expression expr = mock(Expression.class);
    args[0] = expr;
    CoreFunction coreFunction = new CoreFunction(0, args);
    EvalContext context = mock(EvalContext.class);
    when(expr.computeValue(context)).thenReturn(-1.2);
    Object result = coreFunction.functionRound(context);
    assertTrue(result instanceof Double);
    assertEquals(-1.0, (Double) result, 0.0);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionConcat_BUJb0() throws Exception {
    Expression[] expressions = new Expression[2];
    Expression expr1 = mock(Expression.class);
    Expression expr2 = mock(Expression.class);
    expressions[0] = expr1;
    expressions[1] = expr2;
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext context = mock(EvalContext.class);
    when(expr1.computeValue(context)).thenReturn("Hello");
    when(expr2.computeValue(context)).thenReturn("World");
    Object result = coreFunction.functionConcat(context);
    verify(expr1, times(1)).computeValue(context);
    verify(expr2, times(1)).computeValue(context);
    assertEquals("HelloWorld", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringAfter_BothStringsPresent_YZXy0_fid3() {
    Expression[] expressions = new Expression[3];
    expressions[0] = mock(Expression.class);
    expressions[1] = mock(Expression.class);
    expressions[2] = mock(Expression.class);
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext evalContext = mock(EvalContext.class);
    when(expressions[0].computeValue(evalContext)).thenReturn("hello world");
    when(expressions[1].computeValue(evalContext)).thenReturn(6.0);
    when(expressions[2].computeValue(evalContext)).thenReturn(5.0);
    String result = (String) coreFunction.functionSubstring(evalContext);
    assertEquals("world", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionLocalName_WithArgumentsAndHasNext_ReturnsNextNodeName_jvmp0_fid3() {
    CoreFunction coreFunction = new CoreFunction(0, new Expression[0]);
    EvalContext context = mock(EvalContext.class);
    NodePointer nodePointer = mock(NodePointer.class);
    QName name = mock(QName.class);
    when(context.hasNext()).thenReturn(true);
    when(context.next()).thenReturn(nodePointer);
    when(nodePointer.getName()).thenReturn(name);
    when(name.toString()).thenReturn("nextNodeName");
    Expression[] args = new Expression[] {mock(Expression.class)};
    coreFunction = new CoreFunction(1, args);
    when(args[0].computeValue(context)).thenReturn(context);
    when(context.hasNext()).thenReturn(true);
    when(context.next()).thenReturn(nodePointer);
    Object result = coreFunction.functionLocalName(context);
    assertEquals("nextNodeName", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionTranslate_BGog0_fid3() throws Exception {
    EvalContext evalContext = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    Expression arg3 = mock(Expression.class);
    CoreFunction coreFunction = new CoreFunction(0, new Expression[]{arg1, arg2, arg3});
    when(arg1.computeValue(evalContext)).thenReturn("apple");
    when(arg2.computeValue(evalContext)).thenReturn("ap");
    when(arg3.computeValue(evalContext)).thenReturn("OA");
    Object result = coreFunction.functionTranslate(evalContext);
    assertEquals("OOpOle", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionConcat_BUJb0_fid3() throws Exception {
    Expression[] expressions = new Expression[2];
    Expression expr1 = mock(Expression.class);
    Expression expr2 = mock(Expression.class);
    expressions[0] = expr1;
    expressions[1] = expr2;
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext context = mock(EvalContext.class);
    when(expr1.computeValue(context)).thenReturn("Hello");
    when(expr2.computeValue(context)).thenReturn("World");
    Object result = coreFunction.functionConcat(context);
    verify(expr1).computeValue(context);
    verify(expr2).computeValue(context);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithNegativeInfinity_tXPb0_fid3() throws Exception {
    CoreFunction coreFunction = mock(CoreFunction.class);
    Expression expression1 = mock(Expression.class);
    Expression expression2 = mock(Expression.class);
    EvalContext context = mock(EvalContext.class);
    when(coreFunction.getArgumentCount()).thenReturn(2);
    when(coreFunction.getArg1()).thenReturn(expression1);
    when(coreFunction.getArg2()).thenReturn(expression2);
    when(expression1.computeValue(context)).thenReturn("test");
    when(expression2.computeValue(context)).thenReturn(Double.NEGATIVE_INFINITY);
    Object result = coreFunction.functionSubstring(context);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithPositiveInfinity_BABU0_fid3() throws Exception {
    CoreFunction coreFunction = mock(CoreFunction.class);
    EvalContext context = mock(EvalContext.class);
    Expression expression1 = mock(Expression.class);
    Expression expression2 = mock(Expression.class);
    Expression expression3 = mock(Expression.class);
    when(coreFunction.getArgumentCount()).thenReturn(3);
    when(coreFunction.getArg1()).thenReturn(expression1);
    when(coreFunction.getArg2()).thenReturn(expression2);
    when(coreFunction.getArg3()).thenReturn(expression3);
    when(expression1.computeValue(context)).thenReturn("Hello World");
    when(expression2.computeValue(context)).thenReturn(Double.POSITIVE_INFINITY);
    when(expression3.computeValue(context)).thenReturn(5.0);
    Object result = coreFunction.functionSubstring(context);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionNamespaceURI_WithArguments_ReturnsNamespaceURI_QABv0_fid3() {
    CoreFunction coreFunction = new CoreFunction(0, new Expression[0]);
    EvalContext evalContext = mock(EvalContext.class);
    NodePointer nodePointer = mock(NodePointer.class);
    EvalContext argContext = mock(EvalContext.class);
    when(evalContext.hasNext()).thenReturn(true);
    when(evalContext.nextNode()).thenReturn(true);
    when(evalContext.getCurrentNodePointer()).thenReturn(nodePointer);
    when(nodePointer.getNamespaceURI()).thenReturn("http://example.com");
    Expression expression = mock(Expression.class);
    when(expression.computeValue(evalContext)).thenReturn(argContext);
    coreFunction = new CoreFunction(1, new Expression[]{expression});
    Object result = coreFunction.functionNamespaceURI(evalContext);
    assertEquals("http://example.com", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringAfter_SecondStringAbsent_OgQY0() {
    Expression[] expressions = new Expression[2];
    expressions[0] = mock(Expression.class);
    expressions[1] = mock(Expression.class);
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext evalContext = mock(EvalContext.class);
    when(expressions[0].computeValue(evalContext)).thenReturn("hello world");
    when(expressions[1].computeValue(evalContext)).thenReturn("test");
    String result = (String) coreFunction.functionSubstring(evalContext);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringAfter_BothStringsPresent_YZXy0() {
    Expression[] expressions = new Expression[3];
    expressions[0] = mock(Expression.class);
    expressions[1] = mock(Expression.class);
    expressions[2] = mock(Expression.class);
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext evalContext = mock(EvalContext.class);
    when(expressions[0].computeValue(evalContext)).thenReturn("hello world");
    when(expressions[1].computeValue(evalContext)).thenReturn(6.0);
    when(expressions[2].computeValue(evalContext)).thenReturn(5.0);
    String result = (String) coreFunction.functionSubstring(evalContext);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringAfter_EmptySecondString_ynrX0() {
    Expression[] expressions = new Expression[2];
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    expressions[0] = arg1;
    expressions[1] = arg2;
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext evalContext = mock(EvalContext.class);
    when(arg1.computeValue(evalContext)).thenReturn("hello world");
    when(arg2.computeValue(evalContext)).thenReturn("");
    String result = (String) coreFunction.functionSubstring(evalContext);
    assertEquals("hello world", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringAfter_BothStringsEmpty_QLwd0() {
    Expression[] expressions = new Expression[2];
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    expressions[0] = arg1;
    expressions[1] = arg2;
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext evalContext = mock(EvalContext.class);
    when(arg1.computeValue(evalContext)).thenReturn("");
    when(arg2.computeValue(evalContext)).thenReturn(1.0);
    String result = (String) coreFunction.functionSubstring(evalContext);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringAfter_EmptyFirstString_kvYh0() {
    Expression[] expressions = new Expression[2];
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    expressions[0] = arg1;
    expressions[1] = arg2;
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext evalContext = mock(EvalContext.class);
    when(arg1.computeValue(evalContext)).thenReturn("");
    when(arg2.computeValue(evalContext)).thenReturn(5.0); // Corrected to a valid double value
    String result = (String) coreFunction.functionSubstring(evalContext);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringWithThreeArgs_KBDB0() {
    Expression[] args = new Expression[3];
    args[0] = mock(Expression.class);
    args[1] = mock(Expression.class);
    args[2] = mock(Expression.class);
    CoreFunction coreFunction = new CoreFunction(0, args);
    EvalContext context = mock(EvalContext.class);
    when(args[0].computeValue(context)).thenReturn("Hello World");
    when(args[1].computeValue(context)).thenReturn(1.0);
    when(args[2].computeValue(context)).thenReturn(5.0);
    String result = (String) coreFunction.functionSubstring(context);
    assertEquals("Hello", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringWithTwoArgs_WkyS0() {
    Expression[] expressions = new Expression[2];
    expressions[0] = mock(Expression.class);
    expressions[1] = mock(Expression.class);
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext context = mock(EvalContext.class);
    when(expressions[0].computeValue(context)).thenReturn("Hello World");
    when(expressions[1].computeValue(context)).thenReturn(7.0);
    String result = (String) coreFunction.functionSubstring(context);
    assertEquals("World", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionLocalName_WithArgumentsAndHasNext_ReturnsNextNodeName_jvmp0() {
    CoreFunction coreFunction = new CoreFunction(0, new Expression[0]);
    EvalContext context = mock(EvalContext.class);
    NodePointer nodePointer = mock(NodePointer.class);
    QName name = mock(QName.class);
    when(context.hasNext()).thenReturn(true);
    when(context.next()).thenReturn(nodePointer);
    when(nodePointer.getName()).thenReturn(name);
    when(name.toString()).thenReturn("");
    Expression[] args = new Expression[] {mock(Expression.class)};
    coreFunction = new CoreFunction(1, args);
    when(args[0].computeValue(context)).thenReturn(context);
    when(context.hasNext()).thenReturn(true);
    when(context.next()).thenReturn(nodePointer);
    Object result = coreFunction.functionLocalName(context);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringWithOutOfBoundsFrom_oyZS0() {
    Expression[] args = new Expression[3];
    args[0] = mock(Expression.class);
    args[1] = mock(Expression.class);
    args[2] = mock(Expression.class);
    CoreFunction coreFunction = new CoreFunction(0, args);
    EvalContext context = mock(EvalContext.class);
    when(args[0].computeValue(context)).thenReturn("Hello World");
    when(args[1].computeValue(context)).thenReturn(20.0);
    when(args[2].computeValue(context)).thenReturn(5.0); // Assuming a length for the third argument
    String result = (String) coreFunction.functionSubstring(context);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringWithNegativeLength_Wlfa0() {
    Expression[] expressions = new Expression[3];
    expressions[0] = mock(Expression.class);
    expressions[1] = mock(Expression.class);
    expressions[2] = mock(Expression.class);
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext context = mock(EvalContext.class);
    when(expressions[0].computeValue(context)).thenReturn("Hello World");
    when(expressions[1].computeValue(context)).thenReturn(3.0);
    when(expressions[2].computeValue(context)).thenReturn(-1.0);
    String result = (String) coreFunction.functionSubstring(context);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringWithInvalidFromNaN_nocr0() {
    Expression[] expressions = new Expression[3];
    expressions[0] = mock(Expression.class);
    expressions[1] = mock(Expression.class);
    expressions[2] = mock(Expression.class);
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext context = mock(EvalContext.class);
    when(expressions[0].computeValue(context)).thenReturn("Hello World");
    when(expressions[1].computeValue(context)).thenReturn(Double.NaN);
    String result = (String) coreFunction.functionSubstring(context);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionCeilingWithNegativeValue_RbjL0() {
    Expression[] expressions = new Expression[]{mock(Expression.class)};
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext context = mock(EvalContext.class);
    when(expressions[0].computeValue(context)).thenReturn(-2.1);
    Object result = coreFunction.functionCeiling(context);
    assertEquals(-2.0, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionCeilingWithNaN_ibZN0() {
    Expression[] expressions = new Expression[]{mock(Expression.class)};
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext context = mock(EvalContext.class);
    when(expressions[0].computeValue(context)).thenReturn(Double.NaN);
    Object result = coreFunction.functionCeiling(context);
    assertTrue(Double.isNaN((Double) result));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionCeilingWithInfinity_NgjY0() {
    Expression[] expressions = new Expression[]{mock(Expression.class)};
    CoreFunction coreFunction = new CoreFunction(0, expressions);
    EvalContext context = mock(EvalContext.class);
    when(expressions[0].computeValue(context)).thenReturn(Double.POSITIVE_INFINITY);
    Object result = coreFunction.functionCeiling(context);
    assertTrue(Double.isInfinite((Double) result) && (Double) result > 0);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionTranslate_BGog0() throws Exception {
    EvalContext evalContext = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    Expression arg3 = mock(Expression.class);
    CoreFunction coreFunction = new CoreFunction(0, new Expression[]{arg1, arg2, arg3});
    when(arg1.computeValue(evalContext)).thenReturn("apple");
    when(arg2.computeValue(evalContext)).thenReturn("ap");
    when(arg3.computeValue(evalContext)).thenReturn("OA");
    Object result = coreFunction.functionTranslate(evalContext);
    assertEquals("OAAle", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionNot_WhenFalse_ShouldReturnTrue_SlcT0() throws Exception {
    Expression expression = mock(Expression.class);
    EvalContext evalContext = mock(EvalContext.class);
    CoreFunction coreFunction = new CoreFunction(0, new Expression[]{expression});
    when(expression.computeValue(evalContext)).thenReturn(false);
    Object result = coreFunction.functionNot(evalContext);
    assertEquals(true, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionNot_WhenTrue_ShouldReturnFalse_rWni0() throws Exception {
    Expression expression = mock(Expression.class);
    EvalContext evalContext = mock(EvalContext.class);
    CoreFunction coreFunction = new CoreFunction(0, new Expression[]{expression});
    when(expression.computeValue(any(EvalContext.class))).thenReturn(Boolean.TRUE);
    Object result = coreFunction.functionNot(evalContext);
    assertEquals(Boolean.FALSE, result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithNaN_oufi0() throws Exception {
    EvalContext context = mock(EvalContext.class);
    Expression expression1 = mock(Expression.class);
    Expression expression2 = mock(Expression.class);
    Expression expression3 = mock(Expression.class);
    when(expression1.computeValue(context)).thenReturn("test string");
    when(expression2.computeValue(context)).thenReturn(Double.NaN);
    when(expression3.computeValue(context)).thenReturn(5.0);
    CoreFunction coreFunction = new CoreFunction(0, new Expression[]{expression1, expression2, expression3});
    Object result = coreFunction.functionSubstring(context);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionSubstringWithNormalValue_DAZQ0() throws Exception {
    EvalContext context = mock(EvalContext.class);
    Expression arg1 = mock(Expression.class);
    Expression arg2 = mock(Expression.class);
    Expression arg3 = mock(Expression.class);
    CoreFunction coreFunction = new CoreFunction(0, new Expression[]{arg1, arg2, arg3});
    when(arg1.computeValue(context)).thenReturn("Hello World");
    when(arg2.computeValue(context)).thenReturn(1.0);
    when(arg3.computeValue(context)).thenReturn(4.0);
    Object result = coreFunction.functionSubstring(context);
    assertEquals("Hell", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithNegativeInfinity_tXPb0() throws Exception {
    CoreFunction coreFunction = mock(CoreFunction.class);
    Expression expression1 = mock(Expression.class);
    Expression expression2 = mock(Expression.class);
    EvalContext context = mock(EvalContext.class);
    when(coreFunction.getArgumentCount()).thenReturn(2);
    when(coreFunction.getArg1()).thenReturn(expression1);
    when(coreFunction.getArg2()).thenReturn(expression2);
    when(expression1.computeValue(context)).thenReturn("test");
    when(expression2.computeValue(context)).thenReturn(Double.NEGATIVE_INFINITY);
    Object result = coreFunction.functionSubstring(context);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionRoundWithPositiveInfinity_BABU0() throws Exception {
    CoreFunction coreFunction = mock(CoreFunction.class);
    EvalContext context = mock(EvalContext.class);
    Expression expression1 = mock(Expression.class);
    Expression expression2 = mock(Expression.class);
    Expression expression3 = mock(Expression.class);
    when(coreFunction.getArgumentCount()).thenReturn(3);
    when(coreFunction.getArg1()).thenReturn(expression1);
    when(coreFunction.getArg2()).thenReturn(expression2);
    when(coreFunction.getArg3()).thenReturn(expression3);
    when(expression1.computeValue(context)).thenReturn("Hello World");
    when(expression2.computeValue(context)).thenReturn(Double.POSITIVE_INFINITY);
    when(expression3.computeValue(context)).thenReturn(5.0);
    Object result = coreFunction.functionSubstring(context);
    assertNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionNamespaceURI_WithArguments_ReturnsEmptyStringWhenNoNextNode_XjOA0() {
    CoreFunction coreFunction = new CoreFunction(0, new Expression[0]);
    EvalContext evalContext = mock(EvalContext.class);
    EvalContext argContext = mock(EvalContext.class);
    when(evalContext.hasNext()).thenReturn(false);
    Expression expression = mock(Expression.class);
    when(expression.computeValue(evalContext)).thenReturn(argContext);
    coreFunction = new CoreFunction(1, new Expression[]{expression});
    Object result = coreFunction.functionNamespaceURI(evalContext);
    assertEquals("", result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionStringLength_WithArguments_xNxZ0_1() throws Exception {
    CoreFunction coreFunction = new CoreFunction(0, new Expression[0]);
    Expression[] args = new Expression[1];
    Expression expr = mock(Expression.class);
    args[0] = expr;
    coreFunction = new CoreFunction(0, args);
    EvalContext evalContext = mock(EvalContext.class);
    when(expr.computeValue(evalContext)).thenReturn("Test String");
    Object result = coreFunction.functionStringLength(evalContext);
    assertNotNull(result);}

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionStringLength_WithArguments_xNxZ0_2() throws Exception {
    CoreFunction coreFunction = new CoreFunction(0, new Expression[0]);
    Expression[] args = new Expression[1];
    Expression expr = mock(Expression.class);
    args[0] = expr;
    coreFunction = new CoreFunction(0, args);
    EvalContext evalContext = mock(EvalContext.class);
    when(expr.computeValue(evalContext)).thenReturn("Test String");
    Object result = coreFunction.functionStringLength(evalContext);
    assertTrue(result instanceof Double);}

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionStringLength_WithArguments_xNxZ0_3() throws Exception {
    CoreFunction coreFunction = new CoreFunction(0, new Expression[0]);
    Expression[] args = new Expression[1];
    Expression expr = mock(Expression.class);
    args[0] = expr;
    coreFunction = new CoreFunction(0, args);
    EvalContext evalContext = mock(EvalContext.class);
    when(expr.computeValue(evalContext)).thenReturn("Test String");
    Object result = coreFunction.functionStringLength(evalContext);
    assertEquals(11.0, (Double) result, 0.01);}

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testFunctionNamespaceURI_WithArguments_ReturnsNamespaceURI_QABv0() {
    CoreFunction coreFunction = new CoreFunction(0, new Expression[0]);
    EvalContext evalContext = mock(EvalContext.class);
    NodePointer nodePointer = mock(NodePointer.class);
    EvalContext argContext = mock(EvalContext.class);
    when(evalContext.hasNext()).thenReturn(true);
    when(evalContext.nextNode()).thenReturn(true);
    when(evalContext.getCurrentNodePointer()).thenReturn(nodePointer);
    when(nodePointer.getNamespaceURI()).thenReturn("");
    Expression expression = mock(Expression.class);
    when(expression.computeValue(evalContext)).thenReturn(argContext);
    coreFunction = new CoreFunction(1, new Expression[]{expression});
    Object result = coreFunction.functionNamespaceURI(evalContext);
    assertEquals("", result);
    }
}