package cursojava.intro.geometria;

public class Linha {
	public Ponto a, b;
	public double comprimento() { 
	    long q = (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
	    return Math.sqrt(q);
	}
}
