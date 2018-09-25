package horzsolt.javaexamples.progressbar2;

import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

/**
 * Example progressable test
 */
public class SampleTest1 implements ProgressableTest {

    AtomicLong currentValue = new AtomicLong(0L);

    public Long getCurrentValue() {
        return currentValue.get();
    }

    @Override
    public Long getMaxValue() {
        return 60L;
    }

    @Override
    public PrintStream getOutputStream() {
        return System.out;
    }

    public void execute() {
        IntStream.range(0, 20).forEach(
                counter -> {
                    System.out.println("Do something important: " + counter);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    currentValue.incrementAndGet();
                }
        );
    }
}

