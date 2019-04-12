<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Professor" %>
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

<!-- Barra superior com os menus de navegação -->
	<nav class="navbar navbar-expand-lg navbar-light bg-primary">
		<a class="navbar-brand" href="#">Inicio</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Alterna navegação">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="#"></a></li>
				<li class="nav-item"><a class="nav-link active"
					href="professor.html">Cadastro de Professor</a></li>
				<li class="nav-item"><a class="nav-link active" href="#">Avaliacao
						Semanal</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Gerar
						Declarção</a></li>

			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle " href="#"
					id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> Semestre </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="#">2018/01</a> <a
							class="dropdown-item" href="#">2018/02</a> <a
							class="dropdown-item" href="#">2019/01</a>
					</div></li>
			</ul>

		</div>
	</nav>
	
	<%Professor professor = (Professor)request.getAttribute("professor"); %>
	<div class="container">
		<div class="row">
			<div class="col-lg-12  mt-30">
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputName">Id: </label>
						<label ><%=professor.getId() %></label>
					</div>
					<div class="form-group col-md-4">
						<label for="inputName">Nome: </label>
						<label ><%=professor.getNome() %>	</label>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputName">E-mail: </label>
						<label ><%=professor.getEmail() %></label>
					</div>
					<div class="form-group col-md-4">
						<label for="inputName">Senha: </label>
						<label ><%=professor.getSenha() %></label>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputName">Matricula: </label>
						<label ><%=professor.getMatricula() %></label>
					</div>
					<div class="form-group col-md-4">
						<label for="inputName">Administrador: </label>
						<label ><%=professor.getAdministrador() %></label>
					</div>
				</div>
			</div>
		</div>
	</div>			

	<script src="assets/scripts/jquery.min.js"></script>
	<script src="assets/scripts/bootstrap/bootstrap.min.js"></script>
</body>
</html>