package br.com.argonavis.java.syntax;

public class ConditionalTest {
	
	public static void main(String[] args) {
		String[] estacoes = {"primavera", "verao", "inverno", "outono", "nenhuma"};
		
		int sorte = (int) (Math.random() * 5); // gera um número de 0 a 4
		
		String estacao = estacoes[sorte];
		
		// switch
		switch(estacao) {
		case "primavera": // se entrar aqui, continua duas estacoes
			System.out.println("Primavera");
		case "verao":
			System.out.println("Verão");
			break;
		case "outono": // se entrar aqui, continua duas estacoes
			System.out.println("Outono");
		case "inverno":
			System.out.println("Inverno");
			break;
		default:
			System.out.println("Viagem cancelada");
		}
		
		// if-else
		if(sorte < 3) {
			System.out.println("Quente!");
		} else if (sorte == 4) {
			System.out.println("Cancelada!");
		} else {
			System.out.println("Frio!");
		}
		
		// Ternary operator
		String palavra = (sorte != 1) ? "posições" : "posição";
		System.out.println("Antes há " + sorte + " " + palavra + "!");
	}

}
