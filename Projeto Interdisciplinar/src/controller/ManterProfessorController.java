package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Professor;
import service.ProfessorService;
import java.util.ArrayList;

/**
 * Servlet implementation class ManterProfessorController
 */
@WebServlet("/ManterProfessorController")
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
		String pAcao = request.getParameter("acao");
		String pNome = request.getParameter("nome");
		String pEmail = request.getParameter("email");
		String pSenha = request.getParameter("senha");
		String pMatricula = request.getParameter("matricula");
		String pAdministrador = request.getParameter("administrador");
		
		int id = -1;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException e){
			
		}
		
		if(pAdministrador == null || pAdministrador.isEmpty()) pAdministrador = "0";
		if(pAdministrador.equals("on")) pAdministrador = "1";

		// instanciar o javabean
		Professor professor = new Professor(pEmail, pSenha);
		professor.setId(id);
		professor.setNome(pNome);
		professor.setMatricula(pMatricula);
		professor.setAdministrador(Integer.parseInt(pAdministrador));

		// instanciar o service
		ProfessorService ps = new ProfessorService();		
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession();
		
		String error = "";
		if (pAcao.equals("Criar")) {
			ps.create(professor);
			ArrayList<Professor> lista = ps.getProfessores();
			session.setAttribute("lstProfessor", lista);
			view = request.getRequestDispatcher("ListarProfessores.jsp");
			
		} else if (pAcao.equals("Excluir")) {		
			try {
				ps.delete(professor.getId());
			}catch (Exception e) {
				System.out.println("ERRO AO EXCLUIR PROFESSOR");
				error = "Erro ao excluir professor";
			}
			
			ArrayList<Professor> lista = (ArrayList<Professor>)session.getAttribute("lstProfessor");
			lista.remove(busca(professor, lista));
			session.setAttribute("lstProfessor", lista);
			view = request.getRequestDispatcher("ListarProfessores.jsp");		
			
		} else if (pAcao.equals("Alterar")) {
			ps.update(professor);
			ArrayList<Professor> lista = (ArrayList<Professor>)session.getAttribute("lstProfessor");
			int pos = busca(professor, lista);
			lista.remove(pos);
			lista.add(pos, professor);
			session.setAttribute("lstProfessor", lista);
			request.setAttribute("professor", professor);
			view = request.getRequestDispatcher("VisualizarProfessor.jsp");	
			
		} else if (pAcao.equals("Visualizar")) {
			professor = ps.load(professor.getId());
			request.setAttribute("professor", professor);
			view = request.getRequestDispatcher("VisualizarProfessor.jsp");	
			
		} else if (pAcao.equals("Editar")) {
			professor = ps.load(professor.getId());
			request.setAttribute("professor", professor);
			view = request.getRequestDispatcher("AlterarProfessor.jsp");		
		}
		
		request.setAttribute("serverError", error);
		
		view.forward(request, response);

	}
		
		
	public int busca(Professor professor, ArrayList<Professor> lista) {
		Professor p;
		for(int i = 0; i < lista.size(); i++){
			p = lista.get(i);
			if(p.getId() == professor.getId()){
				return i;
			}
		}
		return -1;
	}	
		
		
		
		
		
		

	

}