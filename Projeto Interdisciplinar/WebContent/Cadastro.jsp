<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Cadastro - Novo Usuario</title>
<!-- Bootstrap -->
<link href="assets/style/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/style/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet" />
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<!-- MAIN -->
<link href="assets/style/utils.css" rel="stylesheet" />
</head>
<body>
<input type="hidden" name="comand" value="ManterLogin"> 
<div class="container">
<br>
<div class="row justify-content-center">
<!--  Inicio Login -->

<div class="card text-center bg-light w-50 h-75">
<div class="card-header">
<h3>Cadastro</h3>
</div>
<div class="card-body">
<form action="CadastrarController" method="post">
	<div class="form-row">
		<label>${erro }</label>
		<div class="form-group col-xl-12">
		     <label for="inputUsuario">Matricula ou RA</label> 
			<div class="input-group">
		     	<div class="input-group-prepend">
		         	<div class="input-group-text">
		            	<i class="fa fa-envelope"></i>
		          </div>
		        </div> 
		     <input id="matricula" name="documentoWEB" placeholder="matricula/RA" type="text" class="form-control" >
		     
		     </div>
			<label for="inputUsuario">Nome</label> 
			<div class="input-group ">
		    	<div class="input-group-prepend">
		        	<div class="input-group-text">
		            	<i class="fa fa-unlock-alt"></i>
		       		</div>
		     	</div> 
		
		     <input type="text" class="form-control" name="nome" id="nome" required maxlength="100" placeholder="nome" value="">
		   <div>
		   <label for="inputUsuario">Email</label> 
			<div class="input-group ">
		    	<div class="input-group-prepend">
		        	<div class="input-group-text">
		            	<i class="fa fa-unlock-alt"></i>
		       		</div>
		     	</div> 
		
		     <input type="text" class="form-control" name="email" id="email" required maxlength="100" placeholder="Email" value="">
		   <div>
		      <label for="inputUsuario">senha</label> 
			<div class="input-group">
		    	<div class="input-group-prepend">
		        	<div class="input-group-text">
		            	<i class="fa fa-unlock-alt"></i>
		       		</div>
		     	</div> 
		     <input type="password" class="form-control" name="senha" id="senha" required maxlength="100" placeholder="Senha" value="">	     
		     
			<br>
			<div class="input-group ">
			<br>
			  <div class="input-group-prepend">
			    <label class="input-group-text" for="inputGroupSelect01">Tipo de Usuario</label>
			  </div>
			  <select class="custom-select" id="inputSelecao" name="inputSelecao">
			    <option selected>Escolher...</option>
			    <option value="aluno">Aluno</option>
			    <option value="professor">Professor</option>
			    <!-- option value="administrador">Administrador</option-->
			  </select>
			</div>
			
		</div>
		</div>
		</div>
		</div>
		</div>
		</div>
	<br>
	<div class="text-center">
		<button type="submit" class="btn btn-primary" name="Cadastrar" value="Cadastrar">Cadastrar</button>
	</div>
</form>
</div>
<div class="card-footer text-muted">
   <a href="Login.jsp">  Retornar</a>
</div>
</div>
</div>
</div>
<c:import url="Footer.jsp"/>
</body>
</html>