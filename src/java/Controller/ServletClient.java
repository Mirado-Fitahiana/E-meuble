/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Client;
import Model.ConnectionPs;
import Model.Genre;
import Model.Style;
import static Model.Style.style;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ServletClient extends HttpServlet {

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
        Client client = new Client();
        Genre genre = new Genre();
        Vector<Client> liste_c = new Vector();
        Vector<Genre> liste_g = new Vector();
        Client[] clients = null;
        Genre[] genres = null;
        try {
            liste_g = genre.select(null);
            liste_c = client.select(null);
            clients = liste_c.toArray(new Client[liste_c.size()]);
            genres = liste_g.toArray(new Genre[liste_g.size()]);

            request.setAttribute("genres", genres);
            request.setAttribute("clients", clients);
            RequestDispatcher dispatcher = null;
            dispatcher = request.getRequestDispatcher("formClient.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
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
        String nom = request.getParameter("nom");
        String adresse = request.getParameter("adresse");
        String contact = request.getParameter("contact");
        String genre = request.getParameter("genre");
        Date date = Date.valueOf(request.getParameter("date"));
        Client client = new Client(nom, adresse, contact, date, genre);

        Connection connection = ConnectionPs.connexionPostgreSQL();

        try {
            client.insert(null);
        } catch (Exception ex) {
            Logger.getLogger(ServletCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

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
