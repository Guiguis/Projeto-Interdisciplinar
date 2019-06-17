package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectionFactory;
import model.Grupo;
import model.Turma;

public class GrupoDAO {
	
	// retorna uma Grupo com base no ID dela
	public Grupo load(int id) { 
		Connection conn = new ConnectionFactory().getConnection();

		String sqlComand = "SELECT * FROM grupo WHERE id=?";
		ProfessorDAO professorDAO = new ProfessorDAO();
		Grupo grupo = new Grupo();

		try (PreparedStatement stm = conn.prepareStatement(sqlComand)) {
			stm.setInt(1, id);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				grupo.setId(id);
				grupo.setNumero(rs.getInt("numero"));
				grupo.setNome((rs.getString("nome")));
				grupo.setOrientador(professorDAO.load(rs.getInt("orientador_id")));
			}
			System.out.println("Grupo fui carregado: ");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Grupo não fui carregado erro: " + e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return grupo;
	}
	
	/**
	 * Carregar grupos de acordo com a turma
	 * @param idTurma
	 * @return ArrayList<Grupo>
	 */
	public ArrayList<Grupo> loadGrupoByTurma(int idTurma) {
		ArrayList<Grupo> lista = new ArrayList<>();
		ProfessorDAO professorDAO = new ProfessorDAO();
		Connection conn = new ConnectionFactory().getConnection();
		String sqlInsert = "SELECT DISTINCT g.nome, g.numero, orientador_id, g.id FROM turma t "
						 + "JOIN turma_aluno a ON t.id = a.turma_id "
						 + "JOIN grupo g ON a.grupo_id = g.id "
						 + "WHERE a.turma_id = ?";

		try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){
			stm.setInt(1, idTurma);
			
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				Grupo grupo = new Grupo();
				grupo.setId(rs.getInt("id"));
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
	
	public ArrayList<Grupo> loadGrupoByTurmaVer2(int idTurma) {  //  versão com versirificação de banca
		ArrayList<Grupo> lista = new ArrayList<>();
		ProfessorDAO professorDAO = new ProfessorDAO();
		Connection conn = new ConnectionFactory().getConnection();
		String sqlInsert = "SELECT DISTINCT g.nome, g.numero, orientador_id, g.id FROM turma t \r\n" + 
				"						 JOIN turma_aluno a ON t.id = a.turma_id \r\n" + 
				"						 JOIN grupo g ON a.grupo_id = g.id \r\n" + 
				"                         JOIN banca b ON a.grupo_id = b.grupo_id\r\n" + 
				"						 WHERE a.turma_id = ?";

		try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){
			stm.setInt(1, idTurma);
			
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				Grupo grupo = new Grupo();
				grupo.setId(rs.getInt("id"));
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
	
	
	/***
	 * 
	 * @return Carrega todos os grupos
	 */
	public ArrayList<Grupo> carrega() {
		ArrayList<Grupo> lista = new ArrayList<>();
		ProfessorDAO professorDAO = new ProfessorDAO();
		Connection conn = new ConnectionFactory().getConnection();
		String sqlInsert = "SELECT * FROM grupo";

		try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){

			ResultSet rs = stm.executeQuery();

			while(rs.next()) {
				Grupo grupo = new Grupo();
				grupo.setId(rs.getInt("id"));
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
