package br.com.argonavis.java.concurrency.executors;

import java.util.concurrent.Executor;

public class ExecutorTest {

	static class MultipleThreadExecutor implements Executor {
		private Thread[] threads;

		public MultipleThreadExecutor(int threadCount) {
			threads = new Thread[threadCount];
		}

		@Override
		public void execute(Runnable task) {
			System.out.println(this.getClass().getSimpleName() + ", Threads:");
			for (Thread t : threads) {
				t = new Thread(task);
				t.start();
			}
		}
	}

	static class SingleThreadExecutor implements Executor {
		@Override
		public void execute(Runnable task) {
			System.out.print(this.getClass().getSimpleName() + ", Thread = ");
			new Thread(task).start();
		}
	}
	
	static class SynchronousExecutor implements Executor {
		@Override
		public void execute(Runnable task) {
			System.out.print(this.getClass().getSimpleName() + ", Thread = ");
			task.run();
		}
	}
	
	public static void main(String[] args) {
		
		Runnable task = () -> System.out.println(Thread.currentThread().getName());

		SynchronousExecutor sync = new SynchronousExecutor();
		sync.execute(task);
		
		SingleThreadExecutor single = new SingleThreadExecutor();
		single.execute(task);
		
		MultipleThreadExecutor multiple = new MultipleThreadExecutor(5);
		multiple.execute(task);

	}

}
