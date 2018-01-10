package br.com.argonavis.java.concurrency.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicReference;

import br.com.argonavis.java.concurrency.Utils;

class ForkJoinReverse extends RecursiveTask<String> {

	private static final long serialVersionUID = 1L;
	private final AtomicReference<String> text;
	private static int joinCount = 0;
	private static int forkCount = 0;

	public ForkJoinReverse(String text) {
		Utils.log("Fork " + ++forkCount + ": [" + text + "]");
		this.text = new AtomicReference<>(text);
	}

	public String[] split(String word) {
		Utils.log("Text to split: [" + word + "]");
		int len = word.length() / 2;
		String left = word.substring(0, len);
		String right = word.substring(len, word.length());
		return new String[] { left, right };
	}

	@Override
	public String compute() {
		if (text.get().length() < 2) {
			Utils.log("Char: [" + text + "]");
		} else if (text.get().length() == 2) {
			// System.out.println("2 chars: [" + text + "]");
			text.set("" + text.get().charAt(1) + text.get().charAt(0)); // concat & reverse
			Utils.log("Chars reversed: [" + text + "]");
		} else {
			String[] parts = split(text.get());
			Utils.log("Split parts: " + Arrays.toString(parts));
			ForkJoinReverse subTask1 = new ForkJoinReverse(parts[0]);
			ForkJoinReverse subTask2 = new ForkJoinReverse(parts[1]);

			subTask1.fork();
			subTask2.fork();

			String result1 = subTask1.join();
			int st1 = ++joinCount;

			String result2 = subTask2.join();
			int st2 = ++joinCount;

			Utils.log("Join " + st1 + ": [" + result1 + "]");
			Utils.log("Join " + st2 + ": [" + result2 + "]");

			text.set(result2 + result1); // concat & reverse
			Utils.log("Joined " + st2 + " + " + st1 + ": [" + text + "]");
		}
		return text.get();
	}
}

public class RecursiveTaskReverseText {
	public static void main(String[] args) {

		// Factory to change the names of the threads
		ForkJoinWorkerThreadFactory factory = new ForkJoinPool.ForkJoinWorkerThreadFactory() {
			@Override
			public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
				final ForkJoinWorkerThread worker = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
				worker.setName("{Th-" + worker.getPoolIndex() + "}");
				return worker;
			}
		};

		// Using custom pool as an example (in real projects, prefer the common pool)
		int parallelism = 4;
		ForkJoinPool pool = new ForkJoinPool(parallelism, factory, null, false); 

		long start = System.currentTimeMillis();
		ForkJoinReverse task = new ForkJoinReverse("ABCDE");
		// ForkJoinReverse task = new ForkJoinReverse(args[0]); // large text

		String result = pool.invoke(task);
		Utils.log(">>> [" + result + "] <<<");
		System.out.println("Elapsed time: " + (System.currentTimeMillis() - start) + " ms");
	}
}
