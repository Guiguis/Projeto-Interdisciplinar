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
		String sqlInsert = 
				"SELECT DISTINCT ano_letivo, semestre_letivo FROM turma ORDER BY ano_letivo DESC";

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
	
	//Carrega todas as turmas baseada no ano e semestre------------------------------------------
	public ArrayList<Turma> mostrarSigla(Turma turma) {
		ArrayList<Turma> lista = new ArrayList<>();

		Connection conn = new ConnectionFactory().getConnection();
		String sqlInsert = 
				"SELECT * FROM turma WHERE semestre_letivo = ? AND ano_letivo = ?";

		try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){
			stm.setInt(1, turma.getSemestreLetivo());
			stm.setInt(2, turma.getAno());
			ResultSet rs = stm.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				int ano = rs.getInt("ano_letivo");
				int semestre = rs.getInt("semestre_letivo");
				String sigla = rs.getString("sigla");
				turma = new Turma(id, semestre, ano, sigla);
				
				lista.add(turma);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
