package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectionFactory;
import model.Turma;


public class TurmaDAO {
	
	//Carrega Todos os anos/semestres -------------------------------------------------------------------------
	public ArrayList<Turma> mostrarAno() {
		Turma turma = null;
		ArrayList<Turma> lista = new ArrayList<Turma>();

		Connection conn = new ConnectionFactory().getConnection();
		String sqlInsert = "SELECT DISTINCT ano_letivo, semestre_letivo FROM turma ORDER BY ano_letivo DESC, semestre_letivo  DESC";

		try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){
			ResultSet rs = stm.executeQuery();

			while(rs.next()) {
				int ano = rs.getInt("ano_letivo");
				int semestre = rs.getInt("semestre_letivo");
				turma = new Turma(semestre, ano);
				
				lista.add(turma);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}	
	
	/**
	 * Retorna todas as turmas referente a um período (ano letivo, semestre)
	 * @param ano
	 * @param semestre
	 * @return ArrayList<Turma>
	 */
	public ArrayList<Turma> getTurmasPeriodo(int ano, int semestre) {
		ArrayList<Turma> lstTurma = new ArrayList<>();

		Connection conn = new ConnectionFactory().getConnection();
		String sqlInsert = "SELECT * FROM turma WHERE ano_letivo = ? AND semestre_letivo = ?";

		try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){
			stm.setInt(1, ano);
			stm.setInt(2, semestre);
			ResultSet rs = stm.executeQuery();

			while(rs.next()) {
				Turma turma = new Turma();
				turma.setId(rs.getInt("id"));
				turma.setSigla(rs.getString("sigla"));
				turma.setAno(rs.getInt("ano_letivo"));
				turma.setSemestreLetivo(rs.getInt("semestre_letivo"));
				lstTurma.add(turma);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lstTurma;
	}

}
