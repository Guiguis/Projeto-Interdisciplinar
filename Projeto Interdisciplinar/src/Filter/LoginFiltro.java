package Filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;

/**
 * Servlet Filter implementation class Filtro
 */
@WebFilter("/*")
public class LoginFiltro implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFiltro() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Usuario logado = (Usuario) session.getAttribute("usuario");
		String path = req.getContextPath();
		String uri = req.getRequestURI();
		String comando = request.getParameter("comand");
		
		if(path.endsWith(".css")){
			chain.doFilter(request,response);
		    return;
		}

		
		if (logado == null && testar(request, response, chain) == true && comando == null) {
			((HttpServletResponse) response).sendRedirect(path + "/Login.jsp");
		} 
		else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	public boolean testar(ServletRequest request, ServletResponse response, FilterChain chain) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Usuario logado = (Usuario) session.getAttribute("usuario");
		String path = req.getContextPath();
		String uri = req.getRequestURI();
		String comando = request.getParameter("comand");
		if(uri.equals(path +"/Login.jsp") ||uri.equals(path +"/NovaSenha.jsp") || uri.equals(path +"/Cadastro.jsp") 
				|| uri.equals(path +"/ConfirmarToken.jsp") || uri.equals(path +"/CadastrarController")
				||uri.equals(path +"/RecuperarSenhaControler")) {
			return false;
		}
		else return true;
	}
}
