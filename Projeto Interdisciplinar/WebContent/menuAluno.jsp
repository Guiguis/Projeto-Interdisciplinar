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
				aria-expanded="false" aria-label="Alterna navegação">
					<span class="navbar-toggler-icon"></span>
				</button>

				
				<!-- REDIRECIONA O USUARIO PARA O LOGIN SE ELE NÃO ESTIVER LOGADO -->
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
								Início
							</a>
						</li>
						<li class="nav-item dropdown">
						    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Vida Acadêmica</a>
						    <div class="dropdown-menu">
						      <a class="dropdown-item" href="#">Notas/Faltas</a>
						      <a class="dropdown-item" href="#">Horários</a>
						      <a class="dropdown-item" href="#">Materiais Didáticos</a>
						      <a class="dropdown-item" href="#">Calendário das Avaliações</a>
						      <a class="dropdown-item" href="#">Plano Curricular</a>
						      <a class="dropdown-item" href="#">Atividades Curriculares</a>
						      <a class="dropdown-item" href="#">Fale com o Professor</a>
						      <a class="dropdown-item" href="#">Identidade Acadêmica</a>
						      
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
						      <a class="dropdown-item" href="#">Renovação On-line de Livros</a>
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
						    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Serviços</a>
						    <div class="dropdown-menu">
						      <a class="dropdown-item" href="#">Solicitações On-line</a>
						      <a class="dropdown-item" href="#">Atendimento</a>
						      <a class="dropdown-item" href="#">Milhas do Conhecimento</a>
						      <a class="dropdown-item" href="#">Impressão online</a>
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

