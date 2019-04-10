package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionFactory;
import model.Turma;


public class TurmaDAO {
	public Turma load(int id) { // retorna uma Grupo com base no ID dela
		Connection conn = new ConnectionFactory().getConnection();

		String sqlComand = "SELECT * FROM turma WHERE id=?";

		Turma turma = null;

		try (PreparedStatement stm = conn.prepareStatement(sqlComand)) {
			stm.setInt(1, id);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				turma = new Turma();
				TemaDAO temaDAO = new TemaDAO();

				turma.setId((rs.getInt("id")));
				turma.setSemestreLetivo((rs.getInt("semestre_letivo")));
				turma.setAnoLetivo((rs.getInt("ano_letivo")));
				turma.setSigla((rs.getString("sigla")));
				turma.setTema(temaDAO.loadTema(rs.getInt("tema_id")));
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

		return turma;
	}


}
