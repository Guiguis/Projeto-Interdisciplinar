<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html class="h-100">
<head>

	<meta charset="UTF-8">
	<meta name="viewport"
		  content="width=device-width, initial-scale=1, maximum-scale=1">
	
	<title>Login</title>
	
	<!-- Bootstrap -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<!-- Fonts / Icons -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" 
		  integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" 
		  crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

	<!-- MAIN -->
	<link href="assets/style/utils.css" rel="stylesheet" />
</head>
<body class="h-100">


	<div class="container h-100">
		<div class="row justify-content-center h-100">
			<div class="card bg-light w-50 m-auto">
				<div class="card-header text-center">
					<img src="assets/images/logo-blue.png" alt="São Judas Tadeu"/>
				</div>
				<div class="card-body">
					<form action="ManterLogin" method="post"
						  class="needs-validation" novalidate>

						<div class="form-row mb-4">
							
							<div class="col-md-12 mb-3">
								<div class="alert-danger text-center">
									<span class="text-danger">
										${erro}
									</span>
								</div>
							</div>
							
							<input type="hidden" name="comand" value="ManterLogin">

							<div class="col-md-12 mb-3">
								
								<label for="usuario">E-mail</label> 
								<div class="input-group">
									<div class="input-group-prepend">
										<div class="input-group-text">
											<i class="fas fa-envelope"></i>
										</div>
									</div> 
									<input id="usuario" name="user" 
										   placeholder="Digite seu e-mail:" type="email" 
										   class="form-control" required="true">
									<div class="valid-feedback">
          								Excelente email :)! 
       								</div>
									<div class="invalid-feedback">
          								Por favor, digite seu e-mail.
       								</div>
								</div>
							</div>

							<div class="col-md-12 mb-3">
								<label for="senha">Senha</label> 
								<div class="input-group">
									<div class="input-group-prepend">
										<div class="input-group-text">
											<i class="fas fa-lock"></i>
										</div>
									</div> 
									<input id="senha" name="senha" type="password"
										   class="form-control" placeholder="Digite sua senha: " 
										   required="true" />
									<div class="valid-feedback">
          								Excelente senha :)! 
       								</div>
									<div class="invalid-feedback">
          								Por favor, digite sua senha.
       								</div>
								</div>
							</div>
						</div>
					
						<button id="buttonLogar" type="submit"class="btn btn-primary" name="logar"
								value="logar">
								Logar
						</button>
					</form>
				</div>

				<div class="card-footer text-muted  text-center">
					<a href="Cadastro.jsp">Usuario novo? Se cadastre aqui</a><br>
					<a href="NovaSenha.jsp">Recuperar Senha? Clique aqui</a>"
					<br>
				</div>
			</div>
		</div>
	</div>
	
	<!-- SCRIPTS DO FOOTER -->
	<c:import url="Footer.jsp"/>

	<script>
	$(document).ready(function() {

		//SUBMIT DO FORMULÁRIO
		$('#buttonLogar').click(function(event) {
			let form = $('.needs-validation')[0];

			if (form.checkValidity() === false) {
	          event.preventDefault();
	          event.stopPropagation();
        	}
        	form.classList.add('was-validated');
		});
	});
	</script>
</body>
</html> 