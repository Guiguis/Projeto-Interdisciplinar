package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
				grupo.setOrientador((professorDAO.load(rs.getInt("orientador_id"))));
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
	
	//Carrega todos os grupos baseado na sigla de uma turma ---------------------------------------------------------------
		public ArrayList<Grupo> carregaGrupos(String sigla) {
			ArrayList<Grupo> lista = new ArrayList<>();
			ProfessorDAO professorDAO = new ProfessorDAO();
			Grupo grupo = new Grupo();

			Connection conn = new ConnectionFactory().getConnection();
			String sqlInsert = 
					"SELECT DISTINCT * FROM turma t JOIN turma_aluno a ON t.id = a.turma_id JOIN grupo g ON a.grupo_id = g.id WHERE sigla = ?";

			try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){

				stm.setString(1, sigla);
				ResultSet rs = stm.executeQuery();

				while(rs.next()) {
					grupo.setNome(rs.getString("nome"));
					grupo.setNumero(rs.getInt("numero"));
					grupo.setOrientador(professorDAO.load(rs.getInt("orientador_id")));
					lista.add(grupo);
				}

			}catch(SQLException e) {
				e.printStackTrace();
			}
			return lista;
		}

}
