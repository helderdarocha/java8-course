package br.com.argonavis.java.concurrency.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import br.com.argonavis.java.concurrency.Utils;

public class CompareMapSingleThread {
	
	public static long test(Function<Integer, Integer> f) {
		long start = System.currentTimeMillis();
		for(int i = 0; i < 5_000_000; i++)
		    f.apply(i);
		return (System.currentTimeMillis() - start);
	}

	public static void main(String[] args) {
		Map<Integer, Integer> hashMap = new HashMap<>();
		System.out.println("HashMap.put(): " + test(i->hashMap.put(i,1)) + " ms");
		System.out.println("HashMap.get(): " + test(i->hashMap.get(i)) + " ms");
		
		Map<Integer, Integer> hashTable = new Hashtable<>();
		System.out.println("Hashtable.put(): " + test(i->hashTable.put(i,1)) + " ms");
		System.out.println("Hashtable.get(): " + test(i->hashTable.get(i)) + " ms");
		
		Map<Integer, Integer> syncMap = Collections.synchronizedMap(new HashMap<Integer, Integer>());
		System.out.println("SynchronizedMap.put(): " + test(i->syncMap.put(i,1)) + " ms");
		System.out.println("SynchronizedMap.get(): " + test(i->syncMap.get(i)) + " ms");
		
		Map<Integer, Integer> concMap = new ConcurrentHashMap<>();
		System.out.println("ConcurrentHashMap.put(): " + test(i->concMap.put(i,1)) + " ms");
		System.out.println("ConcurrentHashMap.get(): " + test(i->concMap.get(i)) + " ms");

	}

}
