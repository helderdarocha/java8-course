package br.com.argonavis.java.concurrency.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/** Adapted from JavaDocs beeper example. */
public class ScheduledFutureDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		Runnable tickTask = () -> System.out.print("tick ");

		// A scheduler for the repetitions
		ScheduledFuture<?> ticker = scheduler.scheduleAtFixedRate(tickTask, 5, 1, TimeUnit.SECONDS);
		

		// Another scheduler for cancellation
		Runnable cancelTask = () -> ticker.cancel(true);
		ScheduledFuture<?> terminator = scheduler.schedule(cancelTask, 15, TimeUnit.SECONDS);
		
		System.out.println("Will start ticking in: " + ticker.getDelay(TimeUnit.SECONDS) + " seconds.");
		System.out.println("Will cancel in: " + terminator.getDelay(TimeUnit.SECONDS) + " seconds.");
		terminator.get(); //  blocks until timed out (and ticker cancelled)
		System.out.println("\nTicks done.");
		terminator.cancel(true); // cancel terminator
		System.out.println("Terminator done.");
		scheduler.shutdown();
	}
}
