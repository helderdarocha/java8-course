package br.com.argonavis.java.threads.nolocks;

public class Service {
	private double rate;
	private String description;

	public Service(String description, double rate) {
		super();
		this.rate = rate;
		this.description = description;
	}
	
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
