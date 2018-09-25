package horzsolt.javaexamples.test;

import horzsolt.javaexamples.progressbar2.ProgressBar;
import horzsolt.javaexamples.progressbar2.SampleTest1;
import org.junit.Test;

public class ProgressBar2Test {

    @Test
    public void simpleTestRun() {

        ProgressBar test1 = new ProgressBar(new SampleTest1());
        test1.start();

    }

    public static void main(String[] args) {
        ProgressBar test1 = new ProgressBar(new SampleTest1());
        test1.start();
    }
}
