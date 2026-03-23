package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 2, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class Parser_parseTestGenerated {

    @Test
    void testParseWithValidOption() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "option a");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"-a"}, new Properties(), false);
        assertNotNull(result);
        assertTrue(result.hasOption("a"));
    }

    @Test
    void testParseWithValidOptionWithValue() throws ParseException {
        Options options = new Options();
        options.addOption(Option.builder("a").hasArg().build());
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"-a", "value"}, new Properties(), false);
        assertNotNull(result);
        assertTrue(result.hasOption("a"));
        assertEquals("value", result.getOptionValue("a"));
    }

    @Test
    void testParseWithUnrecognizedOptionThrowsParseException() {
        Options options = new Options();
        options.addOption("a", false, "option a");
        assertThrows(ParseException.class, () ->
            new Parser() {
                @Override
                public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                    return arguments;
                }
            }.parse(options, new String[]{"-b"}, new Properties(), false)
        );
    }

    @Test
    void testParseWithUnrecognizedOptionWithStopAtNonOption() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "option a");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"-b", "value"}, new Properties(), true);
        assertNotNull(result);
        assertFalse(result.hasOption("a"));
        assertEquals(Arrays.asList("-b", "value"), result.getArgList());
    }

    @Test
    void testParseWithRequiredOptionMissingThrowsParseException() {
        Options options = new Options();
        Option opt = new Option("a", false, "option a");
        opt.setRequired(true);
        options.addOption(opt);
        assertThrows(ParseException.class, () ->
            new Parser() {
                @Override
                public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                    return arguments;
                }
            }.parse(options, new String[]{}, new Properties(), false)
        );
    }

    @Test
    void testParseWithProperties() throws ParseException {
        Options options = new Options();
        options.addOption(Option.builder("a").hasArg().build());
        Properties properties = new Properties();
        properties.setProperty("a", "value");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{}, properties, false);
        assertNotNull(result);
        assertTrue(result.hasOption("a"));
        assertEquals("value", result.getOptionValue("a"));
    }

    @Test
    void testParseWithPropertiesBooleanOption() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "option a");
        Properties properties = new Properties();
        properties.setProperty("a", "true");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{}, properties, false);
        assertNotNull(result);
        assertTrue(result.hasOption("a"));
    }

    @Test
    void testParseWithPropertiesFalseBooleanOption() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "option a");
        Properties properties = new Properties();
        properties.setProperty("a", "false");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{}, properties, false);
        assertNotNull(result);
        assertFalse(result.hasOption("a"));
    }

    @Test
    void testParseWithPropertiesInvalidOptionThrowsParseException() {
        Options options = new Options();
        options.addOption("a", false, "option a");
        Properties properties = new Properties();
        properties.setProperty("b", "value");
        assertThrows(ParseException.class, () ->
            new Parser() {
                @Override
                public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                    return arguments;
                }
            }.parse(options, new String[]{}, properties, false)
        );
    }

    @Test
    void testParseWithOptionGroup() throws ParseException {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", false, "option a"));
        group.addOption(new Option("b", false, "option b"));
        options.addOptionGroup(group);
        Properties properties = new Properties();
        properties.setProperty("a", "true");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{}, properties, false);
        assertNotNull(result);
        assertTrue(result.hasOption("a"));
        assertFalse(result.hasOption("b"));
    }

    @Test
    void testParseWithRequiredOptionGroup() {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(new Option("a", false, "option a"));
        group.addOption(new Option("b", false, "option b"));
        options.addOptionGroup(group);
        assertThrows(ParseException.class, () ->
            new Parser() {
                @Override
                public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                    return arguments;
                }
            }.parse(options, new String[]{}, new Properties(), false)
        );
    }

    @Test
    void testParseWithRequiredOptionGroupSatisfied() throws ParseException {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.setRequired(true);
        group.addOption(new Option("a", false, "option a"));
        group.addOption(new Option("b", false, "option b"));
        options.addOptionGroup(group);
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"-a"}, new Properties(), false);
        assertNotNull(result);
        assertTrue(result.hasOption("a"));
    }

    @Test
    void testParseWithLongOption() throws ParseException {
        Options options = new Options();
        options.addOption("long", false, "long option");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"--long"}, new Properties(), false);
        assertNotNull(result);
        assertTrue(result.hasOption("long"));
    }

    @Test
    void testParseWithLongOptionWithValue() throws ParseException {
        Options options = new Options();
        options.addOption(Option.builder("long").hasArg().build());
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"--long", "value"}, new Properties(), false);
        assertNotNull(result);
        assertTrue(result.hasOption("long"));
        assertEquals("value", result.getOptionValue("long"));
    }

    @Test
    void testParseWithMixedOptions() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "option a");
        options.addOption(Option.builder("b").hasArg().build());
        options.addOption("long", false, "long option");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"-a", "--long", "-b", "value"}, new Properties(), false);
        assertNotNull(result);
        assertTrue(result.hasOption("a"));
        assertTrue(result.hasOption("long"));
        assertTrue(result.hasOption("b"));
        assertEquals("value", result.getOptionValue("b"));
    }

    @Test
    void testParseWithMultipleValuesForOption() throws ParseException {
        Options options = new Options();
        options.addOption(Option.builder("a").hasArgs().build());
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"-a", "value1", "value2"}, new Properties(), false);
        assertNotNull(result);
        assertTrue(result.hasOption("a"));
        assertEquals(2, result.getOptionValues("a").length);
        assertEquals("value1", result.getOptionValues("a")[0]);
        assertEquals("value2", result.getOptionValues("a")[1]);
    }

    @Test
    void testParseWithEmptyOptionValue() throws ParseException {
        Options options = new Options();
        options.addOption(Option.builder("a").hasArg().build());
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"-a", ""}, new Properties(), false);
        assertNotNull(result);
        assertTrue(result.hasOption("a"));
        assertEquals("", result.getOptionValue("a"));
    }

    @Test
    void testParseWithOptionalArgOptionWithValue() throws ParseException {
        Options options = new Options();
        options.addOption(new Option("a", true, "option a"));
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"-a", "value"}, new Properties(), false);
        assertNotNull(result);
        assertTrue(result.hasOption("a"));
        assertEquals("value", result.getOptionValue("a"));
    }

    @Test
    void testParseWithMultipleArguments() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "option a");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"-a", "arg1", "arg2"}, new Properties(), false);
        assertNotNull(result);
        assertTrue(result.hasOption("a"));
        assertEquals(Arrays.asList("arg1", "arg2"), result.getArgList());
    }

    @Test
    void testParseWithStopAtNonOptionAndArguments() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "option a");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"-a", "arg1", "arg2", "-b", "arg3"}, new Properties(), true);
        assertNotNull(result);
        assertTrue(result.hasOption("a"));
        assertEquals(Arrays.asList("arg1", "arg2", "-b", "arg3"), result.getArgList());
    }

    @Test
    void testParseWithNullOptionsThrowsParseException() {
        assertThrows(ParseException.class, () ->
            new Parser() {
                @Override
                public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                    return arguments;
                }
            }.parse(null, new String[]{"-a"}, new Properties(), false)
        );
    }

    @Test
    void testParseWithDoubleDash() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "option a");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"--", "-a", "value"}, new Properties(), false);
        assertNotNull(result);
        assertFalse(result.hasOption("a"));
        assertEquals(Arrays.asList("--", "-a", "value"), result.getArgList());
    }

    @Test
    void testParseWithSingleDash() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "option a");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"-", "-a"}, new Properties(), false);
        assertNotNull(result);
        assertFalse(result.hasOption("a"));
        assertEquals(Arrays.asList("-", "-a"), result.getArgList());
    }

    @Test
    void testParseWithOptionGroupAndConflictingProperties() throws ParseException {
        Options options = new Options();
        OptionGroup group = new OptionGroup();
        group.addOption(new Option("a", false, "option a"));
        group.addOption(new Option("b", false, "option b"));
        options.addOptionGroup(group);
        Properties properties = new Properties();
        properties.setProperty("a", "true");
        properties.setProperty("b", "true");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{}, properties, false);
        assertNotNull(result);
        assertTrue(result.hasOption("a"));
        assertFalse(result.hasOption("b"));
    }

    @Test
    void testParseWithOptionalArgOption() throws ParseException {
        Options options = new Options();
        options.addOption(new Option("a", true, "option a"));
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"-a"}, new Properties(), false);
        assertNotNull(result);
        assertTrue(result.hasOption("a"));
        assertNull(result.getOptionValue("a"));
    }

    @Test
    void testParseWithStopAtNonOptionAndSingleDash() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "option a");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"-", "arg1", "arg2"}, new Properties(), true);
        assertNotNull(result);
        assertFalse(result.hasOption("a"));
        assertEquals(Arrays.asList("-", "arg1", "arg2"), result.getArgList());
    }

    @Test
    void testParseWithStopAtNonOptionAndDoubleDash() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "option a");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"--", "arg1", "arg2"}, new Properties(), true);
        assertNotNull(result);
        assertFalse(result.hasOption("a"));
        assertEquals(Arrays.asList("--", "arg1", "arg2"), result.getArgList());
    }

    @Test
    void testParseWithUnrecognizedOptionAndStopAtNonOption() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "option a");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"-x", "arg1", "arg2"}, new Properties(), true);
        assertNotNull(result);
        assertFalse(result.hasOption("a"));
        assertEquals(Arrays.asList("-x", "arg1", "arg2"), result.getArgList());
    }

    @Test
    void testParseWithNullArguments() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "option a");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments == null ? new String[0] : arguments;
            }
        }.parse(options, null, new Properties(), false);
        assertNotNull(result);
        assertFalse(result.hasOption("a"));
        assertTrue(result.getArgList().isEmpty());
    }

    @Test
    void testParseWithDoubleDashAndStopAtNonOption() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "option a");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"--", "-a", "value"}, new Properties(), true);
        assertNotNull(result);
        assertFalse(result.hasOption("a"));
        assertEquals(Arrays.asList("--", "-a", "value"), result.getArgList());
    }

    @Test
    void testParseWithSingleDashAndStopAtNonOption() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "option a");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"-", "-a", "value"}, new Properties(), true);
        assertNotNull(result);
        assertFalse(result.hasOption("a"));
        assertEquals(Arrays.asList("-", "-a", "value"), result.getArgList());
    }

    @Test
    void testParseWithMultipleDoubleDashes() throws ParseException {
        Options options = new Options();
        options.addOption("a", false, "option a");
        CommandLine result = new Parser() {
            @Override
            public String[] flatten(Options opts, String[] arguments, boolean stopAtNonOption) throws ParseException {
                return arguments;
            }
        }.parse(options, new String[]{"--", "--", "-a", "value"}, new Properties(), false);
        assertNotNull(result);
        assertFalse(result.hasOption("a"));
        assertEquals(Arrays.asList("--", "-a", "value"), result.getArgList());
    }

}