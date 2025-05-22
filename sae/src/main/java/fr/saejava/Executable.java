package fr.saejava;

import java.sql.SQLException;

public class Executable {
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        try {
            ConnexionMySQL co = new ConnexionMySQL();
        } catch (ClassNotFoundException e){
             System.err.println("Driver non trouve");
        }
       
        //  co.connecter(null, "DBmonet", "monet", "monet");
        
         
    }
}