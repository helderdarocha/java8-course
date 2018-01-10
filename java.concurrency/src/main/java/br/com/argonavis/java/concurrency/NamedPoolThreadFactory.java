package br.com.argonavis.java.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class NamedPoolThreadFactory implements ThreadFactory {
	private String name;
	private int threadCount = 0;
	
	public NamedPoolThreadFactory(String name) {
		this.name = name;
	}

	@Override
	public Thread newThread(Runnable task) {
        Thread t = Executors.defaultThreadFactory().newThread(task);
        t.setName(name + "-" + ++threadCount);
        return t;
	}
}
