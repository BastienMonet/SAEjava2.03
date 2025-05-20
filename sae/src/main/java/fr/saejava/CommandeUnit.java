package fr.saejava;


public class CommandeUnit {
    private int numliq;
    private int qte;
    private Livre livre;


    public CommandeUnit(Livre livre, int qte) {
        this.qte = qte;
    }

    public int getNumliq() {
        return numliq;
    }

    public int getQte() {
        return qte;
    }

    public void setNumliq(int numliq) {
        this.numliq = numliq;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
        public Livre getLivre() {
        return livre;
    }

    public int getPrixTotal() {
        return livre.getPrix() * qte;
    }

    @Override
    public String toString() {
        return qte + " " + livre;
    }
}
