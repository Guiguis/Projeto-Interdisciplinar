<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="Header.jsp"/>
<%@page import="model.Avaliacao" %>

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

<div class="container header-margin">
	<div class="row">
		<div class="col-lg-12  mt-30">
		
		<!-- TITULO -->
		<div class="col-md-4 offset-md-4">
			<h1 class="">Avaliar Alunos</h1>
		</div>
		
		<form action="ManterAvaliacaoController?entregaId=${listaAvaliacao[1].entrega.id}" method="post" class="form-horizontal"> 

			<c:forEach var="avaliacao" items="${listaAvaliacao}">
				<div class="form-row">
					<div class="form-group col-md-10">
						<label>${avaliacao.aluno.nome}</label>
						<input type="hidden" class="form-control" name="avaliacaoId${avaliacao.id}" id="avaliacaoId${avaliacao.id}" value="${avaliacao.id}" />
					</div>
					<div class="form-group">
						<label for="inputName">Nota: </label>
						<input type="text" class="form-control individual" name="nota${avaliacao.id }" id="nota${avaliacao.id }" value="${avaliacao.nota}" required/>
					</div>
					<div class="form-group">
						<label for="inputName">Comentarios: </label>
						<input type="text" class="form-control individual" name="comentarios${avaliacao.id }" id="comentarios${avaliacao.id }" value="${avaliacao.comentarios}" required/>
					</div>
				</div>
			</c:forEach>
			<input type="hidden" name="entregaId" id="entregaId" value="${listaAvaliacao[1].entrega.id }" />
			
			<a class="btn btn-danger btn-xs" href="ListarEntregaController?acao=reiniciar&id=${idGrupo }">Cancelar</a>
			<button type="submit" class="btn btn-success" name="acao" value="Atualizar">Enviar</button>
			
			</form>
		</div>
	</div>
</div>			

<c:import url="Footer.jsp"/>

<script>
$(document).ready(function(){
	alert($('.individual').val());
	//Funcao que desabilita a avaliacao de todos
	$('.form-control individual').keyup(function(){
		if(($('.form-control individual').val()).length > 0){
			$('form-control todos').prop("disabled", true);
			alert("teste ");
		}
		else{
			$('form-control todos').prop("disabled", false);
			alert("teste ");
		}
	});
	
	//Funcao que desabilita o campo de avaliacao individual
	$('form-control todos').keyup(function(){
		if(($('form-control todos').val()).length > 0){
			$('.form-control individual').prop("disabled", true);
			alert("teste ");
		}
		else{
			$('.form-control individual').prop("disabled", false);
			alert("teste ");
		}
	});	  
});
</script>
</body>
</html>