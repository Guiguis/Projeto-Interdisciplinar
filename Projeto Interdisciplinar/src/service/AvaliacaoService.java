package service;

import java.io.Serializable;
import java.util.ArrayList;

import dao.AvaliacaoDAO;
import model.Avaliacao;

public class AvaliacaoService implements Serializable{

	AvaliacaoDAO dao;
	
	public AvaliacaoService() {
		dao = new AvaliacaoDAO();
	}
	
	public void createAvaliacao(ArrayList<Avaliacao> lstAvaliacao) {
		for(int i = 0; i < lstAvaliacao.size(); i++) {
			dao.createAvaliacao(lstAvaliacao.get(i));
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
