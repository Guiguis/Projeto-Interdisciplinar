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
import service.EntregaService;

/**
 * Servlet implementation class ListarEntregaController
 */
@WebServlet("/ListarEntregaController")
public class ListarEntregaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarEntregaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntregaService es = new EntregaService();
		ArrayList<Entrega> lista = null;
		
		//Pega as informacoes principais da pagina
		String busca = request.getParameter("data[search]");
		String acao = request.getParameter("acao");
		String pId = request.getParameter("id");
		HttpSession session = request.getSession();
		
		int id = -1;
		try {
			id = Integer.parseInt(pId);
		} catch(Exception e) {}
		
		
		if(acao.equals("busca")) {
			lista = es.loadTodos(id);
			session.setAttribute("lista", lista);
			
		} else if(acao.equals("reiniciar")) {
			lista = es.loadTodos(id);
			session.setAttribute("lista", lista);
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListarEntregas.jsp");
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
