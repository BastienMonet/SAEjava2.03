package fr.saejava.modele;
import fr.saejava.modele.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.checkerframework.checker.units.qual.m;

public class Client extends Utilisateur {
    
    private String adresseUtil = "?";
    private String codePostal = "?";
    private String villeUtil = "?";
    private Double monnaie = 0.00;


    /**
     * Constructeur de la classe Client
     * @param laConnexion la connexion à la base de données
     */
    public Client(ConnexionMySQL laConnexion) {
        super(laConnexion);
    }

    /**
     * Constructeur de la classe Client avec les paramètres
     * @param idUtil l'identifiant de l'utilisateur
     * @param nomUtil le nom de l'utilisateur
     * @param prenomUtil le prénom de l'utilisateur
     * @param pwd le mot de passe de l'utilisateur
     * @param adresseUtil l'adresse de l'utilisateur
     * @param codePostal le code postal de l'utilisateur
     * @param villeUtil la ville de l'utilisateur
     * @param monnaie la monnaie du client
     */
    public Client(int idUtil, String nomUtil, String prenomUtil, String pwd, String adresseUtil, String codePostal,
            String villeUtil, Double monnaie) {
        super(idUtil, nomUtil, prenomUtil, pwd);
        this.adresseUtil = adresseUtil;
        this.codePostal = codePostal;
        this.villeUtil = villeUtil;
        this.monnaie = monnaie;
    }

    /**
     * Retourne l'adresse de l'utilisateur
     * @return l'adresse de l'utilisateur
     */
    public String getAdresseUtil() {
        return adresseUtil;
    }

    /**
     * Retourne le code postal de l'utilisateur
     * @return le code postal de l'utilisateur
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Retourne la ville de l'utilisateur
     * @return la ville de l'utilisateur
     */
    public String getVilleUtil() {
        return villeUtil;
    }

    /**
     * Retourne la monnaie du client
     * @return la monnaie du client
     */
    public double getMonnaie() {
        return monnaie;
    }

    /**
     * Définit l'adresse de l'utilisateur
     * @param adresseUtil l'adresse de l'utilisateur
     */
    public void setAdresseUtil(String adresseUtil) {
        this.adresseUtil = adresseUtil;
    }

    /**
     * Définit le code postal de l'utilisateur
     * @param codePostal le code postal de l'utilisateur
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Définit la ville de l'utilisateur
     * @param villeUtil la ville de l'utilisateur
     */
    public void setVilleUtil(String villeUtil) {
        this.villeUtil = villeUtil;
    }

    /**
     * Définit la monnaie du client
     * @param monnaie la monnaie du client
     */
    public void ajouteMonnaie(double montant){
        this.monnaie += montant;
    }

    /**
     * Définit la monnaie du client
     * @param monnaie la monnaie du client
     */
    @Override
    public boolean seConnecter(String nom, String prenom, String pwd) throws SQLException {
        String sql = "SELECT CLIENT.iduse, nomcli, prenomcli, pwd, adressecli, codePostal, villecli, monnaie \n" + //
                        "FROM UTILISATEUR\n" + //
                        "JOIN CLIENT ON UTILISATEUR.iduse = CLIENT.iduse where nomcli = ? and prenomcli = ? and pwd = ?";
        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement(sql);
        ps.setString(1, nom);
        ps.setString(2, prenom);
        ps.setString(3, pwd);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            this.idUtil = rs.getInt(1);
            this.nomUtil = rs.getString(2);
            this.prenomUtil = rs.getString(3);
            this.pwd = rs.getString(4);
            this.adresseUtil = rs.getString(5);
            this.codePostal = rs.getString(6);
            this.villeUtil = rs.getString(7);
            this.monnaie = rs.getDouble(8);

            rs.close();
            return true;
        } else {
            rs.close();

            return false;
        }
    }

    /**
     * Ajoute une commande à la base de données
     * @param c la commande à ajouter
     * @throws Exception si une erreur survient lors de l'ajout
     */
    public List<Commande> voirSesCommande() throws SQLException, Exception{

        /*
         * si il y a le temps, ajouter une jointure au magasin pour connaitre ces sepciticité
         * 
         */
        List<Commande> res = new ArrayList<>();

        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * \n" + //
                        "FROM UTILISATEUR AS u \n" + //
                        "JOIN COMMANDE AS c \n" + //
                        "ON u.iduse = c.iduse \n" + //
                        "JOIN MAGASIN AS m ON c.idmag = m.idmag where u.iduse = ?");
        ps.setInt(1, this.idUtil);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Commande c = new Commande(rs.getInt("numcom"), rs.getString("datecom"), rs.getString("enligne").charAt(0), rs.getString("livraison").charAt(0), this.getMagasinBDparNom(rs.getString("nommag")), this.getUtilisateurParId(this.idUtil));
            c.setListeCommandeUnit(voirDetailCommande(c));
            res.add(c);
        }

        return res;
    }

    /**
     * Ajoute une commande à la base de données
     * @param c la commande à ajouter
     * @throws Exception si une erreur survient lors de l'ajout
     */
    public void retireSaCommande(Commande c) throws Exception{

        retireDetailCommande(c.getNumCom());

        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("DELETE COMMANDE FROM COMMANDE where iduse = ? and numcom = ?");
        ps.setInt(1, this.idUtil);
        ps.setInt(2, c.getNumCom());
        ps.executeUpdate();

        for (CommandeUnit comU : c.getListeCommandes()){
            ajouteLivreDansMagasin(c.getMagasin(), comU.getLivre(), comU.getQte());
            decrementeAchat(comU.getLivre().getIsbn());
        }
    }

    public void updateSaCommande(Commande c) throws Exception{
        retireSaCommande(this.getCommande(c.getNumCom()));
        ajouteSaCommandeBD(c);
    }

    /**
     * Ajoute une commande à la base de données
     * @param c la commande à ajouter
     * @throws Exception si une erreur survient lors de l'ajout
     */
    @Override
    public String toString() {
        if (idUtil == 0){
            return "ce client n'a pas encore d'identité";
        } else {
            return "Client " + super.toString() + " | " + adresseUtil + " | " + codePostal + " | " + villeUtil + " | " + monnaie;
        }
    }

}
