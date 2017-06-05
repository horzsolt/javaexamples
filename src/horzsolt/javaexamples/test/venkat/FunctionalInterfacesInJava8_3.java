package horzsolt.javaexamples.test.venkat;

import horzsolt.javaexamples.helper.ExecuteWithMetrics;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by horzsolt on 2017. 06. 05..
 */
public class FunctionalInterfacesInJava8_3 {

    public static int op1(int value) {
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {}

        return value * 2;
    }

    @Test
    public void runExample() {

        List<Integer> values = Arrays.asList(10,20,30);

        values.stream()
                .map(index -> ExecuteWithMetrics.exec2(() -> op1(index)))
                .forEach(System.out::println);

        values.stream()
                .map(index -> ExecuteWithMetrics.exec3(index, value -> op1(value - 1)))
                .forEach(System.out::println);
    }
}
