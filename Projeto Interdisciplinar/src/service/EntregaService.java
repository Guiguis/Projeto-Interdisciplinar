package service;

import java.io.Serializable;

import dao.EntregaDAO;
import model.Entrega;

public class EntregaService implements Serializable{

     EntregaDAO dao;
	
	public EntregaService() {
		dao = new EntregaDAO();
	}
	
	public Entrega loadEntrega(int id) {
		return dao.loadEntrega(id);
	}
	
}
