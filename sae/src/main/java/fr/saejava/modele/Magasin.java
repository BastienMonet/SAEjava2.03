package fr.saejava.modele;

import fr.saejava.modele.*;


import java.util.Map;
import java.util.HashMap;

public class Magasin {
    private int idMag;
    private String nomMag;
    private String villeMag;

    /**
     * Constructeur de la classe Magasin
     * @param idMag
     * @param nomMag
     * @param villeMag
     */
    public Magasin(int idMag, String nomMag, String villeMag) {
        this.idMag = idMag;
        this.nomMag = nomMag;
        this.villeMag = villeMag;
    }

    /**
     * Constructeur de la classe Magasin sans paramètres
     */
    public int getIdMag() {
        return idMag;
    }

    /**
     * Retourne l'identifiant du magasin
     * @return l'identifiant du magasin
     */
    public String getNomMag() {
        return nomMag;
    }

    /**
     * Retourne le nom du magasin
     * @return le nom du magasin
     */
    public String getVilleMag() {
        return villeMag;
    }

    /**
     * Retourne la ville du magasin
     * @return la ville du magasin
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null){return false;}
        if (obj == this){return true;}
        if (! (obj instanceof Livre)){return false;}
        Magasin tmp = (Magasin)obj;
        return tmp.idMag == this.idMag && tmp.nomMag.equals(this.nomMag) && tmp.villeMag.equals(this.villeMag);
    }
    
    /**
     * Retourne le code de hachage du magasin
     * @return le code de hachage du magasin
     */
    @Override
    public int hashCode(){
        return idMag * 83;
    }

    /**
     * Définit l'identifiant du magasin
     * @param idMag l'identifiant du magasin
     */
    @Override
    public String toString() {
        return  idMag + " " + nomMag;
        
    }


}



