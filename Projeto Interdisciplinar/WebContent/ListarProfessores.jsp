<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- MENU -->
<c:import url="Header.jsp"/>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <!-- METATAGS -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1, maximum-scale=1, user-scalable=no"/>

        <!-- Bootstrap -->
        <link href="assets/style/bootstrap/css/bootstrap.min.css"
        rel="stylesheet" />
        <link href="assets/style/bootstrap/css/bootstrap-theme.min.css"
        rel="stylesheet" />

        <!-- MAIN -->
        <link href="assets/style/utils.css" rel="stylesheet" />
        <title>Professor</title>
    </head>
<body>
     
<!-- REDIRECIONA O USUARIO PARA O LOGIN SE ELE NÃO ESTIVER LOGADO -->
<c:if test="${ usuario == null}">
	<c:redirect url = "Login.jsp"/> 
</c:if>   

<!-- REDIRECIONA O USUARIO PARA O INDEX SE ELE NÃO FOR ADMINSTRADOR -->
<c:if test="${usuario.administrador != 1}">
	<c:redirect url = "index.jsp"/> 
</c:if> 
  
     
    <!-- BUSCAR PROFESSOR -->
    <div id="main" class="container header-margin">
		
        <!-- ERROR -->
		<c:if test="${not empty serverError}">
			<div class="alert alert-danger" role="alert">
                Não foi possível executar a ação! <br/>
                Motivo: ${serverError}
            </div>
		</c:if>
		
		<form action="ListarProfessor" method="post">
            <div class="row">

               <div class="col-md-12">
                   <h2 class="title">Professores</h2>
                </div>

                <div class="col-md-6">
                    <div class="input-group mb-3">
                        <input name="data[search]" class="form-control rounded" 
                               type="text" placeholder="Digite o nome: ">
                        <span class="input-group-btn ml-2">
                            <input  class="btn btn-outline-primary rounded" 
                                    type="submit" name="acao" value="Buscar">
                        </span>
                    </div>
                </div>

                <div class="col-md-3 offset-md-3 text-right">
                    <a href="cadastroProfessor.jsp" 
                       class="btn btn-success pull-right h2">
                        Criar Professor
                    </a>
                </div>
           </div>
        </form>

        <!-- LISTA DE PROFESSORES -->
        <div id="list" class="row">
            <div class="table-responsive col-md-12">
                <table class="table table-striped" cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Matrícula</th>
                            <th>E-mail</th>
                            <th>Admnisitrador</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="professor" items="${lstProfessor}">
                            <tr>
                                <td>${professor.nome}</td>
                                <td>${professor.matricula}</td>
                                <td>${professor.email}</td>
                                <td>
                                    <div class="custom-control custom-checkbox text-center">
                                        <c:if test="${professor.administrador == 1 }">
                                            <input type="checkbox" disabled checked 
                                                   class="custom-control-input">
                                            <label class="custom-control-label"></label>
                                        </c:if>
                                        <c:if test="${professor.administrador == 0 }">
                                            <input type="checkbox" disabled 
                                                   class="custom-control-input">
                                            <label class="custom-control-label"></label>
                                        </c:if>
                                    </div>
                                </td>
                            
                                <!-- Botoes -->
                                <td class="actions">
                                    <a class="btn btn-info btn-xs" href="ManterProfessorController?acao=Visualizar&id=${professor.id }">
                                        Visualizar
                                    </a>
                                    <a class="btn btn-warning btn-xs" 
                                        href="ManterProfessorController?acao=Editar&id=${professor.id}">Editar
                                    </a>
                                     <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modal-${professor.id}">
       									 Excluir
    								</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody> 
                </table>
            </div>
        </div>
    </div>

    <!-- Modal Delete -->
	<c:forEach var="professor" items="${lstProfessor}">
	    <div class="modal fade" id="modal-${professor.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	        <div class="modal-dialog modal-dialog-centered" role="document">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title" id="exampleModalLabel">
                            Deseja excluir ${professor.nome}?
                        </h5>
	                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                        <span aria-hidden="true">&times;</span>
	                    </button>
	                </div>
	                <div class="modal-body">
	                   Professor: ${professor.nome} será excluído! <br/> 
                       Deseja realmente excluir este professor?
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                            Cancelar
                        </button>
                        <a class="btn btn-primary btn-xs" 
                           href="ManterProfessorController?acao=Excluir&id=${professor.id}">
                           Confirmar
                        </a>
	                </div>
	            </div>
	        </div>
	    </div>
	</c:forEach>

<!-- /#main -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

<script type="text/javascript">
$("#delete-modal").on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget);
        console.log(button);
        
        var recipient = button.data('professor');
        $("#id_excluir").val(recipient);
    });
</script>

</body>
</html>