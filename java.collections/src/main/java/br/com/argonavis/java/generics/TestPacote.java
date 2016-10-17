package br.com.argonavis.java.generics;

public class TestPacote {

	public static void main(String[] args) {
		Pacote<String>  pacote1 = new Pacote<>("Uma frase"); // Elemento é String
		Pacote<Integer> pacote2 = new Pacote<>(12345);       // Elemento é Integer
		
		Etiqueta<Integer, Pacote<String>> etiqueta1 = new Etiqueta<>();
		etiqueta1.setCodigo(123);
		etiqueta1.setItem(pacote1);
		
		Etiqueta<String, int[]> etiqueta2 = new Etiqueta<>();
		etiqueta2.setCodigo("456");
		etiqueta2.setItem(new int[]{1,2,3});
		
		int numero = pacote2.getConteudo();
		String txt = pacote1.getConteudo();
		
		System.out.println("Pacote 1: " + txt);
		System.out.println("Pacote 2: " + numero);
		
		int    cod1 = etiqueta1.getCodigo();
		String cod2 = etiqueta2.getCodigo();
		Pacote<String> item1 = etiqueta1.getItem();
		int[] item2 = etiqueta2.getItem();
		
		System.out.println("Etiqueta 1: codigo:" + cod1 + ", item:" + item1);
		System.out.println("Etiqueta 2: codigo:" + cod2 + ", item:" + item2);
		
	}

}
