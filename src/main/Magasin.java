import java.util.List;

public class Magasin {
    private int idMag;
    private String nomMag;
    private String villeMag;
    private List<Livre> livres;

    public Magasin(int idMag, String nomMag, String villeMag, List<Livre> livres) {
        this.livres = livres;
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
    public List<Livre> getLivres() {
        return livres;
    }

}
