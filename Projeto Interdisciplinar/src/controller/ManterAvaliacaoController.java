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
import javax.servlet.http.HttpSession;

import model.Aluno;
import model.Avaliacao;
import model.Professor;
import service.AlunoService;
import service.AvaliacaoService;
import service.EntregaService;

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
		String pIdGrupo = null;
		String pIdEntrega = null;
		String acao = request.getParameter("acao");
		
		if(request.getParameter("idGrupo") != null || !request.getParameter("idGrupo").equals("") ) {
			pIdGrupo = request.getParameter("idGrupo");
		}
		if(request.getParameter("idEntrega") != null || !request.getParameter("idEntrega").equals("")) {
			pIdEntrega = request.getParameter("idEntrega");
		}
		
		int idGrupo = Integer.parseInt(pIdGrupo);
		int idEntrega = Integer.parseInt(pIdEntrega);
	
		EntregaService es = new EntregaService();
		AlunoService AlunoService = new AlunoService();
		ArrayList<Aluno> listaAluno = AlunoService.grupoAlunos(idGrupo);
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession();
		
		
		
		if(acao.equals("Criar")) {
			// enviar para o jsp
			request.setAttribute("idGrupo", idGrupo);
			request.setAttribute("idEntrega", idEntrega);
			request.setAttribute("listaAluno", listaAluno);
			view = request.getRequestDispatcher("cadastroAvaliacao.jsp");
			view.forward(request, response);	
		}
		else if (acao.equals("Apagar")){
			AvaliacaoService as = new AvaliacaoService();
			as.deleteAvaliacao(idGrupo, listaAluno, idEntrega);
			ArrayList<Avaliacao> lista = (ArrayList<Avaliacao>)session.getAttribute("lista");
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("ListarEntregas.jsp");	
			
		}
		else if(acao.equals("Excluir")) {
			// enviar para o jsp
			AvaliacaoService as = new AvaliacaoService();
			ArrayList<Avaliacao> listaAvaliacao = as.load(idEntrega, idGrupo, listaAluno);
			request.setAttribute("listaAvaliacao", listaAvaliacao);
			request.setAttribute("listaAluno", listaAluno);
			view = request.getRequestDispatcher("ExcluirAvaliacao.jsp");
			view.forward(request, response);	
		}
		else if(acao.equals("Visualizar")) {
			// enviar para o jsp
			AvaliacaoService as = new AvaliacaoService();
			ArrayList<Avaliacao> listaAvaliacao = as.load(idEntrega, idGrupo, listaAluno);
			request.setAttribute("listaAvaliacao", listaAvaliacao);
			request.setAttribute("listaAluno", listaAluno);
			view = request.getRequestDispatcher("VisualizarAvaliacao.jsp");
			view.forward(request, response);	
		}
		else if(acao.equals("Editar")) {
			// enviar para o jsp
			AvaliacaoService as = new AvaliacaoService();
			ArrayList<Avaliacao> listaAvaliacao = as.load(idEntrega, idGrupo, listaAluno);
			request.setAttribute("listaAvaliacao", listaAvaliacao);
			request.setAttribute("listaAluno", listaAluno);
			view = request.getRequestDispatcher("AlterarAvaliacao.jsp");
			view.forward(request, response);
		}
		
		else if(acao.equals("Atualizar")) {
			// enviar para o jsp
			AvaliacaoService as = new AvaliacaoService();
			ArrayList<Avaliacao> listaAvaliacao = as.load(idEntrega, idGrupo, listaAluno);
			request.setAttribute("listaAvaliacao", listaAvaliacao);
			request.setAttribute("listaAluno", listaAluno);
			view = request.getRequestDispatcher("ListarEntregas.jsp");
			view.forward(request, response);
		}
		
		else if(acao.equals("Enviar")) {
		
			String pNotaTodos = request.getParameter("notaTodos");
			String pComentariosTodos = request.getParameter("comentariosTodos");
			String pData = request.getParameter("data");
			
			//Verifica se o campo "todos" foi preenchido, se nao foi ele adiciona os 3 alunos manualmente
			if(pNotaTodos.equals("")) {
				ArrayList<Avaliacao>lstAvaliacao = new ArrayList<Avaliacao>();
				
				//pegar as informacoes do formulario para criar o objeto, logo em seguida joga o mesmo para dentro de uma lista
				for(int i = 0; i < listaAluno.size(); i++) {
					String pNota = request.getParameter("nota"+listaAluno.get(i).getId());
					String pComentarios = request.getParameter("com"+listaAluno.get(i).getId());
					Avaliacao avaliacao = new Avaliacao();
					avaliacao.setEntrega(es.loadEntrega(idEntrega));
					avaliacao.setNota(Double.parseDouble(pNota));
					avaliacao.setComentarios(pComentarios);	
					avaliacao.setDataAvaliacao(formataData(pData));
					lstAvaliacao.add(avaliacao);
				}
				
				// instanciar o service e cria os objetos(avaliacao) no banco
				AvaliacaoService as = new AvaliacaoService();
				as.createAvaliacao(lstAvaliacao, idGrupo, listaAluno);
			
				ArrayList<Avaliacao> lista = new ArrayList<>();
				//carrega os objetos para mostrar na tela
				for(int i = 0; i < lstAvaliacao.size(); i++) {
					Avaliacao avaliacao = lstAvaliacao.get(i);
					lstAvaliacao.set(i, as.loadPorId(avaliacao.getId()));
					lista.add(avaliacao);
				}
				
				// enviar para o jsp
				request.setAttribute("listaAluno", listaAluno);
				request.setAttribute("listaAvaliacao", lista);
				view = request.getRequestDispatcher("VisualizarAvaliacao.jsp");
				view.forward(request, response);	
				
			}
			else{
				//adiciona os objetos a lista
				ArrayList<Avaliacao>lstAvaliacao = new ArrayList<Avaliacao>();
				
				for(int i = 0; i < listaAluno.size(); i++) {
					//pega as informacoes do formulario
					
					Avaliacao avaliacao = new Avaliacao();
					avaliacao.setEntrega(es.loadEntrega(idEntrega));
					avaliacao.setNota(Double.parseDouble(pNotaTodos));
					avaliacao.setComentarios(pComentariosTodos);
					avaliacao.setDataAvaliacao(formataData(pData));
					lstAvaliacao.add(avaliacao);
				}
	
				// instanciar o service e cria os objetos(avaliacao) no banco
				AvaliacaoService as = new AvaliacaoService();
				as.createAvaliacao(lstAvaliacao, idGrupo, listaAluno);
				
				ArrayList<Avaliacao> lista = new ArrayList<>();
				//carrega os objetos para mostrar na tela
				for(int i = 0; i < lstAvaliacao.size(); i++) {
					Avaliacao avaliacao = lstAvaliacao.get(i);
					lstAvaliacao.set(i, as.loadPorId(avaliacao.getId()));
					lista.add(avaliacao);
				}
				
				// enviar para o jsp
				request.setAttribute("listaAluno", listaAluno);
				request.setAttribute("listaAvaliacao", lista);
				view = request.getRequestDispatcher("VisualizarAvaliacao.jsp");
				view.forward(request, response);	
					
			}
		
		}
		

		
		
		
	}
	
	
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
	

}