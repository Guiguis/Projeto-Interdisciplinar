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
	
	public boolean verificarEmail(String email) {
		return dao.verificarEmail(email);
	}
	public String BuscaRaOuMatricula(String email, String criterio) {
		if(criterio.equals("RA") || criterio.equals("ra")) {
			 return dao.buscaRA(email);
		}
		else return dao.buscaMatricula(email);
	}
	public String token(String email,String criterio) {
		String token = dao.BucarOldPassword(email, criterio) + this.BuscaRaOuMatricula(email, criterio);
		return token;
	}
	
	public String confirmarToken(String email,String criterio, String raOuMatricula) {
		String token = this.BuscaRaOuMatricula(email, criterio) + raOuMatricula;
		return token;
	}
	
	public void alterarSenha(String senha, String email) {
		dao.updateSenha(senha, email);
	}
	
}
