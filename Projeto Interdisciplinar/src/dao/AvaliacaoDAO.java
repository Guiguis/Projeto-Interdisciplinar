package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectionFactory;
import model.Aluno;
import model.Avaliacao;
import model.Grupo;

public class AvaliacaoDAO {
	
	/**
     * CRUD: Insere banca
     * @param conn: Connection
     */
	
	
	
	public void createAvaliacao(Avaliacao avaliacao, int idGrupo, int id) {
		Connection conn = new ConnectionFactory().getConnection();
	
		String sql = "INSERT INTO Avaliacao (entrega_id, turma_aluno_id, nota, comentarios, dt_avaliacao) VALUES (?, ?, ?, ?, ?)";
		
		try(PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

				stm.setInt(1, avaliacao.getEntrega().getId());
				stm.setInt(2,id);
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
			stm.setDate(2, avaliacao.getDataAvaliacao());
			stm.setString(3, avaliacao.getComentarios());
			stm.setInt(4, avaliacao.getId());
			
			stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
     * CRUD: Deleta avaliacao
     * @param conn: Connection
     */
	public void deleteAvaliacao(int turmaAluno, int entregaId) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "SELECT * FROM avaliacao WHERE turma_aluno_id = ? AND entrega_id = ?;";
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setInt(1, turmaAluno);
			stm.setInt(2, entregaId);
			stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Avaliacao loadPorId(int id) {
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
	
	public Avaliacao load(int entrega, int turmaAluno) {
		Avaliacao avaliacao = new Avaliacao();
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "SELECT nota, comentarios, dt_avaliacao FROM avaliacao WHERE entrega_id = ? AND turma_aluno_id = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			
			stm.setInt(1,entrega);
			stm.setInt(2,turmaAluno);
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
	
	//Carrega todas as datas em que um grupo foi avalido
	public ArrayList<Date> loadDatas(Grupo grupo) {
		Connection conn = new ConnectionFactory().getConnection();
		ArrayList<Date> lista = new ArrayList<>();
		
		String sqlComand = 
				"SELECT DISTINCT dt_avaliacao FROM Avaliacao " + 
				"JOIN turma_aluno t " + 
				"ON avaliacao.turma_aluno_id = t.id " + 
				"WHERE t.grupo_id = ?";		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			
			stm.setInt(1,grupo.getId());
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				Date data = rs.getDate("dt_avaliacao");
				lista.add(data);
			} 
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	//Carrega uma avaliacao com base na data
	public ArrayList<Avaliacao> loadAvaliacoes(Date data) {
		Connection conn = new ConnectionFactory().getConnection();
		ArrayList<Avaliacao> lista = new ArrayList<>();
		EntregaDAO dao = new EntregaDAO();
		
		String sqlComand = 	"SELECT * FROM avaliacao WHERE dt_avaliacao = ?";
				
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			
			stm.setDate(1, data);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				Avaliacao avaliacao = new Avaliacao();
				avaliacao.setComentarios(rs.getString("comentarios"));
				avaliacao.setEntrega(dao.loadEntrega(rs.getInt("entrega_id")));
				avaliacao.setNota(rs.getInt("nota"));
				lista.add(avaliacao);
			} 
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	

	
	
	
	
	
}
