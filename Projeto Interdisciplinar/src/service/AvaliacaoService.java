package service;

import java.io.Serializable;
import java.util.ArrayList;

import dao.AlunoDAO;
import dao.AvaliacaoDAO;
import model.Aluno;
import model.Avaliacao;

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
			dao.updateAvaliacao(listaAvaliacao);
	}
	
	public void deleteAvaliacao(int entregaId) {
		dao.deleteAvaliacao(entregaId);

	}
	
	public ArrayList<Avaliacao> load(int idEntrega) {
		ArrayList<Avaliacao> lista = new ArrayList<Avaliacao>();
		lista = dao.loadEntregaId(idEntrega);
		return lista;
	}
	
	public Avaliacao loadPorId(int id) {
		return dao.loadPorId(id);
	}
	

	
}
