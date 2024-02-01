/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Chan Kenny
 */
public class Mere {

    String idmere;
    String description;
    Style idstyle;
    Taille idtaille;
    Categorie idcategorie;
    Float pourcentage; 
    
//    new
    
    Float reste;
    
    public Mere() {

    }

    public Mere(String idmere, Float reste) {
        this.idmere = idmere;
        this.reste = reste;
    }

    public Mere(String idmere, Style idstyle, Taille idtaille, Categorie idcategorie, Float reste) {
        this.idmere = idmere;
        this.idstyle = idstyle;
        this.idtaille = idtaille;
        this.idcategorie = idcategorie;
        this.reste = reste;
    }
    
    

    
    public Mere(String idmere, Style idstyle, Taille idtaille, Categorie idcategorie) {
        this.idmere = idmere;
        this.idstyle = idstyle;
        this.idtaille = idtaille;
        this.idcategorie = idcategorie;
    }

    public Mere(Style idstyle, Taille idtaille, Categorie idcategorie) {
        this.idstyle = idstyle;
        this.idtaille = idtaille;
        this.idcategorie = idcategorie;
    }
    
    public  Mere verifyStock_materiel(String id_mere, Connection connection) {
        String request = "SELECT COALESCE(SUM(quantite_entree), 0) - COALESCE(SUM(quantite_sortie), 0) AS reste,"
                + " ? AS id_mere ,taille_nom,style_nom,categorie_nom FROM v_fabrication_mouvement  WHERE id_mere = ? OR id_mere IS NULL group by taille_nom,style_nom,categorie_nom";
        boolean isOpen = false;
        ArrayList<Mere> Materiel_liste = new ArrayList<>();

        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }

            PreparedStatement statement = connection.prepareStatement(request);
            statement.setString(1, id_mere);
            statement.setString(2, id_mere);
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();

            
            while (resultSet.next()) {
                        
                Materiel_liste.add(new Mere(resultSet.getString("id_mere"), 
                        new Style(resultSet.getString("style_nom")),
                        new Taille(resultSet.getString("taille_nom")),
                        new Categorie(resultSet.getString("categorie_nom")),
                        resultSet.getFloat("reste")));
                       System.out.println(resultSet.getString("id_mere") + "  " +resultSet.getFloat("reste"));

            }
            System.out.println("mat liste: " + Materiel_liste.size());

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (!isOpen) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        if (Materiel_liste != null && !Materiel_liste.isEmpty()) {
            return Materiel_liste.get(0);
        }else if(Materiel_liste.size() == 0){
            return null;
        }else{
            return null;
        }
    }
    
    
    public static void insertMere(Mere mere, Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO mere (idstyle,idtaille,idcategorie) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, mere.getIdstyle().getIdStyle());
            statement.setString(2, mere.getIdtaille().getIdTaille());
            statement.setString(3, mere.getIdcategorie().getIdCategorie());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            //connection.rollback();
            e.printStackTrace();
        } finally {
            if (IsOpen == false) {
                connection.close();
            }
        }
    }

    
    public void insert_pourcentage_vente(Connection connection) throws Exception {
        boolean isOpen = false;
        String generatedId = null;

        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            isOpen = true;
        }

        try {
            String sql = "INSERT INTO pourcentage_vente (id_mere, pourcentage, date_pourcentage) VALUES (?, ?, now())";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getIdmere());
            statement.setFloat(2, this.getPourcentage());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }

    }

    
    public String insertMere(Connection connection) throws Exception {
        boolean isOpen = false;
        String generatedId = null;

        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            isOpen = true;
        }

        try {
            String sql = "INSERT INTO mere (idstyle, idtaille, idcategorie) VALUES (?, ?, ?) RETURNING idmere";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getIdstyle().getIdStyle());
            statement.setString(2, this.getIdtaille().getIdTaille());
            statement.setString(3, this.getIdcategorie().getIdCategorie());

            // Execute the update
            ResultSet resultSet = statement.executeQuery();

            // Retrieve the generated id
            if (resultSet.next()) {
                generatedId = resultSet.getString("idmere");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }

        return generatedId;
    }

    public static Mere[] meuble(Connection connection) throws Exception {
        String sql = "";
        sql = "select * from meuble";
        boolean isOpen = false;
        ArrayList<Mere> meres_liste = null;
        Mere[] meres_tableau = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            meres_liste = new ArrayList();
            while (resultSet.next()) {
                Style style = new Style();
                style.setNom(resultSet.getString("style_nom"));
                style.setIdStyle(resultSet.getString("idstyle"));
                Taille taille = new Taille();
                taille.setNom(resultSet.getString("taille_nom"));
                taille.setIdTaille(resultSet.getString("idtaille"));
                Categorie categorie = new Categorie();
                categorie.setNom(resultSet.getString("categorie_nom"));
                categorie.setIdCategorie(resultSet.getString("idcategorie"));
                meres_liste.add(new Mere(
                        resultSet.getString("idmere"),
                        style,
                        taille,
                        categorie
                ));
            }
            meres_tableau = new Mere[meres_liste.size()];
            for (int i = 0; i < meres_liste.size(); i++) {
                meres_tableau[i] = meres_liste.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return meres_tableau;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getIdmere() {
        return idmere;
    }

    public void setIdmere(String idmere) {
        this.idmere = idmere;
    }

    public Style getIdstyle() {
        return idstyle;
    }

    public void setIdstyle(Style idstyle) {
        this.idstyle = idstyle;
    }

    public Taille getIdtaille() {
        return idtaille;
    }

    public void setIdtaille(Taille idtaille) {
        this.idtaille = idtaille;
    }

    public Categorie getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Categorie idcategorie) {
        this.idcategorie = idcategorie;
    }

    public Float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Float pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Float getReste() {
        return reste;
    }

    public void setReste(Float reste) {
        this.reste = reste;
    }

    
}
