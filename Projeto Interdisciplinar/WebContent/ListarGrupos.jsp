<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="Menu.jsp"/>

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
<title>Grupos</title>
</head>

<body>

<!-- Buscar Professor -->
 <div id="main" class="container">
     <form action="ListarGrupo" method="post">
         <div id="top" class="row">
             <div class="col-md-11">
                 <h2>Grupos</h2>
             </div>

             <div class="col-md-6">
                 <div class="input-group mb-3">
                     <input name="data[search]" class="form-control" id="search" type="text" placeholder="Pesquisar Grupo">
                     <div class="col-md-1"></div>
                     <span class="input-group-btn">
                          <input  class="btn btn-outline-primary" type="submit" name="acao" value="buscar">
                     </span>
                 </div>
             </div>
         </div>
	</form>
	
<!-- Listar professores -->
<div id="list" class="row">
   <div class="table-responsive col-md-12">
      <table class="table table-striped" cellspacing="0" cellpadding="0">
        <thead>
          <tr>
              <th>Id</th>
              <th>Nome</th>
              <th>Numero</th>
              <th class="actions">Ações</th>
          </tr>

		  <tbody>
			<c:forEach var="grupo" items="${lista }">
			  <tr>
			    <td>${grupo.id }</td>
			    <td>${grupo.nome }</td>
			    <td>${grupo.numero }</td>
			    <!-- Botoes -->
			    <td class="actions">
			        <a class="btn btn-primary btn-xs" href="ListarEntregaController?acao=reiniciar&id=${grupo.id }">Visualizar Entregas</a>
			    </td>
			  </tr>
			</c:forEach>
		  </tbody> 
    	</table>
 	 </div>
   </div>
</div>

</body>
</html>