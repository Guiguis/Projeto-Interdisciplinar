<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="Menu.jsp"/>
 
<!DOCTYPE html>
<html>
<head>
<!-- Meta Tags -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<title>Avaliacao Semanal</title>

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
		<div class="col-lg-12 mt-30">
		
		<!-- Título -->
			<div class="col-md-4 offset-md-4">
				<h1 class="">Avaliar Alunos</h1>
			</div>
			<form action="ManterAvaliacaoController" method="post"
				class="form-horizontal">
			<!-- Data -->
			<div class="form-group col-md-5 offset-md-4">
				<div class="form-row">
					<div class="form-group col-md-2">
						<label for="inputData">Data</label>
					</div>
					<div class="form-group col-md-4">
						<input id="inputData" type="text"  
						class="form-control" name="data"  placeholder="dd/mm/ano" />
					</div>
				</div>
			</div>
		
		
			<!-- Formulario para nota dos alunos -->
				
				<!-- Formulario para nota do aluno 1 -->
				<div class="form-row">
					<div class="form-group col-md-3">
						<label for="inputNota1">Aluno 1</label>
						<input id="inputNota1" type="text"  
							   class="form-control" name="nota1"  placeholder="Digite a nota" />
					</div>
					<div class="form-group col-md-5">
						<label for="inputComentarios1">Descrição</label> 
						<input id="inputComentarios1" type="text" 
						 	   class="form-control" name="dcomentarios1"/>
					</div>
				</div>
				
				<!-- Formulario para nota do aluno 2 -->
				<div class="form-row">
					<div class="form-group col-md-3">
						<label for="inputNota2">Aluno 2</label>
						<input id="inputNota2" type="text"  
							   class="form-control" name="nota2"  placeholder="Digite a nota" />
					</div>
					<div class="form-group col-md-5">
						<label for="inputComentarios2">Descrição</label> 
						<input id="inputComentarios2" type="text" 
						 	   class="form-control" name="comentarios2"/>
					</div>
				</div>
				
				<!-- Formulario para nota do aluno 3 -->
				<div class="form-row">
					<div class="form-group col-md-3">
						<label for="inputNota3">Aluno 3</label>
						<input id="inputNota3" type="text"  
							   class="form-control" name="nota3"  placeholder="Digite a nota" />
					</div>
					<div class="form-group col-md-5">
						<label for="inputComentarios3">Descrição</label> 
						<input id="inputComentarios3" type="text" 
						 	   class="form-control" name="comentarios3"/>
					</div>
				</div>
				
				<!-- Formulario para nota de todos os alunos -->
				<div class="form-row">
					<div class="form-group col-md-3">
						<label for="inputNotaTodos">Todos os alunos</label>
						<input id="inputNotaTodos" type="text"  
							   class="form-control" name="notaTodos"  placeholder="Digite a nota" />
					</div>
					<div class="form-group col-md-5">
						<label for="inputComentariosTodos">Descrição</label> 
						<input id="inputComentariosTodos" type="text" 
						 	   class="form-control" name="dcomentariosTodos"/>
					</div>
				</div>
				
				
				<button type="submit" class="btn btn-primary">Enviar</button>
				
				
			</form>
		</div>
	</div>
</div>

<script src="assets/scripts/jquery.min.js"></script>
<script src="assets/scripts/bootstrap/bootstrap.min.js"></script>

</body>
</html>