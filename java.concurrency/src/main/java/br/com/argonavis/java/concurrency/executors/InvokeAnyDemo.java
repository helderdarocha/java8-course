package br.com.argonavis.java.concurrency.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import br.com.argonavis.java.concurrency.Utils;

public class InvokeAnyDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(4);
		List<Callable<Integer>> tasks = new ArrayList<>();

		for (int i = 0; i < 2000; i++) { // will stop when any result is available
			tasks.add(() -> {
				Utils.simulatedPause(1000);
				int number = new Random().nextInt(50) + 1;
				System.out.println("Generated: " + number);
				return number;
			});
		}

		System.out.println("Selected number: " + service.invokeAny(tasks));
		service.shutdown();
	}
}
