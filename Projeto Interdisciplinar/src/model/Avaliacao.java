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
	private Aluno aluno;
	
	public Avaliacao() {
		// TODO Auto-generated constructor stub
	}
	
	public Avaliacao(int id, Aluno aluno, Entrega entrega, double nota, String comentarios, Date dataAvaliacao) {
		this.id = id;
		this.entrega = entrega;
		this.nota = nota;
		this.comentarios = comentarios;
		this.dataAvaliacao = dataAvaliacao;
		this.aluno = aluno;
	}
	
	public Avaliacao(Entrega entrega, double nota, String comentarios, Date dataAvaliacao) {
		this.entrega = entrega;
		this.nota = nota;
		this.comentarios = comentarios;
		this.dataAvaliacao = dataAvaliacao;
	}
	
	public Avaliacao(int id, double nota, String comentarios, Date dataAvaliacao) {
		this.id = id;
		this.nota = nota;
		this.comentarios = comentarios;
		this.dataAvaliacao = dataAvaliacao;
	}
	
	public Avaliacao(int id, double nota, String comentarios) {
		this.id = id;
		this.nota = nota;
		this.comentarios = comentarios;
	}
	
	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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
