package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Aluno;
import model.Professor;
import service.AlunoService;
import service.ProfessorService;
import service.UsuarioService;

/**
 * Servlet implementation class ManterLogin
 */
@WebServlet("/ManterLogin")
public class ManterLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManterLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("user");
		String senha = request.getParameter("senha");
		String erro = null;
		
		//Carrega os dados do BD
		UsuarioService us = new UsuarioService();
		int id = us.logar(email, senha);	
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession();	
		
		if(id > 0) {
			ProfessorService ps = new ProfessorService();
			Professor professor = ps.load(id);
			
			//verifica se o usuario é um professor
			if(professor != null) {
				session.setAttribute("usuario", professor);
				view = request.getRequestDispatcher("LoginManter");
			}
			//carrega os dados de aluno
			else {
				AlunoService as = new AlunoService();
				Aluno aluno = as.load(id);
				session.setAttribute("usuario", aluno);
				view = request.getRequestDispatcher("Aluno.jsp");
			}		
		}	
		if(id <= 0) {
			erro = "Usuario ou senha incorreto";
			session.setAttribute("erro", erro);
			view = request.getRequestDispatcher("Login.jsp");
			
		}
		view.forward(request, response);	
	}

}
