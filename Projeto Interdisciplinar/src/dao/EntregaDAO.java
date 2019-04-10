package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionFactory;
import model.Entrega;

public class EntregaDAO {

	public Entrega loadEntrega(int id) {
		Entrega entrega= new Entrega();
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "SELECT data_cadastro FROM entrega WHERE entrega.id = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand)){
			
			stm.setInt(1,id);
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				entrega.setDtCadastro(rs.getDate("DataCadastro"));
			} 
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return entrega;
	}
	
}
