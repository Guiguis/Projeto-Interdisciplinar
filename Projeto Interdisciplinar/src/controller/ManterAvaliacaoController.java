package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	/*
	public static Date conventer(String pData) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data;
		try {
			data = formato.parse(pData);
		} catch (ParseException e) {
			data = null;
		}
		return data;
		
	}
	 */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String pNotaTodos = request.getParameter("notaTodos");
		String pComentariosTodos = request.getParameter("comentariosTodos");
		//String pData = request.getParameter("data");
		String vazio = "";
		
		//Verifica se o campo todos foi preenchido
		//se nao foi ele adiciona os 3 alunos manualmente
		if(pNotaTodos.equals(vazio)) {
			Avaliacao [] lstAvaliacao = new Avaliacao[3];
			
			//pegar as informacoes do formulario para criar o objeto, logo em seguida joga o mesmo para dentro de uma lista
			for(int i = 0; i < 3; i++) {
				int posicao = i+1;
				String pNota = request.getParameter("nota"+posicao);
				String pComentarios = request.getParameter("comentarios"+posicao);
				Avaliacao avaliacao = new Avaliacao();
				avaliacao.setNota(Double.parseDouble(pNota));
				avaliacao.setComentarios(pComentarios);	
				lstAvaliacao[i] = avaliacao;
			}
			
			// instanciar o service e cria os objetos(avaliacao) no banco
			AvaliacaoService as = new AvaliacaoService();
			as.createAvaliacao(lstAvaliacao);
			
			//carrega os objetos para mostrar na tela
			for(int i = 0; i < lstAvaliacao.length; i++) {
				lstAvaliacao[i] = as.loadAvaliacao(lstAvaliacao[i].getId());
				// enviar para o jsp
				request.setAttribute("avaliacao", lstAvaliacao[i]);
			}

			RequestDispatcher view = request.getRequestDispatcher("Avaliacao.jsp");
			view.forward(request, response);
			
		}
		//senao ele define a mesma nota para todos
		else{
			//pega as informacoes do formulario
			Avaliacao avaliacao = new Avaliacao();
			avaliacao.setNota(Double.parseDouble(pNotaTodos));
			avaliacao.setComentarios(pComentariosTodos);	
			//avaliacao.setDataAvaliacao(conventer(pData));
			
			//adiciona os objetos a lista
			Avaliacao [] lstAvaliacao = new Avaliacao[3];
			for(int i = 0; i < lstAvaliacao.length; i++) {
				lstAvaliacao[i] = avaliacao;
			}

			// instanciar o service e cria os objetos(avaliacao) no banco
			AvaliacaoService as = new AvaliacaoService();
			as.createAvaliacao(lstAvaliacao);
			
			//carrega os objetos para mostrar na tela
			for(int i = 0; i < lstAvaliacao.length; i++) {
				lstAvaliacao[i] = as.loadAvaliacao(avaliacao.getId());
				// enviar para o jsp
				request.setAttribute("avaliacao", avaliacao);
			}

			RequestDispatcher view = request.getRequestDispatcher("Avaliacao.jsp");
			view.forward(request, response);	
				
		}
		

	}

}