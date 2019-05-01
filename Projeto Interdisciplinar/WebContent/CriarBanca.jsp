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
			
			<!-- Seleciona os professores -->
				
					<div class="col-md-3">
						<label>Selecione o 1º Professor(a)</label>
						<select name="professor1">
							<c:forEach var="professor" items="${listaProfessor }">
								<option value="${professor.id}">${professor.nome}</option>  
							</c:forEach>
						</select>
					</div>
					
					<div class="col-md-3">
						<label>Selecione o 2º Professor(a)</label>
						<select name="professor2">
							<c:forEach var="professor" items="${listaProfessor }">
								<option value="${professor.id}">${professor.nome}</option>  
							</c:forEach>
						</select>
					</div>
					
					<div class="col-md-3">
						<label>Selecione o 3º Professor(a)</label>
						<select name="professor3">
							<c:forEach var="professor" items="${listaProfessor }">
								<option value="${professor.id}">${professor.nome}</option>  
							</c:forEach>
						</select>
					</div>
				
					<br>
					<!-- Seleciona os grupos -->
					<div class="col-md3">
						<label>Selecione o Grupo</label>
						<select name="grupoId">
							<c:forEach var="grupo" items="${listaGrupo }">
								<option value="${grupo.id}">${grupo.nome}</option>  
							</c:forEach>
						</select>				
					</div>
					
					<!-- Insere a Data -->
					<div class="form-group col-md-4">
						<h6>Insira a data</h6>
						<input id="inputData" type="text" class="form-control" name="data"  placeholder="dd/mm/ano" />
					</div>
					
					<!-- Insere a sala -->
					<div class="form-group col-md-4">
						<h6>Insira a sala</h6>
						<input id="inputData" type="text" class="form-control" name="sala"/>
					</div>
				
			
				 <button type="submit" class="btn btn-primary" name="acao" value="Alterar">Criar</button>
			</form>
		</div>
	</div>
</div>			
			


</body>
</html>