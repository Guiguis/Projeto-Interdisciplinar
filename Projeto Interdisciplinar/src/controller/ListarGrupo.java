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

import model.Grupo;
import model.Professor;
import service.AvaliacaoService;
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
		
		//SESSION
		HttpSession session   = request.getSession();
		String turmaIdSession = (String) session.getAttribute("turmaId");

		//BUSCA GRUPO DE ACORDO COM TURMA
		GrupoService gs = new GrupoService();
		ArrayList<Grupo> lstGrupo = null;
		
		//Pega as informacoes principais da pagina
		String acao = request.getParameter("acao");

		if(acao.equals("reiniciar")) {
			int idTurma = (turmaIdSession != null) ? Integer.parseInt(turmaIdSession) : -1;
			
			//busca todos os grupos da turma selecionada
			lstGrupo = gs.loadGrupoByTurma(idTurma);
			
			//Verifica se o professor logado � administrador
			Professor orientador = (Professor) session.getAttribute("usuario");
			if(orientador.getAdministrador() == 0) {
				
				//Verrifica quais grupos pertencem ao professor
				AvaliacaoService as = new AvaliacaoService();
				ArrayList<Grupo> listaGrupo = as.verificaGruposAvaliar(lstGrupo, orientador);
				
				request.setAttribute("lstGrupo", listaGrupo);
			}
			
			request.setAttribute("lstGrupo", lstGrupo);
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
