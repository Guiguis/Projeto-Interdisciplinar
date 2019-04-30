package service;

import java.io.Serializable;
import java.util.ArrayList;

import dao.ProfessorBancaDAO;
import model.Aluno;
import model.Banca;
import model.Professor;
import model.ProfessorBanca;

public class ProfessorBancaService implements Serializable{

	ProfessorBancaDAO dao;
	
	public ProfessorBancaService() {
		dao = new ProfessorBancaDAO();
	}
	
	public void createProfessorBanca(ProfessorBanca professorBanca) {
		dao.createBanca(professorBanca);
	}
	
	public void updateProfessorBanca(ProfessorBanca professorBanca) {
		dao.updateProfessorBanca(professorBanca);
	}
	
	public void deleteProfessorBanca(ProfessorBanca professorBanca) {
		dao.deleteBanca(professorBanca);
	}
	
	public ProfessorBanca loadProfessorBanca(int id) {
		return dao.loadProfessorBanca(id);
	}
	
	//----
	public String loadTemaGrupo(int idGrupo) {
		return dao.loadTemaGrupo(idGrupo);
	}
	
	//--
	public ArrayList<Aluno> loadAlunosGrupoBanca(int idGrupo) {
		return dao.loadAlunosGrupoBanca(idGrupo);
	}
	
	//--
	public Professor loadOrientadorGrupo(int idGrupo) {
		return dao.loadOrientadorGrupo(idGrupo);
	}
	
	public Banca loadBancaGrupo(int idGrupo) {
		return dao.loadBancaGrupo(idGrupo);
	}
	
	public ArrayList<Professor> loadProfessoresBanca(int idBanca) {
		return dao.loadProfessoresBanca(idBanca);
	}
	
}
