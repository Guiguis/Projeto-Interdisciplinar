<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Professor" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Professor</title>
</head>
<body>
	<%Professor professor = (Professor)request.getAttribute("professor"); %>
	Id: <%=professor.getId() %><br>
	Nome: <%=professor.getNome() %><br>
	E-mail: <%=professor.getEmail() %><br>
	Senha: <%=professor.getSenha() %><br>
	Matricula: <%=professor.getMatricula() %><br>
	administrador: <%=professor.getAdministrador() %><br>
</body>
</html>