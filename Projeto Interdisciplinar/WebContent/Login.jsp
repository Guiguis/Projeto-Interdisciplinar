<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Login</title>
<!-- Bootstrap -->
<link href="assets/style/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/style/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet" />

<!-- MAIN -->
<link href="assets/style/utils.css" rel="stylesheet" />
</head>
<body>
<div class="container">
<!-- NavBar - Inicio -->
	<nav class="navbar navbar-expand-lg navbar-light bg-primary">
		<a class="navbar-brand" href="#">Inicio</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Alterna navegação">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="#"></a></li>
				<li class="nav-item"><a class="nav-link active"
					href="professor.html">Cadastro de Professor</a></li>
				<li class="nav-item"><a class="nav-link active" href="Login.jsp">Avaliacao
						Semanal</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Gerar
						Declarção</a></li>

			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle " href="#"
					id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> Semestre </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="#">2018/01</a> <a
							class="dropdown-item" href="#">2018/02</a> <a
							class="dropdown-item" href="#">2019/01</a>
					</div></li>
			</ul>

		</div>
	</nav>	
<!-- NavBar - Termino -->
<!--  Inicio Login -->
<div class="row">
	<div class="col-lg-12 mt-30">
		<div class="col-md-12" align="Center" id="Login" >
			<h1>Login</h1>
		</div>
		<div class="col-md-8"  align="Center" id="divFaixaPretaLogin">
			<img alt="" src="Img\Login\linhaPreta.jpg" id="linha_preta_Login">
		</div>
		<div class="col-md-6" align="Center"id="divFaixaCinzaLogin">
			<img alt="" src="Img\Login\LinhaCinza.jpg" id="Linha_Cinza_login">
		</div>
		<div class="col-md-12" id="ComposLogin">
		<div id="User"><!-- Campos Usuario -->
				<form action="Login" method="post" class="form-horizontal">
					<div class="form-row">
						<div class="form-group col-md-12" align="Center">
							<label for="inputUser" align="Center" id="LabelUser">Usuario:</label>
							<div class="form-group col-md-6" align="Center">
							<input id="inputUser" type="text"  
								   class="form-control" name="nome" align="Center" /> 	
								   </div>	  
						</div>
						</div>
				</form>
			</div>
		</div>
		<div id="Senha"><!-- Campos Senha -->
				<form action="Login" method="post" class="form-horizontal">
					<div class="form-row">
						<div class="form-group col-md-12" align="Center">
							<label for="inputSenha" align="Center" id="LabelSenha">Senha:</label>
							<div class="form-group col-md-6" align="Center">
							<input id="inputSenha" type="password"  
								   class="form-control" name="nome" align="Center" /> 	
								   </div>	  
						</div>
						</div>
				</form>
		<div class="col-md-11" align="right">
			<a href="#">Esqueceu a senha?</a>
		</div>
		
		</div>
		<div align="center" class ="col-md-4">
		<button type="submit" class="btn btn-primary" >Login</button>
		</div>		
	</div>
	<div class="col-lg-12 mt-30"><!-- Rodapé -->
	<div id="logo" align="center"> 
			<img alt="" src="Img\LogoUsjt.jpeg" >
		</div>
	</div>

</div>
</div>
<script src="assets/scripts/jquery.min.js"></script>
<script src="assets/scripts/bootstrap/bootstrap.min.js"></script>
</body>
</html>