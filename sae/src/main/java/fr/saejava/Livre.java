import java.util.List;

public class Livre {
    private int isbn;
    private String titre;
    private int nbPages;
    private String datePubli;
    private int prix;
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
}
