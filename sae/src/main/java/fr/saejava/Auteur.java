package fr.saejava;


public class Auteur {
    private int idAuteur;
    private String nomAuteur;
    private String anneeNais;
    private String anneeDeces;

    public Auteur(int idAuteur, String nomAuteur, String anneeNais, String anneeDeces) {
        this.idAuteur = idAuteur;
        this.nomAuteur = nomAuteur;
        this.anneeNais = anneeNais;
        this.anneeDeces = anneeDeces;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public String getAnneeNais() {
        return anneeNais;
    }

    public String getAnneeDeces() {
        return anneeDeces;
    }
}