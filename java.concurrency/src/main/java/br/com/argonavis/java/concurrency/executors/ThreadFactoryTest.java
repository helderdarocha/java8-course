package br.com.argonavis.java.concurrency.executors;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadFactoryTest {

	static class SimpleThreadFactory implements ThreadFactory {
		Map<String, Object> defaultProperties;
		private static int threadCount = 1;
		
		public SimpleThreadFactory() {
			this.defaultProperties = new HashMap<>();
		}

		public SimpleThreadFactory(Map<String, Object> defaultProperties) {
			this.defaultProperties = defaultProperties;
		}
		
		private void configure(Thread t) {
			if(defaultProperties.containsKey("DAEMON_THREAD")) {
				t.setDaemon(true);
			}
			if(defaultProperties.containsKey("NAME")) {
				t.setName((String)defaultProperties.get("NAME") + threadCount++);
			}
			if(defaultProperties.containsKey("PRIORITY")) {
				t.setPriority((Integer)defaultProperties.get("PRIORITY"));
			}
		}
		
		@Override
		public Thread newThread(Runnable task) {
			Thread t = new Thread(task);
			if(!defaultProperties.isEmpty())
				configure(t);
			return t;
		}
	}

	public static void main(String[] args) {
		// ThreadFactory
		ThreadFactory fac = new SimpleThreadFactory(Map.of("NAME", "th-", "PRIORITY", 6));
		Runnable task = () -> System.out.println(Thread.currentThread().toString());
		fac.newThread(task).start();
		fac.newThread(task).start();
		fac.newThread(task).start();
		
		// ExecutorService
		ThreadPoolExecutor service = new ThreadPoolExecutor(3, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
		service.setThreadFactory(new SimpleThreadFactory(Map.of("NAME", "zzz-", "PRIORITY", 7)));
		service.execute(task);
		service.execute(task);
		service.execute(task); 
		service.execute(task); // will reuse threads
		service.execute(task);
		service.execute(task);
		service.shutdown();
		
		
	}

}
