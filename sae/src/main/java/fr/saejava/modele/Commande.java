package fr.saejava.modele;

import fr.saejava.modele.*;

import java.util.List;
import java.util.ArrayList;

public class Commande {
    private int numCom;
    private String dateCom;
    private Character enligne;
    private Character livraison;
    private List<CommandeUnit> listeCommandes;
    private Magasin mag;
    private Client client;


    /**
     * Constructeur de la classe Commande
     * @param numCom le numéro de la commande
     * @param dateCom la date de la commande
     * @param enligne 'O' si la commande est en ligne, 'N' sinon
     * @param livraison 'C' si la livraison est à domicile, 'M' si elle est au magasin
     * @param mag le magasin associé à la commande
     * @param c le client qui a passé la commande
     */
    public Commande(int numCom, String dateCom, Character enligne, Character livraison, Magasin mag, Client c) {
        this.numCom = numCom;
        this.dateCom = dateCom;
        this.enligne = enligne;
        this.livraison = livraison;
        this.listeCommandes = new ArrayList<>();
        this.mag = mag;
        this.client = c;
    }

    /**
     * Constructeur de la classe Commande sans paramètres
     */
    public int getNumCom() {
        return numCom;
    }

    /**
     * Retourne la date de la commande
     * @return la date de la commande
     */
    public String getDateCom() {
        return dateCom;
    }

    /**
     * Retourne si la commande est en ligne ou non
     * @return 'O' si la commande est en ligne, 'N' sinon
     */
    public Character enligne() {
        return enligne;
    }

    /**
     * Retourne si la livraison est à domicile ou au magasin
     * @return 'C' si la livraison est à domicile, 'M' si elle est au magasin
     */
    public Character getLivraison() {
        return livraison;
    }

    /**
     * Retourne la liste des commandes unitaires associées à cette commande
     * @return la liste des commandes unitaires
     */
    public List<CommandeUnit> getListeCommandes() {
        return listeCommandes;
    }

    /**
     * Retourne le client qui a passé la commande
     * @return le client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Retourne le magasin associé à la commande
     * @return le magasin
     */
    public Magasin getMagasin() {
        return mag;
    }

    /**
     * Retourne le nombre de commandes unitaires dans cette commande
     * @return le nombre de commandes unitaires
     */
    public void setNumCom(int numCom) {
        this.numCom = numCom;
    }

    /**
     * Définit la date de la commande
     * @param dateCom la date de la commande
     */
    public void setDateCom(String dateCom) {
        this.dateCom = dateCom;
    }

    /**
     * Définit si la commande est en ligne ou non
     * @param enligne 'O' si la commande est en ligne, 'N' sinon
     */
    public void setEnligne(char enligne) {
        this.enligne = enligne;
    }

    /**
     * Définit si la livraison est à domicile ou au magasin
     * @param livraison 'C' si la livraison est à domicile, 'M' si elle est au magasin
     */
    public void setLivraison(char livraison) {
        this.livraison = livraison;
    }

    /**
     * Définit le client qui a passé la commande
     * @param c le client
     */
    public void setListeCommandeUnit(List<CommandeUnit> cU){
        this.listeCommandes = cU;
    }

    /**
     * Ajoute une commande unitaire à la liste des commandes
     * @param commandeUnit
     */
    public void addCommandeUnit(CommandeUnit commandeUnit) {
        this.listeCommandes.add(commandeUnit);
    }

    /**
     * Retire la dernière commande unitaire de la liste des commandes
     */
    public void removeCommandeUnit() {
        if (! listeCommandes.isEmpty()) {
            this.listeCommandes.remove(listeCommandes.size() - 1);
        }
    }

    /**
     * Calcule le prix total de la commande en additionnant les prix de chaque commande unitaire
     * @return le prix total de la commande
     */
    public double prixTotCommande(){
        int total = 0;
        for (CommandeUnit comUnit : listeCommandes){
            total += comUnit.getPrixTotal();
        }
        return total;
    }

    /**
     * Ajoute une commande unitaire à la liste des commandes, en fusionnant les quantités si le livre existe déjà
     * @param commandeUnit la commande unitaire à ajouter
     */
    public void ajouterCommandeUnit(CommandeUnit commandeUnit) {
        if (this.listeCommandes.isEmpty()){
            this.listeCommandes.add(commandeUnit);
            return;
        }
        Integer qte = null;
        System.out.println("coucou");
        List<CommandeUnit> cp = new ArrayList<>(listeCommandes);
        for (CommandeUnit comU : cp){
            if (commandeUnit.getLivre().equals(comU.getLivre())){;
                qte = comU.getQte();
                this.listeCommandes.remove(comU);
            }
        }
        if (qte != null){
            commandeUnit.setQte(qte + commandeUnit.getQte());
            this.listeCommandes.add(commandeUnit);
        } else {
            this.listeCommandes.add(commandeUnit);
        }
    }

    // public void commander() throws IllegalArgumentException, Exception{
    //     /*
    //      * methode la plus delicate du sujet : il s'agit de modifier le nbre d'achat des magasins grace au commande en plus
    //      * de les retirer du stock
    //      */
    //     Magasin lemagasin = getMagasin();
    //     for (CommandeUnit comU : listeCommandes){
    //         Livre lelivre = comU.getLivre();
    //         int qte = comU.getQte();
    //         lelivre.incrementeAchat(qte);
    //         lemagasin.retireLivre(lelivre, qte);
    //     } 
    // }

    // public void renvoyer() throws IllegalArgumentException, Exception{
    //     Magasin lemagasin = getMagasin();
    //     for (CommandeUnit comU : listeCommandes){
    //         Livre lelivre = comU.getLivre();
    //         int qte = comU.getQte();
    //         lelivre.incrementeAchat(-qte);
    //         lemagasin.ajouteLivre(lelivre, qte);
    //     } 
    // }

    /**
     * Retourne une représentation textuelle de la commande
     * @return une chaîne de caractères représentant la commande
     */
    @Override
    public String toString() {
        String res = "la commande " + this.numCom;

        if (enligne == 'O'){
            res += " en ligne ";
        } else {
            res += " en magasin ";
        }
        if (livraison == 'C')
            res += "livré à " + client.getAdresseUtil();
        else 
            res += "livré au magasin " + mag.getNomMag();

        return res + " de " + mag.getNomMag();
    }
}
