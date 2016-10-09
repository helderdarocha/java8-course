package br.com.argonavis.java8.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExemploSelect {

	private static final String SELECT_ALL    = "SELECT id, imdb, titulo, diretor, ano, duracao FROM filme";
	private static final String SELECT_SEC_21 = "SELECT imdb, titulo, diretor, ano FROM filme WHERE ano > ?";

	public static void main(String[] args) throws IOException {
		List<String> tudo  = selectAllFilmes();
		List<String> novos = selectFilmesNovos(2000);
		
		System.out.println("\nTodos os filmes:");
		for(String linha: tudo) {
			System.out.println(linha);
		}
		
		System.out.println("\nFilmes depois de 2000:");
		for(String linha: novos) {
			System.out.println(linha);
		}
	}
	
	public static List<String> selectAllFilmes() throws IOException {
		Connection con = null;
		PreparedStatement stmt = null;
		
		List<String> lista = new ArrayList<>();

		try {
			con = DriverManagerConnectionFactory.getConnection("postgresql");
			
			stmt = con.prepareStatement(SELECT_ALL);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				lista.add(rs.getInt("id") + " " 
			            + rs.getString("imdb") + " " 
						+ rs.getString("titulo") + " " 
			            + rs.getString("diretor") + " (" 
						+ rs.getInt("ano") + ") duração: " 
			            + rs.getInt("duracao") + " minutos.");
			}

		} catch (SQLException e) {
			System.out.println("Listagem de dados falhou: " + e);
		} finally {
			DriverManagerConnectionFactory.closeConnection();
		}
		
		return lista;
	}
	
	
	public static List<String> selectFilmesNovos(int ano) throws IOException {
		Connection con = null;
		PreparedStatement stmt = null;
		
		List<String> lista = new ArrayList<>();

		try {
			con = DriverManagerConnectionFactory.getConnection("h2");
			
			stmt = con.prepareStatement(SELECT_SEC_21);
			stmt.setInt(1, ano);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				lista.add(rs.getString("imdb") + " " 
						+ rs.getString("titulo") + " " 
			            + rs.getString("diretor") + " (" 
						+ rs.getInt("ano") + ")");
			}

		} catch (SQLException e) {
			System.out.println("Listagem de dados falhou: " + e);
		} finally {
			DriverManagerConnectionFactory.closeConnection();
		}
		
		return lista;
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
