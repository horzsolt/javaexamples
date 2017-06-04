package horzsolt.javaexamples.test.venkat;

import horzsolt.javaexamples.helper.ExecuteWithMetrics;
import org.junit.Test;

/**
 * Created by horzsolt on 2017. 06. 04..
 */
public class FunctionalInterfacesInJava8_2 {

    public static int op1(int value) {
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {}

        return value * 2;
    }

    @Test
    public static void runExample() {

        int result = ExecuteWithMetrics.exec2(() -> op1(2));
        System.out.println("FunctionalInterfacesInJava8_2 intResult: " + result);

        double result2 = ExecuteWithMetrics.exec2(() -> op1(2) * 1.0);
        System.out.println("FunctionalInterfacesInJava8_2 doubleResult: " + result2);
    }
}
