package fr.saejava;

import java.sql.*;

public class Client extends Utilisateur {
    
    private String adresseUtil;
    private int codePostal;
    private String villeUtil;
    private double monnaie;

    public Client(int idUtil, String nomUtil, String prenomUtil, String pwd,Catalogue cat ,String adresseUtil, int codePostal,
            String villeUtil, ConnexionMySQL laConnexion) {
        super(idUtil, nomUtil, prenomUtil, pwd, cat, laConnexion);
        this.adresseUtil = adresseUtil;
        this.codePostal = codePostal;
        this.villeUtil = villeUtil;

        this.laConnexion = laConnexion;
    }
    public String getAdresseUtil() {
        return adresseUtil;
    }
    public int getCodePostal() {
        return codePostal;
    }
    public String getVilleUtil() {
        return villeUtil;
    }
    public void setAdresseUtil(String adresseUtil) {
        this.adresseUtil = adresseUtil;
    }
    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }
    public void setVilleUtil(String villeUtil) {
        this.villeUtil = villeUtil;
    }

    public void ajouteMonnaie(double montant){
        this.monnaie += montant;
    }

    public void ajouteCommande(Commande com) throws Exception{
        if (monnaie - com.prixTotCommande() > 0){
            commandes.add(com);
            monnaie -= com.prixTotCommande();
            com.commander();
        } else {
            throw new Exception("prix de la commande trop élever");
        }
    }

    public void retireCommande(Commande com) throws IllegalArgumentException, Exception{
        this.monnaie += com.prixTotCommande();
        commandes.remove(com);
        com.renvoyer();
    }

}
