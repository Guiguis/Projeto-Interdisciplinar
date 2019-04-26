<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="Menu.jsp"/>
<%@page import="model.Avaliacao" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Bootstrap -->
<link href="assets/style/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/style/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet" />

<!-- MAIN -->
<link href="assets/style/utils.css" rel="stylesheet" />
<title>Professor</title>
</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-lg-12  mt-30">
		<div class="col-md-4 offset-md-4">
			<h1 class="">Avaliar Alunos</h1>
		</div>
		
		<div class="form-group col-md-4">
			<label for="inputName">Data da avaliacao: </label>
			<% // <fmt:formatDate pattern="dd/MM/yyyy" value="${lstAvaliacao[0].dataAvaliacao.parameterName}"/>%>
			<label ></label>
		</div>
	
	
		

		

			<c:forEach var="avaliacao" items="${listaAvaliacao}">
				<div class="form-row">
					<div class="form-group">
						<label for="inputName">Nota: </label>
						<input type="text" class="form-control" name="nota&avaliacao.id" id="nota" 
									required maxlength="100" value="${avaliacao.nota}">
					</div>
					<div class="form-group">
						<label for="inputName">Comentarios: </label>
						<input type="text" class="form-control" name="comentario&avaliacao.id" id="comentario" 
									required maxlength="100" value="${avaliacao.comentarios}">
					</div>
				</div>
			</c:forEach>
		
		<a class="btn btn-warning btn-xs" href="ManterAvaliacaoController?acao=Atualizar&idGrupo=${entrega.grupo.id}&idEntrega=${entrega.id}">Editar</a>
		</div>
	</div>
</div>			

<script src="assets/scripts/jquery.min.js"></script>
<script src="assets/scripts/bootstrap/bootstrap.min.js"></script>
</body>
</html>