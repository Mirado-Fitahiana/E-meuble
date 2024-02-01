<%-- 
    Document   : formule.jsp
    Created on : 19 déc. 2023, 15:43:04
    Author     : 1905
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@page import="Model.*" %>
<%  
    StyleMateriel[] stylemateriels = (StyleMateriel[]) request.getAttribute("stylemateriels");
    String idmere = (String)request.getAttribute("idmere");
    String idStyle = (String)request.getAttribute("idStyle");
%>

<main role="main" class="main-content">
    <form action="Servletformule" method="post">
        <div class="container-fluid">
            <div class="row justify-content-center">
                <div class="col-12">
                    <div class="row my-4">
                        <!-- Small table -->
                        <div class="col-md-12">
                            <div class="card shadow">
                                <div class="card-body">
                                    <!-- table -->
                                    <table class="table datatables" id="dataTable-1">
                                        <thead>
                                            <tr>
                                                <th><strong>Materiels</strong></th>
                                                <th><strong>Quantite</strong></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (int i = 0; i < stylemateriels.length; i++) {%>
                                            <tr>

                                                <td><strong><%=stylemateriels[i].getMateriel().getNom()%></strong></td>
                                                <td>
                                                    <input type="text" class="form-control" id="<%=stylemateriels[i].getIdStyleMateriel()%>" name="<%=stylemateriels[i].getIdStyleMateriel()%>" placeholder="Quantité">
                                                </td>
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
                <input type="hidden" class="form-control" id="idmere" name="idmere" value="<%=idmere%>">
                <input type="hidden" class="form-control" id="idStyle" name="idStyle" value="<%=idStyle%>">

                <button type="submit"  class="btn btn-primary">Valider</button>
            </div> <!-- .row -->
        </div> <!-- .container-fluid -->
    </form> 
    <a href="ServletListeStyle"><button class="btn btn-primary">Retour</button></a>
</main> <!-- main -->
<%@ include file="footer.jsp" %>