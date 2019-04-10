package service;

import dao.AlunoDAO;
import model.Aluno;

public class AlunoService {

	AlunoDAO dao;
	
	public AlunoService() {
		dao = new AlunoDAO();
	}
	
	public void create(Aluno aluno) {
		dao.create(aluno);
	}
	
	public void update(Aluno aluno) {
		dao.update(aluno);
	}
	
	public void delete(int id) {
		dao.delete(id);
	}
	
	public Aluno load(int id) {
		return dao.load(id);
	}
}
