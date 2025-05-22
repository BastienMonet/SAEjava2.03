package fr.saejava;


import java.util.List;

public class Livre {
    private int isbn;
    private String titre;
    private int nbPages;
    private int datePubli;
    private int prix;
    private int nbreAchats = 0;

    public Livre(int isbn, String titre, int nbPages, int datePubli, int prix) {
        this.isbn = isbn;
        this.titre = titre;
        this.nbPages = nbPages;
        this.datePubli = datePubli;
        this.prix = prix;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getTitre() {
        return titre;
    }

    public int getNbPages() {
        return nbPages;
    }

    public int getDatePubli() {
        return datePubli;
    }

    public int getPrix() {
        return prix;
    }

    public int getNbreAchats() {
        return nbreAchats;
    }

        
    public void setNbreAchats(int nbreAchats) {
        this.nbreAchats = nbreAchats;
    }

    public void incrementeAchat(int qte) throws Exception{
        /*
         * permet d'incrementer ou de decrementer le compteur d'achat nbre d'achat
         * @param : int la quantite correspondant au nombre de livre acheter
         */
        if (nbreAchats + qte >= 0)
            nbreAchats += qte;
        else 
            throw new Exception("impossible, il ne peut pas y avoir un nombre négatif d'achat");
    }


    @Override
    /*
     * ! a ameliorer mais ne surtout pas mettre un equals avec nbreD'achat : c'est là
     *   qu'est l'astuce pour conaitre le nombre de livre acheter dans un magasin
     */
    public boolean equals(Object obj) {
        if (obj == null){return false;}
        if (obj == this){return true;}
        if (! (obj instanceof Livre)){return false;}
        Livre tmp = (Livre)obj;
        return tmp.isbn == this.isbn;
    }
    

    @Override
    public int hashCode(){
        return isbn * 83;
    }

    @Override
    public String toString() {
        return titre + nbreAchats;
    }
}
