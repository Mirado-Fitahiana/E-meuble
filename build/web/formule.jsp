<%-- 
    Document   : formule.jsp
    Created on : 19 déc. 2023, 15:43:04
    Author     : 1905
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@page import="Model.*" %>
<% Categorie[] categories = (Categorie[]) request.getAttribute("categories");
    String nomStyle = (String) request.getAttribute("nom");
    Taille[] tailles = (Taille[]) request.getAttribute("tailles");
    StyleMateriel[] stylemateriels = (StyleMateriel[]) request.getAttribute("stylemateriels");
%>

<main role="main" class="main-content">
    <form action="Servletformule" method="post">
        <div class="container-fluid">
        <div class="card-header">
            <h2><strong class="card-title">Style <%=nomStyle%></strong></h2>
        </div>
            <div class="row justify-content-center">
                <div class="col-12">
                    <div class="form-group row">
                        <label for="inputEmail3" class="col-sm-3 col-form-label">Categorie de meuble</label>
                        <div class="col-sm-9">
                            <select name="categorie"  id="categorie" class="form-control">
                                <% for (int i = 0; i < categories.length; i++) {%>
                                <option value="<%=categories[i].getIdCategorie()%>">
                                    <%=categories[i].getNom()%>
                                </option>
                                <%} %>
                            </select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="inputEmail3" class="col-sm-3 col-form-label">Taille</label>
                        <div class="col-sm-9">
                            <select name="taille"  id="taille" class="form-control">
                                <% for (int i = 0; i < tailles.length; i++) {%>
                                <option value="<%=tailles[i].getIdTaille()%>">
                                    <%=tailles[i].getNom()%>
                                </option>
                                <%}%>
                            </select>
                        </div>
                    </div>
                <input type="hidden" class="form-control" id="style" name="style" value="<%=request.getAttribute("idstyle") %>" placeholder="quantite">
                <button type="submit"  class="btn btn-primary">Valider</button>
            </div> <!-- .row -->
        </div> <!-- .container-fluid -->
    </form> 
</main> <!-- main -->
<%@ include file="footer.jsp" %>