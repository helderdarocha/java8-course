package br.com.argonavis.java8.jdbc.pool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExemploInsertDataSource {
	
	private static final String SQL = "INSERT INTO Filme (id, imdb, titulo, diretor, ano, duracao) VALUES(?,?,?,?,?,?)";

	public static void main(String[] args) throws IOException {
		inserirFilme("tt0013442", "Nosferatu, eine Symphonie des Grauens", "F. W. Murnau", 1922, 81);
		inserirFilme("tt1937390", "Nymphomaniac", "Lars von Trier", 2013, 330);
		inserirFilme("tt1527186", "Melancolia", "Lars von Trier", 2011, 136);
		inserirFilme("tt0113083", "La Flor de mi Secreto", "Pedro Almodovar", 1995, 103);
		inserirFilme("tt0101765", "La double vie de Véronique", "Krzysztof Kieslowski", 1991, 98);
	}
	
	public static void inserirFilme(String imdb, String titulo, String diretor, int ano, int duracao) throws IOException {
		DataSourceConnectionFactory factory = DataSourceConnectionFactory.create("postgresql");
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = factory.getConnection();
			 
			int id = nextID(con);
			
			stmt = con.prepareStatement(SQL);
			stmt.setInt(1, id);
			stmt.setString(2, imdb);
			stmt.setString(3, titulo);
			stmt.setString(4, diretor);
			stmt.setInt(5, ano);
			stmt.setInt(6, duracao);
			
			stmt.executeUpdate();
			
			System.out.println("Statement executado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Inserção de dados falhou: " + e);
		} finally {
			factory.closeConnection();
		}

	}
	
	public static int nextID(Connection con) throws IOException {
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) FROM Filme;");
			if(rs.next()) {
				int max = rs.getInt(1);
				return max + 1;
			}
			return 1;
		} catch (SQLException e) {
			System.out.println("Falha na obtenção da sequencia: " + e);
			throw new IOException(e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
