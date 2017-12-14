package cursojava.intro;

import cursojava.intro.geometria.Ponto;

public class MapaDePontos {
	public static void main(String[] args) {
		Ponto p1, p2, p3;
		p1 = new Ponto();
		p2 = new Ponto();
		p3 = new Ponto();
		
		p1.x = 50;
		p2.x = 10;
		p1.y = 125;
		
		System.out.println("p1.x: " + p1.x);
		System.out.println("p1.y: " + p1.y);
		System.out.println("p2.x: " + p2.x);
		/* Este trecho inteiro foi eliminado
		System.out.println("p2.y: " + p2.y);
		System.out.println("p3.x: " + p3.x);
		System.out.println("p3.y: " + p3.y);
		*/
		
		System.out.println("Distancia entre p1 e p2: " + distancia(p1, p2));
	}
	
	public static double distancia(Ponto a, Ponto b) { 
		long q = (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
		return Math.sqrt(q);
	}
}
