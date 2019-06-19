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
 * Servlet implementation class CadastrarController
 */
@WebServlet("/CadastrarController")
public class CadastrarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastrarController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String opcao = request.getParameter("inputSelecao");
		String nome = request.getParameter("nome");
		String ra = request.getParameter("documentoWEB");
		String matricula = request.getParameter("documentoWEB");
		//Carrega os dados do BD
		UsuarioService us = new UsuarioService();
		int id = us.logar(email, senha);	
		System.out.println(email+" "+senha+" "+ opcao+" "+ " "+nome+" "+ra+" "+matricula);
		RequestDispatcher view = null;
		HttpSession session = request.getSession();	
		
		if(id > 0) {
			System.out.println("Primeiro if");
			request.setAttribute("erro", "Usuario Ja Cadastrado!");
			view = request.getRequestDispatcher("Cadastro.jsp");
				
		}	
		if(id <= 0) {

			if(opcao.equals("aluno")) {
				System.out.println("1");
				AlunoService alunoservice = new AlunoService();
				Aluno aluno = new Aluno();
				aluno.setNome(nome);
				aluno.setEmail(email);
				aluno.setRa(ra);
				aluno.setSenha(senha);
				alunoservice.create(aluno);
				request.setAttribute("acerto", "Aluno "+nome+ "Cadastrado com sucesso!!");
			}else if (opcao.equals("professor")) {
				System.out.println(2);
				ProfessorService professorService = new ProfessorService();
				Professor professor = new Professor();
				professor.setEmail(email);
				professor.setNome(nome);
				professor.setAdministrador(0);
				professor.setMatricula(matricula);
				professor.setSenha(senha);	
				professorService.create(professor);
				request.setAttribute("acerto", "Professor "+nome+ "Cadastrado com sucesso!!");
				
			}
//			else if (opcao.equals("administrador")) {
//				System.out.println(3);
//				ProfessorService professorService = new ProfessorService();
//				Professor professor = new Professor();
//				professor.setEmail(email);
//				professor.setNome(nome);
//				professor.setAdministrador(1);
//				professor.setMatricula(matricula);
//				professor.setSenha(senha);	
//				professorService.create(professor);	
//				request.setAttribute("acerto", "Professor "+nome+ "Cadastrado com sucesso!!");
//				
//			}
			else if (opcao.isEmpty()) {
				System.out.println("erro1");
				request.setAttribute("erro","Erro no ComboBox");
			}
		view = request.getRequestDispatcher("Login.jsp");
		}
		view.forward(request, response);	
	
	}

}
