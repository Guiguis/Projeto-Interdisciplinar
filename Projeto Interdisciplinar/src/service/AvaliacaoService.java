package service;

import java.io.Serializable;

import model.Avaliacao;
import dao.AvaliacaoDAO;

public class AvaliacaoService implements Serializable{

	AvaliacaoDAO dao;
	
	public AvaliacaoService() {
		dao = new AvaliacaoDAO();
	}
	
	public void createAvaliacao(Avaliacao avaliacao) {
		dao.createAvaliacao(avaliacao);
	}
	
	public void updateAvaliacao(Avaliacao avaliacao) {
		dao.updateAvaliacao(avaliacao);
	}
	
	public void deleteAvaliacao(Avaliacao avaliacao) {
		dao.deleteAvaliacao(avaliacao);
	}
	
	public void loadAvaliacao(int id) {
		dao.loadAvaliacao(id);
	}
}
