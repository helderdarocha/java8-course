package br.com.argonavis.java8.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExemploTransacoes {
	
	private static Connection con = null;
	private static PreparedStatement stmt = null;
	
	private static final String SQL = "INSERT INTO Filme (id, imdb, titulo, diretor, ano, duracao) VALUES(?,?,?,?,?,?)";

	public static void main(String[] args) throws IOException, SQLException {

		try {
			con = DriverManagerConnectionFactory.getConnection("postgresql");
			con.setAutoCommit(false);
			
			inserirFilme("tt0066921", "A Clockwork Orange", "Stanley Kubrick", 1972, 136);
			
			if((int)(Math.random() * 5) == 0) {
				throw new SQLException("20% chance exception!");
			}
			
			inserirFilme("tt0069293", "Solyaris", "Andrei Tarkovsky", 1972, 167);
			
			if((int)(Math.random() * 5) == 0) {
				throw new SQLException("20% chance exception!");
			}
			
			inserirFilme("tt0079944", "Stalker", "Andrei Tarkovsky", 1979, 163);
			
			if((int)(Math.random() * 5) == 0) {
				throw new SQLException("20% chance exception!");
			}
			
			stmt = con.prepareStatement("UPDATE Filme SET titulo = 'Melancholia' WHERE imdb = 'tt1527186';");
			stmt.executeUpdate();
			
			if((int)(Math.random() * 5) == 0) {
				throw new SQLException("20% chance exception!");
			}
			
			con.commit();
			
		} catch (SQLException e) {
			con.rollback();
			System.out.println("Inserção e atualização falhou - rolling back: " + e);
		} finally {
			DriverManagerConnectionFactory.closeConnection();
		}
	}
	
	public static void inserirFilme(String imdb, String titulo, String diretor, int ano, int duracao) throws IOException, SQLException {

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
