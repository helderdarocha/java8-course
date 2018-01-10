package br.com.argonavis.java.concurrency.executors.futures;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import br.com.argonavis.java.concurrency.Utils;

public class FutureTaskDemo {

	static class RunnableTask implements Runnable {
		private volatile long result = -1;
		private final long input;

		public RunnableTask(long input) {
			this.input = input;
		}

		@Override
		public void run() {
			Utils.log("Starting task.");
			Utils.simulatedPause(1000);
			result = System.currentTimeMillis() - input;
			Utils.log("Ending task.");
		}

		public long getLastResult() {
			return result;
		}
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(4);
		long start = System.currentTimeMillis();
		RunnableTask runnableTask = new RunnableTask(start); // shared

		List<FutureTask<LocalTime>> taskList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Callable<LocalTime> callableTask = Executors.callable(runnableTask,
					LocalTime.ofInstant(Instant.ofEpochMilli(start), ZoneId.systemDefault()));
			taskList.add(new FutureTask<>(callableTask));
		}
		List<FutureTask<LocalTime>> tasks = Collections.unmodifiableList(taskList);

		for (FutureTask<LocalTime> task : tasks)
			service.execute(task);

		for (FutureTask<LocalTime> task : tasks) {
			try {
				Utils.log("Future task started at " + (task.get()) + " produced result: " + runnableTask.getLastResult());
			} catch (InterruptedException | ExecutionException e) {
				Utils.log("Exception " + e + " in " + task);
			}
		}
		service.shutdown();
	}

}
