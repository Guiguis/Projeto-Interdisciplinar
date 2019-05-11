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
import model.Entrega;
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
		String pIdGrupo = request.getParameter("idGrupo");
		String pIdEntrega = request.getParameter("idEntrega");
		String acao = request.getParameter("acao");
		int idGrupo = -1;
		int idEntrega  = -1;
	
		try {
			idGrupo = Integer.parseInt(pIdGrupo);
			idEntrega = Integer.parseInt(pIdEntrega);
		} catch(Exception e) {
			System.out.println("Erro ao conventer o id de Grupo e/ou o id de Entrega... Erro: " + e);
		}
		
	
		EntregaService es = new EntregaService();
		AlunoService AlunoService = new AlunoService();
		ArrayList<Aluno> listaAluno = AlunoService.grupoAlunos(idGrupo);
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession();
		
		
		if(acao.equals("Criar")) {
			// enviar para o jsp\
			request.setAttribute("idGrupo", idGrupo);
			request.setAttribute("idEntrega", idEntrega);
			request.setAttribute("listaAluno", listaAluno);
			view = request.getRequestDispatcher("cadastroAvaliacao.jsp");
		}
		else if (acao.equals("Apagar")){		
			
			int idEn = Integer.parseInt(request.getParameter("idEntrega"));
			
			//Exclui a avaliacao de lista de avaliados
			
			ArrayList<Entrega> listaNaoAvaliados = (ArrayList<Entrega>) session.getAttribute("listaNaoAvaliados");
			ArrayList<Entrega> listaAvaliados = (ArrayList<Entrega>) session.getAttribute("listaAvaliados");
			 
			for(int i = 0; i < listaAvaliados.size(); i++) {
				//verifica se a o id da entrega excluido e igual a entrega da lista
				if(listaAvaliados.get(i).getId() == idEn) {
					System.out.println("Achou a entrega na lista");
					Entrega entrega = listaAvaliados.get(i);
					listaAvaliados.remove(i);
					listaNaoAvaliados.add(entrega);
				}
				else {
					System.out.println("NAO Achou a entrega na lista");
				}
			}
			
			//Exclui a avaliacao no banco
			AvaliacaoService as = new AvaliacaoService();
			as.deleteAvaliacao(idEn);
			ArrayList<Avaliacao> listaAvaliacao = new ArrayList<Avaliacao>();
			listaAvaliacao = as.load(idEn);
			
			request.setAttribute("listaNaoAvaliados", listaNaoAvaliados);
			request.setAttribute("listaAvaliados", listaAvaliados);
			
			view = request.getRequestDispatcher("ListarEntregas.jsp");	
			
		}
		else if(acao.equals("Visualizar")) {
			// enviar para o jsp
			request.setAttribute("idGrupo", idGrupo);
			AvaliacaoService as = new AvaliacaoService();
			ArrayList<Avaliacao> listaAvaliacao = as.load(idEntrega);
			request.setAttribute("listaAvaliacao", listaAvaliacao);
			view = request.getRequestDispatcher("VisualizarAvaliacao.jsp");
		}
		else if(acao.equals("Editar")) {
			// enviar para o jsp
			request.setAttribute("idGrupo", idGrupo);
			request.setAttribute("idEntrega", idEntrega);
			AvaliacaoService as = new AvaliacaoService();
			ArrayList<Avaliacao> listaAvaliacao = as.load(idEntrega);
			request.setAttribute("idEntrega", idEntrega);
			request.setAttribute("listaAvaliacao", listaAvaliacao);
			view = request.getRequestDispatcher("AlterarAvaliacao.jsp");
		}
		
		else if(acao.equals("Atualizar")) {
			// enviar para o jsp
			int idEn = Integer.parseInt(request.getParameter("entregaId"));
			AvaliacaoService as = new AvaliacaoService();
			ArrayList<Avaliacao> listaAvaliacao = new ArrayList<Avaliacao>();
			listaAvaliacao = as.load(idEn);
			
			System.out.println("Atualizar request idEntrega: " + idEn);

			
			for(int i = 0; i < listaAvaliacao.size(); i++) {
				Avaliacao avaliacao = null;
				int idAvaliacao = listaAvaliacao.get(i).getId();
				
				//pegando dados do formulario
				int id = Integer.parseInt(request.getParameter("avaliacaoId"+idAvaliacao));
				Double pNota = Double.parseDouble(request.getParameter("nota"+idAvaliacao));
				String pComentarios = request.getParameter("comentarios"+idAvaliacao);
				
				//criando uma nova avaliacao e colocando na lista
				avaliacao = new Avaliacao(id, pNota, pComentarios);
				listaAvaliacao.set(i, avaliacao);
				
				System.out.println("Id = " + id + "  Nota = " + pNota + " Comentarios = " + pComentarios);
			}
			
			as.updateAvaliacao(listaAvaliacao);
			request.setAttribute("idGrupo", idGrupo);
			request.setAttribute("listaAvaliacao", listaAvaliacao);
			view = request.getRequestDispatcher("ListarEntregas.jsp");
		}
		
		else if(acao.equals("Enviar")) {
			
			String pNotaTodos = request.getParameter("notaTodos");
			String pComentariosTodos = request.getParameter("comentariosTodos");
			String pData = request.getParameter("data");
			
			//Verifica se o campo "todos" foi preenchido
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
					Avaliacao avaliacao = new Avaliacao();
					avaliacao = as.loadPorId(lstAvaliacao.get(i).getId());
					lista.add(avaliacao);
				}
				
				// enviar para o jsp
				request.setAttribute("listaAvaliacao", lista);
				System.out.print("");
				view = request.getRequestDispatcher("VisualizarAvaliacao.jsp");
				
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
					Avaliacao avaliacao = new Avaliacao();
					avaliacao = as.loadPorId(lstAvaliacao.get(i).getId());
					lista.add(avaliacao);
				}
				
				// enviar para o jsp
				request.setAttribute("listaAvaliacao", lista);
				request.setAttribute("idGrupo", idGrupo);
				view = request.getRequestDispatcher("VisualizarAvaliacao.jsp");
					
			}
		
		}
		view.forward(request, response);
		
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
	
	
	public ArrayList<Integer> busca(int entregaId, ArrayList<Avaliacao> lista) {
		ArrayList<Integer> avaliacaoIndex = new ArrayList<>();
		Avaliacao a;
		for(int i = 0; i < lista.size(); i++){
			a = lista.get(i);
			if(a.getEntrega().getId() == entregaId){
				avaliacaoIndex.add(i);
			}
		}
		return avaliacaoIndex;
	}	
	
	

}