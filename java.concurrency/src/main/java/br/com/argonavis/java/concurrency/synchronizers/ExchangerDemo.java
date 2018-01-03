package br.com.argonavis.java.concurrency.synchronizers;

import java.util.Arrays;
import java.util.concurrent.Exchanger;

public class ExchangerDemo {

	static Exchanger<String[]> exchanger = new Exchanger<>();
	static String[] letters = { "A", "B", "C", "D", "E", "F" }; 
	static String[] digits  = { "1", "2", "3", "4", "5", "6" };

	public static void main(String[] args) {
		System.out.println("Letters: " + Arrays.toString(letters));
		System.out.println("Digits: " + Arrays.toString(digits));
		
		new Thread(() -> {
			try {
				String[] result = exchanger.exchange(letters);
				System.out.println("Received in exchange for letters: " + Arrays.toString(result));
			} catch (InterruptedException e) { e.printStackTrace(); }
		}).start();
		
		new Thread(() -> {
			try {
				String[] result = exchanger.exchange(new String[] {"cow"});
				System.out.println("Received in exchange for cow: " + Arrays.toString(result));
			} catch (InterruptedException e) { e.printStackTrace(); }
		}).start();
		
		new Thread(() -> {
			try {
				String[] result = exchanger.exchange(digits);
				System.out.println("Received in exchange for digits: " + Arrays.toString(result));
			} catch (InterruptedException e) { e.printStackTrace(); }
		}).start();
		
		new Thread(() -> {
			try {
				String[] result = exchanger.exchange(new String[] {"cat", "dog"});
				System.out.println("Received in exchange for cat & dog: " + Arrays.toString(result));
			} catch (InterruptedException e) { e.printStackTrace(); }
		}).start();
	}
}