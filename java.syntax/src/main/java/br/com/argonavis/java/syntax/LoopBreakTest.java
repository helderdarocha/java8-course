package br.com.argonavis.java.syntax;

public class LoopBreakTest {

	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			int azar = (int)(Math.random() * 10);
			if(i == azar) {
				System.out.println("Deu azar! Caindo fora!");
				break;
			}
			System.out.println("Linha " + i);
		}
		System.out.println("Fim!");
	}
}
