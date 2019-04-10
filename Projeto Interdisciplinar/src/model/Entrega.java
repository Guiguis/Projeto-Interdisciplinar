package model;

import java.sql.Date;

/**
*
* @author Grupo PI
*/
public class Entrega {

	private int id;
	private Grupo grupo;
	private Atividade atividade;
	private Date dataCadastro;
	
	public Entrega() {
		// TODO Auto-generated constructor stub
	}
		
	public Entrega(Grupo grupo, Atividade atividade, int id, Date dtCadastro) {
		this.id = id;
		this.grupo = grupo;
		this.atividade = atividade;
		this.dataCadastro = dtCadastro;
	}
	
	public Grupo getGrupo() {
		return this.grupo;
	}
	
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public Atividade getAtividade() {
		return this.atividade;
	}
	
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDtCadastro() {
		return this.dataCadastro;
	}
	
	public void setDtCadastro(Date dtCadastro) {
		this.dataCadastro = dtCadastro;
	}
	
}