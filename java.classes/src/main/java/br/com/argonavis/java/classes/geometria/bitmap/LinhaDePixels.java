package br.com.argonavis.java.classes.geometria.bitmap;

public class LinhaDePixels {
	private Pixel a, b;
	
	public LinhaDePixels(Pixel a, Pixel b) {
		this.a = a;
		this.b = b;
	}
	
	public LinhaDePixels(int x1, int y1, int x2, int y2) {
		this.a = new Pixel(x1, y1, 0xff0000);
		this.b = new Pixel(x2, y2, 0xff000);
	}
	
	public Pixel getA() {
		return a;
	}

	public void setA(Pixel a) {
		this.a = a;
	}

	public Pixel getB() {
		return b;
	}

	public void setB(Pixel b) {
		this.b = b;
	}

	public double comprimento() { 
	    long q =  (a.getX() - b.getX()) * (a.getX() - b.getX()) 
	    		+ (a.getY() - b.getY()) * (a.getY() - b.getY());
	    return Math.sqrt(q);
	}
}
