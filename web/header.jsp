<%-- 
    Document   : header
    Created on : 11 déc. 2023, 20:50:07
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="favicon.ico">
        <title>E-meuble</title>
        <!-- Simple bar CSS -->
        <link rel="stylesheet" href="css/simplebar.css">
        <!-- Fonts CSS -->
        <link href="https://fonts.googleapis.com/css2?family=Overpass:ital,wght@0,100;0,200;0,300;0,400;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
        <!-- Icons CSS -->
        <link rel="stylesheet" href="css/feather.css">
        <link rel="stylesheet" href="css/select2.css">
        <link rel="stylesheet" href="css/dropzone.css">
        <link rel="stylesheet" href="css/uppy.min.css">
        <link rel="stylesheet" href="css/jquery.steps.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">
        <link rel="stylesheet" href="css/quill.snow.css">
        <link rel="stylesheet" href="css/dataTables.bootstrap4.css">
        <!-- Date Range Picker CSS -->
        <link rel="stylesheet" href="css/daterangepicker.css">
        <!-- App CSS -->
        <link rel="stylesheet" href="css/app-light.css" id="lightTheme">
        <link rel="stylesheet" href="css/app-dark.css" id="darkTheme" disabled>
    </head>
    <body class="vertical  light  ">
        <div class="wrapper">
            <aside class="sidebar-left border-right bg-white shadow" id="leftSidebar" data-simplebar>
                <a href="#" class="btn collapseSidebar toggle-btn d-lg-none text-muted ml-2 mt-3" data-toggle="toggle">
                    <i class="fe fe-x"><span class="sr-only"></span></i>
                </a>
                <nav class="vertnav navbar navbar-light">
                    <ul class="navbar-nav flex-fill w-100 mb-2">
                        
                        <li class="nav-item">
                            <h1>E-Meuble</h1>
                            <hr>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pl-3" href="ServletMateriel">
                                <i class="fe fe-tool"></i><span class="ml-1 item-text">Insertion Materiaux</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pl-3" href="ServletStyle">
                                <i class="fe fe-pen-tool"></i><span class="ml-1 item-text">Insertion Styles</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pl-3" href="ServletFormStyleMateriel">
                                <i class="fe fe-book"></i><span class="ml-1 item-text">Insertion Materiel par style</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pl-3" href="ServletListeStyle">
                                <i class="fe fe-anchor"></i><span class="ml-1 item-text">Liste materiel par style</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pl-3" href="ServletTaille">
                                <i class="fe fe-cloud"></i><span class="ml-1 item-text">Insertion Taille</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pl-3" href="ServletProfil">
                                <i class="fe fe-user"></i><span class="ml-1 item-text"> Insertion Profil</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pl-3" href="InsertionFournisseur.jsp">
                                <i class="fe fe-truck"></i><span class="ml-1 item-text"> Insertion Fournisseur</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pl-3" href="ServletEmployer">
                                <i class="fe fe-users"></i><span class="ml-1 item-text"> Insertion Personnel</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pl-3" href="ServletPoste">
                                <i class="fe fe-coffee"></i><span class="ml-1 item-text"> Insertion Poste</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pl-3" href="FormulaireTarification.jsp">
                                <i class="fe fe-dollar-sign"></i>
                                <span class="ml-1 item-text">Frais</span>
                            </a>
                        </li>  
                        <li class="nav-item">
                            <a class="nav-link pl-3" href="ServletFabrication">
                                <i class="fe fe-airplay"></i>
                                <span class="ml-1 item-text">Meuble</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pl-3" href="ServletClient">
                                <i class="fe fe-coffee"></i><span class="ml-1 item-text"> Insertion Client</span></a>
                        </li>
                        
                        <li class="nav-item">
                            <a class="nav-link pl-3" href="ServletFacturation">
                                <i class="fe fe-coffee"></i><span class="ml-1 item-text"> Insertion Vente</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pl-3" href="ServletRemise">
                                <i class="fe fe-arrow-down"></i><span class="ml-1 item-text"> Insertion Remise</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pl-3" href="ServletListeFacture">
                                <i class="fe fe-list"></i><span class="ml-1 item-text"> Facture</span></a>
                        </li>
                    </ul>
                </nav>
            </aside>
