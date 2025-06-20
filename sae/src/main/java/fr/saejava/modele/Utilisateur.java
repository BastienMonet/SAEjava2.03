package fr.saejava.modele;

import fr.saejava.modele.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.sql.*;

public abstract class Utilisateur {
    protected int idUtil = 0;
    protected String nomUtil = "?";
    protected String prenomUtil = "?";
    protected String pwd = "?";

    protected ConnexionMySQL laConnexion;
    protected Statement st;
    
    /**
     * Constructeur de la classe Utilisateur
     * @param laConnexion
     */
    public Utilisateur(ConnexionMySQL laConnexion) {
        // this.nomUtil = nomUtil;
        // this.prenomUtil = prenomUtil;
        // this.pwd = pwd;
        // this.catalogue = cat;
        // commandes = new HashSet<>();
        this.laConnexion = laConnexion;
    }

    /**
     * Constructeur de la classe Utilisateur avec les paramètres
     * @param idUtil l'identifiant de l'utilisateur
     * @param nomUtil le nom de l'utilisateur
     * @param prenomUtil le prénom de l'utilisateur
     * @param pwd le mot de passe de l'utilisateur
     */
    public Utilisateur(int idUtil, String nomUtil, String prenomUtil, String pwd) {
        this.idUtil = idUtil;
        this.nomUtil = nomUtil;
        this.prenomUtil = prenomUtil;
        this.pwd = pwd;
    }


    /**
     * Retourne l'identifiant de l'utilisateur
     * @return l'identifiant de l'utilisateur
     */
    public int getIdUtil() {
        return idUtil;
    }

    /**
     * Définit l'identifiant de l'utilisateur
     * @param idUtil l'identifiant de l'utilisateur
     */
    public void setIdUtil(int idUtil) {
        this.idUtil = idUtil;
    }

    /**
     * Retourne le nom de l'utilisateur
     * @return le nom de l'utilisateur
     */
    public String getNomUtil() {
        return nomUtil;
    }

