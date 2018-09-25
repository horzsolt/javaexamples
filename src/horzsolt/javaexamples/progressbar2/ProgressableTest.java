package horzsolt.javaexamples.progressbar2;

import java.io.PrintStream;

public interface ProgressableTest {
    Long getCurrentValue();
    Long getMaxValue();
    void execute();
    PrintStream getOutputStream();
}


