package br.com.argonavis.java.classes;

import br.com.argonavis.java.classes.geometria.Ponto;
import br.com.argonavis.java.classes.geometria.bitmap.Pixel;

public class PixelTest {
	public static void main(String[] args) {
		Pixel pixel = new Pixel();
		System.out.println("x: " + pixel.getX());
		System.out.println("y: " + pixel.getY());
		
		Ponto ponto = pixel;
		System.out.println(ponto.imprimir()); // chama imprimir de Pixel!
	}
}
