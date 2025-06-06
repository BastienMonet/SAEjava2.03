package fr.saejava;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class InterfaceCLI {


    public static void menuCommander(Utilisateur u, Commande com) throws Exception{
        boolean finiCommande = false;
        while (! finiCommande){
            System.out.println("que voulez vous faire ?");
            System.out.println("1 - ajouter un achat");
            System.out.println("2 - faire les comptes");
            System.out.println("3 - finaliser la commande");
            System.out.println("4 - annuler la commande");
            BufferedReader r1 = new BufferedReader(new InputStreamReader(System.in));
            String res1 = r1.readLine();
            switch (res1){
                case ("1"):

                    System.out.println("quel livre souhaitez vous");
                    System.out.println("entrer le nom du livre");

                    Set<Livre> lstLivre = u.onVousRecommandeDansMagasin(com.getMagasin());

                    for (Livre l : lstLivre){
                        System.out.println(l);
                    }


                    BufferedReader r2 = new BufferedReader(new InputStreamReader(System.in));
                    String res2 = r2.readLine();
                    Livre livre = u.getLivreBDparTitre(res2);

                    System.out.println("il y a " + u.qteDansMagasin(livre, com.getMagasin()) + "fois l'exemplaire dans " + com.getMagasin());

                    System.out.println("combien de livre souhaitez vous acheter?");

                    System.out.println("! mettre une valeur plus grande que le nombre d'exemplaire annulera celle-ci !");

                    BufferedReader r3 = new BufferedReader(new InputStreamReader(System.in));
                    try{
                        Integer qte = Integer.valueOf(r3.readLine());

                        if (qte <= u.qteDansMagasin(livre, com.getMagasin())){
                            CommandeUnit comU = new CommandeUnit(livre, qte);
                            com.addCommandeUnit(comU);
                            System.out.println("votre achat a bien été ajoutée à la commande");
                        } else {
                            System.out.println("votre achat n'a pas été pris en compte");
                        }
                    } catch(Exception e){
                        finiCommande = true;
                    }

                    


                    break;
                case ("2"):
                    List<CommandeUnit> lstComU =  u.voirDetailCommande(com);
                    for (CommandeUnit cmuU : lstComU){
                        System.out.println(cmuU);
                    }
                    System.out.println("cela vous fait :" + com.prixTotCommande() + "$ pour l'instant" );
                    System.out.println(com);
                    break;
                case ("3"):
                    u.ajouteCommandeBD(com);
                    System.out.println("votre commande a bien été sauvegarder");
                    finiCommande = true;
                    break;
                case ("4"):
                    finiCommande = true;
                    break;
            }

        }

    }


    public static void menuClient(Client c) throws SQLException, Exception{
        boolean finiClient = false;
        System.out.println("connection reussi");
        System.out.println("bienvenue " + c );
        while (! finiClient){
            System.out.println();
            System.out.println("que souhaiter vous faire");
            System.out.println("1 - consulter le catalogue");
            System.out.println("2 - créer une commande");
            System.out.println("3 - consulter ses commande");
            System.out.println("4 - quitter ce compte");
            BufferedReader r5 = new BufferedReader(new InputStreamReader(System.in));
            String res5 = r5.readLine();
            switch (res5) {
                case ("1") :
                    Set<Livre> lstLivre = c.onVousRecommande();
                    for (Livre l : lstLivre){
                        System.out.println(l);
                    }
                    break;
                case ("2") :
                    System.out.println("4 - la commande est t-elle en ligne");
                    LocalDate myObj;
                    BufferedReader r6 = new BufferedReader(new InputStreamReader(System.in));
                    char res6 = r6.readLine().charAt(0);
                    System.out.println("4 - ou doit être fait la livraison");
                    BufferedReader r7 = new BufferedReader(new InputStreamReader(System.in));
                    char res7 = r7.readLine().charAt(0);
                    System.out.println("4 - dans quel magasin effectuer la commande");
                    List<Magasin> lstMag = c.voirToutLesMagasin();
                    System.out.println(lstMag);
                    BufferedReader r8 = new BufferedReader(new InputStreamReader(System.in));
                    Magasin res8 = c.getMagasinBDparNom(r8.readLine());

                    Commande com = new Commande(0, LocalDate.now().toString(), res6, res7, res8);
                    menuCommander(c, com);
                    break;

                case ("3") :
                    List<Commande> lstCommande = c.voirSesCommande();
                    for (Commande comande : lstCommande){
                        System.out.println(comande);
                    }
                    break;

                case ("4") :
                    finiClient = true;
                    break;

            }
            }

    }



    public static void main(String[] args) throws SQLException, ClassNotFoundException, Exception{
        ConnexionMySQL co = new ConnexionMySQL();
        // co.connecter(null, "DBmonet", "monet", "monet");
        co.connecter(null, "DBmonet", "root", "4dameorc");


        boolean fini = false;
        while (!fini){
            System.out.println("bonjour et bienvenue, selectionner l'action de votre choix");
            System.out.println("1 - Se connecter en tant que client");
            System.out.println("2 - Se connecter en tant qu'admin");
            System.out.println("3 - quitter");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            switch (input){
                case ("1") :
                    System.out.println("entrer votre nom d'utilisateur");
                    BufferedReader r2 = new BufferedReader(new InputStreamReader(System.in));
                    String username = r2.readLine();
                    System.out.println("entrer votre prenom");
                    BufferedReader r3 = new BufferedReader(new InputStreamReader(System.in));
                    String lastname = r3.readLine();
                    System.out.println("entrer votre mot de passe");
                    BufferedReader r4 = new BufferedReader(new InputStreamReader(System.in));
                    String pwd = r4.readLine();
                    try{
                        Client c = new Client(co);
                        boolean seco = c.seConnecter(username, lastname, pwd);
                        if (seco == true){
                            menuClient(c);
            
                        } else {
                            System.out.println("echec de la connection");
                        }

                    } catch (SQLException e) {
                        System.out.println("la base de donnée a rencontrer un problème");
                        System.err.println(e.getMessage());

                    }
                    break;
                case ("2") :
                    System.out.println("WIP");
                    break;
                case ("3") :
                    fini = true;
                    break;

            }
                
        }
        
        

        
        
        
        System.out.println("ça marche");
        
         
    }
}