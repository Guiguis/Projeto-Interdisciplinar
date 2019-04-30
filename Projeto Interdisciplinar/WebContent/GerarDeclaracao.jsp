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
<title>Grupos</title>
</head>

<body>
	
     
<div class="container">
	<div class="col-md-12">
		<h1 class="col-md-4">Gerar Declaração</h1>
	</div>

     <div class="col-md-12">
        <label class="col-md-4">Orientador: ${orientador.nome }</label>
        <label class="col-md-3">Data: <fmt:formatDate pattern="dd/MM/yyyy" value="${banca.data }"/></label>
        <label class="col-md-4">Local: Rua Mooca 1254 Sala: ${banca.sala }</label>
     </div>
     
     <br/>
     <br/>
     <br/>
     
     <div class="col-md-12">
     	<label class="col-md-4">${grupo.nome }<br><br>
     		<c:forEach var="aluno" items="${listaAlunos }">
			    ${aluno.nome }<br>
			</c:forEach>
     	</label>
     
     	<label class="col-md-3"></label>
     
     	<label class="col-md-3">Tema: ${tema }<br><br>
     		<c:forEach var="professor" items="${listaProfessores }">
			    ${professor.nome }<br>
			</c:forEach>
     	</label>
     	
     	
     </div>
     
     
     <br><br><br><br>
     
     <p> Participou da banca como professor/orientador o Sr(a) ${orientador.nome } 
     	no dia <fmt:formatDate pattern="dd/MM/yyyy" value="${banca.data }"/>
     	avaliando os alunos: 
     	<c:forEach var="aluno" items="${listaAlunos }">
			${aluno.nome },
		</c:forEach> referente ao tema ${tema }
		
     </p>
     <p>Ass: São Judas Tadeu</p>
     
     </div>
     
    

     
     

      
         
          

</body>
</html>