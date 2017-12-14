package br.com.argonavis.java.concurrency.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {
	public static void main(String[] args) {
		System.out.println("Execução de 4 threads com agendamento");
		ScheduledExecutorService e = 
		     Executors.newScheduledThreadPool(2);
		e.schedule( new ConcurrentTask("Sc-Um"), 3, TimeUnit.SECONDS);
		e.schedule( new ConcurrentTask("Sc-Dois"), 3, TimeUnit.SECONDS);
		e.schedule( new ConcurrentTask("Sc-Tres"), 2, TimeUnit.SECONDS);
		e.schedule( new ConcurrentTask("Sc-Quatro"), 1, TimeUnit.SECONDS);
		e.shutdown(); // finaliza quando todos terminarem
	}
}
