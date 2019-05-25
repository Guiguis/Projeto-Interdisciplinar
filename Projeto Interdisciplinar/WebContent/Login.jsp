<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Login</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" 
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<!-- MAIN -->
<link href="assets/style/utils.css" rel="stylesheet" />
</head>
<body>

 
<div class="container">
<br>
<div class="row justify-content-center">
<!--  Inicio Login -->

<div class="card text-center bg-light w-50 h-75">
<div class="card-header">
<h3>Login</h3>
</div>
<div class="card-body">
<form action="ManterLogin" method="post" novalidate>
	<div class="form-row">
		<label>${erro}</label>
		<input type="hidden" name="comand" value="ManterLogin"> 
		<div class="form-group col-xl-12">
			<label for="inputUsuario">Usuario</label> 
			<div class="input-group">
		     	<div class="input-group-prepend">
		         	<div class="input-group-text">
		            	<i class="fa fa-envelope"></i>
		          </div>
		        </div> 
		     <input id="user" name="user" placeholder="Email" type="email" class="form-control">
		     </div>
			<label for="inputUsuario">senha</label> 
			<div class="input-group">
		    	<div class="input-group-prepend">
		        	<div class="input-group-text">
		            	<i class="fa fa-unlock-alt"></i>
		       		</div>
		     	</div> 
		     <input type="password" class="form-control" name="senha" id="senha" maxlength="100" placeholder="Senha" value="">
		      </div>
			</div>
		</div>
	<br>
		<button type="submit" id="buttonLogar" class="btn btn-primary" name="logar" value="logar">Logar</button>
		
		

</form>
</div>
<div class="card-footer text-muted">
   <a href="Cadastro.jsp">Usuario novo? Se cadastre aqui</a>
   <br>
   <!--<a href="novaSenha.jsp">Esqueceu a senha?</a> -->
</div>
</div>
</div>
</div>
<c:import url="Footer.jsp"/>


<script>
	$(document).ready(function() {
		
		let userInput = $('#user');
		let passInput = $('#senha'); 

		$('#buttonLogar').click(function(event) {

			let userTxt = $(userInput).val();
			let passTxt = $(passInput).val();

			console.log('userTxt: ' + userTxt);
			console.log('passTxt: ' + passTxt);

			if (userTxt == '' || passTxt == ''){
				alert('Todos os campos devem ser preenchidos');
				return false
			} else{
				return true;
			}

		});
	});
</script>

</body>
</html> 