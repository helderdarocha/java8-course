package br.com.argonavis.java.arrays;

public class Localidade {
	private double longitude;
	private double latitude;
	private String nome;
	public Localidade() {}
	public Localidade(double longitude, double latitude, String nome) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.nome = nome;
	}

	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Localidade [longitude=" + longitude + ", latitude=" + latitude + ", nome=" + nome + "]";
	}
	
}
