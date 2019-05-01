package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectionFactory;
import model.Aluno;
import model.Banca;
import model.Professor;
import model.ProfessorBanca;

public class ProfessorBancaDAO {

	public boolean createBanca(ProfessorBanca professorBanca) {
		Connection conn = new ConnectionFactory().getConnection();
		boolean resp;
		String sqlComand = "INSERT INTO professores_banca (professor_id, banca_id) VALUES (?, ?)";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setInt(1, professorBanca.getProfessor().getId());
			stm.setInt(2,  professorBanca.getBanca().getId());
			
			int affectedRows = stm.executeUpdate();
			System.out.println("Professor vinculado a banca com sucesso");
			resp = true;
			
	        if (affectedRows == 0) {
	            throw new SQLException("Criaï¿½ï¿½o de ProfessorBanca falhou. Nenhuma linha criada");
	        }

	        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
	        	if (generatedKeys.next()) professorBanca.setId((int) generatedKeys.getLong(1));
	        	else throw new SQLException("Criaï¿½ï¿½o de ProfessorBanca falhou. Nenhum id criado");
	        	resp = false;
	        }
	        
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Professor não vinculado a banca erro: " + e);
			resp = false;
		}
		
		return resp;
	}
	
	/**
     * CRUD: Atualiza banca
     * @param conn: Connection
     */
	public void updateProfessorBanca(ProfessorBanca professorBanca) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "UPDATE professores_banca SET avaliacao = ? WHERE id = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setDouble(1, professorBanca.getAvaliacao());
			stm.setInt(3, professorBanca.getId());
			
			stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
     * CRUD: Deleta Banca
     * @param conn: Connection
     */
	public void deleteBanca(ProfessorBanca professorBanca) {
		Connection conn = new ConnectionFactory().getConnection();
		
		String sqlComand = "DELETE FROM professores_banca WHERE Id = ?";
		try(PreparedStatement stm = conn.prepareStatement(sqlComand, Statement.RETURN_GENERATED_KEYS)){
			stm.setInt(1, professorBanca.getId());
			stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * CRUD: Carrega dados do professor
     * @param conn: Connection
     */
	
	public ProfessorBanca loadProfessorBanca(int id) {
		ProfessorBanca professorBanca = new ProfessorBanca();
		BancaDAO bancaDAO = new BancaDAO();
		ProfessorDAO professorDAO = new ProfessorDAO();

		Connection conn = new ConnectionFactory().getConnection();
		String sqlInsert = "SELECT avaliacao FROM professores_banca WHERE professor_banca.id =?";

		try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){

			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();

			if(rs.next()) {
				professorBanca.setAvaliacao(rs.getDouble("Avaliacao"));
				professorBanca.setBanca(bancaDAO.loadBanca(rs.getInt("banca")));
				professorBanca.setProfessor(professorDAO.load(rs.getInt("professor")));
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return professorBanca;
	}
	
	
	/**
     * CRUD: Carrega dados de todos os alunos de um grupo
     * @param conn: Connection
     */
	
	public ArrayList<Aluno> loadAlunosGrupoBanca(int idGrupo) {
		ArrayList<Aluno> lista = new ArrayList<Aluno>();
		Aluno aluno = null;
		Connection conn = new ConnectionFactory().getConnection();
		String sqlInsert = "SELECT u.id, u.nome, u.email, u.senha, a.Ra FROM usuario u "
							+ "JOIN aluno a ON u.id = a.aluno_id "
							+ "JOIN turma_aluno t ON t.aluno_id = a.aluno_id "
							+ "JOIN grupo g ON g.id = t.grupo_id WHERE g.id = ?";

		try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){

			stm.setInt(1, idGrupo);
			ResultSet rs = stm.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String senha = rs.getString("senha");
				String ra = rs.getString("ra");
				
				aluno = new Aluno(id, nome, email, senha, ra);
				lista.add(aluno);
			}
			System.out.println("Lista carregada com sucesso");

		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Lista não carregada, erro: " + e);
		}
		return lista;
	}
	
	
	/**
     * CRUD: Carrega dados do orientador de um grupo
     * @param conn: Connection
     */
	
	public Professor loadOrientadorGrupo(int idGrupo) {
		Professor orientador = null;
		Connection conn = new ConnectionFactory().getConnection();
		String sqlInsert = "SELECT u.id, u.nome, u.email, u.senha, p.matricula, p.administrador FROM usuario u "
							+ "JOIN professor p ON u.id = p.professor_id "
							+ "JOIN grupo g ON g.orientador_id = p.professor_id WHERE g.id = ?";

		try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){

			stm.setInt(1, idGrupo);
			ResultSet rs = stm.executeQuery();

			if(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String senha = rs.getString("senha");
				String matricula = rs.getString("matricula");
				int administrador = rs.getInt("administrador");
				
				orientador = new Professor(id, nome, email, senha, matricula, administrador);
			}
			System.out.println("Orientador carregado com sucesso");

		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Orientador não carregada, erro: " + e);
		}
		return orientador;
	}
	
	
	/**
     * CRUD: Carrega dados de uma banca 
     * @param conn: Connection
     */
	
	public Banca loadBancaGrupo(int idGrupo) {
		Connection conn = new ConnectionFactory().getConnection();
		String sqlInsert = "SELECT b.id, b.data, b.sala FROM banca b JOIN grupo g ON g.id = b.grupo_id WHERE g.id = ?";
		Banca banca = new Banca();

		try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){

			stm.setInt(1, idGrupo);
			ResultSet rs = stm.executeQuery();

			if(rs.next()) {
				banca.setId(rs.getInt("id"));
				banca.setSala(rs.getString("sala"));
				banca.setData(rs.getDate("data"));
				
			}
			System.out.println("Banca carregada com sucesso");

		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Banca não carregada, erro: " + e);
		}
		return banca;
	}
	
	
	/**
     * CRUD: Carrega os professores de uma banca 
     * @param conn: Connection
     */
	
	public ArrayList<Professor> loadProfessoresBanca(int idBanca) {
		Connection conn = new ConnectionFactory().getConnection();
		String sqlInsert = "SELECT u.id, u.nome, u.email, u.senha, p.matricula, p.administrador FROM banca b "
							+ "JOIN professores_banca pb ON pb.banca_id = b.id "
							+ "JOIN professor p ON p.professor_id = pb.professor_id "
							+ "JOIN usuario u ON u.id = p.professor_id WHERE b.id = ?";
		
		Professor professor = null;
		ArrayList<Professor> lista = new ArrayList<>();

		try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){

			stm.setInt(1, idBanca);
			ResultSet rs = stm.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String senha = rs.getString("senha");
				String matricula = rs.getString("matricula");
				int administrador = rs.getInt("administrador");
				
				professor = new Professor(id, nome, email, senha, matricula, administrador);	
				lista.add(professor);
				
			}
			System.out.println("Professores carregados com sucesso");

		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Professores não podem ser carregados, erro: " + e);
		}
		return lista;
	}
	
	
	/**
     * CRUD: Carrega o tema de um grupo
     * @param conn: Connection
     */
	
	public String loadTemaGrupo(int idGrupo) {
		Connection conn = new ConnectionFactory().getConnection();
		String sqlInsert = "SELECT DISTINCT tema.titulo FROM grupo g "
						+ "JOIN turma_aluno ta ON g.id = ta.grupo_id "
						+ "JOIN turma t ON t.id = ta.turma_id "
						+ "JOIN tema ON tema.id = t.tema_id "
						+ "WHERE g.id = ?";
		
		String tema = null;

		try(PreparedStatement stm = conn.prepareStatement(sqlInsert)){

			stm.setInt(1, idGrupo);
			ResultSet rs = stm.executeQuery();

			if(rs.next()) {
				tema = rs.getString("titulo");
			}
			System.out.println("Tema carregado com sucesso");

		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Tema não podem ser carregado, erro: " + e);
		}
		return tema;
	}	
	
}
