package controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Grupo;
import model.Professor;
import service.GrupoService;
import service.ProfessorService;

/**
 * Servlet implementation class ListarGrupo
 */
@WebServlet("/CriarEmail")
public class CriarEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriarEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GrupoService gs = new GrupoService();
		ProfessorService ps = new ProfessorService();
		ArrayList<Grupo> listaGrupo = gs.carrega();
		ArrayList<Professor> listaProfessor	= ps.carrega();
		
		//Pega as informacoes principais da pagina
		request.setAttribute("listaProfessor", listaProfessor);
		request.setAttribute("listaGrupo", listaGrupo);

		RequestDispatcher dispatcher = request.getRequestDispatcher("CriarBanca.jsp");
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
