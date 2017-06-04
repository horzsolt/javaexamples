package horzsolt.javaexamples.test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

import org.junit.Test;

public class LongAccumulatorExample {

	@Test
	public void runExample() throws InterruptedException {
		
		int numberOfThreads = 4;
		int numberOfIncrements = 100;
		
		LongAccumulator accumulator = new LongAccumulator(Long::sum, 0L);
		ExecutorService executorService = Executors.newFixedThreadPool(8);
		final CountDownLatch latch = new CountDownLatch(numberOfIncrements * numberOfThreads);
		 
		Runnable accumulateAction = () -> IntStream
		  .rangeClosed(0, numberOfIncrements)
		  .forEach(i -> {
			  accumulator.accumulate(i);
			  latch.countDown();
		  });
			  
		 
		for (int i = 0; i < numberOfThreads; i++) {
		    executorService.execute(accumulateAction);
		}
		
		latch.await();
		assertEquals(accumulator.get(), 20200);
		executorService.shutdown();
	}

}
