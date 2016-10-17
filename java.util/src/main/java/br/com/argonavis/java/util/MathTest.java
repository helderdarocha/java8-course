package br.com.argonavis.java.util;

import java.util.Date;
import java.util.Random;

public class MathTest {

	public static void main(String[] args) {
		double numero1 = 123.56;
		double numero2 = 123.46;
		System.out.println("floor("+numero1+"): " + Math.floor(numero1));
		System.out.println("ceil("+numero1+"):  " + Math.ceil(numero1));
		System.out.println("round("+numero1+"): " + Math.round(numero1));
		System.out.println("floor("+numero2+"): " + Math.floor(numero2));
		System.out.println("ceil("+numero2+"):  " + Math.ceil(numero2));
		System.out.println("round("+numero2+"): " + Math.round(numero2));

        Random num = new Random();
        num.setSeed(new Date().getTime());
        System.out.println("nextInt(3): " + num.nextInt(3));
        System.out.println("nextDouble(): " + num.nextDouble());
        
        System.out.println("(int)(Math.random() * 100): " + (int)(Math.random() * 100));
	}

}
