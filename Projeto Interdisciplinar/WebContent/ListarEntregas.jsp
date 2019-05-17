<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="Header.jsp"/>

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
<title>Entregas</title>
</head>

<body>
<!-- Buscar Professor -->
 <div id="main" class="container header-margin">
     <form action="ListarEntregaController" method="post">
         <div id="top" class="row">
             <div class="col-md-11">
                 <h2>Entregas</h2>
             </div>
         </div>
	</form>
	
<!-- Listar Entregas-->
<div id="list" class="row">
   <div class="table-responsive col-md-12">
      <table class="table table-striped" cellspacing="0" cellpadding="0">
        <thead>
          <tr>
              <th>Tema</th>
              <th>data</th>
              <th class="actions">Ações</th>
          </tr>		  
		  
		  	<!-- LISTA AS ENTREGA NÃO AVALIADAS E AS SUAS RESPECTIVAS OPCOES -->
			<c:forEach var="entrega" items="${listaNaoAvaliados }">
			  <tr>
			  	<p hidden name="entregaId">${listaNaoAvaliados[1].id }</p>
			  	<td>${entrega.atividade.tema.titulo}</td>
			  	<td><fmt:formatDate pattern="dd/MM/yyyy" value="${entrega.dtCadastro}"/></td>
				<!-- Botoes -->
		    	<td class="actions">
		    		<a class="btn btn-success btn-xs" href="ManterAvaliacaoController?acao=Criar&idEntrega=${entrega.id}&idGrupo=${entrega.grupo.id}">Criar Avaliacao</a>
		    	</td>
			  </tr>
			</c:forEach>
			
			<!-- LISTA AS ENTREGA AVALIADAS E AS SUAS RESPECTIVAS OPCOES -->
			<c:forEach var="entrega" items="${listaAvaliados }">
			  <tr>
			  	<p hidden name="entregaId">${listaAvaliados[1].id }</p>
			  	<td>${entrega.atividade.tema.titulo}</td>
			  	<td><fmt:formatDate pattern="dd/MM/yyyy" value="${entrega.dtCadastro}"/></td>
				<!-- Botoes -->
		    	<td class="actions">
		    		<a class="btn btn-warning btn-xs" href="ManterAvaliacaoController?acao=Editar&idEntrega=${entrega.id}&idGrupo=${entrega.grupo.id}">Editar</a>
			        <a class="btn btn-info btn-xs" href="ManterAvaliacaoController?acao=Visualizar&idEntrega=${entrega.id}&idGrupo=${entrega.grupo.id}">Visualizar Avaliacao</a>
		    	</td>
			  </tr>
			</c:forEach>
			
		  </thead>	
    	</table>
    	
    	

    	
    	
    	 
    	
 	 </div>
   </div>
</div>

	    


</body>
</html>