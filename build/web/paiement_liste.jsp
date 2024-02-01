<%-- 
    Document   : paiement_liste
    Created on : 28 janv. 2024, 10:33:31
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*" %>
<%@ include file="header.jsp" %>
<%
Facture[] liste_f_tab = (Facture[])request.getAttribute("facture");
V_detail_vente_client vente = (V_detail_vente_client)request.getAttribute("vente");    
%>
<style>
    .card-body {
        margin: 20px;
    }

    .table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    th, td {
        padding: 15px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
        background-color: #f2f2f2;
    }

    tbody tr:hover {
        background-color: #f5f5f5;
    }
</style>
<main role="main" id="main" class="main-content">

    <div class="card-header">
        <h6>FACTURE N° <%=vente.getDate_de_naissance().toString().replace("-", "")+"-"+vente.getId_vente().replace("vente", "") %></h6>
        <h6>MONTANT TOTAL : <%=vente.getMontant() %> AR</h6>
    </div>

    <div class="card-body">
        <table class="table">
            <thead>
                <tr>
                    <th><strong>Date</strong></th>
                    <th><strong>Payé</strong></th>
                    <th><strong>Restant</strong></th>
                </tr>
            </thead>
            <tbody>             
                <% for (int i = 0; i < liste_f_tab.length; i++) {
                %>
                <tr>
                    <td><%=liste_f_tab[i].getDate_paiement()%> </td>
                    <td><%=liste_f_tab[i].getMontant_paye()%> AR</td>
                    <td><%=liste_f_tab[i].getMontant_restant()%> AR</td>
                </tr>
                <%    }
                %>
            </tbody>
        </table>
    </div>
</main>
<%@ include file="footer.jsp" %>
