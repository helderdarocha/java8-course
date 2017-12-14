package br.com.argonavis.java.threads;

public class ThreadTest4 {
	public static void main(String[] args) {

		StringArray a = new StringArray();
		
		new Thread(() -> {
			a.reverse();
			System.out.println("T1.1: " + a);
			a.reverse();
			System.out.println("T1.2: " + a);
		}).start();
		
		new Thread(() -> {
			a.reverse();
			System.out.println("T2.1: " + a);
			a.reverse();
			System.out.println("T2.2: " + a);
		}).start();

	}
}
