package br.com.argonavis.java8.jdbc.pool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExemploDropTableDataSource {

	public static void main(String[] args) throws IOException, SQLException {
		DataSourceConnectionFactory factory = DataSourceConnectionFactory.create("postgresql");
		Connection con = factory.getConnection();
		Statement stmt = null;
		
		String sql = "DROP TABLE Filme;";
		try {
			stmt = con.createStatement();
			stmt.execute(sql);
			System.out.println("Statement executado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Destruição de tabela falhou: " + e);
		} finally {
			factory.closeConnection();
		}

	}

}