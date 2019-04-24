<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.Turma" %>

<nav class="navbar navbar-expand-lg navbar-light bg-primary">
		<a class="navbar-brand" href="#">Inicio</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Alterna navegação">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="index.jsp"></a></li>
				<li class="nav-item"><a class="nav-link active"
					href="cadastroProfessor.jsp">Cadastro de Professor</a></li>
				<li class="nav-item"><a class="nav-link active" href="cadastroAvaliacao.jsp">Avaliacao
						Semanal</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Gerar
						Declarção</a></li>
				<li class="nav-item active"><a class="nav-link" href="Grupo.jsp">Grupo
						</a></li>
				<li class="nav-item active"><a class="nav-link" href="ListarProfessor">Professores
						</a></li>

			</ul>	
			
			<ul class="navbar-nav ml-auto" >
				<select name="semestre" id="semestre">
					<c:forEach var="s" items="${listaSemestre }">
		          		<option value="${s.ano}">${s.ano}/${s.semestreLetivo}</option>
		          </c:forEach>
				</select>
			</ul>
			
		</div>
	</nav>


