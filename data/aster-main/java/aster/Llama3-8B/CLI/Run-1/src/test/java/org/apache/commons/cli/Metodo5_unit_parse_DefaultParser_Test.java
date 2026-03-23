/**
 * Filtered unit tests for method: parse(final Options options, final String[] arguments, final Properties properties, final boolean stopAtNonOption)
 * Original class: DefaultParser
 * Tests that actually call the target method
 */
package org.apache.commons.cli;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo5_unit_parse_DefaultParser_Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionWithoutEqual_Wdac0() throws ParseException {
    Options options = new Options();
    options.addOption("longOption", "longOption", true, "description");
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = new String[]{"--longOption"};
    CommandLine commandLine = parser.parse(options, arguments, new Properties(), false);
    assertEquals("description", commandLine.getParsedOptionValue("longOption"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMatchingLongOptions_eEnG2() throws ParseException {
    Options options = new Options();
    options.addOption("L", "L", true, "L option");
    String[] arguments = new String[]{"--L", "--L=V"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutMatchingLongOptions_bhbn3() throws ParseException {
    Options options = new Options();
    options.addOption("L", "L", true, "L option");
    String[] arguments = new String[]{"--M", "--M=V"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArrayList_KcHH4() throws ParseException {
    Options options = new Options();
    options.addOption("L", "L", true, "L option");
    String[] arguments = new String[]{"--L", "--L=V", "--L=V", "--L=V"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleLongOption_dFpy2() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"--option", "arg1", "arg2"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(2, commandLine.getArgs().length);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleShortAndLongOption_JzGn3() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"-o", "arg1", "arg2"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(2, commandLine.getArgs().length);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleUnknownToken_qesK4() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"unknown", "arg1", "arg2"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(2, commandLine.getArgs().length);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleLongOption_BfXv2() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"--opt", "arg1", "arg2"};
    CommandLine result = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(2, result.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleShortAndLongOption_gRIM3() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"-o", "--opt", "arg1", "arg2"};
    CommandLine result = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(3, result.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleUnknownToken_jiFZ4() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"unknown", "arg1", "arg2"};
    CommandLine result = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(2, result.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleShortOption_hLxi5() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"-o", "arg1", "arg2"};
    CommandLine result = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(2, result.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleLongOption_IXID2() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"--opt", "arg1", "arg2"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(2, commandLine.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleShortAndLongOption_UhPt3() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"-o", "arg1", "arg2"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(2, commandLine.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleUnknownToken_FxXB6() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(new Options(), new String[]{"unknown"}, new Properties(), true);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptions_AqAw3_2() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("L", "longOption", true, "long option description");
    String[] arguments = {"-L", "arg1", "arg2"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    assertEquals("arg2", cmd.getOptionValue("L", ""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithRequiredOptions_BHsa4_2() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addRequiredOption("L", "longOption", true, "long option description");
    String[] arguments = {"-L", "arg1", "arg2"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    assertEquals("arg2", cmd.getOptionValue("L", ""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithNoArguments_NsNj5() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("L", "longOption", true, "long option description");
    String[] arguments = {"-L"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    assertEquals("true", cmd.getOptionValue("L", ""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithNoMatchingOptions_QpGS6_1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"-X", "arg1", "arg2"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    assertEquals("arg1", cmd.getArgList().get(0));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithNoMatchingOptions_QpGS6_2() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"-X", "arg1", "arg2"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    assertEquals("arg2", cmd.getArgList().get(1));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithProperties_Bgoa7_1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("L", "longOption", true, "long option description");
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    String[] arguments = {"-L", "arg1", "arg2"};
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("arg1", cmd.getOptionValue("L", ""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithProperties_Bgoa7_2() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("L", "longOption", true, "long option description");
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    String[] arguments = {"-L", "arg1", "arg2"};
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("arg2", cmd.getOptionValue("L", ""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithProperties_Bgoa7_3() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("L", "longOption", true, "long option description");
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    String[] arguments = {"-L", "arg1", "arg2"};
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("value", cmd.getOptionProperties("L").getProperty("key"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithStopAtNonOption_PWhc8_2() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("L", "longOption", true, "long option description");
    String[] arguments = {"-L", "arg1", "arg2", "non-option", "arg3"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), true);
    assertEquals("arg2", cmd.getOptionValue("L", ""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionWithoutEqual_kOcy0() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--test"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionWithEqual_qmvj1() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--test=value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_llul2() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"-t"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOptionAndValue_VpNE3() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"-t", "value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptionAndValue_yNwn7() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--test", "value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleLongOptionWithEqual_EmptyMatchingOpts_aJdB0() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = new String[]{"--test"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine commandLine = parser.parse(options, arguments, properties, stopAtNonOption);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleLongOptionWithEqual_MoreThanOneMatchingOpts_oZue1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = new String[]{"--test1", "--test2"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine commandLine = parser.parse(options, arguments, properties, stopAtNonOption);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleLongOptionWithEqual_OptionHasArg_ekhL3() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = new String[]{"--test", "value"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine commandLine = parser.parse(options, arguments, properties, stopAtNonOption);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleLongOptionWithoutEqual_EmptyMatchingOpts_EnOZ5() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = new String[]{"-t"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine commandLine = parser.parse(options, arguments, properties, stopAtNonOption);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleLongOptionWithoutEqual_MoreThanOneMatchingOpts_qNeK6() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = new String[]{"-t1", "-t2"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine commandLine = parser.parse(options, arguments, properties, stopAtNonOption);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArgument_FXzy0() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"--opt", "value"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine commandLine = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("value", commandLine.getOptionValue("--opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_BCZe1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"-o", "value"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine commandLine = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("value", commandLine.getOptionValue("-o"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNegativeNumber_IDnJ3() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"--opt=-1"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine commandLine = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("-1", commandLine.getOptionValue("--opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithPartialLongOption_uGPO4() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"--optL"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine commandLine = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("optL", commandLine.getOptionValue("--optL"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_myFd5() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"--opt"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine commandLine = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("opt", commandLine.getOptionValue("--opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortAndLongOption_pPGr6_1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"-o", "--opt"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine commandLine = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("o", commandLine.getOptionValue("-o"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortAndLongOption_pPGr6_2() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"-o", "--opt"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine commandLine = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("opt", commandLine.getOptionValue("--opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_zcCy0_1() throws ParseException {
    Options options = new Options();
    String[] arguments = {"--opt1", "arg1", "--opt2", "arg2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("arg1", cmd.getOptionValue("--opt1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_zcCy0_2() throws ParseException {
    Options options = new Options();
    String[] arguments = {"--opt1", "arg1", "--opt2", "arg2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("arg2", cmd.getOptionValue("--opt2"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNegativeNumber_GrqT1() throws ParseException {
    Options options = new Options();
    String[] arguments = {"--opt1", "-10"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("-10", cmd.getOptionValue("--opt1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutArgument_xRhZ2() throws ParseException {
    Options options = new Options();
    String[] arguments = {"--opt1"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("", cmd.getOptionValue("--opt1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleArguments_SGpV3_1() throws ParseException {
    Options options = new Options();
    String[] arguments = {"--opt1", "arg1", "arg2", "--opt2", "arg3"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("arg1", cmd.getOptionValue("--opt1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleArguments_SGpV3_2() throws ParseException {
    Options options = new Options();
    String[] arguments = {"--opt1", "arg1", "arg2", "--opt2", "arg3"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("arg2", cmd.getOptionValue("--opt1", "default"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleArguments_SGpV3_3() throws ParseException {
    Options options = new Options();
    String[] arguments = {"--opt1", "arg1", "arg2", "--opt2", "arg3"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("arg3", cmd.getOptionValue("--opt2"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownToken_zCdP6() throws ParseException {
    Options options = new Options();
    String[] arguments = {"unknownToken"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    assertEquals("Unknown token: unknownToken", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithDeprecatedHandler_Dhxt7() throws ParseException {
    Options options = new Options();
    String[] arguments = {"--opt1", "arg1"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("arg1", cmd.getOptionValue("--opt1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_Yrez8_1() throws ParseException {
    Options options = new Options();
    String[] arguments = {"--opt1", "arg1"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("arg1", cmd.getOptionValue("--opt1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_Yrez8_2() throws ParseException {
    Options options = new Options();
    String[] arguments = {"--opt1", "arg1"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("value", cmd.getOptionProperties("--opt1").getProperty("key"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArgument_HMBp0() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"--opt", "value"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine cmd = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("value", cmd.getOptionValue("--opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_Qjij1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"-o", "value"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine cmd = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("value", cmd.getOptionValue("-o"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionAndArgument_bMGz2() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"--opt=value"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine cmd = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("value", cmd.getOptionValue("--opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOptionAndArgument_PaCK3() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"-o=value"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine cmd = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("value", cmd.getOptionValue("-o"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOption_FPAw5() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"--opt"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine cmd = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("opt", cmd.getOptionValue("--opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNegativeNumber_eZZj7() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"--opt=-1"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine cmd = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("-1", cmd.getOptionValue("--opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithPartialLongOption_LUmB8() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"--optL"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine cmd = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("optL", cmd.getOptionValue("--optL"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_eZIq9() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"--optL=value"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine cmd = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("value", cmd.getOptionValue("--optL"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_Zrnx6() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--opt"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownToken_XzoM8() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"unknown-token"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, arguments, properties, true);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNegativeNumber_upqH9() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--opt", "-1"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, arguments, properties, true);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithPartialMatching_yfuC10() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--opt1", "arg1", "arg2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, arguments, properties, true);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_ainQ11() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--opt", "--long-opt", "arg1", "arg2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, arguments, properties, true);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_rgZm10() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--longOption", "value", "-s", "shortOption"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    DefaultParser parser = new DefaultParser(false);
    CommandLine cmd = parser.parse(options, arguments, properties, stopAtNonOption);
    assertNotNull(cmd);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_Hmng12() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--longOption", "value", "-s", "shortOption"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    boolean stopAtNonOption = false;
    DefaultParser parser = new DefaultParser(false);
    CommandLine cmd = parser.parse(options, arguments, properties, stopAtNonOption);
    assertNotNull(cmd);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredArgs_ohUY14() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--longOption", "value", "-s", "shortOption", "requiredArg"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    DefaultParser parser = new DefaultParser(false);
    CommandLine cmd = parser.parse(options, arguments, properties, stopAtNonOption);
    assertNotNull(cmd);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_yTQx1_1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "long-a", true, "description");
    options.addOption("b", "long-b", false, "description");
    String[] arguments = new String[]{"--a", "123", "--b", "abc", "def"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    assertEquals("123", commandLine.getParsedOptionValue("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_yTQx1_2() throws ParseException {
    Options options = new Options();
    options.addOption("a", "long-a", true, "description");
    options.addOption("b", "long-b", false, "description");
    String[] arguments = new String[]{"--a", "123", "--b", "abc", "def"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    assertEquals("abc", commandLine.getOptionValue("b"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_yTQx1_3() throws ParseException {
    Options options = new Options();
    options.addOption("a", "long-a", true, "description");
    options.addOption("b", "long-b", false, "description");
    String[] arguments = new String[]{"--a", "123", "--b", "abc", "def"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    assertEquals("def", commandLine.getArgList().get(0));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_qlfq5_1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "long-a", true, "description");
    String[] arguments = new String[]{"--a", "123"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    assertEquals("123", commandLine.getParsedOptionValue("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_qlfq5_2() throws ParseException {
    Options options = new Options();
    options.addOption("a", "long-a", true, "description");
    String[] arguments = new String[]{"--a", "123"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    assertEquals("value", commandLine.getOptionProperties("a").getProperty("key"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_CFnx6_1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "long-a", true, "description");
    String[] arguments = new String[]{"--a", "123", "def", "ghi"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    assertEquals("123", commandLine.getParsedOptionValue("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_CFnx6_2() throws ParseException {
    Options options = new Options();
    options.addOption("a", "long-a", true, "description");
    String[] arguments = new String[]{"--a", "123", "def", "ghi"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    assertEquals(1, commandLine.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutStopAtNonOption_HHja7_1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "long-a", true, "description");
    String[] arguments = new String[]{"--a", "123", "def", "ghi"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    assertEquals("123", commandLine.getParsedOptionValue("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutStopAtNonOption_HHja7_2() throws ParseException {
    Options options = new Options();
    options.addOption("a", "long-a", true, "description");
    String[] arguments = new String[]{"--a", "123", "def", "ghi"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    assertEquals(3, commandLine.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseRequiredOptionWithoutValue_ekeq6() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addRequiredOption("opt1", "long-opt1", false, "description");
    String[] arguments = new String[]{"--long-opt1"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseRequiredOptionWithoutValueAndStopAtNonOption_gjXH7() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addRequiredOption("opt1", "long-opt1", false, "description");
    String[] arguments = new String[]{"--long-opt1", "value", "non-option"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, true);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseRequiredOptionWithoutValueAndNotStopAtNonOption_Uqjn8() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addRequiredOption("opt1", "long-opt1", false, "description");
    String[] arguments = new String[]{"--long-opt1", "value", "non-option"};
    Properties properties = new Properties();
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    assertEquals("value", commandLine.getOptionValue("opt1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownArgument_eZEm6() throws ParseException {
    Options options = new Options();
    options.addOption("o", "option", true, "Option description");
    String[] arguments = new String[]{"value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOption_ZFSa4() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addRequiredOption("opt", "required-opt", false, "description");
    String[] arguments = new String[]{"--required-opt", "value"};
    Properties properties = new Properties();
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("value", cmd.getOptionValue("required-opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptionWithoutValue_vpdF5() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addRequiredOption("opt", "required-opt", false, "description");
    String[] arguments = new String[]{"--required-opt"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptionWithoutValueAndNotStopAtNonOption_JFgk7() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addRequiredOption("opt", "required-opt", false, "description");
    String[] arguments = new String[]{"--non-required-opt"};
    Properties properties = new Properties();
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertNull(cmd.getOptionValue("required-opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptionWithoutValueAndProperties_TKft8() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addRequiredOption("opt", "required-opt", false, "description");
    String[] arguments = new String[]{"--required-opt"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("value", cmd.getOptionValue("required-opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_baCx4_2() throws ParseException {
    Options options = new Options();
    options.addOption("a", "abc", true, "description");
    options.addOption("b", "def", false, "description");
    String[] arguments = new String[]{"--abc", "123", "-b", "456", "789"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("456", cmd.getOptionValue("b"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptions_Hqnd5() throws ParseException {
    Options options = new Options();
    options.addRequiredOption("a", "abc", true, "description");
    String[] arguments = new String[]{"--abc", "123"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    assertEquals("Missing required option: a", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_stYn6() throws ParseException {
    Options options = new Options();
    options.addOption("a", "abc", true, "description");
    String[] arguments = new String[]{"--xyz", "123"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    assertEquals("Unknown option: xyz", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownArgument_AcUI7() throws ParseException {
    Options options = new Options();
    options.addOption("a", "abc", true, "description");
    String[] arguments = new String[]{"123", "xyz"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    assertEquals("Unknown argument: xyz", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_rkIs8() throws ParseException {
    Options options = new Options();
    options.addOption("a", "abc", true, "description");
    String[] arguments = new String[]{"123", "xyz", "--abc", "456"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, true);
    assertEquals("456", cmd.getOptionValue("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_Wvmi9_1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "abc", true, "description");
    String[] arguments = new String[]{"--abc", "123"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("123", cmd.getOptionValue("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_Wvmi9_2() throws ParseException {
    Options options = new Options();
    options.addOption("a", "abc", true, "description");
    String[] arguments = new String[]{"--abc", "123"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("value", cmd.getOptionProperties("key").getProperty("key"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutAllowPartialMatching_eMqI1_aCNM0() throws ParseException {
    Options options = new Options();
    options.addOption("L", "L", true, "Description");
    String[] arguments = new String[]{"--L", "--L=V"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(false);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseShortOption_pkHI1_dTOA0() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("opt1", "short-opt1", true, "description");
    String[] arguments = new String[]{"-s", "value"};
    Properties properties = new Properties();
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    assertEquals("value", commandLine.getOptionValue("short-opt1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseShortOptionWithoutValue_YAgZ3_rUwH0() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("opt1", "short-opt1", false, "description");
    String[] arguments = new String[]{"-s"};
    Properties properties = new Properties();
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    assertTrue(commandLine.hasOption("short-opt1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptions_AqAw3() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("L", "longOption", true, "long option description");
    String[] arguments = {"-L", "arg1", "arg2"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    assertEquals("arg1", cmd.getOptionValue("L", ""));
    assertEquals("arg2", cmd.getOptionValue("L", ""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithRequiredOptions_BHsa4() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addRequiredOption("L", "longOption", true, "long option description");
    String[] arguments = {"-L", "arg1", "arg2"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    assertEquals("arg1", cmd.getOptionValue("L", ""));
    assertEquals("arg2", cmd.getOptionValue("L", ""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithNoMatchingOptions_QpGS6() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"-X", "arg1", "arg2"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    assertEquals("arg1", cmd.getArgList().get(0));
    assertEquals("arg2", cmd.getArgList().get(1));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithProperties_Bgoa7() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("L", "longOption", true, "long option description");
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    String[] arguments = {"-L", "arg1", "arg2"};
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("arg1", cmd.getOptionValue("L", ""));
    assertEquals("arg2", cmd.getOptionValue("L", ""));
    assertEquals("value", cmd.getOptionProperties("L").getProperty("key"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithStopAtNonOption_PWhc8() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("L", "longOption", true, "long option description");
    String[] arguments = {"-L", "arg1", "arg2", "non-option", "arg3"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), true);
    assertEquals("arg1", cmd.getOptionValue("L", ""));
    assertEquals("arg2", cmd.getOptionValue("L", ""));
    assertEquals("arg3", cmd.getArgList().get(2));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortAndLongOption_pPGr6() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"-o", "--opt"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine commandLine = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("o", commandLine.getOptionValue("-o"));
    assertEquals("opt", commandLine.getOptionValue("--opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_zcCy0() throws ParseException {
    Options options = new Options();
    String[] arguments = {"--opt1", "arg1", "--opt2", "arg2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("arg1", cmd.getOptionValue("--opt1"));
    assertEquals("arg2", cmd.getOptionValue("--opt2"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithMultipleArguments_SGpV3() throws ParseException {
    Options options = new Options();
    String[] arguments = {"--opt1", "arg1", "arg2", "--opt2", "arg3"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("arg1", cmd.getOptionValue("--opt1"));
    assertEquals("arg2", cmd.getOptionValue("--opt1", "default"));
    assertEquals("arg3", cmd.getOptionValue("--opt2"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_Yrez8() throws ParseException {
    Options options = new Options();
    String[] arguments = {"--opt1", "arg1"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("arg1", cmd.getOptionValue("--opt1"));
    assertEquals("value", cmd.getOptionProperties("--opt1").getProperty("key"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_yTQx1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "long-a", true, "description");
    options.addOption("b", "long-b", false, "description");
    String[] arguments = new String[]{"--a", "123", "--b", "abc", "def"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    assertEquals("123", commandLine.getParsedOptionValue("a"));
    assertEquals("abc", commandLine.getOptionValue("b"));
    assertEquals("def", commandLine.getArgList().get(0));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_qlfq5() throws ParseException {
    Options options = new Options();
    options.addOption("a", "long-a", true, "description");
    String[] arguments = new String[]{"--a", "123"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    assertEquals("123", commandLine.getParsedOptionValue("a"));
    assertEquals("value", commandLine.getOptionProperties("a").getProperty("key"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_CFnx6() throws ParseException {
    Options options = new Options();
    options.addOption("a", "long-a", true, "description");
    String[] arguments = new String[]{"--a", "123", "def", "ghi"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    assertEquals("123", commandLine.getParsedOptionValue("a"));
    assertEquals(1, commandLine.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutStopAtNonOption_HHja7() throws ParseException {
    Options options = new Options();
    options.addOption("a", "long-a", true, "description");
    String[] arguments = new String[]{"--a", "123", "def", "ghi"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    assertEquals("123", commandLine.getParsedOptionValue("a"));
    assertEquals(3, commandLine.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_baCx4() throws ParseException {
    Options options = new Options();
    options.addOption("a", "abc", true, "description");
    options.addOption("b", "def", false, "description");
    String[] arguments = new String[]{"--abc", "123", "-b", "456", "789"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("123", cmd.getParsedOptionValue("a"));
    assertEquals("456", cmd.getOptionValue("b"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_Wvmi9() throws ParseException {
    Options options = new Options();
    options.addOption("a", "abc", true, "description");
    String[] arguments = new String[]{"--abc", "123"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("123", cmd.getOptionValue("a"));
    assertEquals("value", cmd.getOptionProperties("key").getProperty("key"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptionWithEqual_fLvR1() throws ParseException {
    Options options = new Options();
    options.addOption("longOption", "longOption", true, "description");
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = new String[]{"--longOption=value"};
    CommandLine commandLine = parser.parse(options, arguments, new Properties(), false);
    assertEquals("value", commandLine.getParsedOptionValue("longOption"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseLongOptionWithoutEqual_qkCx0() throws ParseException {
    Options options = new Options();
    options.addOption("opt", "long-opt", true, "description");
    String[] arguments = new String[]{"--long-opt", "value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    assertEquals("value", commandLine.getParsedOptionValue("opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseLongOptionWithEqual_heEF1() throws ParseException {
    Options options = new Options();
    options.addOption("opt", "long-opt", true, "description");
    String[] arguments = new String[]{"--long-opt=value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    assertEquals("value", commandLine.getParsedOptionValue("opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSkipParsing_tycM0() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    parser.parse(new Options(), new String[]{"--"}, new Properties(), true);
    assertEquals(true, parser.skipParsing);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAddArg_fqmj1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    parser.parse(new Options(), new String[]{"arg"}, new Properties(), true);
    assertEquals("arg", parser.cmd.getArgs()[0]);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSkipParsing_TKub0() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"--", "arg1", "arg2"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(2, commandLine.getArgs().length);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAddArg_khYA1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"arg1", "arg2"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(2, commandLine.getArgs().length);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSkipParsing_MjRC0() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"--", "arg1", "arg2"};
    CommandLine result = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(2, result.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAddArg_TIGb1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"arg1", "arg2"};
    CommandLine result = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(2, result.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSkipParsing_YDbo0() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"--", "arg1", "arg2"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(2, commandLine.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testAddArg_ZNyu1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"arg1", "arg2"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(2, commandLine.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleUnknownToken_OGgI4() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"unknownToken"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(1, commandLine.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testSkipParsing_PkkP0() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    parser.parse(new Options(), new String[]{"--"}, new Properties(), true);
    assertTrue(parser.skipParsing);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleLongOptionWithoutEqual_ibDj2() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"--opt"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleShortAndLongOption_rCpe3() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"-a", "--opt"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleUnknownToken_GPkg4() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"unknownToken"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleTokenWithArgument_Ywxq5() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"--opt", "arg1"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithValidArguments_hhNV0() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--opt1", "arg1", "--opt2", "arg2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithInvalidArguments_AeIq1() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--opt1", "arg1", "invalid", "--opt2", "arg2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNegativeNumber_KCsK2() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--opt1", "-10"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNonNumber_laPL3() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--opt1", "abc"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptions_AqAw3_1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("L", "longOption", true, "long option description");
    String[] arguments = {"-L", "arg1", "arg2"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    assertEquals("arg1", cmd.getOptionValue("L", ""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithRequiredOptions_BHsa4_1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addRequiredOption("L", "longOption", true, "long option description");
    String[] arguments = {"-L", "arg1", "arg2"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    assertEquals("arg1", cmd.getOptionValue("L", ""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithStopAtNonOption_PWhc8_1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("L", "longOption", true, "long option description");
    String[] arguments = {"-L", "arg1", "arg2", "non-option", "arg3"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), true);
    assertEquals("arg1", cmd.getOptionValue("L", ""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithStopAtNonOption_PWhc8_3() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("L", "longOption", true, "long option description");
    String[] arguments = {"-L", "arg1", "arg2", "non-option", "arg3"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), true);
    assertEquals("arg3", cmd.getArgList().get(2));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_GDmv4() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--unknown"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithAmbiguousOption_ewIs5() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--test", "--test2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptionAndNoValue_nLEM8() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--test"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptionAndMultipleValues_QgUZ9() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--test", "value1", "value2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testOptionNull_NRUR0() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testOptionGroup_wuPs1() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    options.addOptionGroup(new OptionGroup());
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testOptionValue_uDZT3() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    options.addOption("opt", "opt", true, "description");
    properties.setProperty("opt", "value");
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testOptionNoArg_nVBU4() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    options.addOption("opt", "opt", false, "description");
    properties.setProperty("opt", "yes");
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testOptionValues_fHXd5() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    options.addOption("opt", "opt", true, "description");
    properties.setProperty("opt", "value1,value2");
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testOptionNoValue_xBTH6() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    options.addOption("opt", "opt", true, "description");
    properties.setProperty("opt", "");
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testOptionYesNo_VxjP7() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    options.addOption("opt", "opt", true, "description");
    properties.setProperty("opt", "yes");
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testOptionTrueFalse_rTVJ8() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    options.addOption("opt", "opt", true, "description");
    properties.setProperty("opt", "true");
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testOptionOne_rzje9() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    options.addOption("opt", "opt", true, "description");
    properties.setProperty("opt", "1");
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testOptionMultiple_OBcy10() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    options.addOption("opt", "opt", true, "description");
    properties.setProperty("opt", "value1,value2,value3");
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownToken_Mntr2() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"unknown"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine commandLine = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("unknown", commandLine.getArgList().get(0));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptions_zlgG4() throws ParseException {
    Options options = new Options();
    options.addRequiredOption("opt1", "opt1", true, "required option 1");
    String[] arguments = {"--opt1", "arg1"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("arg1", cmd.getOptionValue("opt1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutRequiredOptions_Mlwz5() throws ParseException {
    Options options = new Options();
    options.addRequiredOption("opt1", "opt1", true, "required option 1");
    String[] arguments = {};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    assertEquals("Missing required option: opt1", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownToken_mhUC4() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = {"unknown"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    CommandLine cmd = parser.parse(options, arguments, properties, stopAtNonOption);
    assertEquals("unknown", cmd.getArgList().get(0));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_Gsew3() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--opt", "arg1", "arg2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptions_KAkK4() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--opt", "--req-opt"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, true);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_xmgu5() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"arg1", "arg2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_KsXw13() throws ParseException {
    Options options = new Options();
    String[] arguments = new String[]{"--longOption", "value", "-s", "shortOption", "nonOption"};
    Properties properties = new Properties();
    boolean stopAtNonOption = true;
    DefaultParser parser = new DefaultParser(false);
    CommandLine cmd = parser.parse(options, arguments, properties, stopAtNonOption);
    assertNotNull(cmd);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseLongOption_caBy0() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("opt1", "long-opt1", true, "description");
    String[] arguments = new String[]{"--long-opt1", "value"};
    Properties properties = new Properties();
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    assertEquals("value", commandLine.getOptionValue("opt1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseLongOptionWithoutValue_RKvl2() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("opt1", "long-opt1", false, "description");
    String[] arguments = new String[]{"--long-opt1"};
    Properties properties = new Properties();
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    assertTrue(commandLine.hasOption("opt1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseUnknownOption_nexI4() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = new String[]{"--unknown-opt"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseRequiredOption_lXEM5() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addRequiredOption("opt1", "long-opt1", true, "description");
    String[] arguments = new String[]{"--long-opt1", "value"};
    Properties properties = new Properties();
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    assertEquals("value", commandLine.getOptionValue("opt1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptions_OzGL4() throws ParseException {
    Options options = new Options();
    options.addRequiredOption("o", "option", true, "Option description");
    String[] arguments = new String[]{"-o", "value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    assertEquals("value", commandLine.getParsedOptionValue("option", String.class));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_rEUo5() throws ParseException {
    Options options = new Options();
    options.addOption("o", "option", true, "Option description");
    String[] arguments = new String[]{"--unknown", "value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredArguments_BshO7() throws ParseException {
    Options options = new Options();
    options.addRequiredOption("o", "option", true, "Option description");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_InLy0() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("opt", "long-opt", true, "description");
    String[] arguments = new String[]{"--long-opt", "value"};
    Properties properties = new Properties();
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("value", cmd.getOptionValue("long-opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_ZIyB1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("s", "short-opt", true, "description");
    String[] arguments = new String[]{"-s", "value"};
    Properties properties = new Properties();
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("value", cmd.getOptionValue("short-opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongAndShortOption_AGJY2_1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("opt", "long-opt", true, "description");
    options.addOption("s", "short-opt", true, "description");
    String[] arguments = new String[]{"--long-opt", "value", "-s", "value2"};
    Properties properties = new Properties();
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("value", cmd.getOptionValue("long-opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongAndShortOption_AGJY2_2() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("opt", "long-opt", true, "description");
    options.addOption("s", "short-opt", true, "description");
    String[] arguments = new String[]{"--long-opt", "value", "-s", "value2"};
    Properties properties = new Properties();
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("value2", cmd.getOptionValue("short-opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithNoOption_GhwE3() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    String[] arguments = new String[]{"value"};
    Properties properties = new Properties();
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertNull(cmd.getOptionValue("opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptionWithoutValueAndStopAtNonOption_eliE6() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addRequiredOption("opt", "required-opt", false, "description");
    String[] arguments = new String[]{"--non-required-opt"};
    Properties properties = new Properties();
    try {
    parser.parse(options, arguments, properties, true);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_baCx4_1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "abc", true, "description");
    options.addOption("b", "def", false, "description");
    String[] arguments = new String[]{"--abc", "123", "-b", "456", "789"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("123", cmd.getParsedOptionValue("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleLongOption_dFpy3() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"--option", "arg1", "arg3"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(3, commandLine.getArgs().length);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleShortAndLongOption_JzGn3_fid1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"-o", "arg1", "arg3"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(3, commandLine.getArgs().length);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleUnknownToken_qesK4_fid1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"unknown", "arg1", "arg3"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(3, commandLine.getArgs().length);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleLongOption_BfXv3() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"--opt", "arg1", "arg3"};
    CommandLine result = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(3, result.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleShortAndLongOption_gRIM4() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"-o", "--opt", "arg1", "arg2"};
    CommandLine result = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(4, result.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleUnknownToken_jiFZ4_fid1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"unknown", "arg1", "arg3"};
    CommandLine result = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(3, result.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleShortOption_hLxi5_fid1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"-o", "arg1", "arg3"};
    CommandLine result = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(3, result.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleLongOption_IXID3() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"--opt", "arg1", "arg3"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(3, commandLine.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleShortAndLongOption_UhPt3_fid1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = {"-o", "arg1", "arg3"};
    CommandLine commandLine = parser.parse(new Options(), arguments, new Properties(), true);
    assertEquals(3, commandLine.getArgList().size());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptions_AqAw3_2_fid1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("L", "longOption", true, "long option description");
    String[] arguments = {"-L", "arg1", "arg1"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    assertEquals("arg1", cmd.getOptionValue("L", ""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithRequiredOptions_BHsa4_2_fid1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addRequiredOption("L", "longOption", true, "long option description");
    String[] arguments = {"-L", "arg1", "arg1"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), false);
    assertEquals("arg1", cmd.getOptionValue("L", ""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseOptionsWithStopAtNonOption_PWhc8_2_fid1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("L", "longOption", true, "long option description");
    String[] arguments = {"-L", "arg1", "arg1", "non-option", "arg3"};
    CommandLine cmd = parser.parse(options, arguments, new Properties(), true);
    assertEquals("arg1", cmd.getOptionValue("L", ""));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParse_yTQx1_3_fid1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "long-a", true, "description");
    options.addOption("b", "long-b", false, "description");
    String[] arguments = new String[]{"--a", "123", "--b", "abc", "--a"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine commandLine = parser.parse(options, arguments, properties, true);
    assertEquals("--a", commandLine.getArgList().get(0));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseRequiredOptionWithoutValueAndNotStopAtNonOption_Uqjn8_fid1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addRequiredOption("opt1", "long-opt1", false, "description");
    String[] arguments = new String[]{"--long-opt1", "value", "non-option"};
    Properties properties = new Properties();
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    assertNull(commandLine.getOptionValue("opt1"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOption_ZFSa4_fid1() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addRequiredOption("opt", "required-opt", false, "description");
    String[] arguments = new String[]{"--required-opt", "value"};
    Properties properties = new Properties();
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertNull(cmd.getOptionValue("required-opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_stYn6_fid1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "abc", true, "description");
    String[] arguments = new String[]{"--xyz", "123"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    assertEquals("Unrecognized option: --xyz", e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithStopAtNonOption_rkIs8_fid1() throws ParseException {
    Options options = new Options();
    options.addOption("a", "abc", true, "description");
    String[] arguments = new String[]{"123", "xyz", "--abc", "456"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    CommandLine cmd = parser.parse(options, arguments, properties, true);
    assertNull(cmd.getOptionValue("a"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongAndShortOption_AGJY2() throws ParseException {
    DefaultParser parser = new DefaultParser(true);
    Options options = new Options();
    options.addOption("opt", "long-opt", true, "description");
    options.addOption("s", "short-opt", true, "description");
    String[] arguments = new String[]{"--long-opt", "value", "-s", "value2"};
    Properties properties = new Properties();
    CommandLine cmd = parser.parse(options, arguments, properties, false);
    assertEquals("value", cmd.getOptionValue("long-opt"));
    assertEquals("value2", cmd.getOptionValue("short-opt"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutArguments_XPEQ3() {
    Options options = new Options();
    options.addOption("opt", "longOpt", true, "description");
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = new String[0];
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    parser.parse(options, arguments, properties, stopAtNonOption);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutArgumentsAndProperties_ahtL1() {
    Options options = new Options();
    CommandLine cmd = new CommandLine();
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, null, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleConcatenatedOptionsWithMultipleOptions_ymdG5_cKvQ0() {
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(null, new String[]{"abc", "def"}, null);
    assertEquals("abc", commandLine.getOptionValue("abc"));
    assertEquals("def", commandLine.getOptionValue("def"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleConcatenatedOptions4_SByV3_DepC0() {
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(null, new String[]{"a", "b"}, null);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testHandleConcatenatedOptionsWithStripLeadingAndTrailingQuotes_ZfXr4_OBEY0() {
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine cmd = parser.parse(new Options(), new String[]{"-a", "abc"}, new Properties());
    } catch (ParseException e) {
    fail("ParseException was expected");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOptions_mdec1_ZTYk0() {
    Options options = new Options();
    try {
    CommandLine commandLine = new DefaultParser().parse(options, new String[]{"--a=1", "--b=2"}, new Properties());
    assertEquals("1", commandLine.getOptionValue("--a"));
    assertEquals("2", commandLine.getOptionValue("--b"));
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_agPX3_fhgr0() {
    Options options = new Options();
    try {
    CommandLine commandLine = new DefaultParser().parse(options, new String[]{"-c", "1"}, new Properties());
    assertEquals("", commandLine.getOptionValue("-c"));
    } catch (ParseException e) {
    fail("ParseException was expected");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_FxVc1_jMzn0() {
    Options options = new Options();
    try {
    CommandLine commandLine = new DefaultParser().parse(options, new String[]{"--long-option", "value"}, new Properties());
    assertEquals(1, commandLine.getOptions().length);
    } catch (ParseException e) {
    fail("ParseException was expected");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithAmbiguousOption_dlIo3() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "1", "-b", "2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithAmbiguousOption_JqZA3() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "value1", "--option1", "--option2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithAmbiguousOption_PhiZ3() {
    Options options = new Options();
    options.addOption("a", "longA", true, "description");
    options.addOption("b", "longB", false, "description");
    String[] arguments = new String[]{"-l"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected AmbiguousOptionException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutRequiredArgs_jSyb1() {
    Options options = new Options();
    options.addRequiredOption("opt", "longOpt", true, "description");
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = new String[]{"-help"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    parser.parse(options, arguments, properties, stopAtNonOption);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutRequiredOptions_Rswh1() {
    Options options = new Options();
    options.addRequiredOption("opt", "longOpt", true, "description");
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = new String[]{"-help"};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    parser.parse(options, arguments, properties, stopAtNonOption);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutArguments_leav3() {
    Options options = new Options();
    options.addOption("opt", "longOpt", true, "description");
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = new String[]{};
    Properties properties = new Properties();
    boolean stopAtNonOption = false;
    try {
    parser.parse(options, arguments, properties, stopAtNonOption);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnrecognizedOption_McPB3() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "value", "-b", "true", "-d", "yes"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArgumentsAndProperties_RAUp0() {
    Options options = new Options();
    CommandLine cmd = new CommandLine();
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = new String[]{"-a", "value", "-b", "value", "-c", "value"};
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptions_NpSe2() {
    Options options = new Options();
    CommandLine cmd = new CommandLine();
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = new String[]{"-a", "value", "-b", "value"};
    try {
    parser.parse(options, arguments, properties, false);
    fail("Expected ParseException");
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArguments_qStH0_gJmQ0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "1", "--b", "2", "--c", "3"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutArguments_ooUL1_pGFY0() {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_NSNd2_Vwhb0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "1", "--b", "2"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptions_tRpA3_zIaE0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "1", "--b", "2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithOptionAndArgument_GJZz2_LAGD0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "1", "--b", "2", "3"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithOptionWithoutArgument_AlQp3_ySKc0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "--b", "2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_kukn2_lyqd0() {
    Options options = new Options();
    String[] arguments = new String[]{"--longOption", "value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_BpCW3_liIR0() {
    Options options = new Options();
    String[] arguments = new String[]{"-s", "value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_xBWN2_fzdy0() {
    Options options = new Options();
    String[] arguments = new String[]{"--longOption"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_prGQ3_nCxa0() {
    Options options = new Options();
    String[] arguments = new String[]{"-s"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArguments_exNG0_MDkp0() {
    Options options = new Options();
    String[] arguments = {"-a", "1", "--b", "2", "--c", "3"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutArguments_rkjA1_qaQl0() {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    fail("ParseException was thrown");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_ZJIa2_vnUS0() {
    Options options = new Options();
    String[] arguments = {"--longOption", "value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_SsIY3_mKMa0() {
    Options options = new Options();
    String[] arguments = {"-s", "value"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithShortOption_daRr2_zoyT0() {
    Options options = new Options();
    options.addOption("a", "short-option", true, "short option");
    String[] arguments = new String[]{"-a"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_nYYX3_DZBK0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "1"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArguments_FtCD0_FtcV0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "1", "--b", "2", "--c"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithLongOption_EtTZ2_WHHA0() {
    Options options = new Options();
    String[] arguments = new String[]{"--a", "1", "--b", "2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithAmbiguousOption_zQEd3_xbBB0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "1", "--b", "2", "--c", "3", "--d", "4"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownToken_mLXI2_JGiD0() {
    Options options = new Options();
    String[] arguments = new String[]{"-x", "y"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArguments_Kzfc0_soVa0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "value1", "--option2", "value2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_lfnk2_SMrF0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "value1"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithUnknownOption_KQWE3_BrPF0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "1", "--b", "2", "-d"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptions_dJPk2_HMaY0() {
    Options options = new Options();
    options.addRequiredOption("a", "a", true, "a description");
    try {
    CommandLine commandLine = new DefaultParser().parse(options, new String[]{"-a", "1"}, new Properties());
    assertEquals("1", commandLine.getOptionValue("a"));
    } catch (ParseException e) {
    fail("ParseException was expected");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArguments_sOdg0_tqbr0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "1", "-b", "2", "-c", "3"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_ISlw2_kyWx0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "1", "-b", "2"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArguments_alop0_aDJf0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "value1", "-b", "value2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArguments_mEmx0_kVuv0() {
    Options options = new Options();
    String[] arguments = {"-a", "1", "-b", "2", "-c", "3"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOption_UouZ3_aeRV0() {
    Options options = new Options();
    String[] arguments = {"-a", "1"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArguments_hrEu0_SPED0() {
    Options options = new Options();
    String[] arguments = {"-a", "1", "-b", "2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_UbUm2_bRCE0() {
    Options options = new Options();
    String[] arguments = {"-a", "1"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithAmbiguousOption_vxIi3_GLxj0() {
    Options options = new Options();
    String[] arguments = {"-l", "1", "-L", "2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutArguments_luFR1_kjDp0() {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    fail("ParseException occurred: " + e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArguments_vcsB0_aFkJ0() {
    Options options = new Options();
    options.addOption("a", "longA", true, "description");
    options.addOption("b", "longB", false, "description");
    String[] arguments = new String[]{"-a", "value", "-b"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutArguments_VeMg1_Vurq0() {
    Options options = new Options();
    options.addOption("a", "longA", true, "description");
    options.addOption("b", "longB", false, "description");
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_mtpy2_WBbk0() {
    Options options = new Options();
    options.addOption("a", "longA", true, "description");
    options.addOption("b", "longB", false, "description");
    String[] arguments = new String[]{"-a", "value"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutArguments_XnGJ1_vTmz0() {
    Options options = new Options();
    try {
    CommandLine commandLine = new DefaultParser().parse(options, new String[]{}, new Properties(), false);
    } catch (ParseException e) {
    fail("ParseException was expected");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_wOvY3_tJKL0() {
    Options options = new Options();
    String[] arguments = {"-a", "1", "-b", "2"};
    Properties properties = new Properties();
    properties.setProperty("key", "value");
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOption_oeZx2_PtyO0() {
    Options options = new Options();
    options.addRequiredOption("a", "a", true, "Required option a");
    try {
    CommandLine commandLine = new DefaultParser().parse(options, new String[]{"-a", "1"}, new Properties());
    assertEquals("1", commandLine.getOptionValue("a"));
    } catch (ParseException e) {
    fail("ParseException was expected");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOption_PVBj2_ewxT0() {
    Options options = new Options();
    options.addRequiredOption("a", "long-option", true, "description");
    try {
    CommandLine commandLine = new DefaultParser().parse(options, new String[]{"--long-option", "value"}, new Properties());
    assertEquals(1, commandLine.getOptions().length);
    } catch (ParseException e) {
    fail("ParseException was expected");
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_xjms3_rNvW0() {
    Options options = new Options();
    try {
    CommandLine commandLine = new DefaultParser().parse(options, new String[]{}, new Properties(), false);
    } catch (ParseException e) {
    fail("ParseException occurred: " + e.getMessage());
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArguments_HQzr0_chav0() {
    Options options = new Options();
    String[] arguments = {"-a", "value1", "-b", "value2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_oogG2_tsMk0() {
    Options options = new Options();
    String[] arguments = {"-a", "value1", "-b", "value2"};
    Properties properties = new Properties();
    properties.setProperty("key1", "value1");
    properties.setProperty("key2", "value2");
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine commandLine = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithProperties_qFmZ0_cFSJ0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "1", "-b", "2"};
    Properties properties = new Properties();
    properties.setProperty("c", "3");
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine result = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredOptions_wCBK2_avpC0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "1", "-b", "2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine result = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithRequiredArgs_ZXnk3_VzlT0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "1", "arg1", "arg2"};
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine result = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArgumentsAndProperties_FHsa0_LYzx0() {
    Options options = new Options();
    CommandLine cmd = new CommandLine();
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = new String[]{"-a", "value", "-b", "true", "-c", "yes"};
    try {
    parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutArgumentsAndProperties_TPhJ1_Idaz0() {
    Options options = new Options();
    CommandLine cmd = new CommandLine();
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, new String[0], properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArgumentsButNoProperties_ZxLk2_fEWe0() {
    Options options = new Options();
    CommandLine cmd = new CommandLine();
    Properties properties = null;
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = new String[]{"-a", "value", "-b", "true", "-c", "yes"};
    try {
    parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArgumentsAndProperties_wMHg0_VYOs0() {
    Options options = new Options();
    String[] arguments = new String[]{"-a", "value", "-b", "true", "-c", "1"};
    Properties properties = new Properties();
    properties.setProperty("d", "yes");
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine result = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutArgumentsAndProperties_Lgek1_QhsU0() {
    Options options = new Options();
    String[] arguments = new String[0];
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    try {
    CommandLine result = parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArgumentsAndProperties_Pfmj0_Sffl0() {
    Options options = new Options();
    CommandLine cmd = new CommandLine();
    Properties properties = new Properties();
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = new String[]{"-a", "value", "-b", "true", "-c", "1"};
    try {
    parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithoutArgumentsAndProperties_JWRe1_YwtU0() {
    Options options = new Options();
    CommandLine cmd = new CommandLine();
    Properties properties = null;
    DefaultParser parser = new DefaultParser(true);
    try {
    parser.parse(options, new String[0], properties, false);
    } catch (ParseException e) {
    }
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testParseWithArgumentsButNoProperties_ZGXU2_cNoP0() {
    Options options = new Options();
    CommandLine cmd = new CommandLine();
    Properties properties = null;
    DefaultParser parser = new DefaultParser(true);
    String[] arguments = new String[]{"-a", "value", "-b", "true", "-c", "1"};
    try {
    parser.parse(options, arguments, properties, false);
    } catch (ParseException e) {
    }
    }
}