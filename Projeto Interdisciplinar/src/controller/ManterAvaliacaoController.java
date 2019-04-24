package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
	
	public static Date formataData(String pData) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date dataUtil;
		Date data = new Date(System.currentTimeMillis());
		try {
			dataUtil = formato.parse(pData);
		} catch (ParseException e) {
			e.printStackTrace();
			dataUtil = data;
		}
		java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
		return dataSql;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pNotaTodos = request.getParameter("notaTodos");
		String pComentariosTodos = request.getParameter("comentariosTodos");
		String pData = request.getParameter("data");
		
		//Verifica se o campo "todos" foi preenchido, se nao foi ele adiciona os 3 alunos manualmente
		if(pNotaTodos.equals("")) {
			ArrayList<Avaliacao>lstAvaliacao = new ArrayList<Avaliacao>();
			
			//pegar as informacoes do formulario para criar o objeto, logo em seguida joga o mesmo para dentro de uma lista
			for(int i = 0; i < 3; i++) {
				int posicao = i+1;
				String pNota = request.getParameter("nota"+posicao);
				String pComentarios = request.getParameter("comentarios"+posicao);
				Avaliacao avaliacao = new Avaliacao();
				avaliacao.setNota(Double.parseDouble(pNota));
				avaliacao.setComentarios(pComentarios);	
				avaliacao.setDataAvaliacao(formataData(pData));
				int turma = i+1;
				avaliacao.setTurmaAluno(turma);
				lstAvaliacao.add(avaliacao);
			}
			
			// instanciar o service e cria os objetos(avaliacao) no banco
			AvaliacaoService as = new AvaliacaoService();
			as.createAvaliacao(lstAvaliacao);
		
			ArrayList<Avaliacao> lista = new ArrayList<>();
			//carrega os objetos para mostrar na tela
			for(int i = 0; i < lstAvaliacao.size(); i++) {
				Avaliacao avaliacao = lstAvaliacao.get(i);
				lstAvaliacao.set(i, as.load(avaliacao.getId()));
				lista.add(avaliacao);
			}
			
			// enviar para o jsp
			request.setAttribute("lstAvaliacao", lista);
			RequestDispatcher view = request.getRequestDispatcher("Avaliacao.jsp");
			view.forward(request, response);	
			
		}
		else{
			//adiciona os objetos a lista
			ArrayList<Avaliacao>lstAvaliacao = new ArrayList<Avaliacao>();
			
			for(int i = 0; i < 3; i++) {
				//pega as informacoes do formulario
				Avaliacao avaliacao = new Avaliacao();
				avaliacao.setNota(Double.parseDouble(pNotaTodos));
				avaliacao.setComentarios(pComentariosTodos);
				avaliacao.setDataAvaliacao(formataData(pData));
				int turma = i+1;
				avaliacao.setTurmaAluno(turma);
				lstAvaliacao.add(avaliacao);
			}

			// instanciar o service e cria os objetos(avaliacao) no banco
			AvaliacaoService as = new AvaliacaoService();
			as.createAvaliacao(lstAvaliacao);
			
			ArrayList<Avaliacao> lista = new ArrayList<>();
			//carrega os objetos para mostrar na tela
			for(int i = 0; i < lstAvaliacao.size(); i++) {
				Avaliacao avaliacao = lstAvaliacao.get(i);
				lstAvaliacao.set(i, as.load(avaliacao.getId()));
				lista.add(avaliacao);
			}
			
			// enviar para o jsp
			request.setAttribute("lstAvaliacao", lista);
			RequestDispatcher view = request.getRequestDispatcher("Avaliacao.jsp");
			view.forward(request, response);	
				
		}
		

	}

}