package br.com.argonavis.java8.jdbc.dao;

import java.util.List;

public class Cinemateca {

	public static void main(String[] args) {
		
		Filme novo = new Filme();
		novo.setAno(1968);
		novo.setDuracao(160);
		novo.setDiretor("Kubrick");
		novo.setTitulo("2001: A Space Odyssey");
		novo.setImdb("tt0062622");
		
		FilmeDAO dao = new FilmeDAO("postgresql");
		int id = dao.insert(novo);
		
		List<Filme> filmes = dao.findAll();
		
		for(Filme f : filmes) {
			System.out.println(f);
		}
 
		Filme kub = dao.findByID(id);
		System.out.println("Found: " + kub);
		
		kub.setDiretor("Stanley Kubrick");
		dao.update(kub);
		
		int res = dao.deleteByIMDB("tt0066921");
		res += dao.deleteByIMDB("tt0069293");
		res += dao.deleteByIMDB("tt0079944");
		
		System.out.println(res + " filmes removidos.");
		
        filmes = dao.findAll();
		
		for(Filme f : filmes) {
			System.out.println(f);
		}
		
	}

}
