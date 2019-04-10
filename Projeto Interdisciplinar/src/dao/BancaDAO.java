package dao;

import java.sql.Connection;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionFactory;
import model.Banca;
import model.Usuario;

public class BancaDAO {

	
	/**
     * CRUD: Insere banca
     * @param conn: Connection
     */
	public void createBanca(Banca banca) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "INSERT INTO Banca (data, sala) VALUES (?, ?)";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setDate(1, (java.sql.Date) banca.getData());
			stm.setString(2, banca.getSala());
			
			int affectedRows = stm.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Criação de banca falhou. Nenhuma linha criada");
	        }

	        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
	        	if (generatedKeys.next()) banca.setId((int) generatedKeys.getLong(1));
	        	else throw new SQLException("Criação de banca falhou. Nenhum id criado");
	        }
	        
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * CRUD: Atualiza banca
     * @param conn: Connection
     */
	public void updateBanca(Banca banca) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "UPDATE Banca SET data = ?, sala = ? WHERE id = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setDate(1, (java.sql.Date) banca.getData());
			stm.setString(2, banca.getSala());
			stm.setInt(3, banca.getId());
			
			stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
     * CRUD: Deleta Banca
     * @param conn: Connection
     */
	public void deleteBanca(Banca banca) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "DELETE FROM Banca WHERE Id = ?";
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setInt(1, banca.getId());
			stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * CRUD: Carrega dados do professor
     * @param conn: Connection
     */
	
	public Banca loadBanca(int id) {
		Banca banca = new Banca();
		Connection conn = new ConnectionFactory().getConnection();
		String sqlInsert = "SELECT data, sala FROM banca WHERE banca.id =?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){
			
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				banca.setData(rs.getDate("Data"));
				banca.setSala(rs.getString("Sala"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return banca;
	}
	
}
