package model;

import java.util.ArrayList;

/**
*
* @author Grupo PI
*/
public class Grupo {
	
	private int id;
	private int numero;
	private String nome;
	private Professor orientador;
	private ArrayList<Aluno> lstAlunos;
	
	public Grupo() {
		
	}
	
	public Grupo(int id, int numero, String nome) {
		this.id = id;
		this.numero = numero;
		this.nome = nome;
	}
	
	public Grupo(int id, int numero, String nome, Professor orientador) {
		this.id = id;
		this.orientador = orientador;
		this.numero = numero;
		this.nome = nome;
	}
	
	public Grupo(int id, int numero, String nome, Professor orientador, ArrayList<Aluno> lstAlunos) {
		this.id = id;
		this.orientador = orientador;
		this.numero = numero;
		this.nome = nome;
		this.lstAlunos = lstAlunos;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Professor getOrientador() {
		return orientador;
	}
	
	public void setOrientador(Professor orientador) {
		this.orientador = orientador;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<Aluno> getAlunos() {
		return lstAlunos;
	}
	public void setAlunos(ArrayList<Aluno> lstAlunos) {
		this.lstAlunos = lstAlunos;
	}
}
