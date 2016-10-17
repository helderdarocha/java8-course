package br.com.argonavis.java.classes;

import br.com.argonavis.java.classes.geometria.Circulo;
import br.com.argonavis.java.classes.geometria.Figura;
import br.com.argonavis.java.classes.geometria.Retangulo;

public class AbstractTest {
	public static void main(String[] args) {
		Figura fig1, fig2;
		fig1 = new Circulo(50);
		fig2 = new Retangulo(30,40);
		
		System.out.println("Área da Figura 1: " + fig1.area());
		System.out.println("Área da Figura 2: " + fig2.area());
		
		System.out.println("Perímetro da Figura 1: " + fig1.perimetro()); // usa default
		System.out.println("Perímetro da Figura 2: " + fig2.perimetro());
	}
}
