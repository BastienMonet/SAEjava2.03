package fr.saejava;


import java.util.List;

public class Livre {
    private int isbn;
    private String titre;
    private int nbPages;
    private String datePubli;
    private int prix;
    private int nbreAchats = 0;
    private List<Auteur> auteurs;
    private List<Classification> classification;
    private List<Editeur> editeurs;

    public Livre(int isbn, String titre, int nbPages, String datePubli, int prix, List<Auteur> auteurs, List<Classification> classification, List<Editeur> editeurs) {
        this.isbn = isbn;
        this.titre = titre;
        this.nbPages = nbPages;
        this.datePubli = datePubli;
        this.prix = prix;
        this.auteurs = auteurs;
        this.classification = classification;
        this.editeurs = editeurs;
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

    public String getDatePubli() {
        return datePubli;
    }

    public int getPrix() {
        return prix;
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public List<Classification> getClassification() {
        return classification;
    }

    public List<Editeur> getEditeurs() {
        return editeurs;
    }


    public int getNbreAchats() {
        return nbreAchats;
    }

        
    public void setNbreAchats(int nbreAchats) {
        this.nbreAchats = nbreAchats;
    }

    public void incrementeAchat(int qte){
        if (nbreAchats + qte > 0)
            nbreAchats += qte;
        else 
            nbreAchats = 0;
    }


    @Override
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
