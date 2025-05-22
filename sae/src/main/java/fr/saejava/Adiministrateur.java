package fr.saejava;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Adiministrateur extends Utilisateur {
    
    public Adiministrateur(int idUtil, String nomUtil, String prenomUtil, String pwd, Catalogue cat, ConnexionMySQL laconnexion){
        super(idUtil, nomUtil, prenomUtil, pwd, cat, laconnexion);
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
        PreparedStatement ps = laConnexion.prepareStatement("insert into LIVRE values (?, ?, ?, ?, ?)");
        ps.setInt(1, getmaxisbn());
        ps.setString(2, l.getTitre());
        ps.setInt(3, l.getNbPages());
        ps.setInt(4, l.getDatePubli());
        ps.setInt(5, l.getPrix());
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


    public void ajouteClientBD(Client c) throws SQLException{
        st = laConnexion.createStatement();
        PreparedStatement ps1 = laConnexion.prepareStatement("insert into UTILISATEUR values (?, ?, ?, ?)");
        ps1.setInt(1, c.getIdUtil());
        ps1.setString(2, c.getNomUtil());
        ps1.setString(3, c.getPrenomUtil());
        ps1.setString(4, c.getPwd());


        PreparedStatement ps2 = laConnexion.prepareStatement("insert into CLIENT values (?, ?, ?, ?)");
        ps2.setInt(1, c.getIdUtil());
        ps2.setString(2, c.getAdresseUtil());
        ps2.setString(3, c.getCodePostal());
        ps2.setString(4, c.getVilleUtil());
        try{
        ps1.executeUpdate();
        ps2.executeUpdate();
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        
    }


    @Override
    public boolean seConnecter(String nom, String prenom, String pwd) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'seConnecter'");
    }



}
