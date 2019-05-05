package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsuarioDAO;
import model.Aluno;
import model.Professor;
import service.AlunoService;
import service.ProfessorService;

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
		String usuario = request.getParameter("user");
		String senha = request.getParameter("senha");
		//Carrega os dados do BD
		UsuarioDAO usuario1 = new UsuarioDAO();
		int id = usuario1.Load(usuario, senha);
		RequestDispatcher view = null;
		HttpSession session = request.getSession();	
//		Instancia os objetos
		Aluno aluno = new Aluno();
		Professor professor = new Professor();
		aluno.setId(id);
		professor.setId(id);		
		aluno.load();
		professor.load();
//		 Verificação se usuario e cadastrado
		if(id == -1) {
			System.out.println("erro!");
			System.out.println("Usuario não cadastrado!");
			System.out.println(usuario);
			System.out.println(senha);
			System.out.println(id);
			view = request.getRequestDispatcher("Login.jsp");
		}		
//		Teste Logico para carregar dados BD em um objeto certo e redirecionar o http
		else if(aluno.getRa() != null) {
			System.out.println("Aluno OK");;
			session.setAttribute("AlunoLogado", aluno);
			view = request.getRequestDispatcher("Aluno.jsp");	
		}else if(professor.getMatricula() != null) {
			System.out.println("Professor OK");
			session.setAttribute("ProfessorLogado", professor);
			if(professor.getAdministrador() == 0) {
				System.out.println("Professor logado não é ADM!");
				view = request.getRequestDispatcher("ListarEntregas.jsp");
			}else if(professor.getAdministrador() == 1) {
				System.out.println("Professor Logado é ADM!");
				view = request.getRequestDispatcher("ListarProfessores.jsp");
			}
			else System.out.println("Erro! dentro de IF professor");
		}		
		else System.out.println("Erro! Não Carregou nenhuma ID");
	}

}
