package br.com.argonavis.java.syntax;

public class ContinueTest {

	public static void main(String[] args) {
		// for
		int iguais = 0;
		for(int i = 0; i < 10; i++) {
			int sorte = (int)(Math.random() * 10);
			if(i == sorte) {
				++iguais;
				continue;
			}
			System.out.println("For Linha: " + i);
		}
		System.out.println("Elementos iguais: " + iguais);
		
		// while
		iguais = 0;
		int i = 0;
        while(i < 10) {
			int sorte = (int)(Math.random() * 10);
			if(i == sorte) {
				++iguais;
				continue;
			}
			System.out.println("While Linha: " + i);
			i++;
		}
		System.out.println("Elementos iguais: " + iguais);
	}

}
