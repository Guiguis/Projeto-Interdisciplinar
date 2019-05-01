<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

    <!-- BUSCAR PROFESSOR -->
    <div id="main" class="container header-margin">
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
                                    <a class="btn btn-danger btn-xs" 
                                        href="ManterProfessorController?acao=Excluir&id=${professor.id}">Excluir
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody> 
                </table>
            </div>
        </div>
    </div>

<!-- /#main -->
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