package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectionFactory;
import model.Professor;
import model.ProfessorBanca;

public class ProfessorBancaDAO {

	public void createBanca(ProfessorBanca professorBanca) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "INSERT INTO ProfessorBanca (avaliacao) VALUES (?)";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setDouble(1,  professorBanca.getAvaliacao());
			
			int affectedRows = stm.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Cria��o de ProfessorBanca falhou. Nenhuma linha criada");
	        }

	        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
	        	if (generatedKeys.next()) professorBanca.setId((int) generatedKeys.getLong(1));
	        	else throw new SQLException("Cria��o de ProfessorBanca falhou. Nenhum id criado");
	        }
	        
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * CRUD: Atualiza banca
     * @param conn: Connection
     */
	public void updateProfessorBanca(ProfessorBanca professorBanca) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "UPDATE ProfessorBanca SET avaliacao = ? WHERE id = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setDouble(1, professorBanca.getAvaliacao());
			stm.setInt(3, professorBanca.getId());
			
			stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
     * CRUD: Deleta Banca
     * @param conn: Connection
     */
	public void deleteBanca(ProfessorBanca professorBanca) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "DELETE FROM Banca WHERE Id = ?";
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setInt(1, professorBanca.getId());
			stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * CRUD: Carrega dados do professor
     * @param conn: Connection
     */
	
	public ProfessorBanca loadProfessorBanca(int id) {
		ProfessorBanca professorBanca = new ProfessorBanca();
		BancaDAO bancaDAO = new BancaDAO();
		ProfessorDAO professorDAO = new ProfessorDAO();

		Connection conn = new ConnectionFactory().getConnection();
		String sqlInsert = "SELECT avaliacao FROM ProfessorBanca WHERE ProfessorBanca.id =?";

		try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){

			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();

			if(rs.next()) {
				professorBanca.setAvaliacao(rs.getDouble("Avaliacao"));
				professorBanca.setBanca(bancaDAO.loadBanca(rs.getInt("banca")));
				professorBanca.setProfessor(professorDAO.load(rs.getInt("professor")));
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return professorBanca;
	}
	
}
