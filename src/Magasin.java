import java.util.Map;
import java.util.HashMap;

public class Magasin {
    private int idMag;
    private String nomMag;
    private String villeMag;
    private Map<Livre,Integer> livres;

    public Magasin(int idMag, String nomMag, String villeMag) {
        this.idMag = idMag;
        this.nomMag = nomMag;
        this.villeMag = villeMag;
        this.livres = new HashMap<>();
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
    public Map<Livre,Integer> getLivres() {
        return livres;
    }

    public void addLivre(Livre livre, int quantite) {
        if (livres.containsKey(livre)) {
            livres.put(livre, livres.get(livre) + quantite);
        } else {
            livres.put(livre, quantite);
        }
    }


}
