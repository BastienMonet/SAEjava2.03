package fr.saejava;

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


    public Client(ConnexionMySQL laConnexion) {
        super(laConnexion);
    }

    public Client(String nomUtil, String prenomUtil, String pwd, String adresseUtil, String codePostal,
            String villeUtil, Double monnaie) {
        super(nomUtil, prenomUtil, pwd);
        this.adresseUtil = adresseUtil;
        this.codePostal = codePostal;
        this.villeUtil = villeUtil;
        this.monnaie = monnaie;
    }


    public String getAdresseUtil() {
        return adresseUtil;
    }
    public String getCodePostal() {
        return codePostal;
    }
    public String getVilleUtil() {
        return villeUtil;
    }
    public double getMonnaie() {
        return monnaie;
    }
    public void setAdresseUtil(String adresseUtil) {
        this.adresseUtil = adresseUtil;
    }
    public void setCodePostal(String codePostal) {
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

        Adiministrateur a = new Adiministrateur(laConnexion);

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

    public List<CommandeUnit> voirSesDetailCommande(Commande com) throws SQLException, Exception{

        /*
         * si il y a le temps, ajouter une jointure au magasin pour connaitre ces sepciticité
         * 
         */
        List<CommandeUnit> res = new ArrayList<>();

        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("SELECT *" +
                                                    "FROM COMMANDE "+
                                                    "JOIN DETAILCOMMANDE ON COMMANDE.numcom = DETAILCOMMANDE.numcom " +
                                                    "JOIN LIVRE ON DETAILCOMMANDE.isbn = LIVRE.isbn");
        ps.setInt(1, com.getNumCom());
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            CommandeUnit cU = new CommandeUnit(new Livre(rs.getInt("isbn"), rs.getString("titre"), rs.getInt("nbpages"), rs.getInt("datepubli"), rs.getDouble("prix")), rs.getInt("qte"));
            
            res.add(cU);
        }
        return res;
    }


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
        if (rs.next()){
            while(rs.next()){
                Commande c = new Commande(rs.getInt("numcom"), rs.getString("datecom"), rs.getString("enligne").charAt(0), rs.getString("livraison").charAt(0), new Magasin(0, rs.getString("nommag"), rs.getString("villemag"), null));
                c.setListeCommandeUnit(voirSesDetailCommande(c));
                res.add(c);
            }


        } else {
            throw new Exception("vous n'avez acctuelement aucune commande");
        }
        return res;
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

    public Magasin getMagasinBDparId(int idmag) throws SQLException, Exception{
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * FROM MAGASIN where idmag = ? ");
        ps.setInt(1, idmag);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Magasin(rs.getInt(idmag), rs.getString("nommag"), rs.getString("villemag"), null);
        } else {
            throw new Exception("ce magasin n'existe pas");
        }
        
    }


    public Livre getLivreBDparId(int isbn) throws SQLException, Exception{
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * FROM LIVRE where isbn = ? ");
        ps.setInt(1, isbn);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Livre(rs.getInt("isbn"), rs.getString("titre"), rs.getInt("nbpages"), rs.getInt("datepubli"), rs.getDouble("prix"));
        } else {
            throw new Exception("ce livre n'existe pas");
        }
        
    }

    public String qteParMagasin(Livre l) throws SQLException{
        String res = "";
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * FROM POSSEDER JOIN MAGASIN on POSSEDER.idmag = MAGASIN.idmag where isbn = ? ");
        ps.setInt(1, l.getIsbn());
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            res += "choix numero " + rs.getString("idmag") + " il y a " + rs.getString("qte") + " fois l'exemplaire dans le magasin " + rs.getString("nommag") + "\n";
        }
        return res;
    }
    

    @Override
    public String toString() {
        if (idUtil == 0){
            return "ce client n'a pas encore d'identité";
        } else {
            return super.toString() + " | " + adresseUtil + " | " + codePostal + " | " + villeUtil + " | " + monnaie;
        }
    }

}
