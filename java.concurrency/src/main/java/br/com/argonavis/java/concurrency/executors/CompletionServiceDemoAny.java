package br.com.argonavis.java.concurrency.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import br.com.argonavis.java.concurrency.Utils;

public class CompletionServiceDemoAny {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(3);
		List<Callable<Integer>> processors = new ArrayList<>();

		for (int i = 0; i < 2000; i++) { // will stop when any result is available
			processors.add(() -> {
				Utils.simulatedPause(1000);
				int number = new Random().nextInt(50) + 1;
				System.out.println("Generated: " + number);
				return number;
			});
		}

		CompletionService<Integer> cs = new ExecutorCompletionService<>(service);
		List<Future<Integer>> futures = new ArrayList<>(processors.size());

		processors.forEach(proc -> futures.add(cs.submit(proc)));
		Integer result = 0;
		for (int i = futures.size(); i > 0; i--) {
			result = cs.take().get();
			if (result != null)
				break;
		}

		futures.forEach(future -> future.cancel(true)); // cancel others
		System.out.println("Selected number: " + result);
		service.shutdown();
	}
}
