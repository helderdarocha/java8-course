package br.com.argonavis.java.concurrency.executors.service;

import br.com.argonavis.java.concurrency.Utils;

public class ConcurrentTask implements Runnable {
	private String name;
	public ConcurrentTask(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		Utils.log(">--- Started: " + name);
		Utils.simulatedPause(500);
		Utils.log("---[ Finished: " + name);
	}
}