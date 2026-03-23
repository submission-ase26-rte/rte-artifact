/**
 * Extracted tests for method: parse(final Options options, final String[] arguments, final Properties properties, final boolean stopAtNonOption)
 * Original class: DefaultParser
 * Source: ASTER GPT-4
 */
package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;
import static org.mockito.Mockito.*;

public class Metodo5_parse_DefaultParser_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNegativeNumberArgument_utqi0() throws ParseException {
    Options options = mock(Options.class);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new HashSet<>());
    String[] arguments = new String[] {"-123"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    verify(options).getRequiredOptions();
    verify(options).getOptionGroups();
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNegativeNumberArgument_gsFe0() throws ParseException {
    Options options = mock(Options.class);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"--option", "-123"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_withCurrentOptionAcceptsArgAndIsArgument_CsHL0() throws ParseException {
    Options options = mock(Options.class);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    Option mockOption = mock(Option.class);
    when(mockOption.acceptsArg()).thenReturn(true);
    DefaultParser parser = new DefaultParser();
    CommandLine cmd = mock(CommandLine.class);
    CommandLine.Builder cmdBuilder = mock(CommandLine.Builder.class);
    when(cmdBuilder.setDeprecatedHandler(any())).thenReturn(cmdBuilder);
    when(cmdBuilder.build()).thenReturn(cmd);
    String[] arguments = {"--option", "value"};
    Properties properties = new Properties();
    parser.parse(options, arguments, properties, false);
    verify(mockOption).processValue("value");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_withTokenStartingWithDashAndNotSingleDash_duzE1() throws ParseException {
    Options options = mock(Options.class);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    DefaultParser parser = new DefaultParser();
    CommandLine cmd = mock(CommandLine.class);
    CommandLine.Builder cmdBuilder = mock(CommandLine.Builder.class);
    when(cmdBuilder.setDeprecatedHandler(any())).thenReturn(cmdBuilder);
    when(cmdBuilder.build()).thenReturn(cmd);
    String[] arguments = {"-opt"};
    Properties properties = new Properties();
    parser.parse(options, arguments, properties, false);
    verify(cmd, never()).addArg(anyString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_withNegativeNumberArgument_VyWK0() throws ParseException {
    Options options = mock(Options.class);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    String[] arguments = new String[]{"-5"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("-5", result.getArgs()[0]);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNegativeNumberArgument_ykbv0() throws ParseException {
    Options options = mock(Options.class);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    when(options.hasShortOption(anyString())).thenReturn(false);
    when(options.hasLongOption(anyString())).thenReturn(false);
    DefaultParser parser = new DefaultParser();
    String[] arguments = new String[]{"-1234"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    verify(options, times(1)).getRequiredOptions();
    verify(options, times(1)).getOptionGroups();
    verify(options, never()).hasShortOption(anyString());
    verify(options, never()).hasLongOption(anyString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithCurrentOptionAcceptsArgAndIsArgument_zAWK0() throws ParseException {
    Options options = mock(Options.class);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    Option mockOption = mock(Option.class);
    when(mockOption.acceptsArg()).thenReturn(true);
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"--option", "value"};
    Properties properties = new Properties();
    parser.currentOption = mockOption;
    parser.cmd = mock(CommandLine.class);
    parser.parse(options, arguments, properties, false);
    verify(mockOption).processValue("value");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithTokenStartingWithDashButNotSingleDash_HdyF1() throws ParseException {
    Options options = mock(Options.class);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"-opt"};
    Properties properties = new Properties();
    parser.cmd = mock(CommandLine.class);
    parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithOptionToken_spcS0() throws ParseException {
    Options options = mock(Options.class);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    when(options.hasLongOption(anyString())).thenReturn(true);
    when(options.hasShortOption(anyString())).thenReturn(true);
    String[] arguments = {"--validOption"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    verify(options).hasLongOption("--validOption");
    verify(options).hasShortOption("--validOption");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_WithTokenStartingWithDashNotEqualsSingleDash_RlCT1() throws ParseException {
    Options options = mock(Options.class);
    Option mockOption = mock(Option.class);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"-opt"};
    Properties properties = new Properties();
    when(options.getOption("-opt")).thenReturn(mockOption);
    CommandLine result = parser.parse(options, arguments, properties, false);
    verify(options, times(1)).getOption("-opt");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithTokenStartingWithDash_KVra1() throws ParseException {
    Options options = mock(Options.class);
    Option mockOption = mock(Option.class);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"-opt"};
    Properties properties = new Properties();
    when(options.getOption("-opt")).thenReturn(mockOption);
    when(mockOption.acceptsArg()).thenReturn(false);
    CommandLine result = parser.parse(options, arguments, properties, false);
    verify(options).getOption("-opt");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithCurrentOptionAcceptsArgAndIsArgument_kEMO0() throws ParseException {
    Options options = mock(Options.class);
    Option mockOption = mock(Option.class);
    when(mockOption.acceptsArg()).thenReturn(true);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"--option", "value"};
    Properties properties = new Properties();
    when(mockOption.acceptsArg()).thenReturn(true);
    when(options.getOption("--option")).thenReturn(mockOption);
    when(options.hasLongOption("--option")).thenReturn(true);
    when(options.getMatchingOptions("--option")).thenReturn(Collections.singletonList("option"));
    CommandLine result = parser.parse(options, arguments, properties, false);
    verify(mockOption).processValue("value");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithTokenStartingWithDashButNotSingleDash_bwEU1() throws ParseException {
    Options options = mock(Options.class);
    Option mockOption = mock(Option.class);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"-opt"};
    Properties properties = new Properties();
    when(options.hasShortOption("opt")).thenReturn(true);
    when(options.getOption("opt")).thenReturn(mockOption);
    when(mockOption.acceptsArg()).thenReturn(false);
    CommandLine result = parser.parse(options, arguments, properties, false);
    verify(options).getOption("opt");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithCurrentOptionAcceptsArg_uKtF0() throws ParseException {
    Options options = mock(Options.class);
    Option mockOption = mock(Option.class);
    when(mockOption.acceptsArg()).thenReturn(true);
    when(options.getOption(anyString())).thenReturn(mockOption);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"--option", "value"};
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    verify(mockOption, times(1)).acceptsArg();
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithDashPrefixNotSingleDash_Ewys1() throws ParseException {
    Options options = mock(Options.class);
    Option mockOption = mock(Option.class);
    when(options.getOption(anyString())).thenReturn(mockOption);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"-opt"};
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    verify(options, times(1)).getOption("opt");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStripQuotesNull_jdzj0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = {"--option", "\"value with quotes\""};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value with quotes", result.getOptionValue("option"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStripQuotesFalse_gAQu1() throws ParseException {
    DefaultParser parser = new DefaultParser(false);
    Options options = new Options();
    String[] arguments = {"--option", "\"value with quotes\""};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("\"value with quotes\"", result.getOptionValue("option"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithCurrentOptionAcceptsArgAndIsArgument_rycB0() throws ParseException {
    Options options = mock(Options.class);
    Option mockOption = mock(Option.class);
    when(mockOption.acceptsArg()).thenReturn(true);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"--option", "value"};
    Properties properties = new Properties();
    when(options.hasOption("option")).thenReturn(true);
    when(options.getOption("option")).thenReturn(mockOption);
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    verify(mockOption).processValue("value");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithTokenStartingWithDashButNotSingleDash_OfwN1() throws ParseException {
    Options options = mock(Options.class);
    Option mockOption = mock(Option.class);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"-opt"};
    Properties properties = new Properties();
    when(options.hasShortOption("opt")).thenReturn(true);
    when(options.getOption("opt")).thenReturn(mockOption);
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    verify(options).getOption("opt");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithTokenStartingWithDashButNotSingleDash_cINV1() throws ParseException {
    Options options = mock(Options.class);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"-opt"};
    Properties properties = new Properties();
    parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithTokenStartingWithDash_hmIP1() throws ParseException {
    Options options = mock(Options.class);
    Option mockOption = mock(Option.class);
    when(options.getOption(anyString())).thenReturn(mockOption);
    when(options.hasShortOption(anyString())).thenReturn(true);
    when(mockOption.acceptsArg()).thenReturn(false);
    DefaultParser parser = new DefaultParser();
    String[] arguments = new String[]{"-o"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    verify(options, times(1)).hasShortOption("o");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_TokenStartsWithDashButNotSingleDash_limw1() throws ParseException {
    Options options = mock(Options.class);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    String[] arguments = {"-opt"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine cmd = mock(CommandLine.class);
    parser.cmd = cmd;
    parser.parse(options, arguments, properties, false);
    verify(cmd, atLeastOnce()).getOptionValue(anyString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testOptionNotSelectedAndNotPresentInCmd_ChZd1() throws ParseException {
    Options options = mock(Options.class);
    Option opt = mock(Option.class);
    when(options.getOption("option")).thenReturn(opt);
    CommandLine cmd = mock(CommandLine.class);
    when(cmd.hasOption("option")).thenReturn(false);
    Properties properties = new Properties();
    properties.setProperty("option", "true");
    DefaultParser parser = new DefaultParser();
    parser.parse(options, new String[]{}, properties, false);
    verify(cmd).hasOption("option");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testOptionValuesNull_kryX2() throws ParseException {
    Options options = mock(Options.class);
    Option opt = mock(Option.class);
    when(options.getOption("option")).thenReturn(opt);
    when(opt.hasArg()).thenReturn(true);
    when(opt.getValues()).thenReturn(null);
    Properties properties = new Properties();
    properties.setProperty("option", "value");
    DefaultParser parser = new DefaultParser();
    parser.parse(options, new String[]{}, properties, false);
    verify(opt).getValues();
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleUnknownToken_qkEU0() throws ParseException {
    Options options = new Options();
    options.addOption("a", false, "option without an argument");
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"--unknown"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithTokenStartingWithDashNotEqualsDash_CINE1() throws ParseException {
    Options options = mock(Options.class);
    when(options.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(options.getOptionGroups()).thenReturn(new ArrayList<>());
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"-opt"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_NullToken_WAUZ0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = {null};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_LongOptionWithoutEqualSign_wZCZ3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("opt", "option", true, "An option");
    String[] arguments = {"--option"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_LongOptionWithPrefixAndNoDoubleDash_IfEE5() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("o", "opt", true, "An option");
    String[] arguments = {"-opt"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_NullToken_ShouldNotBeShortOption_Gzin0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = mock(Options.class);
    String[] arguments = {null};
    Properties properties = new Properties();
    parser.parse(options, arguments, properties, false);
    verify(options, never()).hasShortOption(anyString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_ShortOptionWithEquals_ShouldHandleShortOption_kEGg2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = mock(Options.class);
    String[] arguments = {"-a=value"};
    Properties properties = new Properties();
    when(options.hasShortOption("a")).thenReturn(true);
    parser.parse(options, arguments, properties, false);
    verify(options).hasShortOption("a");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_ValidShortOption_ShouldReturnTrue_dSOI3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = mock(Options.class);
    String[] arguments = {"-a"};
    Properties properties = new Properties();
    when(options.hasShortOption("a")).thenReturn(true);
    parser.parse(options, arguments, properties, false);
    verify(options).hasShortOption("a");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_MultipleShortOptionsConcatenated_ShouldHandleEach_wOxB4() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = mock(Options.class);
    String[] arguments = {"-abc"};
    Properties properties = new Properties();
    when(options.hasShortOption("a")).thenReturn(true);
    when(options.hasShortOption("b")).thenReturn(false);
    when(options.hasShortOption("c")).thenReturn(true);
    parser.parse(options, arguments, properties, false);
    verify(options).hasShortOption("a");
    verify(options).hasShortOption("b");
    verify(options).hasShortOption("c");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_NullToken_utys0_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = {null}; // Test input with null token
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    assertNotNull(result);}

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_NullToken_utys0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = {null}; // Test input with null token
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    assertNotNull(result);
    assertTrue(result.getArgList().isEmpty()); // Expect no arguments processed
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_LongOptionPrefix_fkTj4() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("o", "option", false, "Test option");
    String[] arguments = {"-oValue"}; // Test input with long option prefix
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    assertNotNull(result);
    assertTrue(result.hasOption("o")); // Expect the option to be recognized
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_NullToken_Omyt0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = mock(Options.class);
    String[] arguments = {null}; // Test input with null token
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_ShortOptionExists_ahoT0() throws ParseException {
    Options options = mock(Options.class);
    when(options.hasShortOption("a")).thenReturn(true);
    when(options.getOption("a")).thenReturn(new Option("a", "alpha", true, "Alpha option"));
    String[] arguments = {"-a"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_HandleUnknownToken_dRad1() throws ParseException {
    Options options = mock(Options.class);
    when(options.hasShortOption("x")).thenReturn(false);
    String[] arguments = {"-x"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_JavaProperty_paSQ5() throws ParseException {
    Options options = mock(Options.class);
    when(options.hasShortOption("D")).thenReturn(true);
    Option opt = new Option("D", "define", true, "Define property");
    when(options.getOption("D")).thenReturn(opt);
    String[] arguments = {"-Dkey=value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_HandleConcatenatedOptions_VLAq8() throws ParseException {
    Options options = mock(Options.class);
    when(options.hasShortOption("a")).thenReturn(true);
    when(options.hasShortOption("b")).thenReturn(true);
    when(options.hasShortOption("c")).thenReturn(true);
    Option optA = new Option("a", "alpha", false, "Alpha option");
    Option optB = new Option("b", "beta", false, "Beta option");
    Option optC = new Option("c", "gamma", false, "Gamma option");
    when(options.getOption("a")).thenReturn(optA);
    when(options.getOption("b")).thenReturn(optB);
    when(options.getOption("c")).thenReturn(optC);
    String[] arguments = {"-abc"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_withOptionTokens_kFTe0() throws ParseException {
    Options mockOptions = mock(Options.class);
    when(mockOptions.getRequiredOptions()).thenReturn(new ArrayList<>());
    when(mockOptions.getOptionGroups()).thenReturn(new ArrayList<>());
    when(mockOptions.hasLongOption(anyString())).thenReturn(true);
    when(mockOptions.hasShortOption(anyString())).thenReturn(true);
    String[] arguments = {"--longOption", "-s"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(mockOptions, arguments, properties, false);
    verify(mockOptions).hasLongOption("longOption");
    verify(mockOptions).hasShortOption("s");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_CoversIsOption_NZGs0_1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", true, "Beta option with argument");
    String[] arguments = new String[]{"-a", "--beta=value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("a"));}

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_CoversIsOption_NZGs0_2() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", true, "Beta option with argument");
    String[] arguments = new String[]{"-a", "--beta=value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("beta"));}

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_CoversIsOption_NZGs0_3() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", true, "Beta option with argument");
    String[] arguments = new String[]{"-a", "--beta=value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("beta"));}

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testOptionValueNotYesTrueOr1_pXhN3() throws ParseException {
    Options options = mock(Options.class);
    Option opt = mock(Option.class);
    when(options.getOption("option")).thenReturn(opt);
    when(opt.hasArg()).thenReturn(false);
    Properties properties = new Properties();
    properties.setProperty("option", "no");
    DefaultParser parser = new DefaultParser();
    parser.parse(options, new String[]{}, properties, false);
    verify(opt, never()).processValue(anyString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testContinueOnInvalidBooleanValue_LqbL4() throws ParseException {
    Options options = mock(Options.class);
    Option opt = mock(Option.class);
    when(options.getOption("option")).thenReturn(opt);
    when(opt.hasArg()).thenReturn(false);
    Properties properties = new Properties();
    properties.setProperty("option", "no");
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, new String[]{}, properties, false);
    verify(opt, never()).processValue(anyString());
    verify(result, never()).addOption(opt);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testOptionWithEqualSignAcceptsArg_IfDl2() throws ParseException {
    Options options = new Options();
    Option opt = new Option("f", "file", true, "file to process");
    options.addOption(opt);
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"--file=value"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    assertEquals("value", cmd.getOptionValue("file"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testOptionWithEqualSignDoesNotAcceptArg_xkqm3() throws ParseException {
    Options options = new Options();
    options.addOption("a", "option", false, "option does not accept an argument");
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"--option=value"};
    try {
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    fail("Expected ParseException because option does not accept an argument");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleOptionSelectsFirstMatchingOption_QgMi4() throws ParseException {
    Options options = new Options();
    options.addOption("opt1", "option1", true, "first option");
    options.addOption("opt2", "option2", true, "second option");
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"--option1=value"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    assertEquals("value", cmd.getOptionValue("option1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleUnknownTokenWithNoMatchingOption_QPCf5() throws ParseException {
    Options options = new Options();
    DefaultParser parser = new DefaultParser();
    String[] arguments = {"--nonexistent"};
    try {
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    fail("Expected ParseException because of unknown option");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_ShortToken_CGKw1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = {"-"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_LongOptionWithEqualSign_vsFb2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("opt", "option", true, "An option");
    String[] arguments = {"--option=value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_LongOptionWithPrefixNotStartingWithDoubleDash_pyzZ4() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("o", "option", true, "An option");
    String[] arguments = {"-option=value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_EmptyToken_ShouldNotBeShortOption_UChx1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = mock(Options.class);
    String[] arguments = {"-"};
    Properties properties = new Properties();
    parser.parse(options, arguments, properties, false);
    verify(options, never()).hasShortOption(anyString());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_CoversIsOption_NZGs0() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", true, "Beta option with argument");
    String[] arguments = new String[]{"-a", "--beta=value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("a"));
    assertTrue(result.hasOption("beta"));
    assertEquals("value", result.getOptionValue("beta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_ShortToken_Dqcl1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = {"-"}; // Test input with short token
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    assertNotNull(result);
    assertTrue(result.getArgList().contains("-")); // Expect the short token to be treated as an argument
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_LongOptionMatch_kMHv2() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("opt", "option", false, "Test option");
    String[] arguments = {"--option"}; // Test input with matching long option
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    assertNotNull(result);
    assertTrue(result.hasOption("option")); // Expect the option to be recognized
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_LongOptionWithEquals_uItu3() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("opt", "option", true, "Test option");
    String[] arguments = {"--option=value"}; // Test input with long option and value
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    assertNotNull(result);
    assertEquals("value", result.getOptionValue("option")); // Expect the option value to be parsed
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_LongOptionPrefixWithoutDoubleDash_nZqb5() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("o", "option", false, "Test option");
    String[] arguments = {"-o"}; // Test input with long option prefix without double dash
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    assertNotNull(result);
    assertTrue(result.hasOption("o")); // Expect the option to be recognized
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_ShortToken_isCW1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = mock(Options.class);
    String[] arguments = {"-"}; // Test input with short token
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_OptionAcceptsArg_oukS4() throws ParseException {
    Options options = mock(Options.class);
    when(options.hasShortOption("o")).thenReturn(true);
    Option opt = new Option("o", "option", true, "Option with arg");
    when(options.getOption("o")).thenReturn(opt);
    String[] arguments = {"-o", "value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_ShortOptionWithEqualAndValue_sRnB6() throws ParseException {
    Options options = mock(Options.class);
    when(options.hasShortOption("s")).thenReturn(true);
    Option opt = new Option("s", "short", true, "Short option with value");
    when(options.getOption("s")).thenReturn(opt);
    String[] arguments = {"-s=value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_Dulm2() throws ParseException {
    Options options = new Options();
    options.addOption("c", "gamma", false, "Gamma option");
    String[] arguments = new String[]{"--unknown"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertFalse(result.hasOption("unknown"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_WithProperties_msVO3_1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = {"--alpha"};
    Properties properties = new Properties();
    properties.setProperty("alpha", "fromProperties");
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_WithProperties_msVO3_2() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = {"--alpha"};
    Properties properties = new Properties();
    properties.setProperty("alpha", "fromProperties");
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("fromProperties", result.getOptionValue("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNonOptionStop_ZrHs3_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("d", "delta", false, "Delta option without argument");
    String[] arguments = new String[]{"--delta", "non-option-arg"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertFalse(result.getArgList().contains("non-option-arg"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithAmbiguousPartialMatchingLongOption_WhIh2() {
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", false, "Beta option");
    String[] arguments = new String[]{"--a"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    assertThrows(AmbiguousOptionException.class, () -> {
    parser.parse(options, arguments, properties, false);
    });
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidLongOptionAndProperty_HNZM3_2() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = new String[]{"--alpha=value"};
    Properties properties = new Properties();
    properties.setProperty("alpha", "newValue");
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("newValue", result.getOptionProperties("alpha").getProperty("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithAmbiguousPartialMatching_kkXA3() {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", false, "Beta option");
    String[] arguments = new String[]{"--al"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected AmbiguousOptionException was not thrown.");
    } catch (ParseException e) {
    assertTrue(e instanceof AmbiguousOptionException);
    assertTrue(e.getMessage().contains("Ambiguous option: --al could be: [alpha, beta]"));
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnrecognizedOption_bdlr0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = {"--unknown"};
    Properties properties = new Properties();
    boolean stopAtNonOption = true;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    fail("Expected an UnrecognizedOptionException to be thrown");
    } catch (ParseException e) {
    assertTrue(e instanceof UnrecognizedOptionException);
    assertEquals("Unrecognized option: --unknown", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithAmbiguousLongOption_uRXN2() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", "config", false, "Config option");
    options.addOption("conf", "configure", false, "Configure option");
    String[] arguments = {"--conf"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    fail("Expected an AmbiguousOptionException to be thrown");
    } catch (ParseException e) {
    assertTrue(e instanceof AmbiguousOptionException);
    assertEquals("Ambiguous option: --conf", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithOptionAndProperties_vLGJ3() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("d", "data", true, "Data option");
    String[] arguments = {"--data"};
    Properties properties = new Properties();
    properties.setProperty("data", "somevalue");
    boolean stopAtNonOption = false;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    assertTrue(result.hasOption("data"));
    assertEquals("somevalue", result.getOptionValue("data"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithJavaPropertyOption_NEOI3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("D", true, "Java property");
    String[] arguments = new String[]{"-Dkey=value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionProperties("D").getProperty("key"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithJavaProperty_VDVS2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("D", true, "define properties");
    String[] arguments = new String[]{"-Dkey=value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("D"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_kRvg1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[]{"--unknown"};
    Properties properties = new Properties();
    boolean stopAtNonOption = true;
    try {
    parser.parse(options, arguments, properties, stopAtNonOption);
    fail("Expected ParseException");
    } catch (ParseException e) {
    assertNotNull(e);
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptionsIncludingUnknown_JWzr2() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", false, "Beta option");
    String[] arguments = {"-a", "--gamma", "-b"};
    Properties properties = new Properties();
    boolean stopAtNonOption = true;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    assertTrue(result.hasOption("a"));
    assertTrue(result.hasOption("b"));
    assertFalse(result.hasOption("gamma"));
    } catch (ParseException e) {
    assertTrue(e instanceof UnrecognizedOptionException);
    assertEquals("Unrecognized option: --gamma", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptionMissing_UzYy0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("r", "required", true, "A required option");
    String[] arguments = new String[]{};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties, false);
    fail("Expected a ParseException due to missing required option");
    } catch (ParseException e) {
    assertTrue(e instanceof MissingArgumentException);
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseStoppingAtNonOption_XpdY3() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("opt", false, "A simple option");
    String[] arguments = new String[]{"--opt", "non-option-arg"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("opt"));
    assertEquals(1, result.getArgList().size());
    assertEquals("non-option-arg", result.getArgList().get(0));
    } catch (ParseException e) {
    fail("ParseException should not have occurred");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptionsMissing_qLQh2() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("r", "required", true, "A required option");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException for missing required option");
    } catch (ParseException e) {
    assertTrue(e instanceof MissingArgumentException);
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptionsMissing_rIKk2() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("r", "required", true, "A required option");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    assertThrows(MissingArgumentException.class, () -> {
    parser.parse(options, arguments, properties, false);
    });
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseStopAtNonOption_Kyax0_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[]{"--option1", "value1", "--option2"};
    CommandLine result = parser.parse(options, arguments, true);
    assertTrue(result.hasOption("option1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseStopAtNonOption_Kyax0_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[]{"--option1", "value1", "--option2"};
    CommandLine result = parser.parse(options, arguments, true);
    assertEquals("value1", result.getOptionValue("option1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidOptionAndProperty_OJQQ2_gkzr0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("D", "define", true, "Define properties");
    String[] arguments = new String[]{"-Dkey=value"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    boolean stopAtNonOption = false;
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    assertNotNull(result);
    assertEquals("value", result.getOptionValue("D"));
    assertEquals("value", properties.getProperty("key"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidOptionAndProperties_bQYX2_ofuZ0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = new String[]{"--alpha=value"};
    Properties properties = new Properties();
    properties.setProperty("alpha", "newvalue");
    boolean stopAtNonOption = false;
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    assertNotNull(result);
    assertEquals("newvalue", result.getOptionValue("alpha"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithPropertiesAndStopAtNonOption_ItHs3_eSiI0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", "charlie", true, "Charlie option");
    String[] arguments = new String[]{"-c", "value", "non-option"};
    Properties properties = new Properties();
    properties.setProperty("charlie", "override");
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("charlie"));
    assertEquals("override", result.getOptionValue("charlie"));
    assertTrue(result.getArgList().contains("non-option"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithJavaProperty_gVuk2_LUIX0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("D", null, true, "Java property");
    String[] arguments = new String[]{"-Dkey=value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.hasOption("D"));
    assertEquals("value", result.getOptionProperties("D").getProperty("key"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_WithProperties_msVO3() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = {"--alpha"};
    Properties properties = new Properties();
    properties.setProperty("alpha", "fromProperties");
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("alpha"));
    assertEquals("fromProperties", result.getOptionValue("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNonOptionStop_ZrHs3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("d", "delta", false, "Delta option without argument");
    String[] arguments = new String[]{"--delta", "non-option-arg"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("delta"));
    assertFalse(result.getArgList().contains("non-option-arg"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidLongOptionAndProperty_HNZM3() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = new String[]{"--alpha=value"};
    Properties properties = new Properties();
    properties.setProperty("alpha", "newValue");
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("alpha"));
    assertEquals("newValue", result.getOptionProperties("alpha").getProperty("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptionsMissing_ymnh3() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("r", "required", true, "Required option");
    String[] arguments = new String[]{"-a"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    Exception exception = assertThrows(ParseException.class, () -> {
    parser.parse(options, arguments, properties, stopAtNonOption);
    });
    assertTrue(exception.getMessage().contains("Missing required option"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseStopAtNonOption_Kyax0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[]{"--option1", "value1", "--option2"};
    CommandLine result = parser.parse(options, arguments, true);
    assertTrue(result.hasOption("option1"));
    assertEquals("value1", result.getOptionValue("option1"));
    assertFalse(result.hasOption("option2"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArguments_EMaL0_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    CommandLine result = parser.parse(options, arguments);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArguments_EMaL0_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    CommandLine result = parser.parse(options, arguments);
    assertTrue(result.getOptions().length == 0);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArguments_VXoG0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = {};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.getOptions().length == 0);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_GmPr1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = {"--unknown"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    assertNotNull(e);
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidOption_HDhS2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("v", "verbose", false, "Enable verbose mode");
    String[] arguments = {"--verbose"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("verbose"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_knUj3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", "config", true, "Config file path");
    String[] arguments = {};
    Properties properties = new Properties();
    properties.setProperty("config", "path/to/config");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("path/to/config", result.getOptionValue("config"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_UpDR0() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--alpha"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_iVFg1() throws ParseException {
    Options options = new Options();
    options.addOption("b", "beta", false, "Beta option");
    String[] arguments = new String[]{"-b"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("b"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_rUbD2_1() throws ParseException {
    Options options = new Options();
    options.addOption("c", "charlie", false, "Charlie option");
    String[] arguments = new String[]{"-c", "nonOptionArg"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("c"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_rUbD2_2() throws ParseException {
    Options options = new Options();
    options.addOption("c", "charlie", false, "Charlie option");
    String[] arguments = new String[]{"-c", "nonOptionArg"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertArrayEquals(new String[]{"nonOptionArg"}, result.getArgs());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_CESv3() throws ParseException {
    Options options = new Options();
    options.addOption("d", "delta", true, "Delta option");
    String[] arguments = new String[]{};
    Properties properties = new Properties();
    properties.setProperty("delta", "value");
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("delta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_kQVp0() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--alpha"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_KbNk1() throws ParseException {
    Options options = new Options();
    options.addOption("b", "beta", false, "Beta option");
    String[] arguments = new String[]{"-b"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("b"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_CXMo3_1() throws ParseException {
    Options options = new Options();
    options.addOption("d", "delta", false, "Delta option");
    String[] arguments = new String[]{"-d", "non-option-arg"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("d"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_CXMo3_2() throws ParseException {
    Options options = new Options();
    options.addOption("d", "delta", false, "Delta option");
    String[] arguments = new String[]{"-d", "non-option-arg"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertEquals("non-option-arg", result.getArgs()[0]);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_AllOptionsProvided_hIvA0_1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    options.addOption("b", "beta", false, "Beta option");
    String[] arguments = {"--alpha=123", "--beta"};
    Properties properties = new Properties();
    properties.setProperty("alpha", "456");
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_AllOptionsProvided_hIvA0_2() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    options.addOption("b", "beta", false, "Beta option");
    String[] arguments = {"--alpha=123", "--beta"};
    Properties properties = new Properties();
    properties.setProperty("alpha", "456");
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("beta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_AllOptionsProvided_hIvA0_3() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    options.addOption("b", "beta", false, "Beta option");
    String[] arguments = {"--alpha=123", "--beta"};
    Properties properties = new Properties();
    properties.setProperty("alpha", "456");
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("123", result.getOptionValue("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_StopAtNonOption_faHc1_1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = {"--alpha=123", "nonOptionArg"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_StopAtNonOption_faHc1_2() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = {"--alpha=123", "nonOptionArg"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertEquals("123", result.getOptionValue("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_StopAtNonOption_faHc1_3() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = {"--alpha=123", "nonOptionArg"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertEquals("nonOptionArg", result.getArgs()[0]);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_WithNegativeNumber_VrmN2_1() throws ParseException {
    Options options = new Options();
    options.addOption("n", "number", true, "Number option");
    String[] arguments = {"--number", "-123"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("number"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_WithNegativeNumber_VrmN2_2() throws ParseException {
    Options options = new Options();
    options.addOption("n", "number", true, "Number option");
    String[] arguments = {"--number", "-123"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("-123", result.getOptionValue("number"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionWithoutEqual_lbnS0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option without argument");
    String[] arguments = new String[]{"--alpha"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionWithEqual_XJpa1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "beta", true, "Beta option with argument");
    String[] arguments = new String[]{"--beta=value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("beta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_iwlG2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", "charlie", true, "Charlie option with argument");
    String[] arguments = new String[]{"-c", "123"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("123", result.getOptionValue("c"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNonOptionStop_ZrHs3_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("d", "delta", false, "Delta option without argument");
    String[] arguments = new String[]{"--delta", "non-option-arg"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("delta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNoArguments_dscQ0_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.getOptions().length == 0);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNoArguments_dscQ0_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.getArgList().isEmpty());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_hcEv1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[]{"--unknown"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException for unknown option");
    } catch (ParseException e) {
    assertNotNull(e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidLongOption_snBh2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--alpha"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidLongOptionAndEqualSign_LqIG3_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--beta=value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("beta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidLongOptionAndEqualSign_LqIG3_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--beta=value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("beta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNoArgumentsAndNoProperties_UvUS0_1() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.getOptions().length == 0);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNoArgumentsAndNoProperties_UvUS0_2() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.getArgList().isEmpty());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownLongOption_gICQ1() {
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--unknown"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    assertThrows(ParseException.class, () -> {
    parser.parse(options, arguments, properties, false);
    });
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidLongOptionAndProperty_HNZM3_1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = new String[]{"--alpha=value"};
    Properties properties = new Properties();
    properties.setProperty("alpha", "newValue");
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnrecognizedOption_PEJw0_1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = {"-b"};
    Properties properties = new Properties();
    Exception exception = assertThrows(ParseException.class, () -> {
    parser.parse(options, arguments, properties, false);
    });
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithAmbiguousOption_rBkg2_1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("a", "alp", false, "Short for alpha");
    String[] arguments = {"--al"};
    Properties properties = new Properties();
    Exception exception = assertThrows(AmbiguousOptionException.class, () -> {
    parser.parse(options, arguments, properties, false);
    });
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_ZTwb1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--beta"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_agEg1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    try {
    CommandLine result = parser.parse(options, new String[]{"--beta"}, new Properties(), false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    assertTrue(e.getMessage().contains("Unrecognized option"));
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_Aluf1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--beta"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException was not thrown.");
    } catch (ParseException e) {
    assertTrue(e instanceof UnrecognizedOptionException);
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptionMissing_HRZF2() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("a", "alpha", true, "Alpha option");
    String[] arguments = new String[]{"--alpha"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException for missing argument.");
    } catch (ParseException e) {
    assertTrue(e instanceof MissingArgumentException);
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithPropertiesProvidingValues_zDqC3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("alpha", "value");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_nSgP2() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[]{"--unknown"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("ParseException expected due to unknown option");
    } catch (ParseException e) {
    assertTrue(e.getMessage().contains("unknown"));
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_tcdt3_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--alpha", "arg1", "--beta"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_tcdt3_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--alpha", "arg1", "--beta"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertFalse(result.hasOption("beta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_tcdt3_3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--alpha", "arg1", "--beta"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertEquals(2, result.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_tcdt3_4() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--alpha", "arg1", "--beta"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertEquals("arg1", result.getArgList().get(0));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_tcdt3_5() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--alpha", "arg1", "--beta"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertEquals("--beta", result.getArgList().get(1));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_gBtX2() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[]{"--unknownOption"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    assertTrue(e.getMessage().contains("Unrecognized option"));
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownLongOption_IrLX1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--beta"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException was not thrown.");
    } catch (ParseException e) {
    assertTrue(e.getMessage().contains("Unrecognized option: --beta"));
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithKnownLongOptionWithEqual_EWhU1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = {"--beta=value"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    assertTrue(result.hasOption("beta"));
    assertEquals("value", result.getOptionValue("beta"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_xBJJ1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = {"--beta"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    assertNotNull(e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_qDqj1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--beta"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException was not thrown");
    } catch (ParseException e) {
    assertEquals("Unrecognized option: --beta", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidLongOptionWithEqualSign_Kgku2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("t", "test", true, "Test option");
    String[] arguments = new String[]{"--test=value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("test"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptionsAndStopAtNonOption_DTfu3_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--alpha", "--beta=value", "non-option-arg"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptionsAndStopAtNonOption_DTfu3_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--alpha", "--beta=value", "non-option-arg"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertEquals("value", result.getOptionValue("beta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptionsAndStopAtNonOption_DTfu3_3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--alpha", "--beta=value", "non-option-arg"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.getArgList().contains("non-option-arg"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArgumentsAndNoProperties_fodu0_1() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArgumentsAndNoProperties_fodu0_2() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals(0, result.getOptions().length);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithSingleLongOption_gipp1_1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--alpha"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionAndValue_mNxB2_1() throws ParseException {
    Options options = new Options();
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--beta=value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionAndValue_mNxB2_2() throws ParseException {
    Options options = new Options();
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--beta=value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("beta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionAndValue_mNxB2_3() throws ParseException {
    Options options = new Options();
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--beta=value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("beta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptionsAndStopAtNonOption_wiDB3_1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--alpha", "--beta=value", "non-option-arg"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptionsAndStopAtNonOption_wiDB3_2() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--alpha", "--beta=value", "non-option-arg"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptionsAndStopAtNonOption_wiDB3_3() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--alpha", "--beta=value", "non-option-arg"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("beta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptionsAndStopAtNonOption_wiDB3_4() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--alpha", "--beta=value", "non-option-arg"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertEquals("value", result.getOptionValue("beta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptionsAndStopAtNonOption_wiDB3_5() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--alpha", "--beta=value", "non-option-arg"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertArrayEquals(new String[]{"non-option-arg"}, result.getArgs());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionEquals_lhiQ0_1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha description");
    String[] arguments = new String[]{"--alpha=value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionEquals_lhiQ0_2() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha description");
    String[] arguments = new String[]{"--alpha=value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_HuuX1() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--unknown"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException was not thrown.");
    } catch (ParseException e) {
    assertTrue(e.getMessage().contains("Unrecognized option"));
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_NmFz2() throws ParseException {
    Options options = new Options();
    options.addOption("b", "beta", false, "Beta description");
    String[] arguments = new String[]{"-b"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("b"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_JWRt3_1() throws ParseException {
    Options options = new Options();
    options.addOption("c", "charlie", true, "Charlie description");
    String[] arguments = new String[]{};
    Properties properties = new Properties();
    properties.setProperty("charlie", "value");
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("charlie"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_JWRt3_2() throws ParseException {
    Options options = new Options();
    options.addOption("c", "charlie", true, "Charlie description");
    String[] arguments = new String[]{};
    Properties properties = new Properties();
    properties.setProperty("charlie", "value");
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("charlie"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionWithoutEqual_fsom0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option without argument");
    String[] arguments = new String[]{"--alpha"};
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    assertTrue(result.hasOption("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionWithEqual_oCPC1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "beta", true, "Beta option with argument");
    String[] arguments = new String[]{"--beta=value"};
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    assertEquals("value", result.getOptionValue("beta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownToken_YwWK2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[]{"--gamma"};
    try {
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    fail("Expected ParseException was not thrown.");
    } catch (ParseException e) {
    assertTrue(e.getMessage().contains("Unrecognized option"));
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_zsNo3_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("d", "delta", false, "Delta option without argument");
    String[] arguments = new String[]{"--delta", "--", "non-option-arg"};
    CommandLine result = parser.parse(options, arguments, new Properties(), true);
    assertTrue(result.hasOption("delta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_zsNo3_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("d", "delta", false, "Delta option without argument");
    String[] arguments = new String[]{"--delta", "--", "non-option-arg"};
    CommandLine result = parser.parse(options, arguments, new Properties(), true);
    assertEquals("non-option-arg", result.getArgs()[0]);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_trzd0() throws ParseException {
    Options options = new Options();
    options.addOption("a", false, "activate something");
    String[] arguments = {"-a"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_SVSU1() throws ParseException {
    Options options = new Options();
    options.addOption("b", "build", false, "build something");
    String[] arguments = {"--build"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("b"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithOptionAndValue_UETl2_1() throws ParseException {
    Options options = new Options();
    options.addOption("c", "configure", true, "configure something");
    String[] arguments = {"--configure=fast"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("c"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithOptionAndValue_UETl2_2() throws ParseException {
    Options options = new Options();
    options.addOption("c", "configure", true, "configure something");
    String[] arguments = {"--configure=fast"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("fast", result.getOptionValue("c"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptions_LtVg3_1() throws ParseException {
    Options options = new Options();
    options.addOption("d", false, "debug mode");
    options.addOption("e", "execute", true, "execute something");
    String[] arguments = {"-d", "--execute=now"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("d"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptions_LtVg3_2() throws ParseException {
    Options options = new Options();
    options.addOption("d", false, "debug mode");
    options.addOption("e", "execute", true, "execute something");
    String[] arguments = {"-d", "--execute=now"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("e"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptions_LtVg3_3() throws ParseException {
    Options options = new Options();
    options.addOption("d", false, "debug mode");
    options.addOption("e", "execute", true, "execute something");
    String[] arguments = {"-d", "--execute=now"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("now", result.getOptionValue("e"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_WeTF0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", false, "option without argument");
    String[] arguments = {"-a"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionAndArgument_dPMK1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "buffer", true, "option with argument");
    String[] arguments = {"--buffer=1024"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("1024", result.getOptionValue("buffer"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptions_FWUI2_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", "create", false, "option without argument");
    options.addOption("d", "delete", false, "option without argument");
    String[] arguments = {"-c", "--delete"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("c"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptions_FWUI2_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", "create", false, "option without argument");
    options.addOption("d", "delete", false, "option without argument");
    String[] arguments = {"-c", "--delete"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("delete"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_sOqY3_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("e", "edit", true, "option with argument");
    String[] arguments = {"--edit", "file.txt", "extraArg"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertEquals("file.txt", result.getOptionValue("edit"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_sOqY3_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("e", "edit", true, "option with argument");
    String[] arguments = {"--edit", "file.txt", "extraArg"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.getArgList().contains("extraArg"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_bqID0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", false, "activate something");
    String[] arguments = {"-a"};
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    assertTrue(result.hasOption("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_nHtg1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "build", false, "build option");
    String[] arguments = {"--build"};
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    assertTrue(result.hasOption("b"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithOptionAndValue_Bmul2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", "configure", true, "configure option");
    String[] arguments = {"--configure=fast"};
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    assertEquals("fast", result.getOptionValue("c"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptions_BFPs3_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("d", "delete", false, "delete option");
    options.addOption("e", "edit", true, "edit option");
    String[] arguments = {"-d", "--edit=text"};
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    assertTrue(result.hasOption("d"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptions_BFPs3_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("d", "delete", false, "delete option");
    options.addOption("e", "edit", true, "edit option");
    String[] arguments = {"-d", "--edit=text"};
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    assertEquals("text", result.getOptionValue("e"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_JRtB1_1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"-b"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    Exception exception = assertThrows(ParseException.class, () -> {
    parser.parse(options, arguments, properties, stopAtNonOption);
    });
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptionsMissing_ymnh3_1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("r", "required", true, "Required option");
    String[] arguments = new String[]{"-a"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    Exception exception = assertThrows(ParseException.class, () -> {
    parser.parse(options, arguments, properties, stopAtNonOption);
    });
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_oCCr3() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = {"--unknown"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties, false);
    fail("ParseException expected");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnrecognizedOption_IZCR0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[]{"-x"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    fail("Expected an UnrecognizedOptionException to be thrown");
    } catch (ParseException e) {
    assertTrue(e instanceof UnrecognizedOptionException);
    assertEquals("Unrecognized option: -x", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithKnownOptionNoArgument_eFBc1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", false, "no argument");
    String[] arguments = new String[]{"-a"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    assertTrue(result.hasOption("a"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithOptionAndValue_FIaa2() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", true, "requires an argument");
    String[] arguments = new String[]{"-b", "value"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    assertTrue(result.hasOption("b"));
    assertEquals("value", result.getOptionValue("b"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_QUpy3() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", true, "requires an argument");
    String[] arguments = new String[]{};
    Properties properties = new Properties();
    properties.setProperty("c", "propValue");
    boolean stopAtNonOption = false;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    assertTrue(result.hasOption("c"));
    assertEquals("propValue", result.getOptionValue("c"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_DwZf2() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[]{"--unknown"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties, false);
    fail("ParseException expected due to unknown option");
    } catch (ParseException e) {
    assertTrue(e.getMessage().contains("unknown"));
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_YfWs1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--beta"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    fail("Expected ParseException");
    } catch (ParseException e) {
    assertNotNull(e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnrecognizedOption_kkfn0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"-b"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties, false);
    fail("Expected an UnrecognizedOptionException to be thrown");
    } catch (ParseException e) {
    assertTrue(e instanceof UnrecognizedOptionException);
    assertEquals("Unrecognized option: -b", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRecognizedOption_Rvjs1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"-a"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("a"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_QpUW2() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"-a", "foo"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("a"));
    assertEquals(1, result.getArgList().size());
    assertEquals("foo", result.getArgList().get(0));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_RcrX3() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = new String[]{};
    Properties properties = new Properties();
    properties.setProperty("alpha", "value");
    try {
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("a"));
    assertEquals("value", result.getOptionValue("a"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_tnAL1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--beta"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    parser.parse(options, arguments, properties, stopAtNonOption);
    fail("Expected ParseException for unknown option");
    } catch (ParseException e) {
    assertNotNull(e);
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptionMissing_iENi3() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("r", "required", true, "Required option");
    String[] arguments = new String[]{};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    parser.parse(options, arguments, properties, stopAtNonOption);
    fail("Expected ParseException due to missing required option");
    } catch (ParseException e) {
    assertNotNull(e);
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_DZfj1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--beta"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException for unknown option");
    } catch (ParseException e) {
    assertTrue(e.getMessage().contains("Unrecognized option"));
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_NoGI0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", false, "option without argument");
    String[] arguments = new String[]{"-a"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_WlTE1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("option", "longoption", false, "long option without argument");
    String[] arguments = new String[]{"--longoption"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("option"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithOptionAndValue_Owfk2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "optionb", true, "option with argument");
    String[] arguments = new String[]{"--optionb=value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("b"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithOptionAndValue_IQrd2() throws ParseException {
    Options options = new Options();
    options.addOption("c", "configure", true, "configure something");
    String[] arguments = {"--configure=setup"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("setup", result.getOptionValue("c"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptions_dAan3_1() throws ParseException {
    Options options = new Options();
    options.addOption("d", "delete", false, "delete something");
    options.addOption("e", "edit", true, "edit something");
    String[] arguments = {"-d", "--edit=text"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("d"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptions_dAan3_2() throws ParseException {
    Options options = new Options();
    options.addOption("d", "delete", false, "delete something");
    options.addOption("e", "edit", true, "edit something");
    String[] arguments = {"-d", "--edit=text"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("text", result.getOptionValue("e"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_njHy0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", false, "activate something");
    String[] arguments = new String[]{"-a"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_VMky1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "build", false, "build something");
    String[] arguments = new String[]{"--build"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("b"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_yFZz3_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", false, "check something");
    String[] arguments = new String[]{"-c", "file.txt"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("c"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_yFZz3_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", false, "check something");
    String[] arguments = new String[]{"-c", "file.txt"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertArrayEquals(new String[]{"file.txt"}, result.getArgs());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionAndEqualSign_KIut1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("option", "longoption", true, "set a value");
    String[] arguments = new String[]{"--longoption=value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("longoption"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_TJnU2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[]{"--unknown"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("ParseException expected due to unknown option");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_UdIY3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("D", "define", true, "define a property");
    String[] arguments = new String[]{};
    Properties properties = new Properties();
    properties.setProperty("define", "value");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("define"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownShortOption_qTPm0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = {"-b"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    fail("Expected an UnrecognizedOptionException to be thrown");
    } catch (ParseException e) {
    assertTrue(e instanceof UnrecognizedOptionException);
    assertEquals("Unrecognized option: -b", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidLongOptionWithEqualSign_BcbA1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("setting", "setting", true, "Setting option");
    String[] arguments = {"--setting=value"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    assertTrue(result.hasOption("setting"));
    assertEquals("value", result.getOptionValue("setting"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithPropertiesAndStopAtNonOption_GEOx3() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = {"-a", "value", "non-option-arg"};
    Properties properties = new Properties();
    properties.setProperty("alpha", "prop-value");
    boolean stopAtNonOption = true;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    assertTrue(result.hasOption("a"));
    assertEquals("value", result.getOptionValue("a"));
    assertEquals("non-option-arg", result.getArgs()[0]);
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_lyJA1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--beta"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    assertTrue(e.getMessage().contains("Unrecognized option"));
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_HKPV1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--unknown"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException for unknown option");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnrecognizedOption_gvDc0_1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[]{"-unknown"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    Exception exception = assertThrows(ParseException.class, () -> {
    parser.parse(options, arguments, properties, stopAtNonOption);
    });
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptionProvided_JsYV1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("r", "required", true, "A required option");
    String[] arguments = new String[]{"--required", "value"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("required"));
    assertEquals("value", result.getOptionValue("required"));
    } catch (ParseException e) {
    fail("ParseException should not have occurred");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithPropertiesProvidingValues_JkbE2() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("opt", true, "An option that can be set via properties");
    String[] arguments = new String[]{};
    Properties properties = new Properties();
    properties.setProperty("opt", "propValue");
    try {
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("opt"));
    assertEquals("propValue", result.getOptionValue("opt"));
    } catch (ParseException e) {
    fail("ParseException should not have occurred");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMissingRequiredArguments_dspK1_1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("b", "beta", true, "Requires an argument");
    String[] arguments = new String[]{"-b"};
    Properties properties = new Properties();
    Exception exception = assertThrows(ParseException.class, () -> {
    parser.parse(options, arguments, properties, false);
    });
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArgumentsAndEmptyProperties_DnQr0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    assertEquals(0, result.getOptions().length);
    } catch (ParseException e) {
    fail("ParseException thrown: " + e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnrecognizedOptionInProperties_sGst1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", false, "option without argument");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("b", "true"); // 'b' is not recognized
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException was not thrown.");
    } catch (ParseException e) {
    assertEquals("Default option wasn't defined", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidOptionInProperties_dcfu2() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", true, "option with argument");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("a", "value");
    try {
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    assertTrue(result.hasOption("a"));
    assertEquals("value", result.getOptionValue("a"));
    } catch (ParseException e) {
    fail("ParseException thrown: " + e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArgumentsAndStopAtNonOption_kQjW3() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", false, "option without argument");
    String[] arguments = new String[]{"-a", "non-option-arg"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertNotNull(result);
    assertTrue(result.hasOption("a"));
    assertArrayEquals(new String[]{"non-option-arg"}, result.getArgs());
    } catch (ParseException e) {
    fail("ParseException thrown: " + e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArgumentsAndNoProperties_qgub0_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = {};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArgumentsAndNoProperties_qgub0_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = {};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals(0, result.getOptions().length);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnrecognizedOptionInProperties_sBOf1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = {};
    Properties properties = new Properties();
    properties.setProperty("unrecognized", "true");
    assertThrows(ParseException.class, () -> {
    parser.parse(options, arguments, properties, false);
    });
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidOptionInProperties_quAu2_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("v", "verbose", false, "Verbose mode");
    String[] arguments = {};
    Properties properties = new Properties();
    properties.setProperty("verbose", "true");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidOptionInProperties_quAu2_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("v", "verbose", false, "Verbose mode");
    String[] arguments = {};
    Properties properties = new Properties();
    properties.setProperty("verbose", "true");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("verbose"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithPropertiesAndRequiredOptionNotProvided_ZfKW3() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("r", "required", true, "Required option");
    String[] arguments = {};
    Properties properties = new Properties();
    assertThrows(ParseException.class, () -> {
    parser.parse(options, arguments, properties, false);
    });
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArgumentsAndEmptyProperties_ZDqj0_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArgumentsAndEmptyProperties_ZDqj0_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals(0, result.getOptions().length);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnrecognizedOptionInProperties_bhyz1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", false, "toggle A");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("b", "true");
    assertThrows(ParseException.class, () -> {
    parser.parse(options, arguments, properties, false);
    });
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidOptionInProperties_vjuk2_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", false, "toggle A");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("a", "true");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidOptionInProperties_vjuk2_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", false, "toggle A");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("a", "true");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArgumentsAndStopAtNonOption_ozdJ3_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", true, "needs argument");
    String[] arguments = new String[]{"-a", "value", "--", "non-option"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArgumentsAndStopAtNonOption_ozdJ3_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", true, "needs argument");
    String[] arguments = new String[]{"-a", "value", "--", "non-option"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArgumentsAndStopAtNonOption_ozdJ3_3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", true, "needs argument");
    String[] arguments = new String[]{"-a", "value", "--", "non-option"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertEquals("value", result.getOptionValue("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArgumentsAndStopAtNonOption_ozdJ3_4() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", true, "needs argument");
    String[] arguments = new String[]{"-a", "value", "--", "non-option"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertArrayEquals(new String[]{"non-option"}, result.getArgs());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnrecognizedOptionInProperties_xgnB1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("unrecognized", "value");
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException for unrecognized option");
    } catch (ParseException e) {
    assertTrue(e.getMessage().contains("Default option wasn't defined"));
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidOptionInProperties_fTsg3_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("opt", true, "An option with an argument");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("opt", "value");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidOptionInProperties_fTsg3_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("opt", true, "An option with an argument");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("opt", "value");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidOptionInProperties_fTsg3_3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("opt", true, "An option with an argument");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("opt", "value");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnrecognizedOptionInProperties_EneI1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("unknown", "value");
    assertThrows(UnrecognizedOptionException.class, () -> {
    parser.parse(options, arguments, properties, false);
    });
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnrecognizedOptionInProperties_huOE1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("unrecognized", "true");
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException was not thrown.");
    } catch (ParseException e) {
    assertTrue(e.getMessage().contains("Default option wasn't defined"));
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptionsNotProvided_Tgrc2() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("r", "required", true, "A required option");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException was not thrown.");
    } catch (ParseException e) {
    assertTrue(e.getMessage().contains("Missing required option"));
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithAllRequiredOptionsProvidedViaProperties_ANZz3_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("r", "required", true, "A required option");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("required", "value");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithAllRequiredOptionsProvidedViaProperties_ANZz3_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("r", "required", true, "A required option");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("required", "value");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("required"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithAllRequiredOptionsProvidedViaProperties_ANZz3_3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("r", "required", true, "A required option");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("required", "value");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertEquals("value", result.getOptionValue("required"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseStopAtNonOption_Kyax0_3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[]{"--option1", "value1", "--option2"};
    CommandLine result = parser.parse(options, arguments, true);
    assertFalse(result.hasOption("option2"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArgumentsAndProperties_upFi0_1() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArgumentsAndProperties_upFi0_2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.getOptions().length == 0);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArgumentsAndProperties_upFi0_3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.getArgs().length == 0);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArguments_soTl0_wgOK0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    CommandLine result = null;
    try {
    result = parser.parse(options, arguments, properties);
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    assertNotNull(result);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithSingleLongOption_mFBS1_vTFW0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = {"--alpha"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.hasOption("alpha"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_FUUs2_uaNR0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = {"--beta", "value", "non-option-arg"};
    Properties properties = new Properties();
    CommandLine result = null;
    try {
    result = parser.parse(options, arguments, properties, true);
    } catch (ParseException e) {
    fail("ParseException thrown");
    }
    assertTrue(result.hasOption("beta"));
    assertEquals("value", result.getOptionValue("beta"));
    assertEquals(1, result.getArgList().size());
    assertEquals("non-option-arg", result.getArgList().get(0));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_hAon3_IEXA0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", "charlie", true, "Charlie option");
    String[] arguments = new String[]{"-c", "value"};
    Properties properties = new Properties();
    properties.setProperty("charlie", "default");
    CommandLine result = parser.parse(options, arguments, properties);
    assertNotNull(result);
    assertEquals("value", result.getOptionValue("charlie"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArguments_ZKyf0_fdQS0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    CommandLine result = null;
    try {
    result = parser.parse(options, arguments, properties);
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    assertTrue(result.getOptions().length == 0);
    assertTrue(result.getArgList().isEmpty());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNonOptionArguments_jCNU1_KjgU0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[]{"arg1", "arg2"};
    Properties properties = new Properties();
    CommandLine result = null;
    try {
    result = parser.parse(options, arguments, properties);
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    assertFalse(result.hasOption("arg1"));
    assertEquals(Arrays.asList("arg1", "arg2"), result.getArgList());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithOptionArguments_rZAq2_HANW0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", false, "disable something");
    String[] arguments = new String[]{"-a"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.hasOption("a"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_OMby3_UhzE0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", true, "set a value");
    String[] arguments = new String[]{"-b", "value"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties);
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArguments_olyu0_rDIX0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.getArgList().isEmpty());
    assertTrue(result.getOptions().length == 0);
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_xdvJ1_OPVy0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "longOption", false, "description");
    String[] arguments = new String[]{"--longOption"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.hasOption("longOption"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_qdCy2_auWP0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "shortOption", false, "description");
    String[] arguments = new String[]{"-b"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.hasOption("b"));
    } catch (ParseException e) {
    fail("ParseException was thrown.");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_yDWJ3_wvtf0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", "optionWithArg", true, "description");
    String[] arguments = new String[]{"-c", "value", "nonOptionArg"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("c"));
    assertEquals("value", result.getOptionValue("c"));
    assertEquals(1, result.getArgList().size());
    assertEquals("nonOptionArg", result.getArgList().get(0));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOptions_wZHh0_zlEt0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", false, "option a");
    options.addOption("b", true, "option b");
    String[] arguments = {"-a", "-b", "value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.hasOption("a"));
    assertTrue(result.hasOption("b"));
    assertEquals("value", result.getOptionValue("b"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptions_JgiQ1_HWKj0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "option alpha");
    options.addOption("b", "beta", true, "option beta");
    String[] arguments = {"--alpha", "--beta=value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.hasOption("alpha"));
    assertTrue(result.hasOption("beta"));
    assertEquals("value", result.getOptionValue("beta"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_aFXR2_AZXO0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", false, "option a");
    String[] arguments = {"-a", "nonOptionArg"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("a"));
    assertEquals(1, result.getArgList().size());
    assertEquals("nonOptionArg", result.getArgList().get(0));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_Myrk3_rdQD0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", true, "option a");
    String[] arguments = new String[]{"-a", "value"};
    Properties properties = new Properties();
    properties.setProperty("a", "propertyValue");
    CommandLine result = parser.parse(options, arguments, properties);
    assertNotNull(result);
    assertEquals("value", result.getOptionValue("a"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownToken_iKeW1_XwaB0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = {"unknown"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.getArgList().contains("unknown"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_MFtB3_eXyw0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"-b", "value"};
    Properties properties = new Properties();
    properties.setProperty("beta", "default");
    CommandLine result = parser.parse(options, arguments, properties);
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithKnownOption_BxBq1_RTnG0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = {"--alpha"};
    Properties properties = new Properties();
    try {
    CommandLine cmd = parser.parse(options, arguments, properties);
    assertTrue(cmd.hasOption("alpha"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_lgTo3_JZSY0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = {"--alpha", "randomArg"};
    Properties properties = new Properties();
    CommandLine cmd = parser.parse(options, arguments, properties, true);
    assertTrue(cmd.getArgList().contains("randomArg"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNoArguments_Iqtb0_esHR0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertNotNull(result);
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_USJE3_uQXQ0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = new String[]{"-a", "value"};
    Properties properties = new Properties();
    CommandLine result = null;
    try {
    result = parser.parse(options, arguments, properties);
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    assertNotNull(result);
    assertTrue(result.hasOption("a"));
    assertEquals("value", result.getOptionValue("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNoArguments_rGRq0_IwBu0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    try {
    CommandLine result = parser.parse(options, new String[]{}, new Properties(), false);
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidLongOption_tknF2_JPOO0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    try {
    CommandLine result = parser.parse(options, new String[]{"--alpha=value"}, new Properties(), false);
    assertEquals("value", result.getOptionValue("alpha"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithPropertiesAndStopAtNonOption_xksN3_EsJA0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    Properties props = new Properties();
    props.setProperty("alpha", "fromProps");
    CommandLine result = parser.parse(options, new String[]{"--alpha=value", "nonOptionArg"}, props, true);
    assertEquals("value", result.getOptionValue("alpha"));
    assertTrue(result.getArgList().contains("nonOptionArg"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArguments_snKr0_PpeD0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.getOptions().length == 0);
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionWithEqualSign_NpVc1_KboJ0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("test", "testOption", true, "Test option");
    String[] arguments = new String[]{"--testOption=value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertEquals("value", result.getOptionValue("testOption"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_ciMs3_Vckg0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"-a", "nonOptionArg"};
    Properties properties = new Properties();
    CommandLine result = null;
    try {
    result = parser.parse(options, arguments, properties, true);
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    assertTrue(result.hasOption("a"));
    assertEquals(1, result.getArgs().length);
    assertEquals("nonOptionArg", result.getArgs()[0]);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidLongOptionWithEqualSign_Rtmb2_WJGa0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = new String[]{"--alpha=value"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties);
    assertEquals("value", result.getOptionValue("alpha"));
    } catch (ParseException e) {
    fail(e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArguments_lDCG0_tcZy0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties);
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidLongOptionWithEqual_dNCv2_xwGT0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("t", "test", true, "Test option");
    String[] arguments = {"--test=value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertEquals("value", result.getOptionValue("test"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptionsAndStopAtNonOption_XpWx3_xRBM0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = {"--alpha", "--beta=value", "nonOptionArg"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.hasOption("alpha"));
    assertEquals("value", result.getOptionValue("beta"));
    assertTrue(result.getArgList().contains("nonOptionArg"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArguments_XoXD0_xKBp0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    assertNotNull(result);
    assertTrue(result.getOptions().length == 0);
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidShortOption_vcRr1_RLcR0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = {"-a"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.hasOption("a"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidLongOption_wSjr2_ErDO0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = {"--beta=value"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties);
    assertEquals("value", result.getOptionValue("beta"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_eoro2_FwNL0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", "config", true, "option with argument");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("config", "configFile");
    try {
    CommandLine result = parser.parse(options, arguments, properties);
    assertEquals("configFile", result.getOptionValue("config"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_syLz3_ZKKw0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("d", false, "option without argument");
    String[] arguments = {"-d", "--", "nonOptionArg"};
    Properties properties = new Properties();
    CommandLine result = null;
    try {
    result = parser.parse(options, arguments, properties, true);
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    assertTrue(result.hasOption("d"));
    assertEquals("nonOptionArg", result.getArgs()[0]);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_ymPn0_PGJO0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("l", "longOption", false, "A long option without argument");
    String[] arguments = new String[]{"--longOption"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.hasOption("longOption"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOptionAndValue_AShR1_tWKK0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("s", "shortOption", true, "A short option with argument");
    String[] arguments = new String[]{"-svalue"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties);
    assertEquals("value", result.getOptionValue("shortOption"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_uJSb3_Ujhb0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("p", "propertyOption", true, "An option that can be set via properties");
    String[] arguments = new String[]{"-p", "value"};
    Properties properties = new Properties();
    properties.setProperty("propertyOption", "defaultValue");
    CommandLine result = parser.parse(options, arguments, properties);
    assertNotNull(result);
    assertEquals("value", result.getOptionValue("p"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArguments_bvgU0_KtQK0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine result = null;
    try {
    result = parser.parse(options, arguments, properties, stopAtNonOption);
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    assertNotNull(result);
    assertEquals(0, result.getOptions().length);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidLongOption_lfmR2_FUGr0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = new String[]{"--alpha=value"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    assertNotNull(result);
    assertTrue(result.hasOption("alpha"));
    assertEquals("value", result.getOptionValue("alpha"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_xeHu3_fRIB0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = new String[]{"-a", "value"};
    Properties properties = new Properties();
    properties.setProperty("alpha", "true");
    CommandLine result = null;
    try {
    result = parser.parse(options, arguments, properties);
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    assertNotNull(result);
    assertTrue(result.hasOption("a"));
    assertEquals("value", result.getOptionValue("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_rYGg0_govr0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", false, "activate something");
    String[] arguments = {"-a"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.hasOption("a"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_FsjS1_CyYq0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "build", false, "build something");
    String[] arguments = {"--build"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.hasOption("build"));
    } catch (ParseException e) {
    fail(e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithOptionAndValue_WNpQ2_CMCn0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", "configure", true, "configure something");
    String[] arguments = {"--configure=fast"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties);
    assertEquals("fast", result.getOptionValue("configure"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptions_GIaz3_BCad0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("d", "delete", false, "delete something");
    options.addOption("e", "edit", true, "edit something");
    String[] arguments = {"-d", "--edit=text"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.hasOption("delete"));
    assertEquals("text", result.getOptionValue("edit"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_gmsg3_QsDk0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = new String[]{"-a", "value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertNotNull(result);
    assertTrue(result.hasOption("a"));
    assertEquals("value", result.getOptionValue("a"));
    } catch (ParseException e) {
    fail("ParseException should not occur");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_xwLo0_JaHk0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", false, "activate something");
    String[] arguments = new String[]{"-a"};
    try {
    CommandLine result = parser.parse(options, arguments, new Properties());
    assertTrue(result.hasOption("a"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_OWFh1_dlhR0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "build", false, "build something");
    String[] arguments = new String[]{"--build"};
    CommandLine result = null;
    try {
    result = parser.parse(options, arguments, new Properties());
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    assertTrue(result.hasOption("b"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithOptionAndValue_NhHy2_sKEA0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", "configure", true, "configure something");
    String[] arguments = new String[]{"--configure=123"};
    CommandLine result = parser.parse(options, arguments, new Properties());
    assertEquals("123", result.getOptionValue("c"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptions_wABL3_ZvaS0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("x", false, "option x");
    options.addOption("y", false, "option y");
    String[] arguments = new String[]{"-x", "-y"};
    try {
    CommandLine result = parser.parse(options, arguments, new Properties());
    assertTrue(result.hasOption("x"));
    assertTrue(result.hasOption("y"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidLongOption_atfL2_gpFw0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--alpha"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    assertNotNull(result);
    assertTrue(result.hasOption("alpha"));
    } catch (ParseException e) {
    fail("ParseException should not occur");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_oNgh3_bfLu0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = {"-b", "value"};
    Properties properties = new Properties();
    properties.setProperty("beta", "defaultBeta");
    CommandLine result = parser.parse(options, arguments, properties);
    assertNotNull(result);
    assertEquals("value", result.getOptionValue("beta"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithSingleShortOptionAndValue_kRFx2_KzvN0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"-b", "value"};
    Properties properties = new Properties();
    CommandLine result = null;
    try {
    result = parser.parse(options, arguments, properties);
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    assertTrue(result.hasOption("beta"));
    assertEquals("value", result.getOptionValue("beta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithOneOptionAndNoArguments_wYks1_LNAO0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    try {
    CommandLine result = parser.parse(options, new String[]{"-a"}, new Properties());
    assertTrue(result.hasOption("a"));
    assertNull(result.getOptionValue("a"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithOneOptionAndArguments_EJLO2_ERGW0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "beta", true, "Beta option");
    try {
    CommandLine result = parser.parse(options, new String[]{"--beta", "123"}, new Properties());
    assertTrue(result.hasOption("beta"));
    assertEquals("123", result.getOptionValue("beta"));
    } catch (ParseException e) {
    fail("ParseException should not occur");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptionsAndStopAtNonOption_kUjQ3_gFdm0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", "gamma", false, "Gamma option");
    options.addOption("d", "delta", true, "Delta option");
    CommandLine result = parser.parse(options, new String[]{"-c", "--delta", "456", "non-option"}, new Properties(), true);
    assertTrue(result.hasOption("c"));
    assertTrue(result.hasOption("delta"));
    assertEquals("456", result.getOptionValue("delta"));
    assertEquals(1, result.getArgList().size());
    assertEquals("non-option", result.getArgList().get(0));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionAndValue_FVtZ1_HiTG0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("setup", "setup", true, "setup configuration");
    String[] arguments = new String[]{"--setup=fast"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertEquals("fast", result.getOptionValue("setup"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptionsAndStopAtNonOption_ZKqc2_ZIkY0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("v", "verbose", false, "be more verbose");
    options.addOption("c", "config", true, "config file");
    String[] arguments = new String[]{"-v", "--config", "myconfig.cfg", "extraArg"};
    Properties properties = new Properties();
    CommandLine result = null;
    try {
    result = parser.parse(options, arguments, properties);
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    assertTrue(result.hasOption("v"));
    assertEquals("myconfig.cfg", result.getOptionValue("config"));
    assertTrue(result.getArgList().contains("extraArg"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithPropertiesAndRequiredOptions_ldQk3_IedL0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("u", "username", true, "user name");
    options.addRequiredOption("p", "password", true, "user password");
    String[] arguments = new String[]{"--username=user"};
    Properties properties = new Properties();
    properties.setProperty("password", "pass123");
    CommandLine result = parser.parse(options, arguments, properties);
    assertEquals("user", result.getOptionValue("username"));
    assertEquals("pass123", result.getOptionValue("password"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_LYHI1_XOEg0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[]{"-valid", "arg1", "-unknown"};
    Properties properties = new Properties();
    boolean stopAtNonOption = true;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    assertNotNull(result);
    assertTrue(result.getArgList().contains("arg1"));
    assertTrue(result.getArgList().contains("-unknown"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidOptions_iQqE2_hGRP0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("v", "verbose", false, "Verbose mode");
    String[] arguments = new String[]{"--verbose"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    CommandLine result = parser.parse(options, arguments, properties, stopAtNonOption);
    assertNotNull(result);
    assertTrue(result.hasOption("verbose"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_eTec3_jNrU0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", "config", true, "Config file path");
    String[] arguments = new String[]{"-c", "path/to/config"};
    Properties properties = new Properties();
    properties.setProperty("config", "path/to/config");
    CommandLine result = parser.parse(options, arguments, properties);
    assertNotNull(result);
    assertTrue(result.hasOption("c"));
    assertEquals("path/to/config", result.getOptionValue("c"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredArguments_TDQy0_HjzO0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("a", "alpha", true, "Requires an argument");
    String[] arguments = new String[]{"-a", "value"};
    Properties properties = new Properties();
    try {
    CommandLine result = parser.parse(options, arguments, properties);
    assertTrue(result.hasOption("a"));
    assertEquals("value", result.getOptionValue("a"));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_LjTA3_pGZt0() {
    try {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", "charlie", false, "No argument option");
    String[] arguments = new String[]{"-c", "--", "-d"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("c"));
    assertFalse(result.hasOption("d"));
    assertEquals(1, result.getArgList().size());
    assertEquals("-d", result.getArgList().get(0));
    } catch (ParseException e) {
    fail("ParseException should not be thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArguments_EMaL0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    CommandLine result = parser.parse(options, arguments);
    assertNotNull(result);
    assertTrue(result.getOptions().length == 0);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_rUbD2() throws ParseException {
    Options options = new Options();
    options.addOption("c", "charlie", false, "Charlie option");
    String[] arguments = new String[]{"-c", "nonOptionArg"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("c"));
    assertArrayEquals(new String[]{"nonOptionArg"}, result.getArgs());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_CXMo3() throws ParseException {
    Options options = new Options();
    options.addOption("d", "delta", false, "Delta option");
    String[] arguments = new String[]{"-d", "non-option-arg"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("d"));
    assertEquals("non-option-arg", result.getArgs()[0]);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_AllOptionsProvided_hIvA0() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    options.addOption("b", "beta", false, "Beta option");
    String[] arguments = {"--alpha=123", "--beta"};
    Properties properties = new Properties();
    properties.setProperty("alpha", "456");
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("alpha"));
    assertTrue(result.hasOption("beta"));
    assertEquals("123", result.getOptionValue("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_StopAtNonOption_faHc1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha option");
    String[] arguments = {"--alpha=123", "nonOptionArg"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("alpha"));
    assertEquals("123", result.getOptionValue("alpha"));
    assertEquals("nonOptionArg", result.getArgs()[0]);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_WithNegativeNumber_VrmN2() throws ParseException {
    Options options = new Options();
    options.addOption("n", "number", true, "Number option");
    String[] arguments = {"--number", "-123"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("number"));
    assertEquals("-123", result.getOptionValue("number"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidLongOptionAndEqualSign_LqIG3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--beta=value"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("beta"));
    assertEquals("value", result.getOptionValue("beta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNoArgumentsAndNoProperties_UvUS0() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.getOptions().length == 0);
    assertTrue(result.getArgList().isEmpty());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnrecognizedOption_PEJw0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = {"-b"};
    Properties properties = new Properties();
    Exception exception = assertThrows(ParseException.class, () -> {
    parser.parse(options, arguments, properties, false);
    });
    assertTrue(exception.getMessage().contains("Unrecognized option"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithAmbiguousOption_rBkg2() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("a", "alp", false, "Short for alpha");
    String[] arguments = {"--al"};
    Properties properties = new Properties();
    Exception exception = assertThrows(AmbiguousOptionException.class, () -> {
    parser.parse(options, arguments, properties, false);
    });
    assertTrue(exception.getMessage().contains("Ambiguous option"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_tcdt3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--alpha", "arg1", "--beta"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("alpha"));
    assertFalse(result.hasOption("beta"));
    assertEquals(2, result.getArgList().size());
    assertEquals("arg1", result.getArgList().get(0));
    assertEquals("--beta", result.getArgList().get(1));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptionsAndStopAtNonOption_DTfu3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--alpha", "--beta=value", "non-option-arg"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("alpha"));
    assertEquals("value", result.getOptionValue("beta"));
    assertTrue(result.getArgList().contains("non-option-arg"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArgumentsAndNoProperties_fodu0() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    assertEquals(0, result.getOptions().length);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithSingleLongOption_gipp1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"--alpha"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    assertTrue(result.hasOption("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionAndValue_mNxB2() throws ParseException {
    Options options = new Options();
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--beta=value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    assertTrue(result.hasOption("beta"));
    assertEquals("value", result.getOptionValue("beta"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptionsAndStopAtNonOption_wiDB3() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    options.addOption("b", "beta", true, "Beta option");
    String[] arguments = new String[]{"--alpha", "--beta=value", "non-option-arg"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertNotNull(result);
    assertTrue(result.hasOption("alpha"));
    assertTrue(result.hasOption("beta"));
    assertEquals("value", result.getOptionValue("beta"));
    assertArrayEquals(new String[]{"non-option-arg"}, result.getArgs());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionEquals_lhiQ0() throws ParseException {
    Options options = new Options();
    options.addOption("a", "alpha", true, "Alpha description");
    String[] arguments = new String[]{"--alpha=value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("alpha"));
    assertEquals("value", result.getOptionValue("alpha"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_JWRt3() throws ParseException {
    Options options = new Options();
    options.addOption("c", "charlie", true, "Charlie description");
    String[] arguments = new String[]{};
    Properties properties = new Properties();
    properties.setProperty("charlie", "value");
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("charlie"));
    assertEquals("value", result.getOptionValue("charlie"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_zsNo3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("d", "delta", false, "Delta option without argument");
    String[] arguments = new String[]{"--delta", "--", "non-option-arg"};
    CommandLine result = parser.parse(options, arguments, new Properties(), true);
    assertTrue(result.hasOption("delta"));
    assertEquals("non-option-arg", result.getArgs()[0]);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithOptionAndValue_UETl2() throws ParseException {
    Options options = new Options();
    options.addOption("c", "configure", true, "configure something");
    String[] arguments = {"--configure=fast"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("c"));
    assertEquals("fast", result.getOptionValue("c"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptions_LtVg3() throws ParseException {
    Options options = new Options();
    options.addOption("d", false, "debug mode");
    options.addOption("e", "execute", true, "execute something");
    String[] arguments = {"-d", "--execute=now"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("d"));
    assertTrue(result.hasOption("e"));
    assertEquals("now", result.getOptionValue("e"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptions_FWUI2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", "create", false, "option without argument");
    options.addOption("d", "delete", false, "option without argument");
    String[] arguments = {"-c", "--delete"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("c"));
    assertTrue(result.hasOption("delete"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_sOqY3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("e", "edit", true, "option with argument");
    String[] arguments = {"--edit", "file.txt", "extraArg"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertEquals("file.txt", result.getOptionValue("edit"));
    assertTrue(result.getArgList().contains("extraArg"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptions_BFPs3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("d", "delete", false, "delete option");
    options.addOption("e", "edit", true, "edit option");
    String[] arguments = {"-d", "--edit=text"};
    CommandLine result = parser.parse(options, arguments, new Properties(), false);
    assertTrue(result.hasOption("d"));
    assertEquals("text", result.getOptionValue("e"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_JRtB1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", "alpha", false, "Alpha option");
    String[] arguments = new String[]{"-b"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    Exception exception = assertThrows(ParseException.class, () -> {
    parser.parse(options, arguments, properties, stopAtNonOption);
    });
    assertTrue(exception.getMessage().contains("Unrecognized option"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleOptions_dAan3() throws ParseException {
    Options options = new Options();
    options.addOption("d", "delete", false, "delete something");
    options.addOption("e", "edit", true, "edit something");
    String[] arguments = {"-d", "--edit=text"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertTrue(result.hasOption("d"));
    assertEquals("text", result.getOptionValue("e"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_yFZz3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("c", false, "check something");
    String[] arguments = new String[]{"-c", "file.txt"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertTrue(result.hasOption("c"));
    assertArrayEquals(new String[]{"file.txt"}, result.getArgs());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnrecognizedOption_gvDc0() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[]{"-unknown"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    Exception exception = assertThrows(ParseException.class, () -> {
    parser.parse(options, arguments, properties, stopAtNonOption);
    });
    assertTrue(exception.getMessage().contains("Unrecognized option"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMissingRequiredArguments_dspK1() {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("b", "beta", true, "Requires an argument");
    String[] arguments = new String[]{"-b"};
    Properties properties = new Properties();
    Exception exception = assertThrows(ParseException.class, () -> {
    parser.parse(options, arguments, properties, false);
    });
    assertTrue(exception instanceof MissingArgumentException);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArgumentsAndNoProperties_qgub0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = {};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    assertEquals(0, result.getOptions().length);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidOptionInProperties_quAu2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("v", "verbose", false, "Verbose mode");
    String[] arguments = {};
    Properties properties = new Properties();
    properties.setProperty("verbose", "true");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    assertTrue(result.hasOption("verbose"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArgumentsAndEmptyProperties_ZDqj0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    assertEquals(0, result.getOptions().length);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidOptionInProperties_vjuk2() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", false, "toggle A");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("a", "true");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    assertTrue(result.hasOption("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArgumentsAndStopAtNonOption_ozdJ3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("a", true, "needs argument");
    String[] arguments = new String[]{"-a", "value", "--", "non-option"};
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties, true);
    assertNotNull(result);
    assertTrue(result.hasOption("a"));
    assertEquals("value", result.getOptionValue("a"));
    assertArrayEquals(new String[]{"non-option"}, result.getArgs());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidOptionInProperties_fTsg3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption("opt", true, "An option with an argument");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("opt", "value");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    assertTrue(result.hasOption("opt"));
    assertEquals("value", result.getOptionValue("opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithAllRequiredOptionsProvidedViaProperties_ANZz3() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    options.addRequiredOption("r", "required", true, "A required option");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    properties.setProperty("required", "value");
    CommandLine result = parser.parse(options, arguments, properties, false);
    assertNotNull(result);
    assertTrue(result.hasOption("required"));
    assertEquals("value", result.getOptionValue("required"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithEmptyArgumentsAndProperties_upFi0() throws ParseException {
    DefaultParser parser = new DefaultParser();
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    CommandLine result = parser.parse(options, arguments, properties);
    assertNotNull(result);
    assertTrue(result.getOptions().length == 0);
    assertTrue(result.getArgs().length == 0);
    }
}