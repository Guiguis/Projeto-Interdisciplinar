package service;

import dao.ProfessorDAO;
import model.Professor;

public class ProfessorService {

	ProfessorDAO dao;
	
	public ProfessorService() {
		dao = new ProfessorDAO();
	}
	
	public void create(Professor professor) {
		dao.create(professor);
	}
	
	public void update(Professor professor) {
		dao.update(professor);
	}
	
	public void delete(int id) {
		dao.delete(id);
	}
	
	public Professor load(int id) {
		return dao.load(id);
	}
}
