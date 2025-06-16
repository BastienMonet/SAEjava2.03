package fr.saejava.modele;

import fr.saejava.modele.*;
public class Classification {
    private int ideway;
    private String nomClass;

    public Classification(int ideway, String nomClassification) {
        this.ideway = ideway;
        this.nomClass = nomClassification;
    }

    public int getIdeway() {
        return ideway;
    }

    public String getNomClass() {
        return nomClass;
    }
}
