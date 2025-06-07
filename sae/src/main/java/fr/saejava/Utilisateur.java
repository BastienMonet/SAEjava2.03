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

    public Set<Livre> onVousRecommande() throws SQLException{
        Set<Livre> res = new HashSet<>();

        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * " +
                                                            "FROM LIVRE " +
                                                            "JOIN POSSEDER ON LIVRE.isbn = POSSEDER.isbn");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Livre l = new Livre(rs.getInt("isbn"), rs.getString("titre"), rs.getInt("nbpages"), rs.getInt("datepubli"), rs.getDouble("prix"));
            
            res.add(l);
        }
        return res;
    }

    public Set<Livre> onVousRecommandeDansMagasin(Magasin m) throws SQLException{
        Set<Livre> res = new HashSet<>();

        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * " +
                                                            "FROM LIVRE " +
                                                            "JOIN POSSEDER ON LIVRE.isbn = POSSEDER.isbn where idmag = ?");
        ps.setInt(1, m.getIdMag());
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Livre l = new Livre(rs.getInt("isbn"), rs.getString("titre"), rs.getInt("nbpages"), rs.getInt("datepubli"), rs.getDouble("prix"));
            
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
            return new Livre(rs.getInt("isbn"), rs.getString("titre"), rs.getInt("nbpages"), rs.getInt("datepubli"), rs.getDouble("prix"));
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

    public void ajouteCommandeBD(Commande com) throws SQLException {
        /*
         * ! ne pas oublier de retirer le nombre de livre commander au magasin attitrer
         */
        PreparedStatement ps = laConnexion.prepareStatement("insert into COMMANDE values (?, ?, ?, ?, ?, ?)");
        int max = getMaxnumCom();
        ps.setInt(1, max);
        ps.setString(2, com.getDateCom());
        ps.setString(3, String.valueOf(com.enligne()));
        ps.setString(4, String.valueOf(com.getLivraison()));
        ps.setInt(5, this.getIdUtil());
        ps.setInt(6, com.getMagasin().getIdMag());
        ps.executeUpdate();

        Administrateur a = new Administrateur(laConnexion);

        for (CommandeUnit comU : com.getListeCommandes()){
            try {
            a.retireLivreDansMagasin(com.getMagasin(), comU.getLivre(), comU.getQte());;
            ajouteCommandeUnitBD(max,comU);
            } catch (Exception e){
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
            CommandeUnit cU = new CommandeUnit(new Livre(rs.getInt("isbn"), rs.getString("titre"), rs.getInt("nbpages"), rs.getInt("datepubli"), rs.getDouble("prix")), rs.getInt("qte"));
            res.add(cU);
        }
        rs.close();
        return res;
    }

    public void retireDetailCommande(int numCom) throws Exception{
        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("DELETE DETAILCOMMANDE " +
                                                    "FROM DETAILCOMMANDE where numcom = ?");
        ps.setInt(1, numCom);
        ps.executeUpdate();
        
    }

    // public void getNbreAchats()

    // public void incrementeAchat(Livre l) throws Exception{
    //     st = laConnexion.createStatement();
    //     PreparedStatement ps = laConnexion.prepareStatement("Update LIVRE set nbAchat = ? where isbn = ?");


    // }

    @Override
    public String toString() {
        return idUtil + " | " + nomUtil + " | " + prenomUtil + " | " + pwd;
    }


}