<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="Menu.jsp"/>

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


<div class="container">
	<div class="row">
		<div class="col-lg-12  mt-30">

			<!-- Título -->
			<h1>Cadastro Professor</h1>

			<form action="ManterProfessorController" method="post" class="form-horizontal">

				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputName">Nome</label>
						<input id="inputName" type="text"  
							   class="form-control" name="nome" />
					</div>
					<div class="form-group col-md-4">
						<label for="inputMatricula">Matrícula</label> 
						<input id="inputMatricula" type="text" 
						 	   class="form-control" name="matricula"
						 	   placeholder="Ex: 8173511">
					</div>
				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputEmail4">E-mail</label>
						<input id="inputEmail4" type="email"  
							   class="form-control" name="email"
							   placeholder="Ex: prof.usjt.com.br"/>
					</div>
					<div class="form-group col-md-4">
						<label for="inputPassword4">Senha</label> <input
							id="inputPassword4" type="password" 
							class="form-control" name="senha">
					</div>
				</div>

				<div class="form-group">
					<div class="custom-control custom-checkbox">
						<input id="customControlAutosizing" type="checkbox" 
							   name="administrador" class="custom-control-input"  /> 
							<label class="custom-control-label" 
								   for="customControlAutosizing">
								Administrador?
							</label>
					</div>
				</div>
				<button type="submit" class="btn btn-primary" name="acao" value="Criar">Enviar</button>
				<a href="index.jsp" class="btn btn-default">Cancelar</a>
			</form>
		</div>
	</div>
</div>




<script src="assets/scripts/jquery.min.js"></script>
<script src="assets/scripts/bootstrap/bootstrap.min.js"></script>
</body>
</html>
