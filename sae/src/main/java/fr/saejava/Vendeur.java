package fr.saejava;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Vendeur extends Utilisateur {
    
    public Vendeur(ConnexionMySQL laConnexion){
        super(laConnexion);
    }


    public Vendeur(String nomUtil, String prenomUtil, String pwd){
        super(nomUtil, prenomUtil, pwd);
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
            Commande c = new Commande(rs.getInt("numcom"), rs.getString("datecom"), rs.getString("enligne").charAt(0), rs.getString("livraison").charAt(0), this.getMagasinBDparNom(rs.getString("nommag")));
            c.setListeCommandeUnit(voirDetailCommande(c));
            res.add(c);
        }

        return res;
    }



    public void retireCommande(int numCom) throws Exception{
        retireDetailCommande(numCom);

        st = laConnexion.createStatement();
        PreparedStatement ps = laConnexion.prepareStatement("DELETE COMMANDE FROM COMMANDE where numcom = ?");
        ps.setInt(1, numCom);
        ps.executeUpdate();
        ps.close();

        
        
    }



    @Override
    public boolean seConnecter(String nom, String prenom, String pwd) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'seConnecter'");
    }


    




}
