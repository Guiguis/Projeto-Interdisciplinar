package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Aluno;
import model.Banca;
import model.Grupo;
import model.Professor;
import service.GrupoService;
import service.ProfessorBancaService;

/**
 * Servlet implementation class GerarDeclaracaoController
 */
@WebServlet("/GerarDeclaracaoController")
public class GerarDeclaracaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerarDeclaracaoController() {
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
		
		//Pega o id do grupo
		String pId = request.getParameter("id");
		int id = -1;
		try {
			id = Integer.parseInt(pId);
		} catch(Exception e) {
			
		}
		
		//Instacia a placa ProfessorBancaService
		ProfessorBancaService pbs = new ProfessorBancaService();
		GrupoService gs = new GrupoService();
		
		//Carrega todoas as informacoes de declaracao
		String tema = pbs.loadTemaGrupo(id);
		ArrayList<Aluno> listaAlunos = pbs.loadAlunosGrupoBanca(id);
		Professor orientador = pbs.loadOrientadorGrupo(id);
		Banca banca = pbs.loadBancaGrupo(id);
		ArrayList<Professor> listaProfessores = pbs.loadProfessoresBanca(banca.getId());
		Grupo grupo = gs.load(id);
		
		
		//Envia as informacoes para a pagina
		request.setAttribute("tema", tema);
		request.setAttribute("listaAlunos", listaAlunos);
		request.setAttribute("orientador", orientador);
		request.setAttribute("banca", banca);
		request.setAttribute("listaProfessores", listaProfessores);
		request.setAttribute("grupo", grupo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("GerarDeclaracao.jsp");
		dispatcher.forward(request, response);
		
	}

}
