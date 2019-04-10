package service;

import java.io.Serializable;

import model.ProfessorBanca;
import dao.ProfessorBancaDAO;

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
	
}
