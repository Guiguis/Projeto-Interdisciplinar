package service;

import java.io.Serializable;

import dao.UsuarioDAO;

public class UsuarioService implements Serializable{
	
	UsuarioDAO dao;
	
	public UsuarioService() {
		dao = new UsuarioDAO();
	}

	public int logar(String email, String senha) {
		return dao.logar(email, senha);
	}
	
}
