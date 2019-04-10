package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Atividade;

import connection.ConnectionFactory;

public class AtividadeDAO {

	/**
     * CRUD: Insere Atividade
     * @param conn: Connection
     */
	public void create(Atividade atividade) {
		Connection conn = new ConnectionFactory().getConnection();

		String sqlComand = "INSERT INTO ATIVIDADE (id,tema_id, numero, descricao, " + 
						   "formato_entrega, dt_inicio, dt_fim) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stm = conn.prepareStatement(sqlComand)) {
			stm.setInt(1, atividade.getId());
			stm.setInt(2, atividade.getTema().getId());
			stm.setInt(3, atividade.getNumero());
			stm.setString(4, atividade.getDescricao());
			stm.setString(5, atividade.getFormatoEntrega());
			stm.setDate(6, (java.sql.Date) atividade.getDtInicio());
			stm.setDate(7, (java.sql.Date) atividade.getDtFim());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
     * CRUD: Exclui Atividade
     * @param conn: Connection
     */
	public void delete(int id) {
		Connection conn = new ConnectionFactory().getConnection();

		String sqlComand = "DELETE FROM ATIVIDADE where id = ?";

		try (PreparedStatement stm = conn.prepareStatement(sqlComand)) {
			stm.setInt(1, id);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
    * CRUD: Carrega Atividade
    * @param conn: Connection
    */
	public Atividade load(int id) { // retorna uma atividade com base no ID dela
		Connection conn = new ConnectionFactory().getConnection();

		String sqlComand = "SELECT * from atividade where id=?";

		Atividade atividade = new Atividade();

		try (PreparedStatement stm = conn.prepareStatement(sqlComand)) {
			stm.setInt(1, id);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				atividade = new Atividade();

				atividade.setId((rs.getInt("id")));
				atividade.setNumero((rs.getInt("numero")));
				atividade.setDescricao((rs.getString("descricao")));
				atividade.setFormatoEntrega(rs.getString("formato_entrega"));
				atividade.setDtInicio((rs.getDate("dt_inicio")));
				atividade.setDtFim((rs.getDate("dt_fim")));
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

		return atividade;
	}

}
