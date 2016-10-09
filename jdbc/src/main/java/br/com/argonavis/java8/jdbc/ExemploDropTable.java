package br.com.argonavis.java8.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExemploDropTable {

	public static void main(String[] args) throws IOException {
		Connection con = null;
		Statement stmt = null;
		
		String sql = "DROP TABLE Filme;";
		try {
			con = DriverManagerConnectionFactory.getConnection("postgresql");
			stmt = con.createStatement();
			stmt.execute(sql);
			System.out.println("Statement executado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Destruição de tabela falhou: " + e);
		} finally {
			DriverManagerConnectionFactory.closeConnection();
		}

	}

}