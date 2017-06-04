package horzsolt.javaexamples.helper;

import java.util.function.Supplier;

/**
 * Created by horzsolt on 2017. 06. 03..
 */
public class ExecuteWithMetrics {

    public static void exec(Runnable codeBlock) {
        long start = System.nanoTime();
        try {
            codeBlock.run();
        } finally {
            long end = System.nanoTime();
            System.out.println("Time taken : " + (end - start) / 1.0e9);
        }
    }

    public static <T> T exec2(Supplier<T> codeBlock) {
        long start = System.nanoTime();
        try {
            return codeBlock.get();
        } finally {
            long end = System.nanoTime();
            System.out.println("Time taken : " + (end - start) / 1.0e9);
        }
    }
}
