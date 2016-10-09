package br.com.argonavis.java8.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ExemploConnectionJdbc {

	public static void main(String[] args) throws IOException, SQLException {
		ExemploPropriedadesJDBC.loadProperties("postgresql");
		
		String driver = System.getProperty("jdbc.driver");
		String username = System.getProperty("jdbc.username");
		String password = System.getProperty("jdbc.password");
		
		System.out.println("driver: " + driver);
		
		try {
		    Class.forName(driver); // Falha se o driver (JAR) não estiver no Classpath
		} catch (ClassNotFoundException e) {
		    System.out.println("Driver não foi encontrado - verifique se o JAR está no Classpath!\n" + e );
		}
		
		Connection con = null;
		
		try {
			String url = System.getProperty("jdbc.url");
			System.out.println("Usando URL: " + url);
			
			con = DriverManager.getConnection(url, username, password);
			
			System.out.println("Conexão funcionou!");
		} catch (SQLException e) {
			System.out.println("Conexão falhou: " + e);
		} finally {
			con.close();
		}

	}

}
