package model;

import java.util.ArrayList;

/**
*
* @author Grupo PI
*/
public class Turma {
	
	private int  id;
	private int  semestreLetivo;
	private int  anoLetivo;
	private Tema tema;
	private String sigla;
	private ArrayList<Aluno> lstAlunos;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Tema getTema() {
		return tema;
	}
	
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	public int getSemestreLetivo() {
		return semestreLetivo;
	}
	
	public void setSemestreLetivo(int semestreLetivo) {
		this.semestreLetivo = semestreLetivo;
	}
	
	public int getAnoLetivo() {
		return anoLetivo;
	}
	
	public void setAnoLetivo(int anoLetivo) {
		this.anoLetivo = anoLetivo;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public ArrayList<Aluno> getLstAlunos() {
		return lstAlunos;
	}

	public void setLstAlunos(ArrayList<Aluno> lstAlunos) {
		this.lstAlunos = lstAlunos;
	}
	
}
