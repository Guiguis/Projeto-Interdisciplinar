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
import service.ProfessorBancaService;

/**
 * Servlet implementation class ListarGrupoDeclaracao
 */
@WebServlet("/ListarGrupoDeclaracao")
public class ListarGrupoDeclaracao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarGrupoDeclaracao() {
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
		ArrayList<Grupo> lstGrupos = null;
				
		//Pega as informacoes principais da pagina
		String acao = request.getParameter("acao");

		if(acao.equals("reiniciar")) {
			int idTurma = (turmaIdSession != null) ? Integer.parseInt(turmaIdSession) : -1;
			
			lstGrupos = gs.loadGrupoByTurma(idTurma);
				
			//Verifica se o professor logado é administrador
			Professor orientador = (Professor) session.getAttribute("usuario");
			if(orientador.getAdministrador() == 0) {
				//Verifica quais grupos o professor participou da banca
				ProfessorBancaService pbs = new ProfessorBancaService();
				Professor professorLogado = (Professor) session.getAttribute("usuario");
				ArrayList<Grupo> lstGruposParticipados = pbs.gruposParticipados(lstGrupos, professorLogado);
				
				request.setAttribute("lstGrupo", lstGruposParticipados);
			}
			
			request.setAttribute("lstGrupo", lstGrupos);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListarGruposDeclaracao.jsp");
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
