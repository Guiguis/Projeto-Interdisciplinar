<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="Header.jsp"/>

<!DOCTYPE html>
<html>
<head>
<!-- Meta Tags -->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<title>Cadastro de Professor</title>

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
<div id="main" class="container header-margin">
    <h2 class="title">Criar Banca</h2>
    <!-- Formulario para alteração de professores -->
    <form action="EnviarEmail" method="post" class="from-horizontal">
        <!-- area de campos do form -->        
        
        <div class="form-row">
			<div class="form-group col-md-3">
				<div class="form-group">
                    <label for="exampleFormControlSelect1">Selecione o 1º Professor(a)</label>
                    <select class="form-control" name="professor1">
                        <c:forEach var="professor" items="${listaProfessor }">
                            <option value="${professor.id}">${professor.nome}</option>  
                        </c:forEach>
                    </select>
                  </div>
            </div>
        <div class="form-group col-md-3">
			<div class="form-group">
                <label for="exampleFormControlSelect1">Selecione o 2º Professor(a)</label>
                    <select class="form-control" name="professor2">
                        <c:forEach var="professor" items="${listaProfessor }">
                            <option value="${professor.id}">${professor.nome}</option>  
                        </c:forEach>
            </select>
          </div>    
            </div>
         <div class="form-group col-md-3">
            <div class="form-group">
                <label for="exampleFormControlSelect1">Selecione o 3º Professor(a)</label>
                    <select class="form-control"  name="professor3">
                        <c:forEach var="professor" items="${listaProfessor }">
							<option value="${professor.id}">${professor.nome}</option>  
						</c:forEach>
                    </select>
             </div>
		</div>

		<div class="form-row">
			<div class="form-group col-md-4">
                <div class="form-group">
                <label for="exampleFormControlSelect1">Selecione o Grupo</label>
                    <select class="form-control"  name="grupoId">
                        <c:forEach var="grupo" items="${listaGrupo }">
								<option value="${grupo.id}">${grupo.nome}</option>  
							</c:forEach>
                    </select>
                </div>
		     </div>
            <div class="form-group col-md-4">
                <label for="inputEmail4">Data</label>
				<input id="inputData" type="text" class="form-control" name="data"  placeholder="dd/mm/ano" />
			</div>
			<div class="form-group col-md-4">
				<label for="inputPassword4">Sala</label> 
				<input id="inputData" type="text" class="form-control" name="sala"/>
			</div>
		</div>
            </div>
        <br/>
        
            <div id="actions" class="row">
            <div class="col-md-12">
                <button type="submit" class="btn btn-primary" name="acao" value="Alterar">Criar</button>
                
            </div>
            
        </div>
                 
    </form>
            
</div>
			


</body>
</html>