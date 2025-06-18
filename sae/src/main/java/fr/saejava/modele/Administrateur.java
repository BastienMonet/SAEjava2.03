package fr.saejava.modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

import fr.saejava.exception.CompteDejaPrisException;

public class Administrateur extends Utilisateur {
    
    public Administrateur(ConnexionMySQL laconnexion){
        super(laconnexion);
    }

    public Administrateur(String nomUtil, String prenomUtil, String pwd){
        super(nomUtil, prenomUtil, pwd);
    }

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

    public int getmaxisbn() throws SQLException{
        st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery("select max(isbn) max from LIVRE");
        if (rs.next()){
            return rs.getInt("max") + 1;
        } else {
            return 1;
        }  
    }


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

    public int getMaxIdUtil() throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("SELECT MAX(iduse) AS iduse FROM UTILISATEUR");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("iduse") + 1;
        } else {
            return 1;
        }
    }


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

    public int getMaxIdMag() throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("SELECT MAX(idMag) AS maxId FROM MAGASIN");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("maxId") + 1;
        } else {
            return 1;
        }
    }

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



    ArrayList<Map.Entry<String, Integer>> CAparMagasin() throws SQLException{
        ArrayList<Map.Entry<String, Integer>> result = new ArrayList<>();

        PreparedStatement ps = laConnexion.prepareStatement("select nommag Magasin, sum(qte * prix) total" +
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


    

        @Override
        public String toString() {
            return "Admin " + super.toString();
        }
}
