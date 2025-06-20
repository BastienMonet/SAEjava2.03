package fr.saejava.modele;

import fr.saejava.modele.*;
public class Classification {
    private int ideway;
    private String nomClass;

    /**
     * Constructeur de la classe Classification
     * @param ideway l'identifiant de la classification
     * @param nomClassification le nom de la classification
     */
    public Classification(int ideway, String nomClassification) {
        this.ideway = ideway;
        this.nomClass = nomClassification;
    }

    /**
     * Constructeur de la classe Classification sans paramètres
     */
    public int getIdeway() {
        return ideway;
    }

    /**
     * Constructeur de la classe Classification sans paramètres
     */
    public String getNomClass() {
        return nomClass;
    }
}
