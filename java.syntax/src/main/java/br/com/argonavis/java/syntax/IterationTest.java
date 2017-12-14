package br.com.argonavis.java.syntax;

import java.util.Arrays;

public class IterationTest {

	public static void main(String[] args) {
		
		int[] numbers = {96, 23, 45, 5, 22, 92, 29, 11, 24, 65, 28, 2, 33, 51, 80, 56, 7, 9, 1, 77, 48};
		
		// indexed
		for(int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] +" ");
		}
		
		System.out.println("\n-----");
		
		// iterative
		for(int item : numbers) {
			System.out.print(item +" ");
		}
		
		System.out.println("\n-----");
		
		// Streams + Lambda (Java 8)
		Arrays.stream(numbers)
		      .sorted()
		      .forEach(e -> System.out.print(e +" "));

	}

}
