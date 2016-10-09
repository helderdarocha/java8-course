package br.com.argonavis.java8.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerConnectionFactory {

	private static Connection con;

	public static void loadProperties(String database) throws IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream in = classLoader.getResourceAsStream("META-INF/" + database + ".properties");
		System.getProperties().load(in);
	}

	public static Connection getConnection(String database) throws IOException, SQLException {

		if (con == null) {

			loadProperties(database);

			String driver = System.getProperty("jdbc.driver");
			String username = System.getProperty("jdbc.username");
			String password = System.getProperty("jdbc.password");

			try {
				Class.forName(driver); // Falha se o driver (JAR) não estiver no
										// Classpath
			} catch (ClassNotFoundException e) {
				System.out.println(
						"Driver " + driver + " não foi encontrado - verifique se o JAR está no Classpath!\n" + e);
			}

			try {
				String url = System.getProperty("jdbc.url");

				con = DriverManager.getConnection(url, username, password);

				System.out.println("Conexão a " + url + " realizada com sucesso!");
			} catch (SQLException e) {
				System.out.println("Conexão falhou: " + e);
			}

		}

		return con; // fechamento é responsabilidade da classe que usar a
					// conexão

	}
	
	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
	}
}
