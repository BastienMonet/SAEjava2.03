package fr.saejava;

import java.util.List;

public class Catalogue {
    List<Utilisateur> utilisateur;
    List<Magasin> magasin;
    

    public Catalogue(List<Utilisateur> utilisateur, List<Magasin> magasin) {
        this.utilisateur = utilisateur;
        this.magasin = magasin;
    }


    public void ajouteUtilisateur(Utilisateur user){
        utilisateur.add(user);
    }

    public List<Utilisateur> getUtilisateur() {
        return utilisateur;
    }

    public List<Magasin> getMagasin() {
        return magasin;
    }
}
