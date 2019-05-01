<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="Header.jsp"/>

<!DOCTYPE html>
<html>
<head>
<!-- Meta Tags -->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<title>Cadastro de Professor</title>

<!-- Bootstrap -->
<link href="assets/style/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/style/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet" />

<!-- MAIN -->
<link href="assets/style/utils.css" rel="stylesheet" />
</head>
<body>


<div class="container header-margin">
	<div class="row">
		<div class="col-lg-12">
		
		<!-- Título -->
			<div class="col-md-4 offset-md-4">
				<h1 class="">Criar Banca</h1>	
			</div>
			
			<form action="EnviarEmail" method="post" class="form-horizontal">
			
			
				<select name="professor1">
					<c:forEach var="professor" items="${listaProfessor }">
						<option value="${professor.id}">${professor.nome}</option>  
					</c:forEach>
				</select>
				
				<select name="professor2">
					<c:forEach var="professor" items="${listaProfessor }">
						<option value="${professor.id}">${professor.nome}</option>  
					</c:forEach>
				</select>
				
				<select name="professor3">
					<c:forEach var="professor" items="${listaProfessor }">
						<option value="${professor.id}">${professor.nome}</option>  
					</c:forEach>
				</select>
				
				<select name="grupoId">
					<c:forEach var="grupo" items="${listaGrupo }">
						<option value="${grupo.id}">${grupo.numero}</option>  
					</c:forEach>
				</select>				
				
				
				<div class="form-group col-md-4">
					<h5>Insira a data em que a banca acontecerá</h5>
					<input id="inputData" type="text" class="form-control" name="data"  placeholder="dd/mm/ano" />
				</div>
				
				<div class="form-group col-md-4">
					<h5>Insira a sala em que a banca acontecerá</h5>
					<input id="inputData" type="text" class="form-control" name="sala"/>
				</div>
			
			
				 <button type="submit" class="btn btn-primary" name="acao" value="Alterar">Criar</button>
			</form>
		</div>
	</div>
</div>			
			


</body>
</html>