    /**
     * Définit le nom de l'utilisateur
     * @param nomUtil le nom de l'utilisateur
     */
    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }

    /**
     * Retourne le prénom de l'utilisateur
     * @return le prénom de l'utilisateur
     */
    public String getPrenomUtil() {
        return prenomUtil;
    }

    /**
     * Définit le prénom de l'utilisateur
     * @param prenomUtil le prénom de l'utilisateur
     */
    public void setPrenomUtil(String prenomUtil) {
        this.prenomUtil = prenomUtil;
    }

    /**
     * Retourne le mot de passe de l'utilisateur
     * @return le mot de passe de l'utilisateur
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * Définit le mot de passe de l'utilisateur
     * @param pwd le mot de passe de l'utilisateur
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * Méthode abstraite pour se connecter
     * @param nom le nom de l'utilisateur
     * @param prenom le prénom de l'utilisateur
     * @param pwd le mot de passe de l'utilisateur
     * @return true si la connexion est réussie, false sinon
     * @throws SQLException si une erreur SQL se produit
     */
    public abstract boolean seConnecter(String nom, String prenom, String pwd) throws SQLException;

    /**
     * Méthode abstraite pour se déconnecter
     */
    public List<Magasin> voirToutLesMagasin() throws SQLException{
        List<Magasin> lstMag = new ArrayList<>();

        String query = "SELECT * FROM MAGASIN";
        st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()){
            lstMag.add(new Magasin(rs.getInt("idmag"), rs.getString("nommag"), rs.getString("villemag")));
        }
        return lstMag;
    }

    /**
     * Méthode pour voir tous les magasins contenant une certaine caractéristique dans leur nom
     * @param carac la caractéristique à rechercher dans le nom des magasins
     * @return une liste de magasins correspondant à la caractéristique
     * @throws SQLException si une erreur SQL se produit
     */
    public List<Magasin> voirToutLesMagasin(String carac) throws SQLException{
        List<Magasin> lstMag = new ArrayList<>();

        String query = "SELECT * FROM MAGASIN where nommag like ? ";
        PreparedStatement ps = laConnexion.prepareStatement(query);
        ps.setString(1, "%"+carac+"%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            lstMag.add(new Magasin(rs.getInt("idmag"), rs.getString("nommag"), rs.getString("villemag")));
        }
        return lstMag;
    }

    /**
     * Méthode pour voir tous les livres contenant une certaine caractéristique dans leur titre
     * @param carac la caractéristique à rechercher dans le titre des livres
     * @return une liste de livres correspondant à la caractéristique
     * @throws SQLException si une erreur SQL se produit
     */
    public List<Livre> voirToutLesLivres(String carac) throws SQLException{
        List<Livre> lstLivre = new ArrayList<>();

        String query = "SELECT * FROM LIVRE where titre like ? ";
        PreparedStatement ps = laConnexion.prepareStatement(query);
        ps.setString(1,"%"+ carac + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            lstLivre.add(new Livre(rs.getInt("isbn"), rs.getString("titre"), rs.getInt("nbpages"), rs.getInt("datepubli"), rs.getDouble("prix"), rs.getInt("nbreAchat")));
        }
        return lstLivre;
    }

    /**
     * Méthode pour voir tous les livres
     * @return une liste de tous les livres
     * @throws SQLException si une erreur SQL se produit
     */
    public List<Livre> voirToutLesLivres() throws SQLException{
        List<Livre> lstLivre = new ArrayList<>();

        String query = "SELECT * FROM LIVRE";
        st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()){
            lstLivre.add(new Livre(rs.getInt("isbn"), rs.getString("titre"), rs.getInt("nbpages"), rs.getInt("datepubli"), rs.getDouble("prix"), rs.getInt("nbreAchat")));
        }
        return lstLivre;
    }

    /**
     * Méthode pour voir les livres recommandés
     * @return une liste de livres recommandés
     * @throws SQLException si une erreur SQL se produit
     */
    public List<Livre> onVousRecommande() throws SQLException{
        List<Livre> res = new ArrayList<>();

        PreparedStatement ps = laConnexion.prepareStatement("SELECT distinct isbn, titre, nbpages, datepubli, prix, nbreAchat " +
                                                            "FROM LIVRE " +
                                                            "natural join POSSEDER ORDER BY nbreAchat DESC");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Livre l = new Livre(rs.getInt("isbn"), rs.getString("titre"), rs.getInt("nbpages"), rs.getInt("datepubli"), rs.getDouble("prix"), rs.getInt("nbreAchat"));
            
            res.add(l);
        }
        return res;
    }

    /**
     * Méthode pour voir les livres recommandés avec une caractéristique dans le titre
     * @param carac la caractéristique à rechercher dans le titre des livres
     * @return une liste de livres recommandés correspondant à la caractéristique
     * @throws SQLException si une erreur SQL se produit
     */
    public List<Livre> onVousRecommande(String carac) throws SQLException{
        List<Livre> res = new ArrayList<>();

        PreparedStatement ps; 
        
        if (! carac.equals("")){
            ps = laConnexion.prepareStatement("SELECT distinct isbn, titre, nbpages, datepubli, prix, nbreAchat " +
                                                            "FROM LIVRE " +
                                                            "natural join POSSEDER where titre like ? ORDER BY nbreAchat DESC ");
            ps.setString(1,  "%" + carac + "%");
        } else {
            ps = laConnexion.prepareStatement("SELECT distinct isbn, titre, nbpages, datepubli, prix, nbreAchat " +
                                                            "FROM LIVRE " +
                                                            "natural join POSSEDER ORDER BY nbreAchat DESC ");
        }
        
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Livre l = new Livre(rs.getInt("isbn"), rs.getString("titre"), rs.getInt("nbpages"), rs.getInt("datepubli"), rs.getDouble("prix"), rs.getInt("nbreAchat"));
            
            res.add(l);
        }
        return res;
    }

    /**
     * Méthode pour voir les livres recommandés dans un magasin spécifique
     * @param m le magasin dans lequel on veut voir les livres recommandés
     * @return une liste de livres recommandés dans le magasin
     * @throws SQLException si une erreur SQL se produit
     */
    public List<Livre> onVousRecommandeDansMagasin(Magasin m) throws SQLException{
        List<Livre> res = new ArrayList<>();
        
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * " +
                                                            "FROM LIVRE " +
                                                            "JOIN POSSEDER ON LIVRE.isbn = POSSEDER.isbn where idmag = ? ORDER BY nbreAchat DESC");
        ps.setInt(1, m.getIdMag());
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Livre l = new Livre(rs.getInt("isbn"), rs.getString("titre"), rs.getInt("nbpages"), rs.getInt("datepubli"), rs.getDouble("prix"), rs.getInt("nbreAchat"));
            
            res.add(l);
        }
        return res;
    }

    /**
     * Méthode pour voir les livres recommandés dans un magasin spécifique avec une caractéristique dans le titre
     * @param m le magasin dans lequel on veut voir les livres recommandés
     * @param carac la caractéristique à rechercher dans le titre des livres
     * @return une liste de livres recommandés dans le magasin correspondant à la caractéristique
     * @throws SQLException si une erreur SQL se produit
     */
    public List<Livre> onVousRecommandeDansMagasin(Magasin m, String carac) throws SQLException{
        List<Livre> res = new ArrayList<>();

        PreparedStatement ps;

        if (! carac.equals("")){
            ps = laConnexion.prepareStatement("SELECT * " +
                                                            "FROM LIVRE " +
                                                            "JOIN POSSEDER ON LIVRE.isbn = POSSEDER.isbn where idmag = ? and titre like ? ORDER BY nbreAchat DESC");
        ps.setInt(1, m.getIdMag());
        ps.setString(2, "%" + carac + "%");
        } else {
            ps = laConnexion.prepareStatement("SELECT * " +
                                                            "FROM LIVRE " +
                                                            "JOIN POSSEDER ON LIVRE.isbn = POSSEDER.isbn where idmag = ? ORDER BY nbreAchat DESC");
        ps.setInt(1, m.getIdMag());
        }
        
         
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Livre l = new Livre(rs.getInt("isbn"), rs.getString("titre"), rs.getInt("nbpages"), rs.getInt("datepubli"), rs.getDouble("prix"), rs.getInt("nbreAchat"));
            
            res.add(l);
        }
        return res;
    }

    /**
     * Méthode pour obtenir un utilisateur par son identifiant
     * @param id l'identifiant de l'utilisateur
     * @return l'utilisateur correspondant à l'identifiant
     * @throws SQLException si une erreur SQL se produit
     */
    public Magasin getMagasinBDparNom(String nommag) throws SQLException, Exception{
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * FROM MAGASIN where nommag = ? ");
        ps.setString(1, nommag);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Magasin(rs.getInt("idmag"), rs.getString("nommag"), rs.getString("villemag"));
        } else {
            throw new Exception("ce magasin n'existe pas");
        }
        
    }

    /**
     * Méthode pour obtenir un livre par son titre
     * @param titre le titre du livre
     * @return le livre correspondant au titre
     * @throws SQLException si une erreur SQL se produit
     * @throws Exception si le livre n'existe pas
     */
    public Livre getLivreBDparTitre(String titre) throws SQLException, Exception{
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * FROM LIVRE where titre = ? ");
        ps.setString(1, titre);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Livre(rs.getInt("isbn"), rs.getString("titre"), rs.getInt("nbpages"), rs.getInt("datepubli"), rs.getDouble("prix"), rs.getInt("nbreAchat"));
        } else {
            throw new Exception("ce livre n'existe pas");
        }
        
    }

    /**
     * Méthode pour obtenir un utilisateur par son identifiant
     * @param id l'identifiant de l'utilisateur
     * @return l'utilisateur correspondant à l'identifiant
     * @throws SQLException si une erreur SQL se produit
     */
    public int getMaxnumCom() throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("SELECT MAX(numcom) AS maxcom FROM COMMANDE");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("maxcom") + 1;
        } else {
            return 1;
        }
    }

    /**
     *  Méthode pour obtenir le numéro de la prochaine ligne de commande
     * @return  le numéro de la prochaine ligne de commande
     * @throws SQLException si une erreur SQL se produit
     */
    public int getMaxnumComU() throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("SELECT MAX(numlig) AS maxlig FROM DETAILCOMMANDE");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("maxlig") + 1;
        } else {
            return 1;
        }
    }

    /**
     * Méthode pour obtenir un utilisateur par son identifiant
     * @param id l'identifiant de l'utilisateur
     * @return l'utilisateur correspondant à l'identifiant
     * @throws SQLException si une erreur SQL se produit
     */
    public void retireLivreDansMagasin(Magasin m, Livre l, int qte) throws Exception{
        if (qte <= 0 || qte > 99){
            throw new Exception("la quantité saisie est érroné");
        }
        PreparedStatement ps = laConnexion.prepareStatement("select qte from POSSEDER where idmag = ? and isbn = ?");
        ps.setInt(1, m.getIdMag());
        ps.setInt(2, l.getIsbn());

        ResultSet rs = ps.executeQuery();
        
        if (rs.next()){
            int qteActuel = rs.getInt("qte");

            if(qteActuel > qte){
                PreparedStatement ps2 = laConnexion.prepareStatement("UPDATE POSSEDER set qte = ? where idmag = ? and isbn = ?");
                ps2.setInt(1, qteActuel - qte);
                ps2.setInt(2, m.getIdMag());
                ps2.setInt(3, l.getIsbn());
                try{
                    ps2.executeUpdate();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    System.err.println("erreur a la requete 2");
                }

            } else if (qteActuel == qte){
                PreparedStatement ps2 = laConnexion.prepareStatement("DELETE from POSSEDER where idmag = ? and isbn = ?");
                ps2.setInt(1, m.getIdMag());
                ps2.setInt(2, l.getIsbn());
                try{
                    ps2.executeUpdate();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    System.err.println("erreur a la requete 3");
                }

            } else {
                throw new Exception("il n'y a pas assez de livre pour retirer une tel quantite");
            }

            
        } else {
            throw new Exception("le livre n'est pas dans le magasin");
        }
    }

    /**
     * Méthode pour obtenir un utilisateur par son identifiant
     * @param id l'identifiant de l'utilisateur
     * @return l'utilisateur correspondant à l'identifiant
     * @throws SQLException si une erreur SQL se produit
     */
    public Commande getCommande(int numCom) throws Exception{
        PreparedStatement ps = laConnexion.prepareStatement("select * from COMMANDE join MAGASIN on COMMANDE.idmag = MAGASIN.idmag where numcom = ?");
        ps.setInt(1, numCom);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            Commande c = new Commande(rs.getInt("numcom"), rs.getString("datecom"), rs.getString("enligne").charAt(0), rs.getString("livraison").charAt(0), getMagasinBDparNom(rs.getString("nommag")), this.getUtilisateurParId(rs.getInt("iduse")));
            getCommandeUnit(c);
            return c;
        } else {
            throw new SQLException("la commande demander n'existe pas");
        }
    }

    public void getCommandeUnit(Commande c) throws Exception{
        PreparedStatement ps = laConnexion.prepareStatement("select * from DETAILCOMMANDE join LIVRE on DETAILCOMMANDE.isbn = LIVRE.isbn where numcom = ?");
        ps.setInt(1, c.getNumCom());
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            c.addCommandeUnit(new CommandeUnit(getLivreBDparTitre(rs.getString("titre")), rs.getInt("qte")));
        }
        
    }

    /**
     * Méthode pour vérifier si une commande est valide
     * @param com la commande à vérifier
     * @return true si la commande est valide, false sinon
     */
    public boolean CommandeValide(Commande com) {
        for (CommandeUnit comU : com.getListeCommandes()){
            try {
                if (qteDansMagasin(comU.getLivre(), com.getMagasin()) < comU.getQte()){
                    return false;
                }
            } catch (Exception e){
                System.err.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * Méthode pour ajouter une commande à la base de données
     * @param com la commande à ajouter
     * @throws Exception si une erreur survient lors de l'ajout
     */
    public void ajouteSaCommandeBD(Commande com) throws Exception {
        PreparedStatement ps = laConnexion.prepareStatement("insert into COMMANDE values (?, ?, ?, ?, ?, ?)");
        int max = getMaxnumCom();
        ps.setInt(1, max);
        ps.setString(2, com.getDateCom());
        ps.setString(3, String.valueOf(com.enligne()));
        ps.setString(4, String.valueOf(com.getLivraison()));
        ps.setInt(5, this.getIdUtil());
        ps.setInt(6, com.getMagasin().getIdMag());
        ps.executeUpdate();

        if (CommandeValide(com)){
            for (CommandeUnit comU : com.getListeCommandes()){
                try {
                retireLivreDansMagasin(com.getMagasin(), comU.getLivre(), comU.getQte());
                ajouteCommandeUnitBD(max,comU);
                incrementeAchat(comU.getLivre().getIsbn());
                } catch (Exception e){
                    System.err.println(e.getMessage());
                    System.err.println("vous avez demmander une commande impossible");
                }
            }
        } else {
            throw new Exception("vous avez demmander une commande impossible");
        }

        
        
    }

    /**
     * Méthode pour ajouter une commande à la base de données
     * @param com la commande à ajouter
     * @throws Exception si une erreur survient lors de l'ajout
     */
    public void ajouteUneCommandeBD(Commande com) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("insert into COMMANDE values (?, ?, ?, ?, ?, ?)");
        int max = getMaxnumCom();
        ps.setInt(1, max);
        ps.setString(2, com.getDateCom());
        ps.setString(3, String.valueOf(com.enligne()));
        ps.setString(4, String.valueOf(com.getLivraison()));
        ps.setInt(5, com.getClient().getIdUtil());
        ps.setInt(6, com.getMagasin().getIdMag());
        ps.executeUpdate();

        for (CommandeUnit comU : com.getListeCommandes()){
            try {
            retireLivreDansMagasin(com.getMagasin(), comU.getLivre(), comU.getQte());
            ajouteCommandeUnitBD(max,comU);
            incrementeAchat(comU.getLivre().getIsbn());
            } catch (Exception e){
                System.err.println(e.getMessage());
                System.err.println("vous avez demmander une commande impossible");
            }
        }
        
    }

    /**
     * Méthode pour ajouter une commande unitaire à la base de données
     * @param numCommande le numéro de la commande
     * @param comU la commande unitaire à ajouter
     * @throws SQLException si une erreur SQL se produit
     */
    public void ajouteCommandeUnitBD(int numCommande,CommandeUnit comU) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("insert into DETAILCOMMANDE values (?, ?, ?, ?, ?)");
        ps.setInt(1, numCommande);
        ps.setInt(2, getMaxnumComU());
        ps.setInt(3, comU.getQte());
        ps.setDouble(4, comU.getPrixTotal());
        ps.setInt(5, comU.getLivre().getIsbn());
        ps.executeUpdate();

    }

    /**
     * Méthode pour vérifier la quantité d'un livre dans un magasin
     * @param l le livre à vérifier
     * @param m le magasin dans lequel vérifier la quantité
     * @return la quantité du livre dans le magasin
     * @throws Exception si une erreur survient lors de la récupération de la quantité
     */
    public int qteDansMagasin(Livre l, Magasin m) throws Exception{
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * FROM POSSEDER as p JOIN MAGASIN as m on p.idmag = m.idmag where isbn = ? and nommag = ?");
        ps.setInt(1, l.getIsbn());
        ps.setString(2, m.getNomMag());
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("qte");
        } else {
            return 0;
        }
    }

    /**
     * Méthode pour voir les commandes d'un utilisateur
     * @return une liste de commandes de l'utilisateur
     * @throws SQLException si une erreur SQL se produit
     * @throws Exception si une erreur survient lors de la récupération des commandes
     */
    public List<CommandeUnit> voirDetailCommande(Commande com) throws SQLException, Exception{

        /*
         * si il y a le temps, ajouter une jointure au magasin pour connaitre ces sepciticité
         * 
         */
        List<CommandeUnit> res = new ArrayList<>();

        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("SELECT *" +
                                                    "FROM COMMANDE "+
                                                    "JOIN DETAILCOMMANDE ON COMMANDE.numcom = DETAILCOMMANDE.numcom " +
                                                    "JOIN LIVRE ON DETAILCOMMANDE.isbn = LIVRE.isbn where COMMANDE.numcom = ?");
        ps.setInt(1, com.getNumCom());
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            CommandeUnit cU = new CommandeUnit(new Livre(rs.getInt("isbn"), rs.getString("titre"), rs.getInt("nbpages"), rs.getInt("datepubli"), rs.getDouble("prix"), rs.getInt("nbreAchat")), rs.getInt("qte"));
            res.add(cU);
        }
        rs.close();
        return res;
    }

    /**
     * Méthode pour retirer une commande de la base de données
     * @param c la commande à retirer
     * @throws Exception si une erreur survient lors du retrait
     */
    public void retireDetailCommande(int numCom) throws Exception{
        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("DELETE DETAILCOMMANDE " +
                                                    "FROM DETAILCOMMANDE where numcom = ?");
        ps.setInt(1, numCom);
        ps.executeUpdate();
        
    }

    /**
     * Méthode pour obtenir le nombre d'achats d'un livre par son ISBN
     * @param isbn l'ISBN du livre
     * @return le nombre d'achats du livre
     * @throws Exception si une erreur survient lors de la récupération du nombre d'achats
     */
    public int getNbreAchats(int isbn) throws Exception {
        PreparedStatement ps = laConnexion.prepareStatement("Select * from LIVRE where isbn = ?");
        ps.setInt(1, isbn);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return rs.getInt("nbreAchat");
        } else {
            throw new SQLException("le livre n'existe pas");
        }
        

    }

    /**
     * Méthode pour incrémenter le nombre d'achats d'un livre par son ISBN
     * @param isbn l'ISBN du livre
     * @throws Exception si une erreur survient lors de l'incrémentation
     */
    public void incrementeAchat(int isbn) throws Exception{
        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("Update LIVRE set nbreAchat = ? where isbn = ?");
        ps.setInt(1, getNbreAchats(isbn) + 1);
        ps.setInt(2, isbn);
        ps.executeUpdate();
    }

    /**
     * Méthode pour décrémenter le nombre d'achats d'un livre par son ISBN
     * @param isbn l'ISBN du livre
     * @throws Exception si une erreur survient lors de la décrémentation
     */
    public void decrementeAchat(int isbn) throws Exception{
        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("Update LIVRE set nbreAchat = ? where isbn = ?");
        ps.setInt(1, getNbreAchats(isbn) - 1);
        ps.setInt(2, isbn);
        ps.executeUpdate();
    }

    /**
     * Méthode pour ajouter un livre dans un magasin
     * @param m le magasin dans lequel ajouter le livre
     * @param l le livre à ajouter
     * @param qte la quantité du livre à ajouter
     * @throws Exception si une erreur survient lors de l'ajout
     */
    public void ajouteLivreDansMagasin(Magasin m, Livre l, int qte) throws Exception{
        if (qte <= 0 || qte > 99){
            throw new Exception("la quantité saisie est érroné");
        }
        PreparedStatement ps = laConnexion.prepareStatement("select qte from POSSEDER where idmag = ? and isbn = ?");
        ps.setInt(1, m.getIdMag());
        ps.setInt(2, l.getIsbn());

        ResultSet rs = ps.executeQuery();
        

        if (rs.next()){
            int qteActuel = rs.getInt("qte");

            PreparedStatement ps2 = laConnexion.prepareStatement("UPDATE POSSEDER set qte = ? where idmag = ? and isbn = ?");
            ps2.setInt(1, qteActuel + qte);
            ps2.setInt(2, m.getIdMag());
            ps2.setInt(3, l.getIsbn());
            try{
                ps2.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                System.err.println("erreur a la requete 2");
            }
            
        } else {
            PreparedStatement ps3 = laConnexion.prepareStatement("insert into POSSEDER values (?, ?, ?)");
            ps3.setInt(1, m.getIdMag());
            ps3.setInt(2, l.getIsbn());
            ps3.setInt(3, qte);
            try{
                ps3.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                System.err.println("erreur a la requete 3");
            }
        }

        }

    /**
     * Méthode pour obtenir un utilisateur par son identifiant
     * @param iduse
     * @return l'utilisateur correspondant à l'identifiant
     * @throws SQLException
     */
    public Client getUtilisateurParId(int iduse) throws SQLException{
        PreparedStatement ps = laConnexion.prepareStatement("select * from UTILISATEUR natural join CLIENT where iduse = ?");
        ps.setInt(1, iduse);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return new Client(rs.getInt("iduse"), rs.getString("nomcli"), rs.getString("prenomcli"), rs.getString("pwd"), rs.getString("adressecli"), rs.getString("codepostal"), rs.getString("villecli"), rs.getDouble("monnaie"));
        } else {
            throw new SQLException("ce client n'existe pas");
        }

    }

    /**
     * Méthode pour obtenir un utilisateur par son identifiant
     * @param iduse
     * @return l'utilisateur correspondant à l'identifiant
     * @throws SQLException
     */
    @Override
    public String toString() {
        return idUtil + " | " + nomUtil + " | " + prenomUtil + " | " + pwd;
    }


}