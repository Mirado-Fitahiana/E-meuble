/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import MyDAO.DAO.DAO;
import MyDAO.TableMapping.Column;
import MyDAO.TableMapping.TableInfo;
import java.sql.Date;

/**
 *
 * @author P15A-Mirado-159
 */
@TableInfo(name = "fabrication_mouvement",user = "postgres",pass = "root",database = "meuble")
public class Mouvement_fabrication extends DAO{
    @Column(isPrimary = true, name = "id_fabrication_mouvement")
    private String id_fabrication_mouvement;
    
    @Column (name = "id_mere")
    private String id_mere;
    
    @Column (name = "quantite_entree")
    private Float quantite_entree;
    
     @Column (name = "quantite_sortie")
    private Float quantite_sortie;
    
    @Column (name = "date_fabrication_mouvement")
    private Date date_fabrication_mouvement;

    public Mouvement_fabrication() {
    }

    public Mouvement_fabrication(String id_fabrication_mouvement, String id_mere, Float quantite_entree, Float quantite_sortie, Date date_fabrication_mouvement) {
        this.id_fabrication_mouvement = id_fabrication_mouvement;
        this.id_mere = id_mere;
        this.quantite_entree = quantite_entree;
        this.quantite_sortie = quantite_sortie;
        this.date_fabrication_mouvement = date_fabrication_mouvement;
    }
    

    public String getId_fabrication_mouvement() {
        return id_fabrication_mouvement;
    }

    public void setId_fabrication_mouvement(String id_fabrication_mouvement) {
        this.id_fabrication_mouvement = id_fabrication_mouvement;
    }

    public String getId_mere() {
        return id_mere;
    }

    public void setId_mere(String id_mere) {
        this.id_mere = id_mere;
    }

    public Float getQuantite_entree() {
        return quantite_entree;
    }

    public void setQuantite_entree(Float quantite_entree) {
        this.quantite_entree = quantite_entree;
    }

    public Float getQuantite_sortie() {
        return quantite_sortie;
    }

    public void setQuantite_sortie(Float quantite_sortie) {
        this.quantite_sortie = quantite_sortie;
    }

    public Date getDate_fabrication_mouvement() {
        return date_fabrication_mouvement;
    }

    public void setDate_fabrication_mouvement(Date date_fabrication_mouvement) {
        this.date_fabrication_mouvement = date_fabrication_mouvement;
    }

    
    
    
    
}
