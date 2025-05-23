package fr.saejava;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Client extends Utilisateur {
    
    private String adresseUtil;
    private String codePostal;
    private String villeUtil;
    private Double monnaie;


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
            return true;
        } else {
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
        ps.setString(3, com.getDateArrivee());
        ps.setString(4, String.valueOf(com.getLivraison()));
        ps.setInt(5, this.getIdUtil());
        ps.setInt(6, com.getMagasin().getIdMag());
        ps.executeUpdate();

        for (CommandeUnit comU : com.getListeCommandes()){
            ajouteCommandeUnitBD(max,comU);
        }
        
    }

    public void ajouteCommandeUnitBD(int numCommande,CommandeUnit comU) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("insert into DETAILCOMMANDE values (?, ?, ?, ?, ?)");
        ps.setInt(1, numCommande);
        ps.setInt(2, getMaxnumComU());
        ps.setInt(3, comU.getQte());
        ps.setInt(4, comU.getPrixTotal());
        ps.setInt(5, comU.getLivre().getIsbn());

        ps.executeUpdate();

    }

    public List<Commande> voirSesCommande() throws SQLException{
        List<Commande> res = new ArrayList<>();

        st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery("select ");
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
