package br.com.argonavis.java.threads;

public class ExceptionHandlerExample {
	
	static class InfoExceptionHandler implements Thread.UncaughtExceptionHandler {
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println("| Exception: " + e.getClass().getSimpleName());
			System.out.println("| Thread name: " + t.getName());
			System.out.println("| Thread priority: " + t.getPriority());
			System.out.println("| Thread ID: " + t.getId());
			System.out.println("| Thread state: " + t.getState());
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread.setDefaultUncaughtExceptionHandler(new InfoExceptionHandler());
		
		Thread t = new Thread(() -> {
			throw new RuntimeException();
		});
		t.setUncaughtExceptionHandler((th, ex) -> System.out.println(ex.getClass().getSimpleName() + " at " + th.getName())); 
		t.start();
		
		t.join();
		System.out.println();
		
		throw new RuntimeException();
	}
}
