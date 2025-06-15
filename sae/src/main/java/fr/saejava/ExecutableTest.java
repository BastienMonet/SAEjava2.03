package fr.saejava;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;

public class ExecutableTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, Exception{

        System.out.println("entrer votre nom de base");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nomb = reader.readLine(); 
        System.out.println("entrer le nom du login");
        String noml = reader.readLine(); 
        System.out.println("entrer le mot de passe");
        String pwd = reader.readLine(); 
        try {
           
            ConnexionMySQL co = new ConnexionMySQL();
            // co.connecter(null, "DBmonet", "monet", "monet");
            co.connecter(null, nomb, noml, pwd);
            InterfaceCLI i = new InterfaceCLI(co);
            i.menuMain();
            i.close();

        } catch (Exception e){
            System.out.println("problème de connection avec la base, assurer vous bien\n d'avoir le bon driver jdbc:mysql://servinfo-maria:3306/ et changer le dans ConnexionMySQL.java");
            System.err.println(e.getMessage());
        }
        System.out.println("vous pouvez desormais quitter le script");
        

        

    }
}
