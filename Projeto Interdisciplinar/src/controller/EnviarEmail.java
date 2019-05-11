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

import email.Email;
import model.Banca;
import model.Grupo;
import model.Professor;
import model.ProfessorBanca;
import service.BancaService;
import service.GrupoService;
import service.ProfessorBancaService;
import service.ProfessorService;

/**
 * Servlet implementation class EnviarEmail
 */
@WebServlet("/EnviarEmail")
public class EnviarEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnviarEmail() {
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
		
		//carrega os 3 professores escolhidos para a banca
		int idProfessor1 = Integer.parseInt(request.getParameter("professor1"));
		int idProfessor2 = Integer.parseInt(request.getParameter("professor2"));
		int idProfessor3 = Integer.parseInt(request.getParameter("professor3"));
		int idGrupo = Integer.parseInt(request.getParameter("grupoId"));
		String data = request.getParameter("data");
		String sala = request.getParameter("sala");
		
		GrupoService gs = new GrupoService();
		BancaService bs = new BancaService();
		ProfessorService ps = new ProfessorService();
		ProfessorBancaService pbs = new ProfessorBancaService();	
		
		//Cria uma banca
		System.out.println("Id do grupo: " + idGrupo);
		Grupo grupo = gs.load(idGrupo);
		Banca banca = new Banca(grupo, formataData(data), sala);
		bs.createBanca(banca);
		
		//Carrega os professores escolhidos
		Professor professor1 = ps.load(idProfessor1);
		Professor professor2 = ps.load(idProfessor2);
		Professor professor3 = ps.load(idProfessor3);
		
		ArrayList<Professor> lista = new ArrayList<>();
		lista.add(professor1);
		lista.add(professor2);
		lista.add(professor3);
		
		//Cria os objetos professoresBanca
		ProfessorBanca pb1 = new ProfessorBanca(professor1, banca);
		ProfessorBanca pb2 = new ProfessorBanca(professor2, banca);
		ProfessorBanca pb3 = new ProfessorBanca(professor3, banca);
		
		//Cria no banco os professoresBanca
		ArrayList<ProfessorBanca> listaProfessoresBanca = new ArrayList<>();
		listaProfessoresBanca.add(pb1);
		listaProfessoresBanca.add(pb2);
		listaProfessoresBanca.add(pb3);
		pbs.createProfessorBanca(listaProfessoresBanca);
		
		//Envia o email para os professores cadastrados na banca
		
		for(int i = 0; i < lista.size(); i++) {
			HttpSession session = request.getSession();	
			Professor professor = (Professor) session.getAttribute("usuario");
			
			String assunto = "Você foi designado para uma banca no dia " + data;
			String emailDestinatario = lista.get(i).getEmail();
			String mensagem = "Professor " + lista.get(i).getNome() + ", você foi designado para participar de uma banca no dia " 
								+ data + " e na sala " + sala + "<br> caso não consiga ir no dia informar entre em contato no email: " 
								+ professor.getEmail();
			Email email = new Email(emailDestinatario, assunto, mensagem);
			
			email.enviarEmail(email);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
	}
	
	//Formata a data
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
