package fr.saejava;


public class Utilisateur {
    protected int idUtil;
    protected String nomUtil;
    protected String prenomUtil;
    protected String pwd;
    
    
    public Utilisateur(int idUtil, String nomUtil, String prenomUtil, String pwd) {
        this.nomUtil = nomUtil;
        this.prenomUtil = prenomUtil;
        this.pwd = pwd;
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