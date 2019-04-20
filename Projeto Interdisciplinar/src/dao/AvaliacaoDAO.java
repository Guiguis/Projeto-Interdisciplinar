package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionFactory;
import model.Avaliacao;

public class AvaliacaoDAO {
	
	/**
     * CRUD: Insere banca
     * @param conn: Connection
     */
	
	
	
	public void createAvaliacao(Avaliacao avaliacao) {
		Connection conn = new ConnectionFactory().getConnection();
		String sql = "INSERT INTO Avaliacao (entrega_id, turma_aluno_id, nota, comentarios, dt_avaliacao) VALUES (?, ?, ?, ?, ?)";
		
		try(PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stm.setInt(1, 2);
			stm.setInt(2, avaliacao.getTurmaAluno());
			stm.setDouble(3, avaliacao.getNota());
			stm.setString(4, avaliacao.getComentarios());
			stm.setDate(5, avaliacao.getDataAvaliacao());
			
			int affectedRows = stm.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Cria��o de banca falhou. Nenhuma linha criada");
	        }

	        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
	        	if (generatedKeys.next()) avaliacao.setId((int) generatedKeys.getLong(1));
	        	else throw new SQLException("Cria��o de banca falhou. Nenhum id criado");
	        }
	        
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * CRUD: Atualiza banca
     * @param conn: Connection
     */
	public void updateAvaliacao(Avaliacao avaliacao) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "UPDATE Avaliacao SET nota = ?, data_avaliacao = ?, comentarios = ?, WHERE id = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setDouble(1, avaliacao.getNota());
			stm.setDate(2, (java.sql.Date) avaliacao.getDataAvaliacao());
			stm.setString(3, avaliacao.getComentarios());
			
			stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
     * CRUD: Deleta Banca
     * @param conn: Connection
     */
	public void deleteAvaliacao(Avaliacao avaliacao) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "DELETE FROM avaliacao WHERE Id = ?";
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setInt(1, avaliacao.getId());
			stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Avaliacao loadAvaliacao(int id) {
		Avaliacao avaliacao = new Avaliacao();
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "SELECT nota, comentario, dt_avaliacao FROM avaliacao WHERE id = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			
			stm.setInt(1,id);
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				avaliacao.setNota(rs.getDouble("Nota"));
				avaliacao.setDataAvaliacao(rs.getDate("dt_avaliacao"));
				avaliacao.setComentarios(rs.getString("Comentario"));
			} 
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return avaliacao;
	}
}
