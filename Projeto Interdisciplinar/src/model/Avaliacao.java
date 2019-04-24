package model;

import java.sql.Date;

/**
*
* @author Grupo PI
*/
public class Avaliacao {
	
	private int id;
	private double nota;
	private Entrega entrega;
	private Date dataAvaliacao;
	private String comentarios;
	private int turmaAluno;
	
	public Avaliacao() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Avaliacao(int turmaAluno, double nota, String comentarios, Date dataAvaliacao) {
		this.turmaAluno = turmaAluno;
		this.nota = nota;
		this.comentarios = comentarios;
		this.dataAvaliacao = dataAvaliacao;
	}
	
	public int getTurmaAluno() {
		return turmaAluno;
	}
	
	public void setTurmaAluno(int turmaAluno) {
		this.turmaAluno = turmaAluno;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Entrega getEntrega() {
		return entrega;
	}
	
	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}
	
	public double getNota() {
		return nota;
	}
	
	public void setNota(double nota) {
		this.nota = nota;
	}
	
	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}
	
	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}
	
	public String getComentarios() {
		return comentarios;
	}
	
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
}
