package model;

import java.util.ArrayList;

/**
*
* @author Grupo PI
*/
public class ProfessorBanca {
	
	private int id;
	private double    avaliacao;
	private Professor professor;
	private Banca     banca;
	
	public ProfessorBanca() {
	}
	
	public ProfessorBanca(Professor professor, Banca banca) {
		this.professor = professor;
		this.banca = banca;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Banca getBanca() {
		return banca;
	}

	public void setBanca(Banca banca) {
		this.banca = banca;
	}	
}

