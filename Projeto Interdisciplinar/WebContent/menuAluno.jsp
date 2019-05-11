<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.Turma" %>

<!-- MAIN -->
<link href="assets/style/main.css"   rel="stylesheet" />
<link href="assets/style/header.css" rel="stylesheet" />

<header class="header">
	<div class="container"> 
		<div class="row" >
			
			<nav class="navbar navbar-expand-lg navbar-dark">

				<!-- MOBILE -->
				<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
				aria-expanded="false" aria-label="Alterna navega��o">
					<span class="navbar-toggler-icon"></span>
				</button>

				
				<!-- REDIRECIONA O USUARIO PARA O LOGIN SE ELE N�O ESTIVER LOGADO -->
				<c:if test="${ aluno == null}">
					<c:redirect url = "Login.jsp"/> 
				</c:if>	
				
				<!-- MENU -->
				<div class="collapse navbar-collapse" id="navbarNavDropdown">

					<!-- LOGO -->
					<a class="navbar-brand" href="index.jsp">
						<img alt="" src="assets/images/logo-blue.png" >
					</a>

					<!-- LINKS -->
					<ul class="navbar-nav menu-flex">
						<li class="nav-item active">
							<a class="nav-link" href="Aluno.jsp">
								In�cio
							</a>
						</li>
						<li class="nav-item dropdown">
						    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Vida Acad�mica</a>
						    <div class="dropdown-menu">
						      <a class="dropdown-item" href="#">Notas/Faltas</a>
						      <a class="dropdown-item" href="#">Hor�rios</a>
						      <a class="dropdown-item" href="#">Materiais Did�ticos</a>
						      <a class="dropdown-item" href="#">Calend�rio das Avalia��es</a>
						      <a class="dropdown-item" href="#">Plano Curricular</a>
						      <a class="dropdown-item" href="#">Atividades Curriculares</a>
						      <a class="dropdown-item" href="#">Fale com o Professor</a>
						      <a class="dropdown-item" href="#">Identidade Acad�mica</a>
						      
						    </div>
						  </li>

						
						<li class="nav-item dropdown">
						    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
						    	Ulife
						    </a>
						    <div class="dropdown-menu">
						      <a class="dropdown-item" href="#">Salas de Aula Virtual</a>						        
						    </div>
						  </li>
						<li class="nav-item dropdown">
						    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Biblioteca</a>
						    <div class="dropdown-menu">
						      <a class="dropdown-item" href="#">Consulta ao Acervo da Biblioteca</a>
						      <a class="dropdown-item" href="#">Renova��o On-line de Livros</a>
						      <a class="dropdown-item" href="#">Minha Biblioteca</a>
						      <a class="dropdown-item" href="#">Biblioteca Virtual</a>						      
						    </div>
						  </li>
					
						<li class="nav-item dropdown">
						    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Financeiro</a>
						    <div class="dropdown-menu">
						      <a class="dropdown-item" href="#">Pagamento Online</a>
						      <a class="dropdown-item" href="#">Extrato Financeiro</a>
						      
						    </div>
						  </li>
						
						<li class="nav-item dropdown">
						    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Servi�os</a>
						    <div class="dropdown-menu">
						      <a class="dropdown-item" href="#">Solicita��es On-line</a>
						      <a class="dropdown-item" href="#">Atendimento</a>
						      <a class="dropdown-item" href="#">Milhas do Conhecimento</a>
						      <a class="dropdown-item" href="#">Impress�o online</a>
						      <a class="dropdown-item" href="#">Passe Escolar</a>					      
						    </div>
						  </li>
			
				</ul>
				
				<ul class="nav navbar-nav ml-auto">
			      <li class="nav-item dropdown">
			        	<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false" >${aluno.nome }</a>
			        	<div class="dropdown-menu">
					     	<a class="dropdown-item" href="SairController?acao=sair">Sair</a>		      
					    </div>
			      </li>
			     
			    </ul>
				</div>
				
			</nav>
		</div>
	</div>
</header>

