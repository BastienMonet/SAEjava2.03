package fr.saejava;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Adiministrateur extends Utilisateur {
    
    public Adiministrateur(ConnexionMySQL laconnexion){
        super(laconnexion);
    }

    public Adiministrateur(String nomUtil, String prenomUtil, String pwd){
        super(nomUtil, prenomUtil, pwd);
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
        ps.setDouble(5, l.getPrix());
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

    public int getMaxIdCli() throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("SELECT MAX(iduse) AS iduse FROM CLIENT");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("iduse") + 1;
        } else {
            return 1;
        }
    }


    public void ajouteClientBD(Client c) throws SQLException{
        st = laConnexion.createStatement();
        PreparedStatement ps1 = laConnexion.prepareStatement("insert into UTILISATEUR values (?, ?, ?, ?)");
        int max = getMaxIdCli();
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

    

    @Override
    public boolean seConnecter(String nom, String prenom, String pwd) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'seConnecter'");
    }
}
