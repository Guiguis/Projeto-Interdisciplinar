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
		<div id="User">
		<!-- Campos Usuario -->
				<form action="LoginManter" method="get" class="form-horizontal">
					<div class="form-row">
						<div class="form-group col-md-12" align="Center">
							<label for="inputUser" align="Center" id="LabelUser" name="user">Usuario:</label>
							<div class="form-group col-md-6" align="Center">
							<input id="inputUser" type="text"  
								   class="form-control" name="nome" align="Center" /> 	
								   </div>	  
						</div>
						</div>
						<button type="submit" class="btn btn-primary">Login</button>
				</form>
			</div>
		</div>
		<div id="Senha"><!-- Campos Senha -->
				<form action="LoginManter" method="post" class="form-horizontal">
					<div class="form-row">
						<div class="form-group col-md-12" align="Center">
							<label for="inputSenha" align="Center" id="LabelSenha" name="senha">Senha:</label>
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
		<button type="submit" class="btn btn-primary" href="LoginManter">Login</button>
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