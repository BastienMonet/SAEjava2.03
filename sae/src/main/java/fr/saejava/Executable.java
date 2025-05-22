package fr.saejava;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;

public class Executable {
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        ConnexionMySQL co = new ConnexionMySQL();
        co.connecter(null, "DBmonet", "monet", "monet");
        System.out.println("ça marche");
        ResultSet rs = co.createStatement().executeQuery("select * from LIVRE");

        
         
    }
}