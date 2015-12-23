package ca.etsmtl.applets.seemobile.model;

/**
 * Created by gnut3ll4 on 22/12/15.
 */
public class Poste {

    private String numeroPoste;
    private boolean aPostule;
    private String nomEmployeur;
    private int duree;
    private String guid;
    private String nomPoste;

    public Poste() {
    }

    public String getNumeroPoste() {
        return numeroPoste;
    }

    public void setNumeroPoste(String numeroPoste) {
        this.numeroPoste = numeroPoste;
    }

    public boolean isaPostule() {
        return aPostule;
    }

    public void setaPostule(boolean aPostule) {
        this.aPostule = aPostule;
    }

    public String getNomEmployeur() {
        return nomEmployeur;
    }

    public void setNomEmployeur(String nomEmployeur) {
        this.nomEmployeur = nomEmployeur;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getNomPoste() {
        return nomPoste;
    }

    public void setNomPoste(String nomPoste) {
        this.nomPoste = nomPoste;
    }
}
