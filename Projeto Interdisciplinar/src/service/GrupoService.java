package service;

import java.util.ArrayList;

import dao.GrupoDAO;
import model.Grupo;
import model.Turma;

public class GrupoService {

	GrupoDAO dao;
	
	public GrupoService() {
		dao = new GrupoDAO();
	}
	
	public Grupo load(int id) { 
		return dao.load(id);
	}
	
	public ArrayList<Grupo> loadGrupoByTurma(int idTurma) {
		return dao.loadGrupoByTurma(idTurma);
	}
	
	public ArrayList<Grupo> carrega() {
		return dao.carrega();
	}
	
	
}
