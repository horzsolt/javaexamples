package horzsolt.javaexamples.horzsolt.progressbar;

import java.io.PrintStream;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.concurrent.TimeUnit;

/**
 * Run an arbitrary code and print progress on the provided stream.
 * The progressbar stops when:
 * - the provided currentvalue is less the the maxvalue
 * - exception thrown
 */
public class ProgressBar {

  private static final long REFRESH_INTERVAL = 1000L;

  private PrintStream stream;
  private Supplier<Long> currentValue;
  private long maxValue;
  private Thread progressBar;
  private volatile boolean completed = false;
  private volatile boolean exception = false;
  private long startTime;

  /**
   *
   * @param stream Used to display the progress
   * @param currentValue Lambda that returns the currentvalue
   * @param maxValue Maximum value of the progress
   */
  ProgressBar(PrintStream stream, Supplier<Long> currentValue, long maxValue) {
    this.stream = stream;
    this.currentValue = currentValue;
    this.maxValue = maxValue;
    this.progressBar = new Thread(new ProgressBarThread());
  }

  /**
   * Start a task with a progessbar without any in/out parameters
   *
   * @param task
   * @throws Exception
   */
  public void start(Runnable task) throws Exception {

    startTime = System.nanoTime();

    try {

      progressBar.start();
      task.run();

    } catch (Exception e) {
      exception = true;
    } finally {

      completed = true;

      try {
        progressBar.join();
      } catch (InterruptedException e) {
        throw e;
      }

      return result;
    }
  }

  /**
   * Start a task with only out parameters.
   *
   * @param task
   * @param <T>
   * @return
   * @throws Exception
   */
  public <T> T start(Supplier<T> task) throws Exception {

    startTime = System.nanoTime();
    T result = null;

    try {

      progressBar.start();
      result = task.get();

    } catch (Exception e) {
      exception = true;
    } finally {

      completed = true;

      try {
        progressBar.join();
      } catch (InterruptedException e) {
        throw e;
      }

      return result;
    }
  }

  /**
   * Start a task with in/out parameters.
   *
   * @param input
   * @param task
   * @param <T>
   * @param <R>
   * @return
   * @throws Exception
   */
  public <T, R> R start(T input, Function<T, R> task) throws Exception {

    startTime = System.nanoTime();
    R result = null;

    try {

      progressBar.start();
      result = task.apply(input);

    } catch (Exception e) {
      exception = true;
      throw e;
    } finally {

      completed = true;

      try {
        progressBar.join();
      } catch (InterruptedException e) {
        throw e;
      }

      return result;
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
          if (completed) {
            break;
          }
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
      stream.print(sb);
    }
  }

}

