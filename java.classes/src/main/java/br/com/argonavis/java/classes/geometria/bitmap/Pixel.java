package br.com.argonavis.java.classes.geometria.bitmap;

import br.com.argonavis.java.classes.geometria.Ponto;

public class Pixel extends Ponto {
	private int cor;
	
	public Pixel() {
		this(0,0, 0xff0000);
	}
	
	public Pixel(int x, int y, int cor) {
		super(0,0);
		this.cor = cor;
	}

	public int getCor() {
		return cor;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}
	
	public double distancia(Pixel p) { 
		long q = (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y);
		return Math.sqrt(q);
	}
	
	@Override
	public String imprimir() {
		return super.imprimir() + ", cor: " + Integer.toHexString(cor);
	}
	
}
