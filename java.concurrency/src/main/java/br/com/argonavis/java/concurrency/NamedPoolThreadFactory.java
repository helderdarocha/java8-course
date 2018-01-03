package br.com.argonavis.java.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class NamedPoolThreadFactory implements ThreadFactory {
	
	private String name;
	
	public NamedPoolThreadFactory(String name) {
		this.name = name;
	}

	@Override
	public Thread newThread(Runnable task) {
        Thread t = Executors.defaultThreadFactory().newThread(task);
        String[] oldName = t.getName().split("-");
        t.setName(name + "-" + oldName[oldName.length-3]);
        return t;
	}

}
