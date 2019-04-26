package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectionFactory;
import model.Atividade;
import model.Entrega;
import model.Grupo;
import dao.AtividadeDAO;

public class EntregaDAO {

	public Entrega loadEntrega(int id) {
		Entrega entrega= new Entrega();
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "SELECT id, dt_cadastro FROM entrega WHERE entrega.id = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			
			stm.setInt(1,id);
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				entrega.setId(rs.getInt("id"));
				entrega.setDtCadastro(rs.getDate("dt_cadastro"));
			} 
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return entrega;
	}
	
	/***
	 * 
	 * @param grupo
	 * @return returna todas as entregas de um grupo
	 */
	public ArrayList<Entrega> loadTodos(int id){
		ArrayList<Entrega> listaEntrega= new ArrayList<Entrega>();
		AtividadeDAO dao = new AtividadeDAO();
		GrupoDAO grupoDAO = new GrupoDAO();
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "SELECT * FROM entrega WHERE grupo_id = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				Entrega entrega = new Entrega();
				entrega.setId(rs.getInt("id"));
				entrega.setDtCadastro(rs.getDate("dt_cadastro"));
				entrega.setAtividade(dao.load(rs.getInt("atividade_id")));
				entrega.setGrupo(grupoDAO.load(rs.getInt("grupo_id")));
				listaEntrega.add(entrega);
			} 
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaEntrega;
	}
	
}
