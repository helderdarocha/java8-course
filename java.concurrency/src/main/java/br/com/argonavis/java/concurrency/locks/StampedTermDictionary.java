package br.com.argonavis.java.concurrency.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

import br.com.argonavis.java.concurrency.Utils;

public class StampedTermDictionary {
	private final Map<String, String> dict = new HashMap<>();
	private final StampedLock lock = new StampedLock();

	public String lookup(String key) {
		long stamp = lock.readLock();
		try {
			Utils.begin("Read");
			return dict.get(key); 
		} finally {
			Utils.end("Read");
			lock.unlock(stamp);
		}
	}

	public void insert(String key, String term) {
		long stamp = lock.writeLock(); 
		try {
			Utils.begin("Write");
			dict.put(key, term);
		} finally {
			Utils.end("Write");
			lock.unlock(stamp);
		}
	}
}
