<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.Turma" %>

<!-- MAIN -->
<link href="assets/style/main.css"   rel="stylesheet" />
<link href="assets/style/header.css" rel="stylesheet" />

<header class="header">
	<div class="container"> 
		<div class="row" >
			
			<nav class="navbar navbar-expand-lg navbar-dark hidden-xs vertical-align--center">

				<!-- MOBILE -->
				<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
				aria-expanded="false" aria-label="Alterna navegação">
					<span class="navbar-toggler-icon"></span>
				</button>

				
				<!-- REDIRECIONA O USUARIO PARA O LOGIN SE ELE NÃO ESTIVER LOGADO -->
				<c:if test="${ usuario == null}">
					<c:redirect url = "Login.jsp"/> 
				</c:if>	
				
				<!-- MENU -->
				<div class="collapse navbar-collapse vertical-align--center" id="navbarNavDropdown">

					<!-- LOGO -->
					<div class="float-left col-sm-2 col-md-2 col-lg-2">
						<a class="navbar-brand" href="index.jsp">
							<img alt="" src="assets/images/logo-blue.png" >
						</a>
					</div>

					<!-- LINKS -->
					<div class="float-left col-sm-10 col-md-10 col-lg-7">
						<ul class="navbar-nav menu-flex">
							<li class="nav-item active">
								<a class="nav-link" href="index.jsp">
									Início
								</a>
							</li>
							<c:if test="${usuario.administrador == 1}">
								<li class="nav-item active">
									<a class="nav-link active" href="ListarProfessor?acao=reiniciar">
										Professor
									</a>
								</li>
							</c:if>
						
							<li class="nav-item active">
								<a class="nav-link" href="ListarGrupoDeclaracao?acao=reiniciar">
									Gerar Declaração
								</a>
							</li>
							<li class="nav-item active">
								<a class="nav-link" href="ListarGrupo?acao=reiniciar">
									Avaliação
								</a>
							</li>
						
							<c:if test="${usuario.administrador == 1}">
							<li class="nav-item active">
								<a class="nav-link" href="CriarEmail?acao=reiniciar">
									Banca
								</a>
							</li>
						 </c:if>
						
							<!-- PERÍODOS -->
							<li class="nav-item nav-link">
								
							</li>
	
							<!-- TURMAS -->
							<li class="nav-item col-md-2">
								
							</li>
						</ul>
					</div>
					
					<div class="width-100 col-sm-12 col-md-12 col-lg-3">
						<div class="float-left col-lg-12 mb-4 row">
							<form name="periodoMenu" class="form-periodo float-left col-lg-6" method=post action=LoginManter>
									<select name="periodo" class="custom-select" 
											onchange="javascript:document.periodoMenu.submit();">
									
											<option value="" selected disabled>
												Semestre / Ano
											</option>
													
											<c:forEach var="periodo" items="${lstPeriodo}">
												<c:set var="item" 
												value="${periodo.ano}-${periodo.semestreLetivo}"/>
	
												<c:if test="${periodoSelected == item}">
													<option selected value="${item}">
														${periodo.semestreLetivo}º / ${periodo.ano}
													</option>
												</c:if>
												<c:if test="${periodoSelected != item}">
													<option value="${item}">
														${periodo.semestreLetivo}º / ${periodo.ano}
													</option>
												</c:if>
											</c:forEach>
									</select>
								</form>
								
						<form name="turmaMenu" class="form-turma float-left col-lg-6" method=post action=LoginManter>
							<select name="turma" class="custom-select" 
									onchange="javascript:document.turmaMenu.submit();">
							
									<option value="" selected disabled>
										Turmas
									</option>
											
									<c:forEach var="turma" items="${lstTurmas}">											
										<c:if test="${turmaId == turma.id}">
											<option selected value="${turma.id}">
												${turma.sigla}
											</option>
										</c:if>
										<c:if test="${turmaId != turma.id}">
											<option value="${turma.id}">
												${turma.sigla}
											</option>
										</c:if>
								</c:forEach>
							</select>
						</form>
						</div>
						
						<div class="float-left col-lg-6">
							<label>${usuario.nome}</label>
						</div>
						<div class="float-left col-lg-6">
							<a class="btn btn-info" href="SairController?acao=sair">Sair</a>
						</div>
					</div>	
				</div>
				
				
			</nav>
		</div>
	</div>
</header>


