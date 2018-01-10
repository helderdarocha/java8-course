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

import br.com.argonavis.java.concurrency.Utils;

public class CompletionServiceDemoAll {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(3);
		List<Callable<Integer>> processors = new ArrayList<>();
		
		for(int i = 0; i < 6; i++) {
			processors.add(() -> {
				Utils.simulatedPause(1000);
				return new Random().nextInt(50)+1;
			});
		}

		CompletionService<Integer> cs = new ExecutorCompletionService<>(service);
		processors.forEach(cs::submit);

		System.out.println("Generated numbers (may repeat): ");
		for (int i = processors.size(); i > 0; i--) {
			System.out.print("[" + cs.take().get() + "]");
		}
		service.shutdown();
	}
}
