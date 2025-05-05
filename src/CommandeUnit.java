public class CommandeUnit {
    private int numliq;
    private int qte;
    private double prixVente;

    public CommandeUnit(int numliq, int qte, double prixVente) {
        this.numliq = numliq;
        this.qte = qte;
        this.prixVente = prixVente;
    }

    public int getNumliq() {
        return numliq;
    }

    public int getQte() {
        return qte;
    }
    public double getPrixVente() {
        return prixVente;
    }

    public void setNumliq(int numliq) {
        this.numliq = numliq;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }
}