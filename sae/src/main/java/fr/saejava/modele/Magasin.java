package fr.saejava.modele;

import fr.saejava.modele.*;


import java.util.Map;
import java.util.HashMap;

public class Magasin {
    private int idMag;
    private String nomMag;
    private String villeMag;

    public Magasin(int idMag, String nomMag, String villeMag) {
        this.idMag = idMag;
        this.nomMag = nomMag;
        this.villeMag = villeMag;
    }

    public int getIdMag() {
        return idMag;
    }
    public String getNomMag() {
        return nomMag;
    }
    public String getVilleMag() {
        return villeMag;
    }

    // public void ajouteLivre(Livre livre, int quantite) {
    //     if (livres.containsKey(livre)) {
    //         livres.put(livre, livres.get(livre) + quantite);
    //     } else {
    //         livres.put(livre, quantite);
    //     }
    // }

    // public Boolean isDispo(Livre livre) {
    //     return livres.containsKey(livre);
    // }

    // public void retireLivre(Livre livre, int qte) throws IllegalArgumentException, Exception {
    //     if (livre == null) {
    //         throw new IllegalArgumentException("Le livre ne peut pas être nul.");
    //     }
    //     if (livres.containsKey(livre)) {
    //         int quantite = livres.get(livre);
    //         if (quantite - qte > 0) {
    //             livres.put(livre, quantite - qte);
    //         } else if (quantite - qte == 0){
    //             livres.remove(livre);
    //         } else {
    //             throw new Exception("il n'y a pas assez de livre dans le magsin");
    //         }
    //     }
    //     else {
    //         throw new IllegalArgumentException("Ce livre n'est pas disponible dans ce magasin.");
    //     }
    // }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){return false;}
        if (obj == this){return true;}
        if (! (obj instanceof Livre)){return false;}
        Magasin tmp = (Magasin)obj;
        return tmp.idMag == this.idMag && tmp.nomMag.equals(this.nomMag) && tmp.villeMag.equals(this.villeMag);
    }
    

    @Override
    public int hashCode(){
        return idMag * 83;
    }

    @Override
    public String toString() {
        return  idMag + " " + nomMag;
        
    }


}



