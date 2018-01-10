package br.com.argonavis.java.concurrency.executors.futures;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import br.com.argonavis.java.concurrency.Utils;

public class CallableSubmissionExample {

	static class Computation implements Callable<Double> {
		@Override public Double call() throws Exception {
			Utils.log("Making computation.");
			return Math.sqrt(6 * 29 * 765);
		}
	}
	
	static class SampleTask implements Runnable {
		@Override public void run() {
			Utils.log(">-- Task starting.");
			Utils.simulatedPause(1000);
			Utils.log("--] Task ending.");
		}
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		
		Runnable runnableTask1 = new SampleTask();
		Runnable runnableTask2 = () -> Math.sqrt(144); // Runnable.run()
		Callable<Double> callableTask1 = () -> Math.sqrt(144); // Callable.call()
		Callable<Object> callableTask2 = Executors.callable(runnableTask1);
		Callable<String> callableTask3 = Executors.callable(runnableTask2, "Callable-2 Done"); 
		
		// Runnable
		Future<?>       c1 = service.submit(runnableTask1);
		Future<?>       c2 = service.submit(runnableTask2);
		Future<String>  c3 = service.submit(() -> Math.sqrt(144), "Runnable-3 Done"); // Runnable.run()
		
		// Callable
		Future<Double>  c4 = service.submit(callableTask1);
		Future<Object>  c5 = service.submit(callableTask2);
		Future<String>  c6 = service.submit(callableTask3);
		Future<Double>  c7 = service.submit(new Computation());
		Future<Double>  c8 = service.submit(() -> Math.sqrt(144)); // Callable.call()
		
		try {
			Utils.log("Waiting for C1");
			System.out.println("== Future-1 Runnable: " + c1.get());
			Utils.log("Waiting for C2");
			System.out.println("== Future-2 Runnable: " + c2.get());
			Utils.log("Waiting for C3");
			System.out.println("== Future-3 Runnable: " + c3.get());
			Utils.log("Waiting for C4");
			System.out.println("== Future-4 Callable: " + c4.get());
			Utils.log("Waiting for C5");
			System.out.println("== Future-5 Callable: " + c5.get());
			Utils.log("Waiting for C6");
			System.out.println("== Future-6 Callable: " + c6.get());
			Utils.log("Waiting for C7");
			System.out.println("== Future-7 Callable: " + c7.get());
			Utils.log("Waiting for C8");
			System.out.println("== Future-8 Callable: " + c8.get());
		} catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}

		service.shutdown();
	}
	
}
