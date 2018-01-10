package br.com.argonavis.java.concurrency.executors.service;

import br.com.argonavis.java.concurrency.Utils;

public class ExclusiveTask implements Runnable {
	@Override
	public void run() {
		Utils.log(">--- Exclusive task started.");
		Utils.simulatedPause(1000);
		Utils.log("---] Exclusive task finished.");
	}
}
