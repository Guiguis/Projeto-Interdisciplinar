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

			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item dropdown">
					<select class="form-control" id="exampleFormControlSelect1">
      					<c:forEach var="turma" items="${lista}">
							<option>${turma.ano}</option>
					    </c:forEach>
  				    </select></li>
				
			</ul>
			
			
		</div>
	</nav>


