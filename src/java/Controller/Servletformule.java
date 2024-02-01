/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Categorie;
import Model.ConnectionPs;
import Model.Formule;
import Model.Materiel;
import Model.Mere;
import Model.Style;
import Model.StyleMateriel;
import Model.Taille;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chan Kenny
 */
public class Servletformule extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStyle = request.getParameter("style");
        Connection connection = ConnectionPs.connexionPostgreSQL();
        try {
            request.setAttribute("idstyle", idStyle);
            request.setAttribute("nom", request.getParameter("nom"));

            StyleMateriel[] stylemateriels = StyleMateriel.styleMateriels(idStyle, connection);
            request.setAttribute("stylemateriels", stylemateriels);

            Categorie[] categories = Categorie.categorie(connection);
            request.setAttribute("categories", categories);
            Taille[] tailles = Taille.Taille(connection);
            request.setAttribute("tailles", tailles);

            RequestDispatcher dispatcher = null;
            dispatcher = request.getRequestDispatcher("formule.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("type") != null) {
            String id = request.getParameter("id");
            String[] div = id.split("-");
            String idStyle = div[0];
            String idmere = div[1];
            System.out.println(div[0] + " div[0]");
            request.setAttribute("idmere", idmere);
            request.setAttribute("idStyle", idStyle);
            Connection connection = ConnectionPs.connexionPostgreSQL();
            StyleMateriel[] stylemateriels = null;
            try {
                stylemateriels = StyleMateriel.styleMateriels(idStyle, connection);
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            request.setAttribute("stylemateriels", stylemateriels);
            RequestDispatcher dispatcher = request.getRequestDispatcher("compositionformule.jsp");
            dispatcher.forward(request, response);
        } else if (request.getParameter("idmere") != null) {
            String id_mere = request.getParameter("idmere");
            String idStyle = request.getParameter("idStyle");
            Connection connection = ConnectionPs.connexionPostgreSQL();
            try {
                StyleMateriel[] stylemateriels = StyleMateriel.styleMateriels(idStyle, connection);
                for (int i = 0; i < stylemateriels.length; i++) {
                    Formule formule = new Formule();
                    if (request.getParameter(stylemateriels[i].getIdStyleMateriel()) != null) {
                        Materiel materiel = new Materiel();
                        materiel.setIdMateriel(stylemateriels[i].getMateriel().getIdMateriel());
                        formule.setIdmere(id_mere);
                        formule.setMateriel(materiel);
                        formule.setQuantite(request.getParameter(stylemateriels[i].getIdStyleMateriel()));
                        formule.insertFormule(connection);
                    }
                }
                RequestDispatcher dispatcher = null;
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            PrintWriter out = response.getWriter();
            String idStyle = request.getParameter("style");
            String idCategorie = request.getParameter("categorie");
            String idTaille = request.getParameter("taille");
            String description = request.getParameter("description");

            Connection connection = ConnectionPs.connexionPostgreSQL();
            try {
                Categorie categorie = new Categorie();
                categorie.setIdCategorie(idCategorie);
                Taille taille = new Taille();
                taille.setIdTaille(idTaille);
                Style style = new Style();
                style.setIdStyle(idStyle);
                Mere mere = new Mere();
                mere.setIdcategorie(categorie);
                mere.setIdstyle(style);
                mere.setIdtaille(taille);
                mere.setDescription(description);
                String id_mere = mere.insertMere(connection);
                connection.commit();
                connection.close();
                RequestDispatcher dispatcher = null;
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
