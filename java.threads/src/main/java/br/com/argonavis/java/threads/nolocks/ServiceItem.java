package br.com.argonavis.java.threads.nolocks;

public class ServiceItem {
	private Service service;
	private int hours;
	
	public ServiceItem(Service service, int hours) {
		super();
		this.service = service;
		this.hours = hours;
	}
	
	public double subtotal() {
		double rate = service.getRate();
		System.out.println(Thread.currentThread().getName() + " calculating " + rate + " x " + hours);
		Simulation.pause(200); // pretend this is a long calculation
		double result = rate * hours;
		Simulation.pause(300); // pretend this is a long calculation
		return result;
	}
	
}
