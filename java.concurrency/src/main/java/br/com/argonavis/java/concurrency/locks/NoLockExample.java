package br.com.argonavis.java.concurrency.locks;

import static br.com.argonavis.java.concurrency.Utils.simulatedPause;

public class NoLockExample {

	public static void main(String[] args) {
		new Thread(() -> {
			System.out.println("operation1 before");
			SharedResource.data[0] = 10;
			simulatedPause(100);
			SharedResource.data[1] = 90;
			simulatedPause(200);
			SharedResource.data[2] = SharedResource.data[0] + SharedResource.data[1];
			simulatedPause(200);
			System.out.println(
					SharedResource.data[0] + " + " + SharedResource.data[1] + " = " + SharedResource.data[2]);
			System.out.println("operation1 after");
		}).start();

		new Thread(() -> {
			System.out.println("operation2 before");
			SharedResource.data[0] = 5;
			simulatedPause(100);
			SharedResource.data[1] = 7;
			simulatedPause(200);
			SharedResource.data[2] = SharedResource.data[0] * SharedResource.data[1];
			simulatedPause(200);
			System.out.println(
					SharedResource.data[0] + " * " + SharedResource.data[1] + " = " + SharedResource.data[2]);
			System.out.println("operation2 after");
		}).start();

	}

}
