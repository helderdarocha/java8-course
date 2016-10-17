package cursojava.intro.geometria;

public class Ponto {
	public int x;
	public int y;
	
	public Ponto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public double distancia() {
		return Math.sqrt(x * x + y * y);
	}
	
	public double distancia(Ponto p) { 
		long q = (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y);
		return Math.sqrt(q);
	}

}
