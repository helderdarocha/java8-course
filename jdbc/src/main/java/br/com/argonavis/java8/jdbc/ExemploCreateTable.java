package br.com.argonavis.java8.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExemploCreateTable {

	public static void main(String[] args) throws IOException {
		Connection con = null;
		Statement stmt = null;

		String sql = "CREATE TABLE Filme (id INTEGER PRIMARY KEY, " 
				                       + "imdb CHAR(9) NOT NULL,"
		                               + "titulo VARCHAR(256), "
				                       + "diretor VARCHAR(64), " 
		                               + "ano INTEGER, " 
				                       + "duracao INTEGER);";
		try {
			con = DriverManagerConnectionFactory.getConnection("postgresql");
			stmt = con.createStatement();
			stmt.execute(sql);
			System.out.println("Statement executado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Criação de tabela falhou: " + e);
		} finally {
			DriverManagerConnectionFactory.closeConnection();
		}
	}

}