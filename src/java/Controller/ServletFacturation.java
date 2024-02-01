/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Client;
import Model.ConnectionPs;
import Model.Cout;
import Model.Facture;
import Model.Facture_remise;
import Model.Mere;
import Model.Mouvement_fabrication;
import Model.Remise;
import Model.Vente;
import Model.Vente_detail;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.ir.BreakNode;

/**
 *
 * @author user
 */
public class ServletFacturation extends HttpServlet {

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
        Vector<Client> liste_c = new Vector();
        Client[] clients = null;
        Remise remise = new Remise();
        Vector<Remise> liste_r = new Vector();
        Remise[] remises = null;
        try {
            liste_c = client.select(null);
            liste_r = remise.select(null);
            clients = liste_c.toArray(new Client[liste_c.size()]);
            remises = liste_r.toArray(new Remise[liste_r.size()]);
            Mere[] meubles = Mere.meuble(null);
            request.setAttribute("meres", meubles);
            request.setAttribute("clients", clients);
            request.setAttribute("remises", remises);
            RequestDispatcher dispatcher = null;
            dispatcher = request.getRequestDispatcher("formFacture.jsp");
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
        float montant = 0;
        String montant_paye = request.getParameter("montant_paye");
        String id_vente = null;
        String d = request.getParameter("date");
        Date date = null;
        if (!d.equalsIgnoreCase("")) {
            date = Date.valueOf(d);
        } else {
            date = new java.sql.Date(System.currentTimeMillis());
        }
        PrintWriter out = response.getWriter();
        if (request.getParameter("id_facture") != null) {
            Facture facture = new Facture();
            facture.setId_facture(request.getParameter("id_facture"));
            Facture details = null;
            try {
                Vector<Facture> fact = facture.select(null);
                details = fact.toArray(new Facture[fact.size()])[0];
                montant = details.getMontant_restant();
                id_vente = details.getId_vente();
                if (date.before(details.getDate_paiement())) {
                    out.print("Date invalide");
                    return;
                }
                if (montant == 0) {
                    out.print("Tout payer");
                    return;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        try {
            Facture facturer = new Facture();
            facturer.setId_vente(id_vente);
            facturer.setDate_paiement(date);
            float money_ou_reste = Float.valueOf(montant_paye) - montant;
            System.out.println(money_ou_reste + " MONEY OU RESTE");
            if (money_ou_reste == 0) {
                facturer.setMontant_paye(montant_paye);
                facturer.setMontant_restant(Float.valueOf(0));
                facturer.insert(null);
                RequestDispatcher dispatcher = null;
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } else if (money_ou_reste > 0) {
                facturer.setMontant_paye(montant);
                facturer.setMontant_restant(Float.valueOf(0));
                facturer.insert(null);
                out.print("Monnaie : " + money_ou_reste + " AR");
            } else {
                facturer.setMontant_paye(montant_paye);
                facturer.setMontant_restant(money_ou_reste * -1);
                facturer.insert(null);
                RequestDispatcher dispatcher = null;
                dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        } else {
            String client = request.getParameter("client");
            Connection connection = ConnectionPs.connexionPostgreSQL();
            String[] remise = request.getParameterValues("remise[]");
            String[] uniqueRemise = null;

            float total_remise = 0;
            try {

                /*--------------------------REMISE--------------------------------*/
                if (remise != null) {
                    HashSet<String> uniqueSet = new HashSet<>(Arrays.asList(remise));
                    uniqueRemise = uniqueSet.toArray(new String[0]);
                    for (int i = 0; i < uniqueRemise.length; i++) {
                        Remise rems = new Remise();
                        rems.setId_remise(uniqueRemise[i]);
                        Vector<Remise> vect = rems.select(null);
                        Remise r = vect.toArray(new Remise[vect.size()])[0];
                        float remise_ = r.getMontant();
                        total_remise = total_remise + remise_;
                    }
                }
                /*----------------------------REMISE------------------------------*/
                Mere[] meubles = Mere.meuble(null);
                Vector<Mere> meubles_insuffisant = new Vector<>();
                String message = "";
                float montant_sans_remise = 0;
                for (int i = 0; i < meubles.length; i++) {
                    if (!request.getParameter(meubles[i].getIdmere()).equalsIgnoreCase("")) {

                        Cout cout = new Cout();
                        float prix_vente = cout.prix_vente(meubles[i].getIdstyle().getIdStyle(), meubles[i].getIdtaille().getIdTaille(), connection);
                        System.out.println(prix_vente);
                        float quantites = Float.valueOf(request.getParameter(meubles[i].getIdmere()));
                        Mere verif_mere = new Mere();
                        verif_mere = verif_mere.verifyStock_materiel(meubles[i].getIdmere(), connection);
//                        System.out.println(verif_mere.getReste());
                        if (verif_mere != null) {
                            float insf = verif_mere.getReste() - quantites ;
                            if (insf < 0) {
                                verif_mere.setReste(insf * -1);
                                meubles_insuffisant.add(verif_mere);
                            }
                            montant_sans_remise = montant_sans_remise + (prix_vente * quantites);
                        }else{
                            message = "formule pas encore etablie";
                        }
                        
                    }
                }
                System.out.println("meubles_insuffisant " + meubles_insuffisant.size());
                if (meubles_insuffisant.size() != 0) {
                    out.print("Meuble insuffisanttt .....");
                    for (int i = 0; i < meubles_insuffisant.size(); i++) {
                        out.println("meuble: " + meubles_insuffisant.get(i).getIdcategorie().getNom()+" "+meubles_insuffisant.get(i).getIdstyle().getNom()+" ("+meubles_insuffisant.get(i).getIdtaille().getNom() + ") manque " + meubles_insuffisant.get(i).getReste());
                        if (meubles_insuffisant.get(i) == null) {
                            out.print("formule pas encore etablie");
                        }
                    }
                } else {
                    montant = montant_sans_remise - total_remise;
                    Vente vente = new Vente();
                    vente.setId_client(client);
                    vente.setMontant_sans_remise(montant_sans_remise);
                    vente.setMontant(montant);
                    vente.setDate_vente(date);
                    vente.insert(null);
                    id_vente = Vente.id_vente(connection);
                    if (remise != null) {
                        for (int i = 0; i < uniqueRemise.length; i++) {
                            Facture_remise fact = new Facture_remise();
                            fact.setId_remise(uniqueRemise[i]);
                            fact.setId_vente(id_vente);
                            fact.insert(null);
                        }
                    }
                    for (int i = 0; i < meubles.length; i++) {
                        if (!request.getParameter(meubles[i].getIdmere()).equalsIgnoreCase("")) {
                            Vente_detail vente_detail = new Vente_detail();
                            Cout cout = new Cout();
                            float prix_vente = cout.prix_vente(meubles[i].getIdstyle().getIdStyle(), meubles[i].getIdtaille().getIdTaille(), connection);
                            System.out.println(prix_vente);
                            float quantites = Float.valueOf(request.getParameter(meubles[i].getIdmere()));
                            Mouvement_fabrication mf = new Mouvement_fabrication();
                            mf.setId_mere(meubles[i].getIdmere());
                            mf.setQuantite_entree(Float.valueOf("0"));
                            mf.setQuantite_sortie(quantites);
                            mf.setDate_fabrication_mouvement(date);
                            mf.insert(null);
                            vente_detail.setId_mere(meubles[i].getIdmere());
                            vente_detail.setId_vente(id_vente);
                            vente_detail.setP_unitaire(prix_vente);
                            vente_detail.setQuantite(quantites);
                            vente_detail.insert(null);
                        }
                    }

                    try {
                        Facture facture = new Facture();
                        facture.setId_vente(id_vente);
                        facture.setDate_paiement(date);
                        float money_ou_reste = Float.valueOf(montant_paye) - montant;
                        System.out.println(money_ou_reste + " MONEY OU RESTE");
                        if (money_ou_reste == 0) {
                            facture.setMontant_paye(montant_paye);
                            facture.setMontant_restant(Float.valueOf(0));
                            facture.insert(null);
                            RequestDispatcher dispatcher = null;
                            dispatcher = request.getRequestDispatcher("index.jsp");
                            dispatcher.forward(request, response);
                        } else if (money_ou_reste > 0) {
                            facture.setMontant_paye(montant);
                            facture.setMontant_restant(Float.valueOf(0));
                            facture.insert(null);
                            out.print("Monnaie : " + money_ou_reste + " AR");
                        } else {
                            facture.setMontant_paye(montant_paye);
                            facture.setMontant_restant(money_ou_reste * -1);
                            facture.insert(null);
                            RequestDispatcher dispatcher = null;
                            dispatcher = request.getRequestDispatcher("index.jsp");
                            dispatcher.forward(request, response);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
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
    }
}
