package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionFactory;
import model.Grupo;

public class GrupoDAO {
	
	public Grupo load(int id) { // retorna uma Grupo com base no ID dela
		Connection conn = new ConnectionFactory().getConnection();

		String sqlComand = "SELECT * FROM atividade WHERE id=?";

		Grupo grupo = null;

		try (PreparedStatement stm = conn.prepareStatement(sqlComand)) {
			stm.setInt(1, id);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				grupo = new Grupo();
				ProfessorDAO professorDAO = new ProfessorDAO();

				grupo.setId((rs.getInt("id")));
				grupo.setNumero((rs.getInt("numero")));
				grupo.setNome((rs.getString("nome")));
				grupo.setProfessor((professorDAO.load(rs.getInt("orientador_id"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return grupo;
	}

}
