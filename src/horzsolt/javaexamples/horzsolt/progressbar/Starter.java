package horzsolt.javaexamples.horzsolt.progressbar;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.lang.RuntimeException;

public class Starter {

    public static void main(String[] args) {

        AtomicLong numberOfTasksExecuted = new AtomicLong(0);
        Supplier<Long> currentValue = () -> numberOfTasksExecuted.get();
        int maxValue = 60;

        Supplier<Long> tasks = () -> {
            IntStream.range(0, maxValue).forEach(
                    counter -> {
                        System.out.println("Do something dummy task " + counter);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        numberOfTasksExecuted.incrementAndGet();
                    }
            );

            return 1L; //return the result of the dummy task
        };

        ProgressBar executor = new ProgressBar(System.out, currentValue, maxValue);

        try {
            executor.start(tasks);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
