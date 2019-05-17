<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<!-- Meta Tags -->
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

	<title>Inicio</title>

	<!-- Bootstrap -->
	<link href="assets/style/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
	<link href="assets/style/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet" />

	<!-- MAIN -->
	<link href="assets/style/utils.css" rel="stylesheet" />
</head>
<body>
	<c:import url="menuAluno.jsp"/>
	
	<div id="main" class="container header-margin">
		<div class="row justify-content-md-center">
			<div class="col-lg-10">
				<div id="carousel" class="carousel slide" data-ride="carousel">
				  <ol class="carousel-indicators">
				    <li data-target="#carousel" data-slide-to="0" class="active"></li>
				    <li data-target="#carousel" data-slide-to="1"></li>
				    <li data-target="#carousel" data-slide-to="2"></li>
				  </ol>
				  <div class="carousel-inner">
				    <div class="carousel-item active">
				      <img class="img-fluid d-block" src="assets/images/palestra1.jpg" >
				    </div>
				    <div class="carousel-item">
				      <img class="img-fluid d-block" src="assets/images/palestra2.jpg">
				    </div>
				    <div class="carousel-item">
				      <img class="img-fluid d-block" src="assets/images/palestra3.jpg">
				    </div>
				  </div>
				  <a class="carousel-control-prev" href="#carousel" role="button" data-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="sr-only">Anterior</span>
				  </a>
				  <a class="carousel-control-next" href="#carousel" role="button" data-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="sr-only">Proximo</span>
				  </a>
				</div>
			</div>
		</div>
		
		<div class="row justify-content-md-center">
			<div class="col-lg-10">
				<h2 class="text-muted text-capitalize title text-center"> Seja bem-vindo ${usuario.nome }</h2>
				<p style="width: 50rem;" >A página do aluno é para conseguir ter uma comunicação com o aluno e informar a situação na graduação. </br>Caso tenha alguma dúvida e não consiga resolver por aqui, vá para o CAA na universidade que está matriculado.  </p>
			</div>
		
			<div class="col-lg-5">
				<div class="card border-warning mb-3" style="max-width: 30rem;">
				  <div class="card-header"> Avisos Urgentes</div>
				  <div class="card-body">
				    
				  </div>
				</div>
			</div>
			
			<div class="col-lg-5">
								
				<div class="card bg-light mb-3" style="max-width: 30rem;">
				  <div class="card-header">Conte aqui sua opinião</div>
				  <div class="card-body">
				    
				    <p> Não existem avaliações institucionais</p>
				  </div>
				</div>
				
			</div>
		</div>
	</div>

	<c:import url="Footer.jsp"/>
</body>
</html>