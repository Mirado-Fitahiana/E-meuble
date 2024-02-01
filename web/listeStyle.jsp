<%-- 
    Document   : listeStyle
    Created on : 12 déc. 2023, 17:02:57
    Author     : 1905
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%@page import="Model.*" %>
<% Materiel[] materiels = (Materiel[]) request.getAttribute("materiels");
    Style[] styles = (Style[]) request.getAttribute("styles"); %>
<main role="main" class="main-content">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-12">
                <h2 class="mb-2 page-title">LISTE STYLE</h2>
                <div class="row my-4">
                    <!-- Small table -->
                    <div class="col-md-12">
                        <div class="card shadow">
                            <div class="card-body">
                                <!-- table -->
                                <table class="table datatables" id="dataTable-1">
                                    <thead>
                                        <tr>
                                            <th><strong>NOM</strong></th>
                                            
                                            <th><strong></strong></th>
                                        </tr>
                                    </thead>
                                    <tbody>             
                                        <% for (int i = 0; i < styles.length; i++) {%>
                                        <tr>
                                            <td><strong><%=styles[i].getNom()%></strong></td>
                                            <td><a href="ServletListeStyle?Style=<%=styles[i].getIdStyle()%>&nom=<%=styles[i].getNom()%>"><button class="btn mb-2 btn-outline-success">Materiels composants</button></a></td>
                                        </tr>
                                        <%    }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div> <!-- simple table -->
                </div> <!-- end section -->
            </div> <!-- .col-12 -->
        </div> <!-- .row -->
    </div> <!-- .container-fluid -->
    <%@ include file="footer.jsp" %>



