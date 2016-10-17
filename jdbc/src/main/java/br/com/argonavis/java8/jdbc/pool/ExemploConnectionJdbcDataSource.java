package br.com.argonavis.java8.jdbc.pool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ExemploConnectionJdbcDataSource {

	public static void main(String[] args) throws IOException, SQLException {
		DataSourceConnectionFactory factory = DataSourceConnectionFactory.create("postgresql");
			
		Connection con = factory.getConnection();
		System.out.println("Conexão funcionou!");
		
	}

}
