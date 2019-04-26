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
	
	public void updateAvaliacao(Avaliacao avaliacao) {
		dao.updateAvaliacao(avaliacao);
	}
	
	public void deleteAvaliacao(Avaliacao avaliacao) {
		dao.deleteAvaliacao(avaliacao);
	}
	
	public Avaliacao load(int id) {
		return dao.load(id);
	}
	
	public ArrayList<Date> loadDatas(Grupo grupo) {
		return dao.loadDatas(grupo);
	}
	
	public ArrayList<Avaliacao> loadAvaliacoes(Date data) {
		return dao.loadAvaliacoes(data);
	}
}
