package br.com.argonavis.java.concurrency.atomic.primitive;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicDate {
	private AtomicLong timestamp = new AtomicLong(0);
	
	public void set(Date d) {
		this.timestamp.set(d.getTime());
	}
	
	public Date get() {
		return new Date(timestamp.get());
	}
	
	public Date tomorrow() {
		return new Date(timestamp.addAndGet(24*3600*1000));
	}
	
	public Date yesterday() {
		return new Date(timestamp.addAndGet(-24*3600*1000));
	}
}
