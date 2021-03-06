<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Esqueci minha senha</title>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- Bootstrap -->
<link href="assets/style/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/style/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet" />
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" 
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<!-- MAIN -->
<link href="assets/style/utils.css" rel="stylesheet" />
</head>
<body>
<div class="conteiner">
<br>
<div class="row justify-content-center">
<!-- Inicio do Card -->
	<div class="card text-center bg-light w-50 h-75">
		<div class="card-header">
            <label>${erro }</label>
            <h3 class="text-center" align="Center">Recuperação de Senha</h3>
		<input type="hidden" name="comand" value="RecuperarSenhaControler"> 
		<div class ="card-body text-center">
			
				<form action="RecuperarSenhaControler" method="post">
					<div class="form-row">
	                <div class="form-group col-xl-12">
	                	<label for="DocumentoWeb">Digite Seu Ra Ou Matricula</label>
	                	<div class="input-group">
					     	<div class="input-group-prepend">
					         	<div class="input-group-text">
					            	<i class="far fa-address-card"></i>
					          </div>
					        </div> 
					     	<input id="DocumentoWeb" name="DocumentoWeb" placeholder="Digite seu RA/Matricula" type="text" class="form-control">
					     </div>
	                </div>
	                <br>
	                <div class="form-group col-xl-12">
	                	<label for="TokenWeb">Digite Seu Token</label>
	                	<div class="input-group">
					     	<div class="input-group-prepend">
					         	<div class="input-group-text">
					            	<i class="fas fa-key"></i>
					          </div>
					        </div> 
					     	<input id="TokenWeb" name="TokenWeb" placeholder="Digite seu Token" type="text" class="form-control">
					     </div>
	                </div>
	                <br>
	                <div class="form-group col-xl-12 text-center">
	                	<label for="novaSenha">Digite sua nova senha</label>
	                	<div class="input-group">
					     	<div class="input-group-prepend">
					         	<div class="input-group-text">
					            	<i class="fa fa-unlock-alt"></i>
					          </div>
					        </div> 
					     	<input id="novaSenha" name="novaSenha" placeholder="" type="password" class="form-control">
					     </div>
	                </div>
	                <label></label>
                </div>		
                <button type="submit" id="alterarSenha2" class="btn btn-primary" name="acao" value="alterarSenha2">Alterar Senha</button>
				</form>
		
		</div>
		</div>
	</div>	
</div>

</div>
<c:import url="Footer.jsp"/>

</body>
</html>