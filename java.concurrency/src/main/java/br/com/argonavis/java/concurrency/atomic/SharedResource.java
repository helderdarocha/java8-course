package br.com.argonavis.java.concurrency.atomic;

import java.util.concurrent.atomic.AtomicLong;

public class SharedResource {
	static AtomicLong atomic = new AtomicLong(2);
	static long simple = 2;
}
