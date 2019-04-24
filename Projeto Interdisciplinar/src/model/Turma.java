package model;

import java.util.ArrayList;

/**
*
* @author Grupo PI
*/
public class Turma {
	
	private int  id;
	private int  semestreLetivo;
	private int  ano;
	private Tema tema;
	private String sigla;
	private ArrayList<Aluno> lstAlunos;
	
	public Turma() {
		
	}
	
	public Turma(int semestreLetivo, int ano) {
		this.semestreLetivo = semestreLetivo;
		this.ano = ano;
	}
	
	public Turma(int id, int semestreLetivo, int ano, String sigla) {
		this.id = id;
		this.semestreLetivo = semestreLetivo;
		this.ano = ano;
		this.sigla = sigla;
	}
	
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
	
	public int getAno() {
		return ano;
	}
	
	public void setAno(int ano) {
		this.ano = ano;
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
