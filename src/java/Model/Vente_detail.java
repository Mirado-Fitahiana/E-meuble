/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import MyDAO.DAO.DAO;
import MyDAO.TableMapping.Column;
import MyDAO.TableMapping.TableInfo;

/**
 *
 * @author P15A-Mirado-159
 */
@TableInfo(name = "vente_detail" ,user = "postgres",pass = "root",database = "meuble")
public class Vente_detail extends DAO{
    @Column(name = "id_vente_detail",isPrimary = true)
    private String id_vente_detail;
    @Column(name = "id_vente")
    private String id_vente;
    @Column(name = "id_mere")
    private String id_mere;
    @Column(name = "quantite")
    private Float quantite;
    @Column(name = "prix_unitaire")
    private Float p_unitaire;

    public Vente_detail(String id_vente_detail, String id_vente, String id_mere, Float quantite, Float p_unitaire) {
        this.id_vente_detail = id_vente_detail;
        this.id_vente = id_vente;
        this.id_mere = id_mere;
        this.quantite = quantite;
        this.p_unitaire = p_unitaire;
    }

    public Vente_detail() {
    }

    
    public String getId_vente_detail() {
        return id_vente_detail;
    }

    public void setId_vente_detail(String id_vente_detail) {
        this.id_vente_detail = id_vente_detail;
    }

    public String getId_vente() {
        return id_vente;
    }

    public void setId_vente(String id_vente) {
        this.id_vente = id_vente;
    }

    public String getId_mere() {
        return id_mere;
    }

    public void setId_mere(String id_mere) {
        this.id_mere = id_mere;
    }

    public Float getQuantite() {
        return quantite;
    }

    public void setQuantite(Float quantite) {
        this.quantite = quantite;
    }

    public Float getP_unitaire() {
        return p_unitaire;
    }

    public void setP_unitaire(Float p_unitaire) {
        this.p_unitaire = p_unitaire;
    }
    
    
}
