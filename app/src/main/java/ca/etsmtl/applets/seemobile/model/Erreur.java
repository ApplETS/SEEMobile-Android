package ca.etsmtl.applets.seemobile.model;

/**
 * Created by gnut3ll4 on 29/12/15.
 */
public class Erreur {

    int code;
    String description;
    boolean estVoulue;
    String nom;

    public Erreur() {
    }

    public Erreur(int code, String description, boolean estVoulue, String nom) {
        this.code = code;
        this.description = description;
        this.estVoulue = estVoulue;
        this.nom = nom;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEstVoulue() {
        return estVoulue;
    }

    public void setEstVoulue(boolean estVoulue) {
        this.estVoulue = estVoulue;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
