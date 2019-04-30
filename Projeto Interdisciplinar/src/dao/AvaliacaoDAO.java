package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectionFactory;
import model.Avaliacao;
import model.Entrega;

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
	public void updateAvaliacao(ArrayList<Avaliacao> listaAvaliacao) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "UPDATE Avaliacao SET nota = ?, comentarios = ? WHERE id = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			for(int i = 0; i < listaAvaliacao.size(); i++) {
				System.out.println("ATUALIAZANDO DAO " + listaAvaliacao.get(i).getNota());
				stm.setDouble(1, listaAvaliacao.get(i).getNota());
				stm.setString(2, listaAvaliacao.get(i).getComentarios());
				stm.setInt(3, listaAvaliacao.get(i).getId());
				stm.executeUpdate();
			}	
			System.out.println("Alterado com sucesso");
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao alterar: " + e);
		}
		
	}
	
	/**
     * CRUD: Deleta avaliacao
     * @param conn: Connection
     */
	public void deleteAvaliacao(int entregaId) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "DELETE FROM avaliacao WHERE entrega_id = ?";
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setInt(1, entregaId);
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
	
	public ArrayList<Avaliacao> loadEntregaId(int entrega) {
		AlunoDAO alunoDAO = new AlunoDAO();
		EntregaDAO entregaDAO = new EntregaDAO();
		ArrayList<Avaliacao> lista = new ArrayList<Avaliacao>();
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "SELECT * FROM avaliacao WHERE entrega_id = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			
			stm.setInt(1,entrega);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				Avaliacao avaliacao = new Avaliacao();
				avaliacao.setId(rs.getInt("id"));
				avaliacao.setNota(rs.getDouble("Nota"));
				avaliacao.setDataAvaliacao(rs.getDate("dt_avaliacao"));
				avaliacao.setComentarios(rs.getString("comentarios"));
				avaliacao.setAluno(alunoDAO.loadTurmaAluno(rs.getInt("turma_aluno_id")));
				avaliacao.setEntrega(entregaDAO.loadEntrega(rs.getInt("entrega_id")));
				lista.add(avaliacao);
			} 

			System.out.println("Dados carregados com sucesso");
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Dados carregados SEM sucesso erro: " + e);
		}
		return lista;
	}
	
	
	//Passando um idEntrega e idGrupo ele verifica se essa entrega ja foi avaliada
	public int verrifica(Entrega entrega) {
		Connection conn = new ConnectionFactory().getConnection();
		int i = 0;
		
		String sqlComand = 	"SELECT * from avaliacao WHERE entrega_id = ?";
				
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			
			stm.setInt(1, entrega.getId());
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				int resp = rs.getInt("id");
				if(resp > 0) {
					i = 1;
				}
			} 
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	
	
}
