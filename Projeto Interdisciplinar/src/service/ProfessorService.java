package service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ProfessorDAO;
import model.Professor;

public class ProfessorService implements Serializable{

	ProfessorDAO dao;
	
	public ProfessorService() {
		dao = new ProfessorDAO();
	}
	
	public void create(Professor professor) {
		dao.create(professor);
	}
	
	public Professor create(String email, String senha) {
		Professor professor = new Professor(email,senha);
		dao.create(professor);
		return professor;
	}
	
	public void update(Professor professor) {
		dao.update(professor);
	}
	
	public void delete(int id) throws SQLException {
		dao.delete(id);
	}
	
	public Professor load(int id) {
		return dao.load(id);
	}
	
	public ArrayList<Professor> getProfessores(){
		return dao.getProfessores();
	}
	
	public ArrayList<Professor> buscarProfessor(String busca) {
		return dao.buscarProfessor(busca);
	}
	
	public ArrayList<Professor> carrega() {
		return dao.carrega();
	}
}
