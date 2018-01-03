package br.com.argonavis.java.concurrency.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UnmodifiableExample {

	public static void main(String[] args) {
		List<String> listaMutavel = new ArrayList<>();
		listaMutavel.add("Um");
		listaMutavel.add("Dois");
		List<String> listaImutavel = Collections.unmodifiableList(listaMutavel);
		listaMutavel = null; // apenas acesso imutável é permitido agora
		for(String s : listaImutavel) 
		    System.out.println(s);

		try {
			listaImutavel.add("Tres");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		List<Integer> vazia  = Collections.emptyList(); // Lista imutável e vazia
		Set<String> unidade = Collections.singleton("Hello");  // Set imutável contendo apenas o número 12
		
		List<String> vazia2 = List.of();  // mesmo que Collections.emptyList()
		Set<String> unidade2 = Set.of("Hello");  // mesmo que Collections.singleton("Hello")
		Set<Integer> conjunto = Set.of(2, 5, 8, 45, 67, 99);  // similar a Collections.unmodifiableSet()
		Map<String, Long> mapa = Map.of("Um", 1L, "Dois", 2L, "Tres", 3L);

		if(vazia.equals(vazia2)) System.out.println("Collections.emptyList() equals List.of()");
		if(unidade.equals(unidade2)) System.out.println("Collections.singleton(\"Hello\") equals Set.of(\"Hello\")");
		
		System.out.println(conjunto);
		
		for(Map.Entry<String, Long> entry : mapa.entrySet()) 
		    System.out.println(entry.getKey() + ": " + entry.getValue());
		

	}

}
