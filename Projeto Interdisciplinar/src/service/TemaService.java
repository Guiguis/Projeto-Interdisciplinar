package service;

import java.io.Serializable;

import model.Tema;
import dao.TemaDAO;

public class TemaService implements Serializable{

	TemaDAO dao;
	
	public TemaService() {
		dao = new TemaDAO();
	}
	
	public Tema loadTema(int id) {
		return dao.loadTema(id);
	}
	
}
