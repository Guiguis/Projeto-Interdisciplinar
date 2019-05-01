<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.Turma" %>

<link href="assets/style/main.css"   rel="stylesheet" />
<link href="assets/style/header.css" rel="stylesheet" />

<header class="header">
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
					<a class="navbar-brand" href="index.jsp">
						<img alt="" src="assets/images/logo-blue.png" >
					</a>

					<!-- LINKS -->
					<ul class="navbar-nav menu-flex">
						<li class="nav-item active">
							<a class="nav-link" href="index.jsp">
								Início
							</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link active" href="ListarProfessor?acao=reiniciar">
								Professor
							</a>
						</li>
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
						
						<li class="nav-item active">
							<a class="nav-link" href="CriarEmail?acao=reiniciar">
								Banca
							</a>
						</li>

						<!-- PERÍODOS -->
						<li class="nav-item nav-link">
							<form name="periodoMenu" class="form-periodo" method=post action=LoginManter>
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
						</li>

						<!-- TURMAS -->
						<li class="nav-item">
							<form name="turmaMenu" class="form-turma" method=post action=LoginManter>
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
						</li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
</header>


