import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class LongAdderExample {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		LongAdder counter = new LongAdder();
		ExecutorService executorService = Executors.newFixedThreadPool(8);
		 
		int numberOfThreads = 4;
		int numberOfIncrements = 100;
		
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
		System.out.println("Finishing: " + latch.getCount());		
		assertEquals(counter.sum(), numberOfIncrements * numberOfThreads);
		
		executorService.shutdown();
		System.out.println("Exiting...");
	}

}
