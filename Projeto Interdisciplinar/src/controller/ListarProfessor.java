package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Professor;
import service.ProfessorService;

/**
 * Servlet implementation class ListarProfessor
 */
@WebServlet("/ListarProfessor")
public class ListarProfessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarProfessor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProfessorService ps = new ProfessorService();
		ArrayList<Professor> lstProfessor = null;
		String buscar = request.getParameter("data[search]");
		String acao = request.getParameter("acao");
		HttpSession session = request.getSession();
		

		
		if (acao.equals("Buscar")) {
			lstProfessor = ps.buscarProfessor(buscar);
			session.setAttribute("lstProfessor", lstProfessor);
		} else if (acao.equals("reiniciar")) {
			lstProfessor = ps.getProfessores();
			session.setAttribute("lstProfessor", lstProfessor);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListarProfessores.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
