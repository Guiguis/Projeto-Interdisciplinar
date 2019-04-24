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
	
	//Carrega Todos os anos/semestres -----------------------------------------------------------------
	public ArrayList<Turma> mostrarAno() {
		return dao.mostrarAno();
	}
	
	//Carrega todas as turmas baseada no ano e semestre-------------------------------------------
	public ArrayList<Turma> mostrarSigla(Turma turma) {
		return dao.mostrarSigla(turma);
	}
	
}
