package horzsolt.javaexamples.progressbar2;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class ProgressBar {

    private static final long REFRESH_INTERVAL = 1000L;

    private final ProgressableTest task;
    private final Supplier<Long> currentValue;
    private final Thread progressBar;
    private final long maxValue;
    private final PrintStream stream;
    private volatile boolean exception = false;
    private long startTime;

    public ProgressBar(ProgressableTest task) {
        this.task = task;
        currentValue = () -> task.getCurrentValue();
        this.progressBar = new Thread(new ProgressBarThread());
        this.maxValue = task.getMaxValue();
        this.stream = task.getOutputStream();
    }

    public void start() {

        progressBar.start();

        try {
            startTime = System.nanoTime();
            task.execute();
        } catch (Exception e) {
            exception = true;
        }

    }

    private class ProgressBarThread implements Runnable {

        @Override
        public void run() {

            try {
                stream.println();
                long value;

                while ((value = currentValue.get()) < maxValue) {
                    print(value);
                    Thread.sleep(REFRESH_INTERVAL);
                }

                if (exception) {
                    stream.println();
                    stream.println("Incomplete termination, " + "check log for exception.");
                } else {
                    print(maxValue);
                }
                stream.println();
            } catch (InterruptedException e) {
                stream.println(e);
            }
        }

        /**
         * Given current value prints the progress bar.
         *
         * @param value
         */
        private void print(long value) {

            stream.print('\r');
            double percent = 100.0 * value / maxValue;
            StringBuilder sb = new StringBuilder();
            sb.append(" " + String.format("%.2f", percent) + "% |");

            for (int i = 0; i <= percent; i++) {
                sb.append('X');
            }
            for (int j = 0; j < 100 - percent; j++) {
                sb.append(' ');
            }
            sb.append("|  ");
            sb.append(value + "/" + maxValue);
            long timeInSec = TimeUnit.SECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
            String timeToPrint = String.format("%d:%02d:%02d", timeInSec / 3600, (timeInSec % 3600) / 60, timeInSec % 60);
            sb.append(" Time: " + timeToPrint);
            stream.print(sb + " ddjkélj");
        }
    }

}
