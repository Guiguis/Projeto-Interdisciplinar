package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionFactory;
import model.Atividade;
import model.Usuario;

public class UsuarioDAO {

	/**
     * CRUD: Insere usu�rio
     * @param conn: Connection
     */
	public Usuario createUsuario(Usuario usuario) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "INSERT INTO Usuario (nome, email, senha) VALUES (?, ?, ?)";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setString(1, usuario.getNome());
			stm.setString(2, usuario.getEmail());
			stm.setString(3, usuario.getSenha());
			
			int affectedRows = stm.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Cria��o de usu�rio falhou. Nenhuma linha criada");
	        }

	        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
	        	if (generatedKeys.next()) usuario.setId((int) generatedKeys.getLong(1));
	        	else throw new SQLException("Cria��o de usu�rio falhou. Nenhum id criado");
	        }
	        
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	/**
     * CRUD: Atualiza usu�rio
     * @param conn: Connection
     */
	public Usuario updateUsuario(Usuario usuario) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "UPDATE Usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setString(1, usuario.getNome());
			stm.setString(2, usuario.getEmail());
			stm.setString(3, usuario.getSenha());
			stm.setInt(4, usuario.getId());
			
			stm.executeUpdate();
			
			System.out.println("Atualizado usuario com sucesso");
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Usuario nao atualizado, erro: " + e);
		}
		
		return usuario;
	}
	
	/**
     * CRUD: Deleta usu�rio
     * @param conn: Connection
     */
	public void deleteUsuario(int id) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "DELETE FROM Usuario WHERE Id = ?";
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setInt(1, id);
			stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int Load(String user, String senha) {
		Connection conn= new ConnectionFactory().getConnection();
		
		String sqlComand = "Select id from usuario where email = ? and senha =?";
		try (PreparedStatement stm = conn.prepareStatement(sqlComand)) {
			stm.setString(1, "user");
			stm.setString(2, "senha");
			int i;
			ResultSet rs = stm.executeQuery();
			
			if (rs.next()) {
				i = rs.getInt("id");
				return i;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
		
	}
	
	
	public int logar(String email, String senha) {
		Connection conn = new ConnectionFactory().getConnection();
		int i = -1;
		String sqlComand = "SELECT id from usuario where email = ? and senha = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			
			stm.setString(1, email);
			stm.setString(2, senha);
			ResultSet rs = stm.executeQuery();
			
            if(rs.next()) {
				i = rs.getInt("id");	
            }
            
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	
	public boolean verificarEmail(String email) {
		Connection conn = new ConnectionFactory().getConnection();
		boolean i = false;
		String sqlComand = "select email from usuario where email = ? ";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			
			stm.setString(1, email);
			ResultSet rs = stm.executeQuery();
			
            if(rs.next()) {
				i = true;	
            }
            else i = false;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	
	public String buscaMatricula(String email) {
		Connection conn = new ConnectionFactory().getConnection();
		String i = null;
		String sqlComand = "select usuario.id, usuario.email, professor.matricula\r\n" + 
				"from usuario as usuario, professor as professor\r\n" + 
				"where usuario.email = ? and usuario.id = professor.professor_id ";
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			stm.setString(1, email);
			ResultSet rs = stm.executeQuery();
            if(rs.next()) {
				i = rs.getString("matricula");	
            }
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	public String buscaRA(String email) {
		Connection conn = new ConnectionFactory().getConnection();
		String i = null;
		String sqlComand = "select usuario.id, usuario.email, aluno.ra\r\n" + 
				"from usuario as usuario, aluno as aluno\r\n" + 
				"where usuario.email = ? and usuario.id = aluno.aluno_id ";
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			stm.setString(1, email);
			ResultSet rs = stm.executeQuery();
            if(rs.next()) {
				i = rs.getString("ra");	
            }
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	
	public String BucarOldPassword(String email, String criterio) {
		Connection conn = new ConnectionFactory().getConnection();
		String i = null;
		if(criterio.equals("ra")|| criterio.equals("RA")) {
			String sqlComand = "select usuario.id, usuario.email, aluno.ra, aluno.senha\r\n" + 
					"from usuario as usuario, aluno as aluno\r\n" + 
					"where usuario.email = ? and usuario.id = aluno.aluno_id ";
			try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
				stm.setString(1, email);
				ResultSet rs = stm.executeQuery();
	            if(rs.next()) {
					i = rs.getString("senha");	
	            }
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		else {
			String sqlComand = "select usuario.id, usuario.email, professor.matricula, usuario.senha\r\n" + 
					"from usuario as usuario, professor as professor\r\n" + 
					"where usuario.email = ? and usuario.id = professor.professor_id ";
			try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
				stm.setString(1, email);
				ResultSet rs = stm.executeQuery();
	            if(rs.next()) {
					i = rs.getString("senha");	
	            }
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return i;
	}
	
	public void updateSenha(String senha, String email) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "UPDATE Usuario SET senha = ? WHERE email = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setString(1, senha);
			stm.setString(2, email);
			
			stm.executeUpdate();
			
			System.out.println("Atualizado usuario com sucesso");
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Usuario nao atualizado, erro: " + e);
		}

	}
	
	
}

