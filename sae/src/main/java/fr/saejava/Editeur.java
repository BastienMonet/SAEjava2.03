package fr.saejava;


public class Editeur {
    private int idEdit;
    private String nomEdit;

    public Editeur(int idEdit, String nomEdit) {
        this.idEdit = idEdit;
        this.nomEdit = nomEdit;
    }

    public int getIdEdit() {
        return idEdit;
    }

    public void setIdEdit(int idEdit) {
        this.idEdit = idEdit;
    }

    public String getNomEdit() {
        return nomEdit;
    }

    public void setNomEdit(String nomEdit) {
        this.nomEdit = nomEdit;
    }

    
    
}
