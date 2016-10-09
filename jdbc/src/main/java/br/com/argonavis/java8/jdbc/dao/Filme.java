package br.com.argonavis.java8.jdbc.dao;

public class Filme {
	
	private int id;
	private String imdb;
	private String titulo;
	private String diretor;
	private int ano;
	private int duracao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getImdb() {
		return imdb;
	}
	public void setImdb(String imdb) {
		this.imdb = imdb;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDiretor() {
		return diretor;
	}
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	
	public String toString() {
		return id + ": " 
	            + imdb + " '" 
				+ titulo + "', diretor: " 
	            + diretor + " (" 
				+ ano + ") duração: " 
	            + duracao + " minutos.";
	}
	
}
