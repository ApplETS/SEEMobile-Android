package ca.etsmtl.applets.seemobile.model;

/**
 * Created by gnut3ll4 on 27/12/15.
 */
public class Credentials {

    private String codeAccesUniversel;
    private String motPasse;

    public Credentials() {
    }

    public Credentials(String codeAccesUniversel, String motPasse) {
        this.codeAccesUniversel = codeAccesUniversel;
        this.motPasse = motPasse;
    }

    public String getCodeAccesUniversel() {
        return codeAccesUniversel;
    }

    public void setCodeAccesUniversel(String codeAccesUniversel) {
        this.codeAccesUniversel = codeAccesUniversel;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }
}
