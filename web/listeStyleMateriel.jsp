<%-- 
    Document   : listeStyleMateriel
    Created on : 12 déc. 2023, 17:22:51
    Author     : Chan Kenny
--%>



<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<%@page import="Model.*" %>
<% StyleMateriel[] stylemateriels = (StyleMateriel[]) request.getAttribute("stylemateriels");
            String nomStyle = (String)request.getAttribute("nom");
%>

<main role="main" class="main-content">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-12">
                <h2 class="mb-2 page-title">LISTE MATERIEL POUR LE STYLE <%= nomStyle%></h2>
                <div class="row my-4">
                    <!-- Small table -->
                    <div class="col-md-12">
                        <div class="card shadow">
                            <div class="card-body">
                                <!-- table -->
                                <table class="table datatables" id="dataTable-1">
                                    <thead>
                                        <tr>
                                            <th><strong>NOM MATERIEL</strong></th>
                                        </tr>
                                    </thead>
                                    <tbody>             
                                        <%
                                                if (stylemateriels != null) {
                                                    for (int i = 0; i < stylemateriels.length; i++) {%>
                                        <tr>

                                            <td><strong><%= stylemateriels[i].getMateriel().getNom()%></strong></td>

                                        </tr>
                                        <%    }
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                                    <a href="ServletListeStyle"><button class="btn btn-primary">Retour</button></a>
                        </div>
                    </div> <!-- simple table -->
                </div> <!-- end section -->
            </div> <!-- .col-12 -->
        </div> <!-- .row -->
    </div> <!-- .container-fluid -->

</main> <!-- main -->




<%@ include file="footer.jsp" %>
