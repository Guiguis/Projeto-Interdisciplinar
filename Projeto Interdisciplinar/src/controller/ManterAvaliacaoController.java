package controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Avaliacao;
import service.AvaliacaoService;

/**
 * Servlet implementation class ManterProfessorController
 */
@WebServlet("/ManterAvaliacaoController")
public class ManterAvaliacaoController extends HttpServlet {
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
		String pNota = request.getParameter("notaTodos");
		String pDescricao = request.getParameter("descricaoTodos");
		
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setNota(Double.parseDouble(pNota));
		avaliacao.setComentarios(pDescricao);		

		// instanciar o service
		AvaliacaoService as = new AvaliacaoService();
		as.createAvaliacao(avaliacao);
		avaliacao = as.loadAvaliacao(avaliacao.getId());

		// enviar para o jsp
		request.setAttribute("avaliacao", avaliacao);

		RequestDispatcher view = request.getRequestDispatcher("Avaliacao.jsp");
		view.forward(request, response);

	}

}