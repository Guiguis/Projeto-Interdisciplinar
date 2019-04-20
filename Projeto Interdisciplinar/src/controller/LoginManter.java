package controller;

import java.io.IOException;

import model.Professor;
import service.ProfessorService;

/**
 * Servlet implementation class LoginManter
 */
@WebServlet("/Login")
public class LoginManter {
	public class ManterProfessorController extends HttpServlet {
		private static final long serialVersionUID = 1L;

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String pNome = request.getParameter("nome");
			String pEmail = request.getParameter("email");
			String pSenha = request.getParameter("senha");
			String pMatricula = request.getParameter("matricula");
			String pAdministrador = request.getParameter("administrador");
			
			if(pAdministrador == null || pAdministrador.isEmpty()) pAdministrador = "0";
			if(pAdministrador.equals("on")) pAdministrador = "1";

			// instanciar o javabean
			Professor professor = new Professor(pEmail, pSenha);
			professor.setNome(pNome);
			professor.setMatricula(pMatricula);
			professor.setAdministrador(Integer.parseInt(pAdministrador));

			// instanciar o service
			ProfessorService ps = new ProfessorService();
			ps.create(professor);
			professor = ps.load(professor.getId());

			// enviar para o jsp
			request.setAttribute("professor", professor);

			RequestDispatcher view = request.getRequestDispatcher("Professor.jsp");
			view.forward(request, response);

		}
}
