package controller;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.Criptografia;
import email.Email;
import service.UsuarioService;

/**
 * Servlet implementation class RecuperarSenhaControler
 */
@WebServlet("/RecuperarSenhaControler")
public class RecuperarSenhaControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecuperarSenhaControler() {
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
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
																												//pegando informações necessarias da WEB
		String emailDoUsuario = request.getParameter("emailDoUsuario");
		String acao = request.getParameter("acao");
		String raWeb = request.getParameter("DocumentoWeb");
		String matriculaWeb = request.getParameter("DocumentoWeb");
		String tokenWeb = request.getParameter("TokenWeb");
		String novaSenha = request.getParameter("novaSenha");
		String token = null;
		RequestDispatcher view = null;
		HttpSession session = request.getSession();
		
		 																										// intancia service necessario para testes booleanos
		UsuarioService userService = new UsuarioService();
		
																												//verifica a ação chamada
		if(acao.equals("EnviarToken")) {
			
							System.out.println("linha 69");																					//confirma se o email esta cadastrado
				if(userService.verificarEmail(emailDoUsuario) == true) { 
					Criptografia crypt = new Criptografia();
					Email email = new Email();
					System.out.println("linha 73");
					//verifica se o email e de um aluno
					if(userService.BuscaRaOuMatricula(emailDoUsuario,"RA") != null) { 
						String baseToken =  userService.token(emailDoUsuario, "RA"); 							//Gerando o Tonken com o texto base sendo o RA+SenhaAtual
						try {
							token = crypt.encrypt(baseToken, "adminInternoSistema", Criptografia.ALGORITMO_DES);
							
																												//Manda um email com o token de uso unico
							email.setAssunto("Recuperação de Senha!");
							email.setMsg("Atenção!\n"
									+ " Segue seu token de uso unico: " + token
									+ "\n Favor inserir no sistema para alteração se senha"
									+ "\n Atenciosamente Suporte USJT -  Sistema de Avaliação");
							email.setEmailDestinatario(emailDoUsuario);
							email.enviarEmail(email);
							session.setAttribute("token", token);
							session.setAttribute("email", emailDoUsuario);
							view = request.getRequestDispatcher("ConfirmarToken.jsp");
							view.forward(request, response);
							
						} catch (InvalidKeyException | BadPaddingException | NoSuchPaddingException | IllegalBlockSizeException
								| NoSuchAlgorithmException | InvalidAlgorithmParameterException | InvalidKeySpecException e) {
							e.printStackTrace();
						}
						
					}
					else if (userService.BuscaRaOuMatricula(emailDoUsuario, "matricula") != null) {				//Verifica se o email é de professor
						System.out.println("linha 96");
						String baseToken =  userService.token(emailDoUsuario, "matricula");						//Gera um Token com base o texto de matricula+senhaAtual
						try {
							token = crypt.encrypt(baseToken, "adminInternoSistema", Criptografia.ALGORITMO_DES);	
							
																												//manda um email com o token de uso unico
							email.setAssunto("Recuperação de Senha!");
							email.setMsg("Caro Professor\n"
									+ " Segue seu token de uso unico: " + token
									+ " \n Favor inserir no sistema para alteração se senha"
									+ "\n Atenciosamente Suporte USJT -  Sistema de Avaliação");
							email.setEmailDestinatario(emailDoUsuario);
							email.enviarEmail(email);
							session.setAttribute("token", token);
							session.setAttribute("email", emailDoUsuario);
							view = request.getRequestDispatcher("ConfirmarToken.jsp");
							view.forward(request, response);
						} catch (InvalidKeyException | BadPaddingException | NoSuchPaddingException | IllegalBlockSizeException
								| NoSuchAlgorithmException | InvalidAlgorithmParameterException | InvalidKeySpecException e) {
							e.printStackTrace();
						}
					}			
				}
			}
		
		//verifica a ação - metodo 1
		if(acao.equals("alterarSenha 1")) {
			if(userService.confirmarToken(emailDoUsuario, "RA", raWeb) != null) {
				if(tokenWeb.equals(token)) { 																	//verifica se o Token inserido bate com o token gerado
					userService.alterarSenha(novaSenha, emailDoUsuario); 										//se estiver OK, altera a senha
					session.setAttribute("Sucesso", "Senha alterada!");
					view = request.getRequestDispatcher("Login.jsp");											//manda o usuario de volta a tela de login
					view.forward(request, response);
				}
				else session.setAttribute("erro", "Erro Token Não Confere");
			}
			else if (userService.confirmarToken(emailDoUsuario, "matricula", matriculaWeb)!= null) {
				if(tokenWeb.equals(token)) {																	//verifica se o Token inserido bate com o token gerado
					userService.alterarSenha(novaSenha, emailDoUsuario);										//se estiver OK, altera a senha
					session.setAttribute("Sucesso", "Senha alterada!");
					view = request.getRequestDispatcher("Login.jsp");											//manda o usuario de volta a tela de login
					view.forward(request, response);
				}
				else session.setAttribute("erro", "Erro Token Não Confere");									//Avisa o usuario que o token não esta correto!
									
				}
			}
			
		
				//verifica a ação - metodo 2
			if(acao.equals("alterarSenha2")) {
				System.out.println("linha 150");
				emailDoUsuario = (String) session.getAttribute("email");
				System.out.println(emailDoUsuario);
				String tokenConfirmacao = (String) session.getAttribute("token") ;
				System.out.println(tokenConfirmacao+"   linha 155");
				if(userService.BuscaRaOuMatricula(emailDoUsuario,"ra") != null) {
					System.out.println("linha 150");
					//String tokenConfirmacao = (String) session.getAttribute("token") ;
					System.out.println(tokenConfirmacao+"   linha 158");
					if(tokenWeb.equals(tokenConfirmacao)){
						userService.alterarSenha(novaSenha, emailDoUsuario);
						session.setAttribute("Sucesso", "Senha alterada!");
						view = request.getRequestDispatcher("Login.jsp");											//manda o usuario de volta a tela de login
						view.forward(request, response);
					}
					else session.setAttribute("ERRO", "Token Invalido");
					System.out.println("linha 159");
				}
				else if (userService.BuscaRaOuMatricula(emailDoUsuario, "matricula") != null) {
					System.out.println("linha 169");
					//String tokenConfirmacao = (String) session.getAttribute("token") ;
					System.out.println(tokenConfirmacao+"   linha 152");
					if(tokenWeb.equals(tokenConfirmacao)){
						System.out.println("linha 173");
						userService.alterarSenha(novaSenha, emailDoUsuario);
						session.setAttribute("Sucesso", "Senha alterada!");
						view = request.getRequestDispatcher("Login.jsp");											//manda o usuario de volta a tela de login
						view.forward(request, response);
					}
					else { session.setAttribute("ERRO", "Token Invalido");
					System.out.println("linha 180");
					}
				}
			}
	}
	
}
