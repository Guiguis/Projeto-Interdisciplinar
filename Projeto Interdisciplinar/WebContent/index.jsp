<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="Header.jsp"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- Meta Tags -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
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
				      <img class="img-fluid d-block" src="assets/images/prof1.jpg" >
				      
				    </div>
				    <div class="carousel-item">
				      <img class="img-fluid d-block" src="assets/images/pro.jpeg">
				      
				    </div>
				    <div class="carousel-item">
				      <img class="img-fluid d-block" src="assets/images/prof3.jpg">
				      
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
				<p>Esta tela tem como objetivo informar ao nosso corpo de docentes a informações mais retundantes de sua rotina.
				 Nos menus acimas encontrará todas as opções pertinentes as suas turmas. </p>
			</div>
		
			
				
			</div>
		</div>
	
	</div>
	
	<c:import url="Footer.jsp"/>
</body>
</html>