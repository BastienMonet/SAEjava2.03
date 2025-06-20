package fr.saejava.modele;


import fr.saejava.modele.*;

public class Editeur {
    private int idEdit;
    private String nomEdit;

    /**
     * Constructeur de la classe Editeur
     * @param idEdit l'identifiant de l'éditeur
     * @param nomEdit le nom de l'éditeur
     */
    public Editeur(int idEdit, String nomEdit) {
        this.idEdit = idEdit;
        this.nomEdit = nomEdit;
    }

    /**
     * Constructeur de la classe Editeur sans paramètres
     */
    public int getIdEdit() {
        return idEdit;
    }

    /**
     * Retourne l'identifiant de l'éditeur
     * @return l'identifiant de l'éditeur
     */
    public void setIdEdit(int idEdit) {
        this.idEdit = idEdit;
    }

    /**
     * Retourne le nom de l'éditeur
     * @return le nom de l'éditeur
     */
    public String getNomEdit() {
        return nomEdit;
    }

    /**
     * Définit le nom de l'éditeur
     * @param nomEdit le nom de l'éditeur
     */
    public void setNomEdit(String nomEdit) {
        this.nomEdit = nomEdit;
    }

    
    
}
