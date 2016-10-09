package br.com.argonavis.java8.jdbc;

import java.io.IOException;
import java.io.InputStream;

public class ExemploPropriedadesJDBC {
	
	public static void loadProperties() throws IOException {
		loadProperties(null);
	}
	
	public static void loadProperties(String database) throws IOException {
		if(database == null) {
			database = "jdbc";
		}
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream in = classLoader.getResourceAsStream("META-INF/"+database+".properties");
		System.getProperties().load(in);
	}

	public static void main(String[] args) throws IOException {
		
		loadProperties();
		
		String driver = System.getProperty("jdbc.driver");
		String url = System.getProperty("jdbc.url");
		String username = System.getProperty("jdbc.username");
		String password = System.getProperty("jdbc.password");
		
		System.out.println("Propriedades JDBC: ");
		System.out.println("Driver: " + driver);
		System.out.println("URL: " + url);
		System.out.println("username: " + username);
		System.out.println("password: " + password);
		
	}

}
