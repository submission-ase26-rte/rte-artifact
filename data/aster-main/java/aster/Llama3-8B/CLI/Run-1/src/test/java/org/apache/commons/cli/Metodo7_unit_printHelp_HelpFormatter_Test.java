/**
 * Filtered unit tests for method: printHelp(final PrintWriter pw, final int width, final String cmdLineSyntax, final String header, final Options options, final int leftPad, final int descPad, final String footer, final boolean autoUsage)
 * Original class: HelpFormatter
 * Tests that actually call the target method
 */
package org.apache.commons.cli;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Timeout.ThreadMode.SEPARATE_THREAD;

public class Metodo7_unit_printHelp_HelpFormatter_Test {

    @Test
    public void testPrintHelpCmdLineSyntaxEmpty_oycC2_GZrA0() {
    HelpFormatter helpFormatter = new HelpFormatter();
    PrintWriter printWriter = new PrintWriter(System.out);
    String cmdLineSyntax = "";
    String header = "header";
    Options options = new Options();
    int leftPad = 0;
    int descPad = 0;
    String footer = "footer";
    boolean autoUsage = false;
    helpFormatter.printHelp(printWriter, 80, cmdLineSyntax, header, options, leftPad, descPad, footer, autoUsage);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = SEPARATE_THREAD)
    public void testPrintHelp_DOIE0() {
    HelpFormatter helpFormatter = new HelpFormatter();
    PrintWriter printWriter = new PrintWriter(System.out);
    String cmdLineSyntax = "cmdLineSyntax";
    String header = "header";
    Options options = new Options();
    int leftPad = 0;
    int descPad = 0;
    String footer = "footer";
    boolean autoUsage = false;
    helpFormatter.printHelp(printWriter, 80, cmdLineSyntax, header, options, leftPad, descPad, footer, autoUsage);
    }
}