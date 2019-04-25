<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="Menu.jsp"/>

<!DOCTYPE html>
<html>
<head>
<!-- Meta Tags -->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<title>Alterar Professor</title>

<!-- Bootstrap -->
<link href="assets/style/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/style/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet" />

<!-- MAIN -->
<link href="assets/style/utils.css" rel="stylesheet" />
</head>
<body>


<!-- Container Principal -->
<div id="main" class="container">
    <h3 class="page-header">Alterar Professor #${professor.id }</h3>
    <!-- Formulario para alteração de professores -->
    <form action="ManterProfessorController" method="post">
        <!-- area de campos do form -->
        <input type="hidden" name="id" value="${professor.id }" />
        
        
        <div class="form-row">
			<div class="form-group col-md-6">
				<label for="inputName">Nome</label>
				<input type="text" class="form-control" name="nome" id="nome" required maxlength="100" 
              			placeholder="nome completo" value="${professor.nome }">
			</div>
			<div class="form-group col-md-4">
				<label for="inputMatricula">Matrícula</label> 
				<input type="text" class="form-control" name="matricula" id="matricula" required maxlength="100" 
              			placeholder="Matricula" value="${professor.matricula }">
			</div>
		</div>

		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="inputEmail4">E-mail</label>
				<input type="text" class="form-control" name="email" id="email" required maxlength="100" 
              			placeholder="E-mail" value="${professor.email }">
			</div>
			<div class="form-group col-md-4">
				<label for="inputPassword4">Senha</label> 
				<input type="text" class="form-control" name="senha" id="senha" required maxlength="100" 
             				 placeholder="Senha" value="${professor.senha }">
			</div>
		</div>
      
     	<div class="form-group">
			<div class="custom-control custom-checkbox">
				<c:if test="${professor.administrador == 1 }">
					<input id="customControlAutosizing" type="checkbox" name="administrador" class="custom-control-input"  checked/> 
				</c:if>
				<c:if test="${professor.administrador == 0 }">
					<input id="customControlAutosizing" type="checkbox" name="administrador" class="custom-control-input"  /> 
				</c:if>
				<label class="custom-control-label" for="customControlAutosizing">Administrador?</label>
			</div>
		 </div>
        <br/>
        <div id="actions" class="row">
            <div class="col-md-12">
                <button type="submit" class="btn btn-primary" name="acao" value="Alterar">Salvar</button>
                <a href="ListarProfessores.jsp" class="btn btn-default">Cancelar</a>
            </div>
        </div>
    </form>
</div>

</body>
</html>