<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.Turma" %>

<link href="assets/style/header.css" rel="stylesheet" />

<header class="header bg-info">
	<div class="container">
		<div class="row">
			
			<nav class="navbar navbar-expand-lg navbar-dark">

				<!-- MOBILE -->
				<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
				aria-expanded="false" aria-label="Alterna navegação">
					<span class="navbar-toggler-icon"></span>
				</button>

				<!-- MENU -->
				<div class="collapse navbar-collapse" id="navbarNavDropdown">

					<!-- LOGO -->
					<a class="navbar-brand" href="#">
						<img alt="" src="Img\LogoUsjt.jpeg" >
					</a>

					<!-- LINKS -->
					<ul class="navbar-nav menu-flex">
						<li class="nav-item active">
							<a class="nav-link" href="index.jsp">Início</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link active" href="ListarProfessor?acao=reiniciar">
								Professor
							</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link active" href="cadastroAvaliacao.jsp">
								Avaliacao Semanal
							</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link" href="#">
								Gerar Declarção
							</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link" href="ListarGrupo?acao=reiniciar">
								Grupo
							</a>
						</li>

						<!-- SEMESTRE E ANO LETIVO -->
						<li class="nav-item">
							<form name="formMenu" class="form-semestre-anoletivo" method=post action=LoginManter>
								<select name="semestre" class="custom-select" 
										onchange="javascript:document.formMenu.submit();">
								
												<option value="" selected disabled>Semestre / Ano</option>
												
												<c:forEach var="semestre" items="${lstSemestre}">
												<c:set var="item" 
												value="${semestre.semestreLetivo}-${semestre.ano}"/>

												<c:if test="${escholhido == item}">
												<option selected value="${item}">
													${semestre.semestreLetivo}º / ${semestre.ano}
												</option>
											</c:if>
											<c:if test="${escholhido != item}">
											<option value="${item}">
												${semestre.semestreLetivo}º / ${semestre.ano}
											</option>
										</c:if>
									</c:forEach>
								</select>
							</form>
						</li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
</header>


