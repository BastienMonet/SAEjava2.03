package fr.saejava;

import java.util.List;
import java.util.ArrayList;

public class Commande {
    private int numCom;
    private String dateCom;
    private boolean enligne;
    private char livraison;
    private String dateArrivee;
    private List<CommandeUnit> listeCommandes;

    public Commande(int numCom, String dateCom, boolean enligne, char livraison, String dateArrivee) {
        this.numCom = numCom;
        this.dateCom = dateCom;
        this.enligne = enligne;
        this.livraison = livraison;
        this.dateArrivee = dateArrivee;
        this.listeCommandes = new ArrayList<>();
    }

    public int getNumCom() {
        return numCom;
    }

    public String getDateCom() {
        return dateCom;
    }

    public boolean enligne() {
        return enligne;
    }

    public char getLivraison() {
        return livraison;
    }

    public String getDateArrivee() {
        return dateArrivee;
    }

    public List<CommandeUnit> getListeCommandes() {
        return listeCommandes;
    }

    public void setNumCom(int numCom) {
        this.numCom = numCom;
    }

    public void setDateCom(String dateCom) {
        this.dateCom = dateCom;
    }

    public void setEnligne(boolean enligne) {
        this.enligne = enligne;
    }

    public void setLivraison(char livraison) {
        this.livraison = livraison;
    }

    public void setDateArrivee(String dateArrivee) {
        this.dateArrivee = dateArrivee;
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

    public void commander(){
        for (CommandeUnit comUnit : listeCommandes){
            int qte = comUnit.getQte();
            comUnit.getLivre().incrementeAchat(qte);
        }
    }

    public void renvoyer(){
        for (CommandeUnit comUnit : listeCommandes){
            int qte = comUnit.getQte();
            if (comUnit.getLivre().getNbreAchats() - qte > 0)
                comUnit.getLivre().incrementeAchat(-qte);
            else {
                comUnit.getLivre().setNbreAchats(0);
            }
        }
    }

    @Override
    public String toString() {
        return "la commande : " + numCom + "contient : " + listeCommandes;
    }
}
