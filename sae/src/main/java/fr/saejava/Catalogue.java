package fr.saejava;

import java.util.List;
import java.util.Map;

public class Catalogue {
    List<Utilisateur> utilisateur;
    List<Magasin> magasin;
    

    public Catalogue(List<Utilisateur> utilisateur, List<Magasin> magasin) {
        this.utilisateur = utilisateur;
        this.magasin = magasin;
    }

    public List<Utilisateur> getUtilisateur() {
        return utilisateur;
    }

    public List<Magasin> getMagasin() {
        return magasin;
    }

    public void ajouterMagasin(Magasin mag){
        this.magasin.add(mag);
    }

    public void transfertLivre(Magasin mag1, Magasin mag2, Livre livre){
        Map<Livre, Integer> liMag1 = mag1.getLivres();
        Map<Livre, Integer> liMag2 = mag2.getLivres();
        liMag1.put(livre, liMag1.get(livre) - 1);
        liMag2.put(livre, liMag2.get(livre) + 1);
    }
}
