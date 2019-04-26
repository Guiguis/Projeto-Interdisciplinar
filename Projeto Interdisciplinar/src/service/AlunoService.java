package service;

import java.util.ArrayList;

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
	
	//passando um grupo id ele retorna a lista de alunos que pertence ao mesmo
	public ArrayList<Aluno> grupoAlunos(int id) {
		return dao.grupoAlunos(id);
	}
	
	//retorna o id de turma_aluno de um aluno passando seu grupo e id
	public ArrayList<Integer> turmaAluno(int idGrupo, ArrayList<Aluno> listaAluno) {
		return dao.turmaAluno(idGrupo, listaAluno);
	}
	
}
