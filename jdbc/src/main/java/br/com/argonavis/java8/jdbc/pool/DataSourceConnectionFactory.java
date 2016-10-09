package br.com.argonavis.java8.jdbc.pool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceConnectionFactory {
	
    private DataSource ds;
	private static DataSourceConnectionFactory instance;
	private Connection con;
	
	public static DataSourceConnectionFactory create(String database) throws IOException {
		if(instance == null) {
			instance = new DataSourceConnectionFactory(database);
		}
		return instance;
	}
	
	private DataSourceConnectionFactory(String database) throws IOException {
		loadProperties(database);

		String username = System.getProperty("jdbc.username");
		String password = System.getProperty("jdbc.password");
		String url      = System.getProperty("jdbc.url");
		
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		ds = new HikariDataSource(config);
	}

	private void loadProperties(String database) throws IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream in = classLoader.getResourceAsStream("META-INF/" + database + ".properties");
		System.getProperties().load(in);
	}

	public Connection getConnection() throws SQLException {
		con = ds.getConnection();
		return con;
	}
	
	public void closeConnection() {
		if (con == null) return;
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
	}

}
