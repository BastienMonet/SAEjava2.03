package fr.saejava.modele;


import fr.saejava.modele.*;

public class CommandeUnit {
    private int numliq;
    private int qte;
    private Livre livre;

    /**
     * Constructeur de la classe CommandeUnit
     * @param livre le livre associé à la commande
     * @param qte la quantité de livres commandés
     */
    public CommandeUnit(Livre livre, int qte) {
        this.qte = qte;
        this.livre = livre;
    }

    /**
     * Constructeur de la classe CommandeUnit avec un numéro de ligne
     * @param numliq le numéro de ligne de la commande
     * @param livre le livre associé à la commande
     * @param qte la quantité de livres commandés
     */
    public int getNumliq() {
        return numliq;
    }

    /**
     * Retourne la quantité de livres commandés
     * @return la quantité de livres commandés
     */
    public int getQte() {
        return qte;
    }

    /**
     * Retourne le livre associé à la commande
     * @return le livre associé à la commande
     */
    public void setNumliq(int numliq) {
        this.numliq = numliq;
    }

    /**
     * Définit la quantité de livres commandés
     * @param qte la quantité de livres commandés
     */
    public void setQte(int qte) {
        this.qte = qte;
    }

    /**
     * Définit le livre associé à la commande
     * @param livre le livre associé à la commande
     */
    public Livre getLivre() {
        return livre;
    }

    /**
     * Retourne le prix total de la commande
     * @return le prix total de la commande
     */
    public Double getPrixTotal() {
        return livre.getPrix() * qte;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la commande unitaire
     * @return une chaîne de caractères représentant la commande unitaire
     */
    @Override
    public String toString() {
        return qte + " " + livre + " " + getPrixTotal().toString() + "$";
    }
}
