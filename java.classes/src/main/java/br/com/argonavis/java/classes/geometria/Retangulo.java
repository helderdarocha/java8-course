package br.com.argonavis.java.classes.geometria;

public class Retangulo extends Figura {
	private int altura, largura;
	
	public Retangulo(int altura, int largura) {
		this.altura = altura;
		this.largura = largura;
	}
	
	@Override
	public double area() {
		return altura * largura;
	}
	
	@Override
	public double perimetro() {
		return (largura + altura) * 2;
	}
}
