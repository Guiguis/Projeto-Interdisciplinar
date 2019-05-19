<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="Header.jsp"/>
 
<!DOCTYPE html>
<html>
<head>
<!-- JQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

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

<div class="container header-margin">
	<div class="row">
		<div class="col-lg-12">
		
		<!-- Título -->
			<div class="col-md-4 offset-md-4">
				<h1 class="">Avaliar Alunos</h1>	
			</div>
			
			<form action="ManterAvaliacaoController?idGrupo=${idGrupo}&idEntrega=${idEntrega}" method="post" class="form-horizontal">		
			<!-- Formulario para nota dos alunos -->
				
				<!-- Formulario para nota do aluno individual -->
				<div class="form-row">
				   <c:forEach var="aluno" items="${listaAluno }">
				   		<div class="form-group col-md-2">
						<label class="individual" for="nota${aluno.id }">${aluno.nome}</label>
						<input id="codigo" type="text" class="form-control individual" name="nota${aluno.id }"  placeholder="Digite a nota" required/>
					</div>
					<div class="form-group col-md-5">
						<label class="individual" for="com${aluno.id }">Comentario</label> 
						<input id="com${aluno.id }" type="text" class="form-control individual" name="com${aluno.id }" required/>
					</div>
					<div class="col-md-4"></div>
				   </c:forEach>	
				</div>
			
				<div class="form-group col-md-2">
						<label>Avaliar todos?</label>
					<input type="checkbox" onclick="fun" id="todos">
					</div>
					
				
				<!-- Formulario para nota de todos os alunos -->
				<div class="form-row">	
					
					<div class="form-group col-md-3">
						<label for="inputNotaTodos">Todos os alunos</label>
						<input id="interno" type="text" class="form-control todos" name="notaTodos"  placeholder="Digite a nota" required/>
					</div>
					<div class="form-group col-md-4">
						<label for="inputComentariosTodos">Comentario</label> 
						<input id="comentariosTodos" type="text" class="form-control todos" name="comentariosTodos" required/>
					</div>
				</div>		
				<a class="btn btn-danger btn-xs" href="ListarEntregaController?acao=reiniciar&id=${idGrupo }">Cancelar</a>			
				<button type="submit" class="btn btn-success" name="acao" value="Enviar">Enviar</button>
				
				
			</form>
		</div>
	</div>
</div>

<c:import url="Footer.jsp"/>


<script>

$(document).ready(function(){
	 $("#todos").on("click", function(){
	      $(".individual").prop("hidden", this.checked); 
	      $(".individual").prop("disabled", this.checked); 
	   });
	   
	});
	
</script>

</body>
</html>