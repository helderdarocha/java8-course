package br.com.argonavis.java.arrays;

import java.util.Arrays;

public class ArraysTest1 {

	public static void main(String[] args) {
		double[] latitudes = {-6.88, -9.01, -16.7, -23.5};
		double [] longitudes = {-38.5, -42.7, -49.4, -46.8};
		String[] nomes = {"Cajazeiras","Sao Raimundo Nonato","Goiânia","São Paulo"};
		assert latitudes.length == nomes.length && nomes.length == longitudes.length;
		
		Localidade[] localidades = new Localidade[nomes.length];
		for(int i = 0; i < nomes.length; i++) {
			localidades[i] = new Localidade();
			localidades[i].setLatitude(latitudes[i]);
			localidades[i].setLongitude(longitudes[i]);
			localidades[i].setNome(nomes[i]);
		}

		for(Localidade loc : localidades) {
			System.out.println(loc);
		}
		
		System.out.println(Arrays.toString(latitudes));
		
		int[] numeros = {7,23,9,122,6,34,8,2};
		Arrays.sort(numeros);
		System.out.println(Arrays.toString(numeros));
		
		int[] slice = Arrays.copyOf(numeros,  3);
		System.out.println(Arrays.toString(slice));
		
		int[] range = Arrays.copyOfRange(numeros,  2, 5);
		System.out.println(Arrays.toString(range));
		
		String[] textos = {"sapo", "Zebra", "anta", "Águia", "Antílope", "bode"};
		Arrays.sort(textos);
		System.out.println(Arrays.toString(textos));

	}

}
