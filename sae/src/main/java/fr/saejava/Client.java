public class Client extends Utilisateur {
    
    private String adresseUtil;
    private int codePostal;
    private String villeUtil;
    private double monnaie;


    public Client(int idUtil, String nomUtil, String prenomUtil, String pwd, String adresseUtil, int codePostal,
            String villeUtil) {
        super(idUtil, nomUtil, prenomUtil, pwd);
        this.adresseUtil = adresseUtil;
        this.codePostal = codePostal;
        this.villeUtil = villeUtil;
    }
    public String getAdresseUtil() {
        return adresseUtil;
    }
    public int getCodePostal() {
        return codePostal;
    }
    public String getVilleUtil() {
        return villeUtil;
    }
    public void setAdresseUtil(String adresseUtil) {
        this.adresseUtil = adresseUtil;
    }
    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }
    public void setVilleUtil(String villeUtil) {
        this.villeUtil = villeUtil;
    }

    public void ajouteMonnaie(double montant){
        this.monnaie += montant;
    }


}
