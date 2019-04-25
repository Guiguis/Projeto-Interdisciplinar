package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectionFactory;
import model.Professor;
import model.Usuario;

public class ProfessorDAO extends UsuarioDAO{

	/**
     * CRUD: Insere professor
     * @param conn: Connection
     */
	public void create(Professor professor) {
		Connection conn = new ConnectionFactory().getConnection();
		
		Usuario usuario = createUsuario(professor);
		
		String sqlComand = "INSERT INTO Professor (professor_id, matricula, administrador) VALUES (?, ?, ?)";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			System.out.println(usuario.getId());
			stm.setInt(1, usuario.getId());
			stm.setString(2, professor.getMatricula());
			stm.setInt(3, professor.getAdministrador());
			
			stm.executeUpdate();
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
	
	/**
     * CRUD: Carrega dados do professor
     * @param conn: Connection
     */
	public Professor load(int id) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "SELECT professor.matricula, professor.administrador, " +
							"usuario.id, usuario.nome, usuario.email, usuario.senha " + 
		           			"FROM Professor as professor " + 
		           			"INNER JOIN Usuario as usuario ON usuario.Id = professor.professor_id " + 
		           			"WHERE usuario.id = ?";
		
		Professor professor = null;
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			
			stm.setInt(1, id);
			
			ResultSet rs = stm.executeQuery();
			
            if(rs.next()) {
            	String email = rs.getString("usuario.email");
            	String senha = rs.getString("usuario.senha");
            	
            	professor = new Professor(email, senha);
            	
            	professor.setId(rs.getInt("usuario.id"));
            	professor.setNome(rs.getString("usuario.nome"));
            	professor.setMatricula(rs.getString("professor.matricula"));
            	professor.setAdministrador(rs.getInt("professor.administrador"));
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
		
		return professor;
	}
	
	/**
     * CRUD: Atualiza dados do professor
     * @param conn: Connection
     */
	public void update(Professor professor) {
		Connection conn = new ConnectionFactory().getConnection();
		
		//Atualiza email e senha
		updateUsuario(professor);
		
		String sqlComand = "UPDATE Professor SET administrador = ?, matricula = ? WHERE professor_id = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			stm.setInt(1, professor.getAdministrador());
			stm.setString(2, professor.getMatricula());
			stm.setInt(3, professor.getId());
			
			stm.executeUpdate();            
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
	
	/**
     * CRUD: Deleta professor
     * @param conn: Connection
     */
	public void delete(int id) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "DELETE FROM Professor WHERE professor_id = ?";
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			stm.setInt(1, id);
			stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		deleteUsuario(id);
	}
	
	/**
     * CRUD: Carrega dados de todos os professores
     * @param conn: Connection
     */
	public ArrayList<Professor> getProfessores() {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "SELECT id, nome, email, senha, administrador, matricula "
				    + "FROM usuario JOIN professor p ON usuario.id = p.professor_id";
		
		ArrayList<Professor> lista = new ArrayList<Professor>();
		Professor professor = null;
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			
			ResultSet rs = stm.executeQuery();
			
            while(rs.next()) {  
            	int id = rs.getInt("id");
            	String nome = rs.getString("nome");
            	String senha = rs.getString("senha");
            	String email = rs.getString("email");
            	String matricula = rs.getString("matricula");
            	int administrador = rs.getInt("administrador");
            	professor = new Professor(id, nome, email, senha, matricula, administrador);
            	lista.add(professor);
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
		
		return lista;
	}
	
	/**
     * CRUD: Carrega dados de um professor procurando pelo nome
     * @param conn: Connection
     */
	public ArrayList<Professor> buscarProfessor(String busca) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "SELECT id, nome, email, senha, administrador, matricula "
				    + "FROM usuario JOIN professor p ON usuario.id = p.professor_id "
				    + "WHERE UPPER(nome) like ?";
		
		ArrayList<Professor> lista = new ArrayList<Professor>();
		Professor professor = null;
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			stm.setString(1, "%" + busca.toUpperCase() + "%");
			ResultSet rs = stm.executeQuery();
			
            while(rs.next()) {  
            	int id = rs.getInt("id");
            	String nome = rs.getString("nome");
            	String senha = rs.getString("senha");
            	String email = rs.getString("email");
            	String matricula = rs.getString("matricula");
            	int administrador = rs.getInt("administrador");
            	professor = new Professor(id, nome, email, senha, matricula, administrador);
            	lista.add(professor);
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
		
		return lista;
	}
	
}
