package br.com.argonavis.java.concurrency.forkjoin;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

import br.com.argonavis.java.concurrency.Utils;

class WordCounter extends CountedCompleter<Integer> {

	private static final long serialVersionUID = 1L;
	private static ReentrantLock wordTaskLock = new ReentrantLock();

	private enum Level {
		TEXT, LINE, WORD
	};

	private final AtomicReference<String> wordToCount;
	private final AtomicReference<String> sourceText;
	private final AtomicInteger results;
	private final Level level;
	private final Set<WordCounter> childTasks;

	WordCounter(CountedCompleter<Integer> parentTask, AtomicReference<String> wordToCount, String sourceText,
			Level level) {
		super(parentTask);
		this.wordToCount = wordToCount;
		this.sourceText = new AtomicReference<>(sourceText);
		this.results = new AtomicInteger();
		this.childTasks = Collections.synchronizedSet(new HashSet<>());
		this.level = level;
	}

	public WordCounter(String wordToCount, String sourceText) {
		this.wordToCount = new AtomicReference<>(wordToCount);
		this.sourceText = new AtomicReference<>(sourceText);
		this.results = new AtomicInteger();
		this.childTasks = Collections.synchronizedSet(new HashSet<>());
		this.level = Level.TEXT;
	}

	@Override
	public void compute() {
		switch (level) {
		case TEXT: // split text into lines
			String[] lines = sourceText.get().split("\n");
			Arrays.stream(lines).forEach(line -> {
				WordCounter lineTask = new WordCounter(this, wordToCount, line, Level.LINE);
				childTasks.add(lineTask);
				lineTask.fork();
				addToPendingCount(1); // increment count on pending lines
			});

			System.out.println(this.level + ": Child tasks: " + childTasks.size());
			break;
		case LINE: // this line has spaces; reduce to words
			String[] words = sourceText.get().split("[\\p{Punct}\\s]+");
			Arrays.stream(words).forEach(word -> {
				WordCounter wordTask = new WordCounter(this, wordToCount, word, Level.WORD);
				childTasks.add(wordTask);
				wordTaskLock.lock();
				try {
					wordTask.fork();
					addToPendingCount(1); // increment count on pending words
				} finally {
					wordTaskLock.unlock();
				}
			});

			// System.out.println(this.level + ": Child tasks: " + childTasks.size());
			break;
		case WORD: // word
			wordTaskLock.lock();
			try {
				if ( wordToCount.get().toLowerCase().equals(sourceText.get().toLowerCase())) {
					results.getAndIncrement();
				}
			} finally {
				wordTaskLock.unlock();
			}
			break;
		}
		tryComplete(); // this completes the task if no more are pending

	}

	@Override
	public void onCompletion(CountedCompleter<?> completer) {
		// if(this.level != Level.WORD) System.out.println("Finished processing " + this.level);
		WordCounter parent = (WordCounter) this.getCompleter();
		if(parent != null)
			parent.results.addAndGet(results.get());
	}

	@Override
	public Integer getRawResult() {
		return results.get();
	}
}

public class CountedCompletionWordCounter {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

		ForkJoinPool pool = ForkJoinPool.commonPool();

		String sourceText = Utils.loadText(System.getProperty("user.dir") + "/target/classes/2701.txt");
		// String sourceText = "A test sentence with a whale, a dolphin, \na line with fish, \nanother whale,\nand nothing more.";

		String word = "whale";
		WordCounter task = new WordCounter(word, sourceText);
		long start = System.currentTimeMillis();

		ExecutorService status = Executors.newSingleThreadExecutor();
		status.execute(() -> {
			while (!task.isDone()) {
				System.out.printf("Parallelism: %d, threads: %d, tasks: %d, steals: %d, duration: %d ms.\n",
						pool.getParallelism(), pool.getActiveThreadCount(), pool.getQueuedTaskCount(),
						pool.getStealCount(), System.currentTimeMillis() - start);
				try {
					TimeUnit.MILLISECONDS.sleep(50);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});

		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		int wcount = pool.invoke(task);

		status.shutdown();
		System.out.println("There " + (wcount == 1 ? "is" : "are") + " " + wcount + " occurrence"
				+ (wcount == 1 ? "" : "s") + " of [" + word + "] in the source text.");
		System.out.println("Completed in " + (System.currentTimeMillis() - start) + " ms.");

	}
}
