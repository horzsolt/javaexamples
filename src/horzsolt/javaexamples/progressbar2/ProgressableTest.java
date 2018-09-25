package horzsolt.javaexamples.progressbar2;

import java.io.PrintStream;

/**
 * Represents a progressable arbitrary test contract
 */
public interface ProgressableTest {
    Long getCurrentValue();
    Long getMaxValue();
    void execute();
    PrintStream getOutputStream();
}


