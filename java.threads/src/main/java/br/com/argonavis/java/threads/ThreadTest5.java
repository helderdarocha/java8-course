package br.com.argonavis.java.threads;

public class ThreadTest5 {
	public static void main(String[] args) {

		SyncStringArray b = new SyncStringArray();
		
		new Thread(() -> {
			b.reverse();
			System.out.println("S1.1: " + b);
			b.reverse();
			System.out.println("S1.2: " + b);
		}).start();
		
		new Thread(() -> {
			b.reverse();
			System.out.println("S2.2: " + b);
			b.reverse();
			System.out.println("S2.2: " + b);
		}).start();
		

	}
}
