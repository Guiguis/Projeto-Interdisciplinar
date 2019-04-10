package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionFactory;
import model.Tema;

public class TemaDAO {

	public Tema loadTema(int id) {
		Tema tema = new Tema();
		Connection conn = new ConnectionFactory().getConnection();
		String sqlComand = "SELECT titulo, introducao, requisitos, data_cadastro WHERE tema.id";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			stm.setInt(1, id);
			
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				tema.setTitulo(rs.getString("Titulo"));
				tema.setIntroducao(rs.getString("Introducao"));
				tema.setRequisitos(rs.getString("Requisitos"));
				tema.setDataCadastro(rs.getDate("data_cadastro"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return tema;
	}
	
}
