package fr.saejava.modele;

import fr.saejava.modele.*;
public class Auteur {
    private int idAuteur;
    private String nomAuteur;
    private String anneeNais;
    private String anneeDeces;

    /**
     * Constructeur de la classe Auteur
     * @param idAuteur l'identifiant de l'auteur
     * @param nomAuteur le nom de l'auteur
     * @param anneeNais l'année de naissance de l'auteur
     * @param anneeDeces l'année de décès de l'auteur
     */
    public Auteur(int idAuteur, String nomAuteur, String anneeNais, String anneeDeces) {
        this.idAuteur = idAuteur;
        this.nomAuteur = nomAuteur;
        this.anneeNais = anneeNais;
        this.anneeDeces = anneeDeces;
    }

    /**
     * Constructeur de la classe Auteur sans paramètres
     */
    public int getIdAuteur() {
        return idAuteur;
    }

    /**
     * Constructeur de la classe Auteur sans paramètres
     */
    public String getNomAuteur() {
        return nomAuteur;
    }

    /**
     * Constructeur de la classe Auteur sans paramètres
     */
    public String getAnneeNais() {
        return anneeNais;
    }

    /**
     * Constructeur de la classe Auteur sans paramètres
     */
    public String getAnneeDeces() {
        return anneeDeces;
    }
}