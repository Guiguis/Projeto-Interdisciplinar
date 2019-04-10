package service;

import java.io.Serializable;

import model.Banca;
import dao.BancaDAO;

public class BancaService implements  Serializable{

	BancaDAO dao;
	
	public BancaService() {
		dao = new BancaDAO();
	}
	
	public void createBanca(Banca banca) {
		dao.createBanca(banca);
	}
	
	public void updateBanca(Banca banca) {
		dao.updateBanca(banca);
	}
	
	public void deleteBanca(Banca banca) {
		dao.deleteBanca(banca);
	}
	
	public Banca loadBanca(int id) {
		return dao.loadBanca(id);
	}
	
}
