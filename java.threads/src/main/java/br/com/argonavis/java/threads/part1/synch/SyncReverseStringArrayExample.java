package br.com.argonavis.java.threads.part1.synch;

public class SyncReverseStringArrayExample {
	public static void main(String[] args) {

		System.out.println("Results can be 12345 or 54321 (but may fail because array is not synchronized).");
		StringArray a = new StringArray();
		
		new Thread(() -> {
			a.reverse();
			System.out.println("T1.1: " + a + check(a));
			a.reverse();
			System.out.println("T1.2: " + a + check(a));
		}).start();
		
		new Thread(() -> {
			a.reverse();
			System.out.println("T2.1: " + a + check(a));
			a.reverse();
			System.out.println("T2.2: " + a + check(a));
		}).start();

	}
	
	static String check(StringArray a) {
		return (a.toString().equals("12345") || a.toString().equals("54321")) ? "" : " <--- FAILED!";
	}
}
