package br.com.argonavis.java.threads.timer;

import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

public class TimerExample {
	
	static class RepeatingTask extends TimerTask {
		@Override public void run() {
			System.out.println("Timer tick at: " + Instant.now());
		}
	}

	public static void main(String[] args) {
		
		TimerTask task = new TimerTask() {
			@Override public void run() {
				System.out.println("Timer is done now: " + Instant.now());
				this.cancel();
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(task, 5000);
		System.out.println("Timer will end at: " + Instant.ofEpochMilli(task.scheduledExecutionTime()));
		
		timer.schedule(new RepeatingTask(), 7000, 1000); // start in 7s, repeat every 1s
		
		timer.schedule(new TimerTask() { 
			@Override public void run() {
				System.out.println("Timer is done: " + Instant.now());
				timer.cancel();
			}
			
		}, 12000);

	}

}
