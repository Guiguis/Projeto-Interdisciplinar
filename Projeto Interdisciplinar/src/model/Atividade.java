package model;

import java.util.Date;

/**
*
* @author Grupo PI
*/
public class Atividade {

	private int id;
	private int numero;
	private Tema tema;
	private String descricao;
	private String formatoEntrega;
	private Date dataInicio;
	private Date dataFim;
	
	public Atividade(int id, Tema tema, int numero, String descricao, String formatoEntrega, Date dtInicio, Date dtFim) {
		this.id = id;
		this.tema = tema;
		this.numero = numero;
		this.descricao = descricao;
		this.formatoEntrega = formatoEntrega;
		this.dataInicio = dtInicio;
		this.dataFim = dtFim;
	}
	
	public Atividade() {
		
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Tema getTema() {
		return this.tema;
	}
	
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getFormatoEntrega() {
		return this.formatoEntrega;
	}
	
	public void setFormatoEntrega(String formatoEntrega) {
		this.formatoEntrega = formatoEntrega;
	}
	
	public Date getDtInicio() {
		return this.dataInicio;
	}
	
	
	public void setDtInicio(Date dtInicio) {
		this.dataInicio = dtInicio;
	}
	
	
	public Date getDtFim() {
		return this.dataFim;
	}
	
	
	public void setDtFim(Date dtFim) {
		this.dataFim = dtFim;
	}
	
}
