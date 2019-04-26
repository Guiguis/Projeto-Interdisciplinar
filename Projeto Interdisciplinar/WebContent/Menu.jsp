<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.Turma" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-info">
	<a class="navbar-brand" href="index.jsp">Inicio</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
		aria-expanded="false" aria-label="Alterna navegação">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNavDropdown">
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link" href="index.jsp"></a></li>
			<li class="nav-item active"><a class="nav-link active" href="ListarProfessor?acao=reiniciar">CRUD professor</a></li>
			<li class="nav-item active"><a class="nav-link active" href="cadastroAvaliacao.jsp">Avaliacao Semanal</a></li>
			<li class="nav-item active"><a class="nav-link" href="#">Gerar Declarção</a></li>
			<li class="nav-item active"><a class="nav-link"href="ListarGrupo?acao=reiniciar">Grupo </a></li>
			<li class="nav-item">
				<form method="post" action="Menu">
					<select class="custom-select">
						<option selected>Semestre / Ano</option>
						<c:forEach var="semestre" items="${lstTurma}">
							<option>${semestre}</option>
						</c:forEach>
					</select>
				</form>
      		</li>
		</ul>
	</div>
	
</nav>
<br />


