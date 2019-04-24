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
<title>Professor</title>
</head>


<div id="list" class="row">
    <div class="table-responsive col-md-12">
        <table class="table table-striped" cellspacing="0" cellpadding="0">
            <thead>
              <tr>
                  <th>ID</th>
                  <th>Nome</th>
                  <th>Matricula</th>
                  <th>E-Mail</th>
                  <th class="actions">Ações</th>
              </tr>

<tbody>
<c:forEach var="p" items="${lista }">
    <tr>
         <td>${p.id }</td>
         <td>${p.nome }</td>
         <td>${p.matricula }</td>
         <td> ${p.email }</td>
         <td class="actions">
             <a class="btn btn-success btn-xs" href="ManterProfessorController?acao=Visualizar&id=${p.id }">Visualizar</a>
             <a class="btn btn-warning btn-xs" href="ManterProfessorController?acao=Editar&id=${p.id }">Editar</a>
             <button id="btn${p.id }%>" type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#delete-modal" data-cliente="${cliente.id }">Excluir</button>
         </td>
     </tr>
</c:forEach>
</tbody>
</table>
</div>
</div>



</body>
</html>