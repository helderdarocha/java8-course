package br.com.argonavis.java.concurrency.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedResource {
	static AtomicInteger atomic = new AtomicInteger(2);
	static int simple = 2;
}
