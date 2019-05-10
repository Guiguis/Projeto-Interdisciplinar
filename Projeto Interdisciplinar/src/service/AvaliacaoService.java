package service;

import java.io.Serializable;
import java.util.ArrayList;

import dao.AlunoDAO;
import dao.AvaliacaoDAO;
import model.Aluno;
import model.Avaliacao;
import model.Grupo;
import model.Professor;

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
	
	//Verifica se o professor logado pode avaliar o grupo
	public ArrayList<Grupo> verificaGruposAvaliar(ArrayList<Grupo> listaGrupos, Professor orientador){
		
		for(int i = 0; i < listaGrupos.size(); i++) {
			if(listaGrupos.get(i).getOrientador().getId() != orientador.getId()) {
				listaGrupos.remove(i);
			}
		}
		return listaGrupos;
	}
	
	
}
