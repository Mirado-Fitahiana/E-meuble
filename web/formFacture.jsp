<%-- 
    Document   : formFacture
    Created on : 25 janv. 2024, 06:52:21
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %>
<%@ include file="header.jsp" %>

<% Client[] clients = (Client[]) request.getAttribute("clients");
Mere[] meres = (Mere[]) request.getAttribute("meres");
Remise[] remises = (Remise[]) request.getAttribute("remises");
%>
<main role="main" class="main-content">
    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">VENTE</strong>
        </div>
        <div class="card-body">
            <form method="post" action="ServletFacturation">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Client</label>
                    <div class="col-sm-9">
                        <select name="client"  id="client" class="form-control">
                            <% for (int i = 0; i < clients.length; i++) {%>
                            <option value="<%=clients[i].getId_client()%>">
                                <%= clients[i].getNom()%>
                            </option>
                            <%}%>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-2 col-form-label">Date de vente</label>
                    <div class="col-sm-3">
                        <input class="form-control" type="date" name="date" id="date">
                    </div>
                </div>
                <h2 class="mb-2 page-title">LISTE DES MEUBLES</h2>
                <div class="row my-4">
                    <!-- Small table -->
                    <div class="col-md-12">
                        <div class="card shadow">
                            <div class="card-body">
                                <!-- table -->
                                <table class="table datatables" id="dataTable-1">
                                    <thead>
                                        <tr>
                                            <th><strong>Meuble</strong></th>
                                            <th><strong>Quantité</strong></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                                for (int i = 0; i < meres.length; i++) {%>
                                        <tr>
                                            <td>
                                                <strong>
                                                    <%= meres[i].getIdcategorie().getNom() + "-" + meres[i].getIdstyle().getNom() + "(" + meres[i].getIdtaille().getNom() + ")" %>
                                                </strong>
                                            <td>
                                                <input type="text" class="form-control" id="<%=meres[i].getIdmere() %>" name="<%=meres[i].getIdmere() %>" placeholder="Quantité">
                                            </td>

                                        </tr>
                                        <%    }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                            <div class="card-body">
                                <p class="mb-2"><strong>Remises</strong></p>
                                <%
                                for (int i = 0; i < remises.length ; i++){ %>
                                 <div class="custom-control custom-checkbox">
                                     <input type="checkbox" name="remise[]"  value="<%=remises[i].getId_remise() %>" class="custom-control-input" id="customCheck<%=i+1%>">
                                    <label class="custom-control-label" for="customCheck<%=i+1%>"><%=remises[i].getNom() %></label>
                                </div>
                                <% }
                                %>
                            </div>
                            
                        </div>
                            <br>
                            <br>
                            <br>
                            <strong class="card-title">PAIMENT</strong>
                            <hr>
                            <div class="form-group row">
                                <label for="inputEmail3" class="col-sm-3 col-form-label">Montant payé</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="montant_paye" name="montant_paye" value="0" placeholder="Montant">
                                </div>
                            </div>
                            <div class="form-group mb-2">
                                <button type="submit" class="btn btn-primary">Ajouter au panier</button>
                            </div>
                    </div>
                </div> <!-- simple table -->
        </div> 
        
        </form>
    </div>
</div>
</main>
<%@ include file="footer.jsp" %>
