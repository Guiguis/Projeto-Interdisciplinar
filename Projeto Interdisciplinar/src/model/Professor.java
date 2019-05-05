package model;

import java.util.ArrayList;

import dao.ProfessorDAO;

/**
*
* @author Grupo PI
*/
public class Professor extends Usuario {

	private int administrador;
	private String matricula;
	private ArrayList<Grupo> lstGrupos;

	public Professor(String email, String senha) {
		super(email, senha);
	}
	
	public Professor(int id, String nome, String email, String senha, String matricula, int administrador) {
		super(id, nome, email, senha);
		this.matricula = matricula;
		this.administrador = administrador;
	}
	
	public Professor(int id, String nome, String email, String matricula, int administrador) {
		super(id, nome, email);
		this.matricula = matricula;
		this.administrador = administrador;
	}

	public Professor() {
		// TODO Auto-generated constructor stub
	}

	public int getAdministrador() {
		return administrador;
	}

	public void setAdministrador(int administrador) {
		this.administrador = administrador;
	}

	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public Professor load() {
		ProfessorDAO professorDAO = new ProfessorDAO();
		return professorDAO.load(this.getId());
	}
	
	@Override
	public String toString() {
		return "Professor [id=" + this.getId() + " matricula=" + this.getMatricula() + " admin=" + this.getAdministrador()  + " nome=" + this.getNome() + " email=" + this.getEmail() + "]"; 
	}
}
