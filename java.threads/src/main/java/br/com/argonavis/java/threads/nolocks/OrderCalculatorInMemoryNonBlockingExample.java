package br.com.argonavis.java.threads.nolocks;

import java.util.ArrayList;
import java.util.List;

/**
 * This example uses in-memory objects and can operate with many threads in parallel
 * without locks (compare it with the JPA example which works on shared objects)
 *
 */
public class OrderCalculatorInMemoryNonBlockingExample {
	
	static class SubtotalTask implements Runnable {
		private List<Double> results = new ArrayList<>();
		private Order order;
		
		SubtotalTask(Order order) {
			this.order = order;
		}

		@Override
		public void run() {
			for(ServiceItem item : order.getItems()) {
				results.add(item.subtotal());
				System.out.println("Results: " + results);
			}
		}
		
		public List<Double> getResults() {
			return results;
		}
		
	}

	/**
	 * No synchronized needed since objects are created for each thread, are not shared, and calculations are independent
	 */
	public static void main(String[] args) throws InterruptedException {
		// Object hierarchy doesn't reuse object references (different objects for each structure)
		Service[] services = {new Service("Programming", 120.0), new Service("Reviewing", 60.0), new Service("Debugging", 80.0)};
		Order order1 = new Order();
		order1.addItem(new ServiceItem(services[0], 88));
		order1.addItem(new ServiceItem(services[1], 12));
		order1.addItem(new ServiceItem(services[2], 37));
		Order order2 = new Order();
		order2.addItem(new ServiceItem(services[0], 230));
		order2.addItem(new ServiceItem(services[1], 45));
		order2.addItem(new ServiceItem(services[2], 350));
		Order order3 = new Order();
		order3.addItem(new ServiceItem(services[0], 40));
		order3.addItem(new ServiceItem(services[1], 20));
		
		// Distinct task objects for each thread (each uses local objects)
		SubtotalTask[] tasks = {new SubtotalTask(order1), new SubtotalTask(order2), new SubtotalTask(order3)};
		
		Thread[] threads = new Thread[3];
		for(int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(tasks[i]);
			System.out.println("Starting " + threads[i].getName());
			threads[i].start();
		}
		
		for(Thread t : threads) {
			t.join();
			System.out.println("Finished: " + t.getName());
		}
		
		// Sum is calculated for each thread. Since objects are not shared, the operation can be done in parallel
		System.out.println("All done. Will calculate sum.");
		
		for(SubtotalTask task : tasks) {
			System.out.print("Results: " + task.getResults());
			double sum = 0;
			for(double result : task.getResults()) {
				sum += result;
			}
			System.out.println(", Sum: " + sum);
		}

	}

}
