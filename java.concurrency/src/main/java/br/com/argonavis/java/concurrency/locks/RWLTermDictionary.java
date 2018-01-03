package br.com.argonavis.java.concurrency.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import br.com.argonavis.java.concurrency.Utils;

public class RWLTermDictionary {
	private final Map<String, String> dict = new HashMap<>();
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	private final Lock read = lock.readLock();
	private final Lock write = lock.writeLock();

	public String lookup(String key) {
		read.lock();
		Utils.begin("Read");
		try {
			return dict.get(key);
		} finally {
			Utils.end("Read");
			read.unlock();
		}
	}

	public void insert(String key, String term) {
		write.lock();
		Utils.begin("Write");
		try {
			dict.put(key, term);
		} finally {
			Utils.end("Write");
			write.unlock();
		}
	}
}
