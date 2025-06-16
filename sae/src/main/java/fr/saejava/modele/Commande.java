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

    public Commande(int numCom, String dateCom, Character enligne, Character livraison, Magasin mag, Client c) {
        this.numCom = numCom;
        this.dateCom = dateCom;
        this.enligne = enligne;
        this.livraison = livraison;
        this.listeCommandes = new ArrayList<>();
        this.mag = mag;
        this.client = c;
    }

    public int getNumCom() {
        return numCom;
    }

    public String getDateCom() {
        return dateCom;
    }

    public Character enligne() {
        return enligne;
    }

    public Character getLivraison() {
        return livraison;
    }

    public List<CommandeUnit> getListeCommandes() {
        return listeCommandes;
    }


    public Magasin getMagasin() {
        return mag;
    }

    public void setNumCom(int numCom) {
        this.numCom = numCom;
    }

    public void setDateCom(String dateCom) {
        this.dateCom = dateCom;
    }

    public void setEnligne(char enligne) {
        this.enligne = enligne;
    }

    public void setLivraison(char livraison) {
        this.livraison = livraison;
    }

    public void setListeCommandeUnit(List<CommandeUnit> cU){
        this.listeCommandes = cU;
    }

    public void addCommandeUnit(CommandeUnit commandeUnit) {
        this.listeCommandes.add(commandeUnit);
    }

    public void removeCommandeUnit(CommandeUnit commandeUnit) {
        this.listeCommandes.remove(commandeUnit);
    }


    public double prixTotCommande(){
        int total = 0;
        for (CommandeUnit comUnit : listeCommandes){
            total += comUnit.getPrixTotal();
        }
        return total;
    }

    public void ajouterCommandeUnit(CommandeUnit commandeUnit) {
        this.listeCommandes.add(commandeUnit);
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
            res += "livré sur place";

        return res + " de " + mag.getNomMag();
    }
}
