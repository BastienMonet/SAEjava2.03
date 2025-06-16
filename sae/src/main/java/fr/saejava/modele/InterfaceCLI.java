package fr.saejava.modele;


import fr.saejava.modele.*;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class InterfaceCLI {


    private Client cli;
    private Administrateur adm;
    private Vendeur vend;
    private ConnexionMySQL co;


    public InterfaceCLI(ConnexionMySQL co) throws Exception{
        this.co = co;
        cli = new Client(co);
        vend = new Vendeur(co);
        adm = new Administrateur(co);
    }

    public void close() throws SQLException {
        co.close();
    }
    
    public static void menuCommander(Utilisateur u, Commande com) throws Exception{
        boolean finiCommande = false;
        while (! finiCommande){
            System.out.println("que voulez vous faire ?");
            System.out.println("1 - ajouter un achat");
            System.out.println("2 - faire les comptes");
            System.out.println("3 - finaliser la commande");
            System.out.println("4 - annuler la commande");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String res1 = reader.readLine();
            switch (res1){
                case ("1"):

                    System.out.println("quel livre souhaitez vous");
                    System.out.println("entrer le nom du livre");

                    List<Livre> lstLivre = u.onVousRecommandeDansMagasin(com.getMagasin());

                    for (Livre l : lstLivre){
                        System.out.println(l);
                    }

                    String res2 = reader.readLine();
                    Livre livre = u.getLivreBDparTitre(res2);

                    System.out.println("il y a " + u.qteDansMagasin(livre, com.getMagasin()) + " fois l'exemplaire dans " + com.getMagasin());

                    System.out.println("combien de livre souhaitez vous acheter?");

                    System.out.println("! mettre une valeur plus grande que le nombre d'exemplaire annulera celle-ci !");

                    try{
                        Integer qte = Integer.valueOf(reader.readLine());

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
                    System.out.println("cela vous fait :" + com.prixTotCommande() + " $ pour l'instant" );
                    System.out.println(com);
                    break;
                case ("3"):
                    if (com.getListeCommandes().size() == 0){
                        System.out.println("vous n'avez rien commander");
                    } else {
                        u.ajouteCommandeBD(com);
                        System.out.println("votre commande a bien été sauvegarder");
                    }
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
            System.out.println("3 - consulter ses commandes");
            System.out.println("4 - supprimer une commande");
            System.out.println("5 - quitter ce compte");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String res5 = reader.readLine();
            switch (res5) {
                case ("1") :
                    List<Livre> lstLivre = c.onVousRecommande();
                    for (Livre l : lstLivre){
                        System.out.println(l);
                    }
                    break;
                case ("2") :
                    System.out.println("4 - la commande est t-elle en ligne (en ligne : O) / (sur place : N)");
                    char res6 = reader.readLine().charAt(0);
                    System.out.println("4 - ou doit être fait la livraison (livraison a domicile : C ) / (livraison en magasin M)");
                    char res7 = reader.readLine().charAt(0);
                    System.out.println("4 - dans quel magasin effectuer la commande (tapper son nom)");
                    List<Magasin> lstMag = c.voirToutLesMagasin();
                    System.out.println(lstMag);
                    try {
                        Magasin res8 = c.getMagasinBDparNom(reader.readLine());
                        Commande com = new Commande(0, LocalDate.now().toString(), res6, res7, res8, c.getUtilisateurParId(c.idUtil));
                        menuCommander(c, com);
                    } catch (Exception e){
                        System.err.println(e.getMessage());
                        System.err.println("une erreur c'est produite");
                    }
                    
                    break;

                case ("3") :
                    List<Commande> lstCommande = c.voirSesCommande();
                    for (Commande comande : lstCommande){
                        System.out.println(comande);
                    }
                    break;
                
                case ("4"):
                    List<Commande> lstCommande1 = c.voirSesCommande();
                    for (Commande comande : lstCommande1){
                        System.out.println(comande);
                    }

                    System.out.println("laquel voulez vous supprimer (entrer son numero)");
                    
                    
                    try {
                        int comSuppr = Integer.valueOf(reader.readLine());
                        Commande comm = c.getCommande(comSuppr);
                        c.retireSaCommande(comm);

                        System.out.println("la commande a bien été retirer");
                    } catch (Exception e){
                        System.out.println("le numero que vous avez demander ne correspond pas");
                    }


                    break;
                

                case ("5") :
                    finiClient = true;
                    break;

            }
            }

    }


    public static void menuAdmin(Administrateur a) throws SQLException, Exception{
        boolean finiAdmin = false;
        System.out.println("connection reussi");
        System.out.println("bienvenue " + a );
        while (! finiAdmin){
            System.out.println();
            System.out.println("que souhaitez vous faire");
            System.out.println("1 - ajouter un magasin");
            System.out.println("2 - ajouter une livre");
            System.out.println("3 - ajouter / retirer un livre dans un magasin");
            System.out.println("4 - quitter");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            switch (input) {
                case ("1"):
                    System.out.println("la base de donnée comporte ces magasin");
                    System.out.println(a.voirToutLesMagasin());
                    

                    System.out.println("entrer le nom du magasin");
                    String nom = reader.readLine();
                    System.out.println("entrer la ville ou se situe le magasin");
                    String nomville = reader.readLine();

                    try {
                        a.ajouteMagasinBD(new Magasin(0, nom, nomville));

                        System.out.println("ajout réussi");
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        System.out.println("une erreur c'est produite");
                    }
                    break;
                case ("2"):
                    System.out.println("la base de donnée comporte ces livres");
                    System.out.println(a.voirToutLesLivres());

                    System.out.println("entrer le titre du livre");
                    String noml = reader.readLine();
                    System.out.println("entrer le nombre de page du livre");
                    int nbpl = Integer.valueOf(reader.readLine());
                    System.out.println("entrer l'année de la publication");
                    int annpublil = Integer.valueOf(reader.readLine());
                    System.out.println("entrer le prix ");
                    double prix = Double.valueOf(reader.readLine());
                    

                    try {
                        a.ajouteLivreBD(new Livre(0, noml, nbpl, annpublil, prix, 0));
                        System.out.println("ajout réussi");
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        System.out.println("une erreur c'est produite");
                    }
                    break;
                case ("3"):
                    System.out.println("quel livre? entrer son nom");
                    System.out.println(a.voirToutLesLivres());
                    String nomlivre = reader.readLine();

                    System.out.println("dans quel magasin? enter son nom");
                    System.out.println(a.voirToutLesMagasin());
                    String nomMag = reader.readLine();


                    try {

                        System.out.println("il y a actuellement " + a.qteDansMagasin(a.getLivreBDparTitre(nomlivre), a.getMagasinBDparNom(nomMag)) + " fois l'exemplaire dans ce magasin");
                        System.out.println("quel quantité voulez vous?");
                        String qte = reader.readLine();

                        System.out.println("ajouter ou retirer A/R");
                        String action = reader.readLine();

                        if (action.equals("A")) {
                            
                            a.ajouteLivreDansMagasin(a.getMagasinBDparNom(nomMag), a.getLivreBDparTitre(nomlivre), Integer.valueOf(qte));
                            System.out.println("ajout effectif");
                            
                        }
                        else if (action.equals("R")) {
                            a.retireLivreDansMagasin(a.getMagasinBDparNom(nomMag), a.getLivreBDparTitre(nomlivre), Integer.valueOf(qte));
                            System.out.println("retrait effectif");
                        }
                        else {
                            System.out.println("votre demmande est éronner, séléctionné A pour ajouter ou R pour retirer");
                        } 
                    } catch (Exception e){
                        System.err.println(e.getMessage());
                        System.err.println("une erreur c'est produite");
                    }
                    
                    break;
                case ("4"):
                    finiAdmin = true;
                    break;
            }
            
        }
    }



    public void menuMain() throws Exception{

        boolean fini = false;
        while (!fini){
            System.out.println("bonjour et bienvenue, selectionner l'action de votre choix");
            System.out.println("1 - Se connecter en tant que client");
            System.out.println("2 - Se connecter en tant qu'admin");
            System.out.println("3 - quitter");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            if (input.equals("1") || input.equals("2")){
                System.out.println("entrer votre nom d'utilisateur");
            
                String username = reader.readLine();
                System.out.println("entrer votre prenom");
                String lastname = reader.readLine();
                System.out.println("entrer votre mot de passe");
                String pwd = reader.readLine();

                    try{
                        boolean seco;

                        switch(input){
                            case ("1"):
                                seco = cli.seConnecter(username, lastname, pwd);
                                if (seco == true){
                                    menuClient(cli);
                    
                                } else {
                                    System.out.println("echec de la connection");
                                }
                            
                            case("2"):
                                seco = adm.seConnecter(username, lastname, pwd);
                                    if (seco == true){
                                        menuAdmin(adm);
                        
                                    } else {
                                        System.out.println("echec de la connection");
                                    }


                        } 
                        
                    } catch (SQLException e) {
                            System.out.println("la base de donnée a rencontrer un problème");
                            System.err.println(e.getMessage());
                    }
                    
            } else if (input.equals("3")){
                fini = true;
            }

            }
    }
}