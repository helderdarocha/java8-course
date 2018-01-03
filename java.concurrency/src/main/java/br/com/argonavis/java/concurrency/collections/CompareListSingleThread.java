package br.com.argonavis.java.concurrency.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

public class CompareListSingleThread {
	
	public static long test(Function<Integer, Integer> f, int repeats) {
		long start = System.currentTimeMillis();
		for(int i = 0; i < 100_000 * repeats; i++)
		    f.apply(i);
		return (System.currentTimeMillis() - start);
	}

	public static void main(String[] args) {
		List<Integer> arrayList = new ArrayList<>();
		System.out.println("ArrayList.add(): " + test(i->arrayList.add(i)?1:0, 100) + " ms");
		System.out.println("ArrayList.get(): " + test(i->arrayList.get(i), 100) + " ms");
		
		List<Integer> vector    = new Vector<>();
		System.out.println("Vector.add(): " + test(i->vector.add(i)?1:0, 100) + " ms");
		System.out.println("Vector.get(): " + test(i->vector.get(i), 100) + " ms");
		
		List<Integer> syncList  = Collections.synchronizedList(new ArrayList<>());
		System.out.println("SynchronizedList.add(): " + test(i->syncList.add(i)?1:0, 100) + " ms");
		System.out.println("SynchronizedList.get(): " + test(i->syncList.get(i), 100) + " ms");
		
		List<Integer> copyList  = new CopyOnWriteArrayList<>();
		System.out.println("CopyOnWriteArrayList.add() / 100: " + test(i->copyList.add(i)?1:0, 1) + " ms");
		List<Integer> copyList2  = new CopyOnWriteArrayList<>(arrayList);
		System.out.println("CopyOnWriteArrayList.get(): " + test(i->copyList2.get(i), 100) + " ms");

	}

}
