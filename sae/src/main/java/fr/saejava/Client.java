package fr.saejava;

import java.sql.*;

public class Client extends Utilisateur {
    
    private String adresseUtil;
    private String codePostal;
    private String villeUtil;
    private double monnaie;

    public Client(int idUtil, String nomUtil, String prenomUtil, String pwd,Catalogue cat ,String adresseUtil, String codePostal,
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
    public String getCodePostal() {
        return codePostal;
    }
    public String getVilleUtil() {
        return villeUtil;
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
        String sql = "SELECT * \n" + //
                        "FROM UTILISATEUR\n" + //
                        "JOIN CLIENT ON UTILISATEUR.iduse = CLIENT.iduse where nomcli = ? and prenomcli = ? and pwd = ?";
        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement(sql);
        ps.setString(1, nom);
        ps.setString(2, prenom);
        ps.setString(3, pwd);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            return true;
        } else {
            return false;
        }
    }



    public void ajouteCommandeBD(Commande com) throws SQLException {
        /*
         * ! ne pas oublier de retirer le nombre de livre commander au magasin attitrer
         */
        PreparedStatement ps = laConnexion.prepareStatement("insert into COMMANDE values (?, ?, ?, ?, ?, ?)");
        ps.setInt(1, com.getNumCom());
        ps.setString(2, com.getDateCom());
        ps.setString(3, com.getDateArrivee());
        ps.setString(4, String.valueOf(com.getLivraison()));
        ps.setInt(5, this.getIdUtil());
        ps.setInt(6, com.getMagasin().getIdMag());

        for (CommandeUnit comU : com.getListeCommandes()){
            ajouteCommandeUnitBD(com.getNumCom(),comU);
        }
        ps.executeUpdate();
    }

    public void ajouteCommandeUnitBD(int numCommande,CommandeUnit comU) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("insert into DETAILCOMMANDE values (?, ?, ?, ?, ?)");
        ps.setInt(1, numCommande);
        ps.setInt(2, comU.getNumliq());
        ps.setInt(3, comU.getQte());
        ps.setInt(4, comU.getPrixTotal());
        ps.setInt(5, comU.getLivre().getIsbn());

        ps.executeUpdate();

    }

}
