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

import model.Entrega;
import model.Grupo;
import service.EntregaService;
import service.GrupoService;

/**
 * Servlet implementation class ListarGrupo
 */
@WebServlet("/ListarGrupo")
public class ListarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarGrupo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GrupoService gs = new GrupoService();
		ArrayList<Grupo> lista = null;
		
		//Pega as informacoes principais da pagina
		String busca = request.getParameter("data[search]");
		String acao = request.getParameter("acao");
		HttpSession session = request.getSession();

		
		if(acao.equals("busca")) {
			lista = gs.carrega();
			session.setAttribute("lista", lista);
			
		} else if(acao.equals("reiniciar")) {
			lista = gs.carrega();
			session.setAttribute("lista", lista);
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListarGrupos.jsp");
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
