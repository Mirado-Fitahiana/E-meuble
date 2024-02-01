<%-- 
    Document   : formMateriel
    Created on : 12 déc. 2023, 15:54:38
    Author     : Chan Kenny
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@page import="Model.*" %>
<%
    Materiel[] materiels = (Materiel[]) request.getAttribute("Materiel");
    Fournisseur[] fournisseurs = (Fournisseur[]) request.getAttribute("Fournisseur");
%>
<main role="main" class="main-content">

    <div class="card shadow mb-4">
        <div class="card-header">
            <strong class="card-title">Formulaire Materiel</strong>
        </div>
        <div class="col-md-12">
            <button class="btn btn-outline-success"><a href="#mat">Insertion Matiere </a></button>
            <button class="btn btn-outline-success"><a href="#dataTable-1">Liste </a></button>
        </div>
        <div class="card-body">
            
            <form method="post" action="ServletMateriel">
                <div class="form-group row">
                    <label for="inputEmail3" class="col-sm-3 col-form-label">Nom du materiel</label>
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
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
    <hr>
    <div class="card-body">
        <div class="col-md-12">
            <button class="btn btn-outline-success"><a href="#mat">Insertion Matiere </a></button>
            <button class="btn btn-outline-success"><a href="#dataTable-1">Liste </a></button>
        </div>
        <div class="card shadow mb-4">
            <div class="card-header" id="mat">
                <strong class="card-title">Formulaire prix de matériels</strong>
            </div>
            <div class="card-body">
                <form method="post" action="ServletPrixMateriel">
                    <div class="form-group row">
                        <label for="inputEmail3" class="col-sm-3 col-form-label">Materiel</label>
                        <div class="col-sm-3">
                            <select name="materiel"  id="materiel" class="form-control">
                                <% for (int i = 0; i < materiels.length; i++) {%>
                                <option value="<%=materiels[i].getIdMateriel()%>">
                                    <%=materiels[i].getNom()%>
                                </option>
                                <%} %>
                            </select>
                        </div>
                        <label for="inputEmail3" class="col-sm-3 col-form-label">Fournisseur</label>
                        <div class="col-sm-3">
                            <select name="fournissuer"  id="fournissuer" class="form-control">
                                <% for (int i = 0; i < fournisseurs.length; i++) {%>
                                <option value="<%= fournisseurs[i].getId_fournisseur()%>">
                                    <%= fournisseurs[i].getNom() %>
                                </option>
                                <%}%>
                            </select>
                        </div>
                        <label for="inputEmail3" class="col-sm-3 col-form-label">Quantite</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="quantite" name="quantite" placeholder="0">
                        </div>   
                        <label for="inputEmail3" class="col-sm-3 col-form-label">Prix</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="prix" name="prix" placeholder="0">
                        </div>
                    </div>
                    <div class="form-group mb-2">
                        <button type="submit" class="btn btn-primary">Valider</button>
                    </div>
                </form>
            </div>
        </div>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>

        <!-- table -->
        <div class="col-md-12">
            <button class="btn btn-outline-success"><a href="#mat">Insertion Matiere </a></button>
            <button class="btn btn-outline-success"><a href="#dataTable-1">Liste </a></button>
        </div>
        <table class="table datatables" id="dataTable-1">
            <thead>
                <tr>
                    <th><strong>NOM</strong></th>
                    <th><strong>VOIR</strong></th>
                </tr>
            </thead>
            <tbody>             
                <% for (int i = 0; i < materiels.length; i++) {%>
                <tr>
                    <td><strong><%=materiels[i].getNom()%></strong></td>
                    <td><a href="ServletAfficheFinal?materiel=<%=materiels[i].getIdMateriel()%>"><button class="btn mb-2 btn-outline-success">Details</button></a></td>
            <div class="modal fade" id="defaultModal" tabindex="-1" role="dialog" aria-labelledby="defaultModalLabel" style="display: none;" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="defaultModalLabel">Validation</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">Voulez vous supprimer le materiel <%=materiels[i].getNom()%></div>
                        <a href="ServletMateriel?materiel=<%=materiels[i].getIdMateriel()%>"><button type="button" class="btn mb-2 btn-primary">OUI</button></a>
                        <a><button type="button" class="btn mb-2 btn-secondary" data-dismiss="modal">NON</button></a>
                    </div>
                </div>
            </div>
            </tr>
            <%    }
            %>
            </tbody>
        </table>
    </div>
    <hr>

    </div>
</main> <!-- main -->
<%@ include file="footer.jsp" %>
