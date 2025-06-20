package fr.saejava.modele;

import fr.saejava.modele.*;


import java.util.List;

public class Livre {
    private int isbn;
    private String titre;
    private int nbPages;
    private int datePubli;
    private double prix = 0;
    private int nbreAchats = 0;

    /**
     * Constructeur de la classe Livre
     * @param isbn le numéro ISBN du livre
     * @param titre le titre du livre
     * @param nbPages le nombre de pages du livre
     * @param datePubli l'année de publication du livre
     * @param prix le prix du livre
     * @param nbAchats le nombre d'achats du livre
     */
    public Livre(int isbn, String titre, int nbPages, int datePubli, double prix, int nbAchats) {
        this.isbn = isbn;
        this.titre = titre;
        this.nbPages = nbPages;
        this.datePubli = datePubli;
        this.prix = prix;
        this.nbreAchats = nbAchats;
    }

    /**
     * Constructeur de la classe Livre sans paramètres
     */
    public int getIsbn() {
        return isbn;
    }

    /**
     * Retourne le numéro ISBN du livre
     * @return le numéro ISBN du livre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Retourne le titre du livre  
     * @return le titre du livre
     */
    public int getNbPages() {
        return nbPages;
    }

    /**
     * Retourne le nombre de pages du livre
     * @return le nombre de pages du livre
     */
    public int getDatePubli() {
        return datePubli;
    }

    /**
     * Retourne l'année de publication du livre
     * @return l'année de publication du livre
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Retourne le prix du livre
     * @return le prix du livre
     */
    public int getNbreAchats() {
        return nbreAchats;
    }

    /**
     * Retourne le nombre d'achats du livre
     * @return le nombre d'achats du livre
     */    
    public void setNbreAchats(int nbreAchats) {
        this.nbreAchats = nbreAchats;
    }

    /**
     * Définit le nombre d'achats du livre
     * @param nbreAchats le nombre d'achats du livre
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null){return false;}
        if (obj == this){return true;}
        if (! (obj instanceof Livre)){return false;}
        Livre tmp = (Livre)obj;
        return tmp.isbn == this.isbn;
    }
    
    /**
     * Vérifie si deux livres sont égaux en comparant leur numéro ISBN
     * @param obj l'objet à comparer
     * @return true si les livres sont égaux, false sinon
     */
    @Override
    public int hashCode(){
        return isbn * 83;
    }

    /**
     * Retourne le code de hachage du livre
     * @return le code de hachage du livre
     */
    @Override
    public String toString() {
        return titre + " " + prix + " " + "$ ";
    }
}
