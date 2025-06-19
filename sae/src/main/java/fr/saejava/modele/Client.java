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
        ajouteCommandeBD(c);
    }


    @Override
    public String toString() {
        if (idUtil == 0){
            return "ce client n'a pas encore d'identité";
        } else {
            return "Client " + super.toString() + " | " + adresseUtil + " | " + codePostal + " | " + villeUtil + " | " + monnaie;
        }
    }

}
