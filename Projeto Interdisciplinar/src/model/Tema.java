package model;

import java.util.Date;
import java.util.ArrayList;

/**
*
* @author Grupo PI
*/
public class Tema {

	private int id;
	private String titulo;
	private String introducao;
	private String requisitos;
	private Date dataCadastro;
	private ArrayList<Turma> lstTurmas;
	
	public Tema() {
		// TODO Auto-generated constructor stub
	}
	
	public Tema(String titulo, String introducao, String requisitos, Date dataCadastro) {
		this.titulo = titulo;
		this.introducao = introducao;
		this.requisitos = requisitos;
		this.dataCadastro = dataCadastro;
	}
	
	public void tema(String titulo) {
		this.titulo = titulo;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getIntroducao() {
		return introducao;
	}
	
	public void setIntroducao(String introducao) {
		this.introducao = introducao;
	}
	
	public String getRequisitos() {
		return requisitos;
	}
	
	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public ArrayList<Turma> getLstTurmas() {
		return lstTurmas;
	}

	public void setLstTurmas(ArrayList<Turma> lstTurmas) {
		this.lstTurmas = lstTurmas;
	}
	 
}
