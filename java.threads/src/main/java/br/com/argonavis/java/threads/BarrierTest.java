package br.com.argonavis.java.threads;

import java.util.Arrays;
import java.util.Random;

public class BarrierTest {

	public static void main(String[] args) {
		
		int[] components = new int[3];
		
		Barrier b = new Barrier(
				new Thread(()   -> {
					components[0] = new Random().nextInt(256);
					try {Thread.sleep(1000);} catch (InterruptedException e) {}
					System.out.println("Operation one done. b.acum = " + components[0]);
				}), 
				new Thread(()   -> {
					components[1] = new Random().nextInt(256);
					try {Thread.sleep(5000);} catch (InterruptedException e) {}
					System.out.println("Operation two done. b.acum = " + components[1]);
				}), 
				new Thread(() -> {
					components[2] = new Random().nextInt(256);
					try {Thread.sleep(2000);} catch (InterruptedException e) {}
					System.out.println("Operation three done. b.acum = " + components[2]);
				}));

		
		System.out.println("Waiting for all operations to finish...");
		b.startThreads();
		b.waitForThreads();
		
		System.out.println("Random color RGB components: " + Arrays.toString(components));

	}

}
