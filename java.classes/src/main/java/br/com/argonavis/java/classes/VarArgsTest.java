package br.com.argonavis.java.classes;
import java.util.Arrays;

public class VarArgsTest {

	public static void main(String[] args) {
		VarArgsTest obj = new VarArgsTest();

		int[] array = {7, 8, 9};
		double[] array2 = {1.0, 4.0, 9.0};
		
		obj.print(array);
		obj.print(1, 2, 3);
		
		obj.print(array2);
		obj.print(4.0, 6.0);

	}
	
	public void print(int[] array) {
		System.out.println("Int: " + Arrays.toString(array));
	}
	
	public void print(double... array) {
		System.out.println("Double: " + Arrays.toString(array));
	}

}
