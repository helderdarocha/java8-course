package br.com.argonavis.java.concurrency.collections.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedLetter implements Delayed {
	
	private String letter;
	private long timeout;
	private TimeUnit sourceUnit;
	
	public DelayedLetter(String letter, long delay, TimeUnit sourceUnit) {
		this.letter = letter;
		this.timeout = System.currentTimeMillis() + delay;
		this.sourceUnit = sourceUnit;
	}

	@Override
	public int compareTo(Delayed other) {
		return (int)(this.getDelay(TimeUnit.MILLISECONDS) 
				  - other.getDelay(TimeUnit.MILLISECONDS));
	}

	@Override
	public long getDelay(TimeUnit unit) {
		long remaining = timeout - System.currentTimeMillis();
		return unit.convert(remaining > 0 ? remaining : 0, sourceUnit);
	}
	
	@Override
	public String toString() {
		return letter + ", (" + getDelay(TimeUnit.MILLISECONDS) + " ms)";
	}
	
	public String getLetter() {
		return letter;
	}
}
