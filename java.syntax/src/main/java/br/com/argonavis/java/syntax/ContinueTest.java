package br.com.argonavis.java.syntax;

public class ContinueTest {

	public static void main(String[] args) {
		int iguais = 0;
		for(int i = 0; i < 10; i++) {
			int sorte = (int)(Math.random() * 10);
			if(i == sorte) {
				++iguais;
				continue;
			}
			System.out.println("Linha: " + i);
		}
		System.out.println("Elementos iguais: " + iguais);
	}

}
