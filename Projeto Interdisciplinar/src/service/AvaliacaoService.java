package service;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import dao.AlunoDAO;
import dao.AvaliacaoDAO;
import model.Aluno;
import model.Avaliacao;
import model.Grupo;

public class AvaliacaoService implements Serializable{

	AvaliacaoDAO dao;
	
	public AvaliacaoService() {
		dao = new AvaliacaoDAO();
	}
	
	public void createAvaliacao(ArrayList<Avaliacao> lstAvaliacao, int idGrupo, ArrayList<Aluno> listaAluno) {
		AlunoDAO AlunoDAO = new AlunoDAO();
		ArrayList<Integer> lista = AlunoDAO.turmaAluno(idGrupo, listaAluno);
		
		for(int i = 0; i < lstAvaliacao.size(); i++) {
			int id = lista.get(i);
			dao.createAvaliacao(lstAvaliacao.get(i), idGrupo, id);
		}
	}
	
	public void updateAvaliacao(ArrayList<Avaliacao> listaAvaliacao) {
		for(int i = 0; i < listaAvaliacao.size(); i++) {
			dao.updateAvaliacao(listaAvaliacao.get(i));
		}
		
	}
	
	public void deleteAvaliacao(int idGrupo, ArrayList<Aluno> listaAluno, int entregaId) {
		AlunoDAO alunoDAO = new AlunoDAO();
		ArrayList<Integer> lista = alunoDAO.turmaAluno(idGrupo, listaAluno);
		for(int i = 0; i < lista.size(); i++) {
			dao.deleteAvaliacao(lista.get(i), entregaId);
		}
	}
	
	public ArrayList<Avaliacao> load(int idEntrega, int idGrupo, ArrayList<Aluno> listaAluno) {
		AlunoDAO AlunoDAO = new AlunoDAO();
		ArrayList<Integer> lista = AlunoDAO.turmaAluno(idGrupo, listaAluno);
		ArrayList<Avaliacao> listaAvaliacao = new ArrayList<Avaliacao>();
		for(int i = 0; i < lista.size(); i++) {
			listaAvaliacao.add(dao.load(idEntrega, lista.get(i)));
		}
		return listaAvaliacao;
	}
	
	public Avaliacao loadPorId(int id) {
		return dao.loadPorId(id);
	}
	
	public ArrayList<Date> loadDatas(Grupo grupo) {
		return dao.loadDatas(grupo);
	}
	
	public ArrayList<Avaliacao> loadAvaliacoes(Date data) {
		return dao.loadAvaliacoes(data);
	}
	
}
