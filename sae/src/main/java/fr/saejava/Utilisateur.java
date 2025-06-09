package fr.saejava;

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

    protected Catalogue catalogue;
    protected Set<Commande> commandes;

    protected ConnexionMySQL laConnexion;
    protected Statement st;
    
    
    public Utilisateur(ConnexionMySQL laConnexion) {
        // this.nomUtil = nomUtil;
        // this.prenomUtil = prenomUtil;
        // this.pwd = pwd;
        // this.catalogue = cat;
        // commandes = new HashSet<>();
        this.laConnexion = laConnexion;
    }

    public Utilisateur(String nomUtil, String prenomUtil, String pwd) {
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

    public abstract boolean seConnecter(String nom, String prenom, String pwd) throws SQLException;

    public List<Magasin> voirToutLesMagasin() throws SQLException{
        List<Magasin> lstMag = new ArrayList<>();

        String query = "SELECT * FROM MAGASIN";
        st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()){
            lstMag.add(new Magasin(rs.getInt("idmag"), rs.getString("nommag"), rs.getString("villemag"), null));
        }
        return lstMag;
    }

    public List<Livre> voirToutLesLivre() throws SQLException{
        List<Livre> lstLivre = new ArrayList<>();

        String query = "SELECT * FROM LIVRE";
        st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()){
            lstLivre.add(new Livre(rs.getInt("isbn"), rs.getString("titre"), rs.getInt("nbpages"), rs.getInt("datepubli"), rs.getDouble("prix"), rs.getInt("nbreAchat")));
        }
        return lstLivre;
    }

    public List<Livre> onVousRecommande() throws SQLException{
        List<Livre> res = new ArrayList<>();

        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * " +
                                                            "FROM LIVRE " +
                                                            "JOIN POSSEDER ON LIVRE.isbn = POSSEDER.isbn ORDER BY nbreAchat DESC");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Livre l = new Livre(rs.getInt("isbn"), rs.getString("titre"), rs.getInt("nbpages"), rs.getInt("datepubli"), rs.getDouble("prix"), rs.getInt("nbreAchat"));
            
            res.add(l);
        }
        return res;
    }

    public List<Livre> onVousRecommandeDansMagasin(Magasin m) throws SQLException{
        List<Livre> res = new ArrayList<>();

        st = laConnexion.createStatement();
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

    public Magasin getMagasinBDparNom(String nommag) throws SQLException, Exception{
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * FROM MAGASIN where nommag = ? ");
        ps.setString(1, nommag);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Magasin(rs.getInt("idmag"), rs.getString("nommag"), rs.getString("villemag"), null);
        } else {
            throw new Exception("ce magasin n'existe pas");
        }
        
    }


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

    public int getMaxnumCom() throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("SELECT MAX(numcom) AS maxcom FROM COMMANDE");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("maxcom") + 1;
        } else {
            return 1;
        }
    }

    public int getMaxnumComU() throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("SELECT MAX(numlig) AS maxlig FROM DETAILCOMMANDE");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("maxlig") + 1;
        } else {
            return 1;
        }
    }

    public void retireLivreDansMagasin(Magasin m, Livre l, int qte) throws SQLException, Exception{
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


    public Commande getCommande(int numCom) throws Exception{
        PreparedStatement ps = laConnexion.prepareStatement("select * from COMMANDE join MAGASIN on COMMANDE.idmag = MAGASIN.idmag where numcom = ?");
        ps.setInt(1, numCom);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            Commande c = new Commande(rs.getInt("numcom"), rs.getString("datecom"), rs.getString("enligne").charAt(0), rs.getString("livraison").charAt(0), getMagasinBDparNom(rs.getString("nommag")));
            getCommandeUnit(c);
            return c;
        } else {
            throw new SQLException();
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


    public void ajouteCommandeBD(Commande com) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("insert into COMMANDE values (?, ?, ?, ?, ?, ?)");
        int max = getMaxnumCom();
        ps.setInt(1, max);
        ps.setString(2, com.getDateCom());
        ps.setString(3, String.valueOf(com.enligne()));
        ps.setString(4, String.valueOf(com.getLivraison()));
        ps.setInt(5, this.getIdUtil());
        ps.setInt(6, com.getMagasin().getIdMag());
        ps.executeUpdate();

        for (CommandeUnit comU : com.getListeCommandes()){
            try {
            retireLivreDansMagasin(com.getMagasin(), comU.getLivre(), comU.getQte());;
            ajouteCommandeUnitBD(max,comU);
            incrementeAchat(comU.getLivre().getIsbn());
            } catch (Exception e){
                System.err.println(e.getMessage());
                System.err.println("vous avez demmander une commande impossible");
            }
        }
        
    }

    public void ajouteCommandeUnitBD(int numCommande,CommandeUnit comU) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("insert into DETAILCOMMANDE values (?, ?, ?, ?, ?)");
        ps.setInt(1, numCommande);
        ps.setInt(2, getMaxnumComU());
        ps.setInt(3, comU.getQte());
        ps.setDouble(4, comU.getPrixTotal());
        ps.setInt(5, comU.getLivre().getIsbn());
        ps.executeUpdate();

    }


    public int qteDansMagasin(Livre l, Magasin m) throws Exception{
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * FROM POSSEDER as p JOIN MAGASIN as m on p.idmag = m.idmag where isbn = ? and nommag = ?");
        ps.setInt(1, l.getIsbn());
        ps.setString(2, m.getNomMag());
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt("qte");
        } else {
            throw new Exception("il n'y a plus de livre dans ce magasin");
        }
    }

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

    public void retireDetailCommande(int numCom) throws Exception{
        // TODO
        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("DELETE DETAILCOMMANDE " +
                                                    "FROM DETAILCOMMANDE where numcom = ?");
        ps.setInt(1, numCom);
        ps.executeUpdate();
        
    }

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

    public void incrementeAchat(int isbn) throws Exception{
        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("Update LIVRE set nbreAchat = ? where isbn = ?");
        ps.setInt(1, getNbreAchats(isbn) + 1);
        ps.setInt(2, isbn);
        ps.executeUpdate();
    }

    public void decrementeAchat(int isbn) throws Exception{
        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("Update LIVRE set nbreAchat = ? where isbn = ?");
        ps.setInt(1, getNbreAchats(isbn) - 1);
        ps.setInt(2, isbn);
        ps.executeUpdate();
    }

    public void ajouteLivreDansMagasin(Magasin m, Livre l, int qte) throws SQLException{
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

    @Override
    public String toString() {
        return idUtil + " | " + nomUtil + " | " + prenomUtil + " | " + pwd;
    }


}