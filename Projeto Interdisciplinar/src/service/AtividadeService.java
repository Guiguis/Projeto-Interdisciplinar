package service;

import dao.AtividadeDAO;
import model.Atividade;

public class AtividadeService {
	AtividadeDAO atividadeDAO;

	public AtividadeService() {
		atividadeDAO = new AtividadeDAO();
	}
	
	public void create() {
		Atividade atividade = new Atividade(); 
		atividadeDAO.create(atividade);
	}
	
	public void delete(int id) {
		atividadeDAO.delete(id);
	}
	
	public Atividade load(int id) {
		return atividadeDAO.load(id);
	}
	
	

	
	
}
