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
		String sqlComand = "SELECT * FROM tema WHERE id = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			stm.setInt(1, id);
			
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				tema.setTitulo(rs.getString("titulo"));
				tema.setIntroducao(rs.getString("introducao"));
				tema.setRequisitos(rs.getString("requisitos"));
				tema.setDataCadastro(rs.getDate("dt_cadastro"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return tema;
	}
	
	
}
