<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
	<title>Visulizar: Professor</title>
</head>
<body>

<!-- REDIRECIONA O USUARIO PARA O LOGIN SE ELE NÃO ESTIVER LOGADO -->
<c:if test="${ usuario == null}">
	<c:redirect url = "Login.jsp"/> 
</c:if>

<!-- REDIRECIONA O USUARIO PARA O INDEX SE ELE NÃO FOR ADMINSTRADOR -->
<c:if test="${usuario.administrador != 1}">
	<c:redirect url = "index.jsp"/> 
</c:if> 


<!-- Barra superior com os menus de navegação -->
	<c:import url ="Header.jsp"/>	
	
	<div class="container header-margin">
		<div class="row justify-content-md-center">
			<div class="col-lg-12  mt-30">
				<div class="card">
					<div class="card-header title-name">
					Professor: ${professor.nome}
					</div>
					<div class="card-body">
						<h5 class="card-title text-center font-weight-bold mb-5">Detalhes do Professor</h5>
						<div class="form-row">
							<div class="form-group col col-md-6">
								<label class="font-weight-bold" for="inputName">E-mail: </label>
								<label >${professor.email}</label>
							</div>
							<div class="form-group col-md-4">
								<label class="font-weight-bold" for="inputName">Senha: </label>
								<label >${professor.senha}</label>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label class="font-weight-bold" for="inputName">Matricula: </label>
								<label >${professor.matricula}</label>
							</div>
							<div class="form-group col-md-4">
								<label class="font-weight-bold" for="inputName">Administrador: </label>
								<c:if test="${professor.administrador == 1 }">
									Sim
								</c:if>
								<c:if test="${professor.administrador == 0 }">
									Não
								</c:if>
							</div>
						</div>
						<a class="btn btn-primary btn-xs" href="ListarProfessores.jsp">Voltar</a>
					</div>
				</div>
			</div>
		</div>
	</div>			

	<script src="assets/scripts/jquery.min.js"></script>
	<script src="assets/scripts/bootstrap/bootstrap.min.js"></script>
</body>
</html>