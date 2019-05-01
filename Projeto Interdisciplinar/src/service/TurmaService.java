package service;

import java.io.Serializable;
import java.util.ArrayList;

import model.Turma;
import dao.TurmaDAO;

public class TurmaService implements Serializable {
	
	TurmaDAO dao;
	
	public TurmaService() {
		dao = new TurmaDAO();
	}
	
	public ArrayList<Turma> mostrarAno() {
		return dao.mostrarAno();
	}
	
	public ArrayList<Turma> getTurmasPeriodo(int ano, int semestre) {
		return dao.getTurmasPeriodo(ano, semestre);
	}
	
}
