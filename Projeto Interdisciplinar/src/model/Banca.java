package model;

import java.sql.Date;
import java.util.ArrayList;

/**
*
* @author Grupo PI
*/
public class Banca {
	private int id;
	private Date data;
	private String sala;
	private Grupo grupo;
	private ArrayList<ProfessorBanca> lstProfessorBanca;
	
	public Banca() {
		// TODO Auto-generated constructor stub
	}
	
	public Banca(Grupo grupo, Date data, String sala) {
		this.grupo = grupo;
		this.data = data;
		this.sala = sala;
	}
	
	public Banca(int id, Date data, String sala) {
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
	
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public ArrayList<ProfessorBanca> getLstProfessorBanca() {
		return lstProfessorBanca;
	}

	public void setLstProfessorBanca(ArrayList<ProfessorBanca> lstProfessorBanca) {
		this.lstProfessorBanca = lstProfessorBanca;
	}
	
}
