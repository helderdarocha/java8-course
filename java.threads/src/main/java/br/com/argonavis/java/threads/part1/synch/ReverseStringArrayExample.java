package br.com.argonavis.java.threads.part1.synch;

public class ReverseStringArrayExample {
	public static void main(String[] args) {

		System.out.println("Results can be 12345 or 54321. Should not fail because array is synchronized.");
		SyncStringArray b = new SyncStringArray();
		
		new Thread(() -> {
			b.reverse();
			System.out.println("S1.1: " + b + check(b));
			b.reverse();
			System.out.println("S1.2: " + b + check(b));
		}).start();
		
		new Thread(() -> {
			b.reverse();
			System.out.println("S2.2: " + b + check(b));
			b.reverse();
			System.out.println("S2.2: " + b + check(b));
		}).start();
	}
	
	static String check(SyncStringArray a) {
		return (a.toString().equals("12345") || a.toString().equals("54321")) ? "" : " <--- FAILED!";
	}
}
