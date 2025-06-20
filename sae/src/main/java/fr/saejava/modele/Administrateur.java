package fr.saejava.modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

import fr.saejava.exception.CompteDejaPrisException;

public class Administrateur extends Utilisateur {
    
    /**
     * Constructeur de la classe Administrateur
     * @param laconnexion
     */
    public Administrateur(ConnexionMySQL laconnexion){
        super(laconnexion);
    }

    /**
     * Constructeur de la classe Administrateur
     * @param idUtil l'identifiant de l'utilisateur
     * @param nomUtil le nom de l'utilisateur
     * @param prenomUtil le prénom de l'utilisateur
     * @param pwd le mot de passe de l'utilisateur
     */
    public Administrateur(int idUtil , String nomUtil, String prenomUtil, String pwd){
        super(idUtil, nomUtil, prenomUtil, pwd);
    }

    /**
     * Méthode pour se connecter en tant qu'administrateur
     * @param nom le nom de l'administrateur
     * @param prenom le prénom de l'administrateur
     * @param pwd le mot de passe de l'administrateur
     * @return true si la connexion est réussie, false sinon
     * @throws SQLException si une erreur SQL se produit
     */
    @Override
    public boolean seConnecter(String nom, String prenom, String pwd) throws SQLException {
        String sql = "SELECT ADMINISTRATEUR.iduse, nomcli, prenomcli, pwd \n" + //
                        "FROM UTILISATEUR\n" + //
                        "JOIN ADMINISTRATEUR ON UTILISATEUR.iduse = ADMINISTRATEUR.iduse where nomcli = ? and prenomcli = ? and pwd = ?";
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

            rs.close();
            return true;
        } else {
            rs.close();

            return false;
        }
    }

    /**
     * Méthode pour se connecter en tant qu'administrateur
     * @param nom le nom de l'administrateur
     * @param pwd le mot de passe de l'administrateur
     * @return true si la connexion est réussie, false sinon
     * @throws SQLException si une erreur SQL se produit
     */
    public int getmaxisbn() throws SQLException{
        st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery("select max(isbn) max from LIVRE");
        if (rs.next()){
            return rs.getInt("max") + 1;
        } else {
            return 1;
        }  
    }

    /**
     * Méthode pour ajouter un livre à la base de données
     * @param l le livre à ajouter
     * @throws SQLException si une erreur SQL se produit
     */
    public void ajouteLivreBD(Livre l) throws SQLException{
        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("insert into LIVRE values (?, ?, ?, ?, ?, ?)");
        ps.setInt(1, getmaxisbn());
        ps.setString(2, l.getTitre());
        ps.setInt(3, l.getNbPages());
        ps.setInt(4, l.getDatePubli());
        ps.setDouble(5, l.getPrix());
        ps.setInt(6, 0);
        try{
        ps.executeUpdate();
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        
    }

    /**
     * Méthode pour retirer un livre de la base de données
     * @param isbn l'ISBN du livre à retirer
     * @throws SQLException si une erreur SQL se produit
     */
    public void retireLivreBD(int isbn) throws SQLException{
        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("delete from LIVRE where isbn = ?");
        ps.setInt(1, isbn);
        try{
        ps.executeUpdate();
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        
    }

    /**
     * Méthode pour obtenir le prochain identifiant d'utilisateur
     * @return le prochain identifiant d'utilisateur
     * @throws SQLException si une erreur SQL se produit
     */
    public int getMaxIdUtil() throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("SELECT MAX(iduse) AS iduse FROM UTILISATEUR");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("iduse") + 1;
        } else {
            return 1;
        }
    }

    /**
     * Méthode pour ajouter un client à la base de données
     * @param c le client à ajouter
     * @throws Exception si un compte avec les mêmes informations existe déjà
     */
    public void ajouteClientBD(Client c) throws Exception{
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * from CLIENT natural join UTILISATEUR where " + 
                                        "nomcli = ? and prenomcli = ?");
        ps.setString(1, c.getNomUtil());
        ps.setString(2, c.getPrenomUtil());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            throw new CompteDejaPrisException("Un utilisateur avec ces informations existe déjà.");
        } else {
            st = laConnexion.createStatement();
            PreparedStatement ps1 = laConnexion.prepareStatement("insert into UTILISATEUR values (?, ?, ?, ?)");
            int max = getMaxIdUtil();
            ps1.setInt(1, max);
            ps1.setString(2, c.getNomUtil());
            ps1.setString(3, c.getPrenomUtil());
            ps1.setString(4, c.getPwd());


            PreparedStatement ps2 = laConnexion.prepareStatement("insert into CLIENT values (?, ?, ?, ?, ?)");
            ps2.setInt(1, max);
            ps2.setString(2, c.getAdresseUtil());
            ps2.setString(3, c.getCodePostal());
            ps2.setString(4, c.getVilleUtil());
            ps2.setDouble(5, c.getMonnaie());
            try{
            ps1.executeUpdate();
            ps2.executeUpdate();
            } catch (SQLException e){
                System.err.println(e.getMessage());
            }

        }   
    }

    /**
     * Méthode pour ajouter un administrateur à la base de données
     * @param a l'administrateur à ajouter
     * @throws SQLException si une erreur SQL se produit
     */
    public void ajouteAdminBD(Administrateur a) throws SQLException{
        st = laConnexion.createStatement();
        PreparedStatement ps1 = laConnexion.prepareStatement("insert into UTILISATEUR values (?, ?, ?, ?)");
        int max = getMaxIdUtil();
        ps1.setInt(1, max);
        ps1.setString(2, a.getNomUtil());
        ps1.setString(3, a.getPrenomUtil());
        ps1.setString(4, a.getPwd());


        PreparedStatement ps2 = laConnexion.prepareStatement("insert into ADMINISTRATEUR values (?)");
        ps2.setInt(1, max);
        try{
        ps1.executeUpdate();
        ps2.executeUpdate();
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        
    }

    /**
     * Méthode pour ajouter un vendeur à la base de données
     * @param v le vendeur à ajouter
     * @throws Exception si un compte avec les mêmes informations existe déjà
     */
    public void ajouteVendeurBD(Vendeur v) throws Exception{
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * from VENDEUR natural join UTILISATEUR where " + 
                                        "nomcli = ? and prenomcli = ?");
        ps.setString(1, v.getNomUtil());
        ps.setString(2, v.getPrenomUtil());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            throw new CompteDejaPrisException("Un utilisateur avec ces informations existe déjà.");
        } else {
        st = laConnexion.createStatement();
        PreparedStatement ps1 = laConnexion.prepareStatement("insert into UTILISATEUR values (?, ?, ?, ?)");
        int max = getMaxIdUtil();
        ps1.setInt(1, max);
        ps1.setString(2, v.getNomUtil());
        ps1.setString(3, v.getPrenomUtil());
        ps1.setString(4, v.getPwd());


        PreparedStatement ps2 = laConnexion.prepareStatement("insert into VENDEUR values (?)");
        ps2.setInt(1, max);
        try{
        ps1.executeUpdate();
        ps2.executeUpdate();
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
        
    }

    /**
     * Méthode pour obtenir le prochain identifiant de magasin
     * @return le prochain identifiant de magasin
     * @throws SQLException si une erreur SQL se produit
     */
    public int getMaxIdMag() throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("SELECT MAX(idMag) AS maxId FROM MAGASIN");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("maxId") + 1;
        } else {
            return 1;
        }
    }

    /**
     * Méthode pour ajouter un magasin à la base de données
     * @param m le magasin à ajouter
     * @throws SQLException si une erreur SQL se produit
     */
    public void ajouteMagasinBD(Magasin m) throws SQLException {
        int newIdMag = getMaxIdMag();
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO MAGASIN VALUES (?, ?, ?)");
        ps.setInt(1, getMaxIdMag());
        ps.setString(2, m.getNomMag());
        ps.setString(3, m.getVilleMag());
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * Méthode pour obtenir la liste de tous les magasins
     * @return une liste de tous les magasins
     * @throws SQLException si une erreur SQL se produit
     */
    public ArrayList<Map.Entry<String, Integer>> CAparMagasin() throws SQLException{
        ArrayList<Map.Entry<String, Integer>> result = new ArrayList<>();

        PreparedStatement ps = laConnexion.prepareStatement("select nommag Magasin, sum(qte * prix) total " +
                                "from MAGASIN " +
                                "natural join POSSEDER " +
                                "natural join LIVRE " +
                                "group by nommag;");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            String nommag = rs.getString("Magasin");
            int nbreAchat = rs.getInt("total");

            Map.Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>(nommag, nbreAchat);
            result.add(entry);
        }

        rs.close();
        return result;
    }

    /**
     * Méthode pour vérifier si un livre est dans la base de données
     * @param titre le titre du livre à vérifier
     * @return true si le livre existe, false sinon
     * @throws SQLException si une erreur SQL se produit
     */
    public boolean livreEstDansBD(String titre) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * FROM LIVRE WHERE titre = ?");
        ps.setString(1, titre);
        ResultSet rs = ps.executeQuery();
        boolean exists = rs.next();
        rs.close();
        return exists;
    }

    /**
     * Méthode pour vérifier si un magasin est dans la base de données
     * @param nomMag le nom du magasin à vérifier
     * @return true si le magasin existe, false sinon
     * @throws SQLException si une erreur SQL se produit
     */
    public boolean magasinEstDansBD(String nomMag) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * FROM MAGASIN WHERE nommag = ?");
        ps.setString(1, nomMag);
        ResultSet rs = ps.executeQuery();
        boolean exists = rs.next();
        rs.close();
        return exists;
    }


    
    /**
     * Méthode pour vérifier si un client est dans la base de données
     * @param nom le nom du client à vérifier
     * @param prenom le prénom du client à vérifier
     * @return true si le client existe, false sinon
     * @throws SQLException si une erreur SQL se produit
     */
    @Override
    public String toString() {
        return "Admin " + super.toString();
    }
}
