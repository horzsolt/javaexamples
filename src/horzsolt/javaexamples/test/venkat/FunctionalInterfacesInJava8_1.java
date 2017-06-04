package horzsolt.javaexamples.test.venkat;

import horzsolt.javaexamples.helper.ExecuteWithMetrics;
import org.junit.Test;

/**
 * Created by horzsolt on 2017. 06. 03..
 */
public class FunctionalInterfacesInJava8_1 {

    public static void useless(int value) {
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {}

        if (value == 0) throw new RuntimeException("I don't like zero...");
        System.out.println("Called useless with " + value);
    }

    @Test()
    public void runExample() {
        ExecuteWithMetrics.exec(() -> useless(1));
        ExecuteWithMetrics.exec(() -> useless(2));
    }
}
