package fr.saejava.modele;

import fr.saejava.modele.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Vendeur extends Utilisateur {
    
    public Vendeur(ConnexionMySQL laConnexion){
        super(laConnexion);
    }

    /**
     * Constructeur de la classe Vendeur avec les paramètres
     * @param idUtil l'identifiant de l'utilisateur
     * @param nomUtil le nom de l'utilisateur
     * @param prenomUtil le prénom de l'utilisateur
     * @param pwd le mot de passe de l'utilisateur
     */
    public Vendeur(int idUtil, String nomUtil, String prenomUtil, String pwd){
        super(idUtil, nomUtil, prenomUtil, pwd);
    }

    public List<Commande> voirCommande() throws Exception{

        /*
         * si il y a le temps, ajouter une jointure au magasin pour connaitre ces sepciticité
         * 
         */
        List<Commande> res = new ArrayList<>();

        st = laConnexion.createStatement();
        Statement st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT * \n" + //
                        "FROM UTILISATEUR AS u \n" + //
                        "JOIN COMMANDE AS c \n" + //
                        "ON u.iduse = c.iduse \n" + //
                        "JOIN MAGASIN AS m ON c.idmag = m.idmag");
        while(rs.next()){
            Commande c = new Commande(rs.getInt("numcom"), rs.getString("datecom"), rs.getString("enligne").charAt(0), rs.getString("livraison").charAt(0), this.getMagasinBDparNom(rs.getString("nommag")), getUtilisateurParId(rs.getInt("iduse")));
            c.setListeCommandeUnit(voirDetailCommande(c));
            res.add(c);
        }

        return res;
    }


    /**
     * Retire les détails de la commande
     * @param numCom le numéro de la commande dont on veut retirer les détails
     * @throws Exception
     */
    public void retireCommande(int numCom) throws Exception{
        retireDetailCommande(numCom);

        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("DELETE COMMANDE FROM COMMANDE where numcom = ?");
        ps.setInt(1, numCom);
        ps.executeUpdate();
        ps.close();

        
        
    }

    /**
     * Ajoute une commande à la base de données
     * @param c la commande à ajouter
     * @throws Exception si une erreur survient lors de l'ajout
     */
    @Override
    public boolean seConnecter(String nom, String prenom, String pwd) throws SQLException {
        String sql = "SELECT VENDEUR.iduse, nomcli, prenomcli, pwd \n" + //
                        "FROM UTILISATEUR\n" + //
                        "JOIN VENDEUR ON UTILISATEUR.iduse = VENDEUR.iduse where nomcli = ? and prenomcli = ? and pwd = ?";
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
     * Ajoute un client à la base de données
     * @param c le client à ajouter
     * @throws SQLException si une erreur survient lors de l'ajout
     */
    public List<String> voirToutLesNomClients() throws SQLException {
        List<String> res = new ArrayList<>();
        st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT nomcli, prenomcli FROM UTILISATEUR natural join CLIENT");
        while(rs.next()){
            res.add(rs.getString("nomcli") + " " + rs.getString("prenomcli"));
        }
        return res;
    }

    /**
     * Recherche les clients par nom ou prénom
     * @param carac la chaîne de caractères à rechercher dans les noms ou prénoms des clients
     * @return une liste de noms de clients correspondant à la recherche
     * @throws SQLException si une erreur survient lors de la requête
     */
    public List<String> voirToutLesNomClients(String carac) throws SQLException {
        List<String> res = new ArrayList<>();
        PreparedStatement ps = laConnexion.prepareStatement("SELECT nomcli, prenomcli FROM UTILISATEUR natural join CLIENT where nomcli like ? or prenomcli like ? ");
        ps.setString(1,  "%" + carac + "%");
        ps.setString(2, "%" + carac + "%");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            res.add(rs.getString("nomcli") + " " + rs.getString("prenomcli"));
        }
        return res;
    }

    /**
     * Récupère un client par son nom et prénom
     * @param chaineString la chaîne de caractères contenant le nom et le prénom du client
     * @return un objet Client correspondant au nom et prénom fournis
     * @throws SQLException si une erreur survient lors de la requête
     */
    public Client getClientParNom(String chaineString) throws SQLException {
        String nom = chaineString.split(" ")[0];
        String prenom = chaineString.split(" ")[1];
        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * FROM UTILISATEUR natural join CLIENT where nomcli = ? and prenomcli = ?");
        ps.setString(1, nom);
        ps.setString(2, prenom);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return new Client(rs.getInt("iduse"), rs.getString("nomcli"), rs.getString("prenomcli"), rs.getString("pwd"), 
                              rs.getString("adressecli"), rs.getString("codepostal"), rs.getString("villecli"), rs.getDouble("monnaie"));
        } else {
            throw new SQLException("Aucun client trouvé avec le nom : " + nom);
        }
    }

    /**
     * Récupère l'identifiant d'un client par son nom et prénom
     * @param chaineString la chaîne de caractères contenant le nom et le prénom du client
     * @return l'identifiant du client correspondant au nom et prénom fournis
     * @throws SQLException si une erreur survient lors de la requête
     */
    public int getIdClientParNom(String chaineString) throws SQLException {
        String nom = chaineString.split(" ")[0];
        String prenom = chaineString.split(" ")[1];
        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("SELECT * FROM UTILISATEUR natural join CLIENT where nomcli = ? and prenomcli = ?");
        ps.setString(1, nom);
        ps.setString(2, prenom);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return rs.getInt("iduse");
        } else {
            throw new SQLException("Aucun client trouvé avec le nom : " + nom + " " + prenom);
        }
    }

    /**
     * Retourne une représentation textuelle de l'objet Vendeur
     * @return une chaîne de caractères représentant le vendeur
     */
    @Override
    public String toString() {
        return "Vendeur " + super.toString();
    }


}
