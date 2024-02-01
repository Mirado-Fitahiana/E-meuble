<%-- 
    Document   : formStyle
    Created on : 12 déc. 2023, 16:21:07
    Author     : 1905
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@page import="Model.*" %>
<% Style[] styles = (Style[]) request.getAttribute("style");
%>
<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Formulaire Style</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletStyle">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">nom</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="nom" name="nom" placeholder="nom">
                    </div>
                </div>
                <div class="form-group mb-2">
                    <button type="submit" class="btn btn-primary">Valider</button>
                </div>
            </form>
        </div>
    </div>
    <div class="card-body">
        <!-- table -->
        <table class="table datatables" id="dataTable-1">
            <thead>
                <tr>
                    <th><strong>NOM</strong></th>
                    <td><strong></strong></td>
                </tr>
            </thead>
            <tbody>             
                <% for (int i = 0; i < styles.length; i++) {%>
                <tr>
                    <td><strong><%=styles[i].getNom()%></strong></td>
                    <td><a href="Servletformule?style=<%=styles[i].getIdStyle()%>&nom=<%=styles[i].getNom()%>"><button class="btn mb-2 btn-outline-success"><span>Creer un meuble</span></button></a></td>
            </tr>
            <%    }
            %>
            </tbody>
        </table>
    </div>
</main> <!-- main -->
<%@ include file="footer.jsp" %>
