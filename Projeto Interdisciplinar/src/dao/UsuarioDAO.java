package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionFactory;
import model.Usuario;

public class UsuarioDAO {

	/**
     * CRUD: Insere usuário
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
	            throw new SQLException("Criação de usuário falhou. Nenhuma linha criada");
	        }

	        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
	        	if (generatedKeys.next()) usuario.setId((int) generatedKeys.getLong(1));
	        	else throw new SQLException("Criação de usuário falhou. Nenhum id criado");
	        }
	        
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	/**
     * CRUD: Atualiza usuário
     * @param conn: Connection
     */
	public Usuario updateUsuario(Usuario usuario) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "UPDATE Usuario SET email = ?, senha = ? WHERE id = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setString(1, usuario.getEmail());
			stm.setString(2, usuario.getSenha());
			stm.setInt(3, usuario.getId());
			
			stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	/**
     * CRUD: Deleta usuário
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
	
}
