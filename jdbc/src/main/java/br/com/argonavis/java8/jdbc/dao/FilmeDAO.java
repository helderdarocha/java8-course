package br.com.argonavis.java8.jdbc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.argonavis.java8.jdbc.pool.DataSourceConnectionFactory;

public class FilmeDAO {
	
	private String banco;
	private DataSourceConnectionFactory ds;
	
	
	private static final String SELECT_ALL     = "SELECT id, imdb, titulo, diretor, ano, duracao FROM filme";
	private static final String SELECT_BY_ID   = "SELECT id, imdb, titulo, diretor, ano, duracao FROM filme WHERE id=?";
	private static final String SELECT_BY_IMDB = "SELECT DISTINCT id, imdb, titulo, diretor, ano, duracao FROM filme WHERE imdb=?";
	private static final String DELETE_BY_ID   = "DELETE FROM filme WHERE id=?";
	private static final String DELETE_BY_IMDB = "DELETE FROM filme WHERE imdb=?";
	private static final String INSERT         = "INSERT INTO Filme (id, imdb, titulo, diretor, ano, duracao) VALUES (?,?,?,?,?,?)";
	private static final String UPDATE         = "UPDATE Filme SET imdb=?, titulo=?, diretor=?, ano=?, duracao=? WHERE id = ?;";
	
	public FilmeDAO(String banco) {
		try {
			ds = DataSourceConnectionFactory.create("postgresql");
		} catch (IOException e) {
			System.out.println("Conexão ao banco falhou! " + e);
			e.printStackTrace();
		}
		this.banco = banco;
	}
	
    public Filme findByID(int id) {
    	Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = ds.getConnection();
			
			stmt = con.prepareStatement(SELECT_BY_ID);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Filme filme = new Filme();
				filme.setId(rs.getInt("id"));
				filme.setImdb(rs.getString("imdb"));
				filme.setTitulo(rs.getString("titulo"));
				filme.setDiretor(rs.getString("diretor"));
				filme.setAno(rs.getInt("ano"));
				filme.setDuracao(rs.getInt("duracao") );
				
				return filme;
			}

		} catch (SQLException e) {
			System.out.println("Objeto com ID = "+id+" + não encontrado: " + e);
		} finally {
			ds.closeConnection();
		}
		
		return null;
    }
    
    public Filme findByIMDB(String imdb) {
    	Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			
			stmt = con.prepareStatement(SELECT_BY_IMDB);
			stmt.setString(1, imdb);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Filme filme = new Filme();
				filme.setId(rs.getInt("id"));
				filme.setImdb(rs.getString("imdb"));
				filme.setTitulo(rs.getString("titulo"));
				filme.setDiretor(rs.getString("diretor"));
				filme.setAno(rs.getInt("ano"));
				filme.setDuracao(rs.getInt("duracao") );
				
				return filme;
			}

		} catch (SQLException e) {
			System.out.println("Filme com IMDB = "+imdb+" + não encontrado: " + e);
		} finally {
			ds.closeConnection();
		}
		
		return null;
    }
    
    public List<Filme> findAll() {
    	Connection con = null;
		PreparedStatement stmt = null;
		
		List<Filme> lista = new ArrayList<>();

		try {
			con = ds.getConnection();
			
			stmt = con.prepareStatement(SELECT_ALL);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Filme filme = new Filme();
				filme.setId(rs.getInt("id"));
				filme.setImdb(rs.getString("imdb"));
				filme.setTitulo(rs.getString("titulo"));
				filme.setDiretor(rs.getString("diretor"));
				filme.setAno(rs.getInt("ano"));
				filme.setDuracao(rs.getInt("duracao") );
				
				lista.add(filme);

			}

		} catch (SQLException e) {
			System.out.println("Listagem de dados falhou: " + e);
		} finally {
			ds.closeConnection();
		}
		
		return lista;
    }
    
    public int deleteByID(int id) {
    	
    	Connection con = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0;

		try {
			con = ds.getConnection();
			
			stmt = con.prepareStatement(DELETE_BY_ID);
			stmt.setInt(1, id);
			
			rowsAffected = stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Objeto com ID = "+id+" + não encontrado: " + e);
		} finally {
			ds.closeConnection();
		}
		
		return rowsAffected;
    	
    }
    
    public int deleteByIMDB(String imdb) {
    	
    	Connection con = null;
		PreparedStatement stmt = null;
		int rowsAffected = 0;

		try {
			con = ds.getConnection();
			
			stmt = con.prepareStatement(DELETE_BY_IMDB);
			stmt.setString(1, imdb);
			
			rowsAffected = stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Filmes com IMDB = "+imdb+" + não encontrados: " + e);
		} finally {
			ds.closeConnection();
		}
		
		return rowsAffected;
    	
    }
    
    public void update(Filme filme) {
    	Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = ds.getConnection();
			
			stmt = con.prepareStatement(UPDATE);
			stmt.setString(1, filme.getImdb());
			stmt.setString(2, filme.getTitulo());
			stmt.setString(3, filme.getDiretor());
			stmt.setInt(4, filme.getAno());
			stmt.setInt(5, filme.getDuracao());
			stmt.setInt(6, filme.getId());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Atualização de dados falhou: " + e);
		} finally {
			ds.closeConnection();
		}
    }
    
    public int insert(Filme filme) {
    	Connection con = null;
		PreparedStatement stmt = null;
		
		int id = -1;

		try {
			con = ds.getConnection();
			
			id = nextID(con);
			
			stmt = con.prepareStatement(INSERT);
			stmt.setInt(1, id);
			stmt.setString(2, filme.getImdb());
			stmt.setString(3, filme.getTitulo());
			stmt.setString(4, filme.getDiretor());
			stmt.setInt(5, filme.getAno());
			stmt.setInt(6, filme.getDuracao());
			
			stmt.executeUpdate();

		} catch (SQLException | IOException e) {
			System.out.println("Atualização de dados falhou: " + e);
		} finally {
			ds.closeConnection();
		}	
		
		return id;
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
