package br.com.argonavis.java.classes.geometria;

public class Circulo extends Figura {
	private int raio;
	public Circulo(int raio) {
		this.raio = raio;
	}
	@Override
	public double area() {
		return Math.PI * raio * raio;
	}
}
