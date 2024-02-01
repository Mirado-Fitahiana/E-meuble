<%-- 
    Document   : taille.jsp
    Created on : 19 déc. 2023, 14:58:05
    Author     : 1905
--%>




<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@page import="Model.*" %>
<% Taille[] taille = (Taille[]) request.getAttribute("tailles");
%>
<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Formulaire Taille</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletTaille">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Taille description</label>
                    <div class="col-sm-9">
                        <input type="text" name="nom" id="nom" class="form-control">
                    </div>
                </div>
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
                <input type="hidden" value="1" name="type" id="type">

            </form>
        </div>
    </div>
    <div class="card-body">
        <!-- table -->
        <table class="table datatables" id="dataTable-1">
            <thead>
                <tr>
                    <th><strong>NOM</strong></th>
            </thead>
            <tbody>             
                <% for (int i = 0; i < taille.length; i++) {%>
            <tr>
                <td><strong><%=taille[i].getNom()%></strong></td>
            </tr>
            <%    }
            %>
            </tbody>
        </table>
    </div>

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Nombre d'ouvrier(Taille standard)</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletTaille">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Nombre d'ouvriers</label>
                    <div class="col-sm-9">
                        <input type="text" name="nombre_ouvrier" id="nombre_ouvrier" class="form-control">
                    </div>
                </div>
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
                <input type="hidden" value="2" name="type" id="type">
                </div>
            </form>
        </div>
    </div>
</main> <!-- main -->
<%@ include file="footer.jsp" %>
