package fr.saejava;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Utilisateur {
    protected int idUtil;
    protected String nomUtil;
    protected String prenomUtil;
    protected String pwd;

    protected Catalogue catalogue;
    protected Set<Commande> commandes;
    
    
    public Utilisateur(int idUtil, String nomUtil, String prenomUtil, String pwd, Catalogue cat) {
        this.nomUtil = nomUtil;
        this.prenomUtil = prenomUtil;
        this.pwd = pwd;
        this.catalogue = cat;
        commandes = new HashSet<>();
    }


    public int getIdUtil() {
        return idUtil;
    }


    public void setIdUtil(int idUtil) {
        this.idUtil = idUtil;
    }


    public String getNomUtil() {
        return nomUtil;
    }


    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }


    public String getPrenomUtil() {
        return prenomUtil;
    }


    public void setPrenomUtil(String prenomUtil) {
        this.prenomUtil = prenomUtil;
    }


    public String getPwd() {
        return pwd;
    }


    public void setPwd(String pwd) {
        this.pwd = pwd;
    }



}