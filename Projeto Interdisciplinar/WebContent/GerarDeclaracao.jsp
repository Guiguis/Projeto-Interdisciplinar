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
	
     
<div class="container header-margin">
  <div class="row center-block">
    
    <div class="col-lg-12">
      <img src="Img\ouvidoria-universidade-sao-judas1.png" class="rounded mx-auto d-block">
    </div>
    
  </div>
  <div class="row justify-content-md-center">
    
    <div class="col-lg-9 text-center">
        <p> <h2 class="text-muted"> DECLARAÇÃO </h2></p>
    </br>
        <p>Declaro, para os devidos fins, que o professor <h5>${orientador.nome }</h5>
        participou, na qualidade de professor (orientador / convidado), da Banca de Trabalho de Conclusão de Curso de Graduação do ${grupo.nome }, nesta Universidade, sob o título: “${tema }”.</p>
        <p> São Paulo, <fmt:formatDate pattern="dd/MM/yyyy" value="${banca.data }" />.</p>
   
    <div class="row justify-content-md-center">
        <div class="col-4">
    <table class="table">
  <thead>
    <tr>  
      <th scope="col">Banca</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="professor" items="${listaProfessores }">
			   <tr>
			   <td> ${professor.nome } </td>
			   </tr>
			</c:forEach>
  </tbody>
</table>
    </div>
    
    <div class="col-4">
    	<table class="table">
  <thead>
    <tr>  
      <th scope="col">Integrantes</th>
    </tr>
  </thead>
  <tbody>
    	<c:forEach var="aluno" items="${listaAlunos }">
    	<tr>
			<td>${aluno.nome }</td>
		</tr>
		</c:forEach>
  </tbody>
</table>
    </div>
    </div>
    
    </br>           
    <p>_______________________________________________________________<br>
    Professora Dra. Ana Paula Gonçalves Serra</br>
    Coordenadora dos cursos Ciência da Computação, Engenharia de Computação, Sistemas de Informação e Tecnologia em Análise e Desenvolvimento de Sistemas
</p>
    
    
    </div>
</div>
</div>
     

      
         
          

</body>
</html>