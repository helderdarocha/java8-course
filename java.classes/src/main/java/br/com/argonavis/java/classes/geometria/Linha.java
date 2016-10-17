package br.com.argonavis.java.classes.geometria;

public class Linha {
	private Ponto a, b;
	
	public Linha(Ponto a, Ponto b) {
		this.a = a;
		this.b = b;
	}
	
	public Linha(int x1, int y1, int x2, int y2) {
		this.a = new Ponto(x1, y1);
		this.b = new Ponto(x2, y2);
	}
	
	public Ponto getA() {
		return a;
	}

	public void setA(Ponto a) {
		this.a = a;
	}

	public Ponto getB() {
		return b;
	}

	public void setB(Ponto b) {
		this.b = b;
	}

	public double comprimento() { 
	    long q =  (a.x - b.y) * (a.y - b.y) 
	    		+ (a.y - b.y) * (a.y - b.y);
	    return Math.sqrt(q);
	}
	
	@Override
	public Object clone() {
		try {
			Linha linha = (Linha)super.clone();
			linha.a = (Ponto)a.clone();
			linha.b = (Ponto)b.clone();
			return linha;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
