package horzsolt.javaexamples.test;

import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Created by horzsolt on 2017. 05. 14..
 */
public class PrimeSample_Venkat {

    public static boolean isPrime(int number) {
        return number > 1 &&
                IntStream.range(2, number)
                .noneMatch(i -> number % i == 0);
    }

    @Test
    public void runExample() {
        IntStream.range(1, 8)
                .forEach(i ->
                System.out.printf("isPrime(%d)? %b\n", i, isPrime(i)));
    }
}
