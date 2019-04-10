package model;

import java.util.Date;
import java.util.ArrayList;

/**
*
* @author Grupo PI
*/
public class Banca {
	private int id;
	private Date data;
	private String sala;
	private ArrayList<ProfessorBanca> lstProfessorBanca;
	
	public Banca() {
		// TODO Auto-generated constructor stub
	}
	
	public Banca(String grupoId, int numero, String nome ,int id, Date data, String sala) {
		this.id = id;
		this.data = data;
		this.sala = sala;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public ArrayList<ProfessorBanca> getLstProfessorBanca() {
		return lstProfessorBanca;
	}

	public void setLstProfessorBanca(ArrayList<ProfessorBanca> lstProfessorBanca) {
		this.lstProfessorBanca = lstProfessorBanca;
	}
	
}
