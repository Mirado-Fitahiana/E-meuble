<%-- 
    Document   : Graphe
    Created on : 25 janv. 2024, 14:24:04
    Author     : Chan Kenny
--%>
<head>
    <meta charset="UTF-8">
    <title>Graphique en Tarte</title>
    <!-- Incluez Chart.js depuis un CDN -->
    <link rel="stylesheet" href="assets/css/question.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        /* Styles pour centrer le conteneur au milieu de la page */
        body {
            text-align: center; /* Centre le contenu horizontalement */
        }
        .graph-container {
            display: inline-block; /* Définit le conteneur comme élément en ligne */
            text-align: left; /* Réinitialise le texte à gauche à l'intérieur du conteneur */
        }
    </style>
</head>
<%@page import="Model.*" %>
<%@ include file="header.jsp" %>
<%
    V_detail_client_vente[] v = (V_detail_client_vente[]) request.getAttribute("details");
%>

<main role="main" class="main-content">
    <div class="card-body ">
        <h1>Repartition homme et femme</h1>
        <!-- Ajoutez la classe "graph-container" au conteneur -->
        <div class="graph-container" style="width: 500px; height: 500px;">
            <canvas id="myPieChart"></canvas>
        </div>

        <script>
            // Créez un graphique en tarte
            //mettez la valeur des pourcentage
            var homme = 0;
            var femme = 0;
            <% for(int i=0; i < v.length; i++){ 
                if(v[i].getId_genre().equalsIgnoreCase("genre1")){ %>
                    homme = <%=v[i].getSomme_quantite()%>;
                <%}else{%>
                femme =  <%=v[i].getSomme_quantite()%>;
                <%} %>
            <%}%>
            var ctx = document.getElementById("myPieChart").getContext("2d");
            var myPieChart = new Chart(ctx, {
                type: "pie", // Type de graphique : "pie" pour un graphique en tarte
                data: {
                    labels: ["HOMME", "FEMME"], // Étiquettes pour les parties du graphique
                    datasets: [{
                            data: [homme, femme], // Les données sous forme de tableau
                            backgroundColor: ["#FF5733", "#337DFF"] // Couleurs des parties du graphique
                        }]
                },
                options: {
                }
            });
        </script>

    <script src="assets/js/piegraph.js"></script>
    </div>
            <div class="row my-4">
                    <!-- Small table -->
                    <div class="col-md-12">
                        <div class="card shadow">
                            <div class="card-body">
                                <!-- table -->
                                <table class="table datatables" id="dataTable-1">
                                    <thead>
                                        <tr>
                                            <th><strong>Sexe</strong></th>
                                            <th><strong>Quantité</strong></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                                for (int i = 0; i < v.length; i++) {%>
                                        <tr>
                                            <td>
                                                    <%if(v[i].getId_genre().equalsIgnoreCase("genre1")){%>
                                                <strong>
                                                    Homme
                                                </strong>
                                                <%} else{%>
                                                Femme
                                                <% }%>
                                            <td>
                                                <%=v[i].getSomme_quantite()%>
                                            </td>

                                        </tr>
                                        <%    }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div> <!-- simple table -->
                </div>
</main> <!-- main -->
<%@ include file="footer.jsp" %>
