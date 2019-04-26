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
		<div class="col-lg-12">
		
		<!-- Título -->
			<div class="col-md-4 offset-md-4">
				<h1 class="">Avaliar Alunos</h1>
				
			</div>
			<form action="ManterAvaliacaoController?idGrupo=${idGrupo}&idEntrega=${idEntrega}" method="post"
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
				
				<!-- Formulario para nota do aluno individual -->
				<div class="form-row">
				   <c:forEach var="aluno" items="${listaAluno }">
				   		<div class="form-group col-md-2">
						<label for="nota${aluno.id }">${aluno.nome}</label>
						<input id="nota${aluno.id }" type="text"  
							   class="form-control" name="nota${aluno.id }"  placeholder="Digite a nota" />
					</div>
					<div class="form-group col-md-5">
						<label for="com${aluno.id }">Comentario</label> 
						<input id="com${aluno.id }" type="text" 
						 	   class="form-control" name="com${aluno.id }"/>
					</div>
					<div class="col-md-4"></div>
				   </c:forEach>	
				</div>
			
				
				<!-- Formulario para nota de todos os alunos -->
				<div class="form-row">
					<div class="form-group col-md-3">
						<label for="inputNotaTodos">Todos os alunos</label>
						<input id="inputNotaTodos" type="text"  
							   class="form-control" name="notaTodos"  placeholder="Digite a nota" />
					</div>
					<div class="form-group col-md-5">
						<label for="inputComentariosTodos">Comentario</label> 
						<input id="inputComentariosTodos" type="text" 
						 	   class="form-control" name="comentariosTodos"/>
					</div>
				</div>
				
				
				<button type="submit" class="btn btn-primary" name="acao" value="Enviar">Enviar</button>
				
				
			</form>
		</div>
	</div>
</div>

<script src="assets/scripts/jquery.min.js"></script>
<script src="assets/scripts/bootstrap/bootstrap.min.js"></script>

</body>
</html>