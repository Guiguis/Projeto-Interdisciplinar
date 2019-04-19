package service;

import java.io.Serializable;

import model.Avaliacao;
import dao.AvaliacaoDAO;

public class AvaliacaoService implements Serializable{

	AvaliacaoDAO dao;
	
	public AvaliacaoService() {
		dao = new AvaliacaoDAO();
	}
	
	public void createAvaliacao(Avaliacao [] lstAvaliacao) {
		for(int i = 0; i < lstAvaliacao.length; i++) {
			dao.createAvaliacao(lstAvaliacao[i]);
		}
	}
	
	public void updateAvaliacao(Avaliacao avaliacao) {
		dao.updateAvaliacao(avaliacao);
	}
	
	public void deleteAvaliacao(Avaliacao avaliacao) {
		dao.deleteAvaliacao(avaliacao);
	}
	
	public Avaliacao loadAvaliacao(int id) {
		return dao.loadAvaliacao(id);
	}
}
