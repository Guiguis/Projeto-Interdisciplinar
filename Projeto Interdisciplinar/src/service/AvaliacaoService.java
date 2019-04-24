package service;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import dao.AvaliacaoDAO;
import model.Avaliacao;
import model.Grupo;

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
