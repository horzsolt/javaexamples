package horzsolt.javaexamples.test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

import org.junit.Test;

public class LongAdderExample {
	
	@Test
	public void runExample() throws InterruptedException {
		
		int numberOfThreads = 4;
		int numberOfIncrements = 100;
		
		LongAdder counter = new LongAdder();
		ExecutorService executorService = Executors.newFixedThreadPool(8);
		final CountDownLatch latch = new CountDownLatch(numberOfIncrements * numberOfThreads);
		 
		Runnable incrementAction = () -> IntStream
		  .range(0, numberOfIncrements)
		  .forEach(i -> {
			  counter.increment();
			  latch.countDown();
			  System.out.println(latch.getCount());
		  });
		 
		for (int i = 0; i < numberOfThreads; i++) {
		    executorService.execute(incrementAction);
		}
		
		latch.await();
		
		System.out.println("Counter before sum: " + counter.intValue());
		System.out.println("Finishing: " + latch.getCount());		
		assertEquals(counter.sum(), numberOfIncrements * numberOfThreads);
		
		executorService.shutdown();
		System.out.println("Exiting...");
	}
}
