package ca.etsmtl.applets.seemobile.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by gnut3ll4 on 22/12/15.
 */
@DatabaseTable(tableName = "poste")
public class Poste {

    @DatabaseField(id = true)
    private String guid;

    @DatabaseField
    private int duree;

    @DatabaseField
    private boolean estFavori;

    @DatabaseField
    private String lieu;

    @DatabaseField
    private String numeroPoste;

    @DatabaseField
    private String nomEmployeur;

    @DatabaseField
    private String nomPoste;

    @DatabaseField
    private boolean aPostule;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private PosteDetails details;

    public Poste() {
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public boolean isEstFavori() {
        return estFavori;
    }

    public void setEstFavori(boolean estFavori) {
        this.estFavori = estFavori;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getNumeroPoste() {
        return numeroPoste;
    }

    public void setNumeroPoste(String numeroPoste) {
        this.numeroPoste = numeroPoste;
    }

    public String getNomEmployeur() {
        return nomEmployeur;
    }

    public void setNomEmployeur(String nomEmployeur) {
        this.nomEmployeur = nomEmployeur;
    }

    public String getNomPoste() {
        return nomPoste;
    }

    public void setNomPoste(String nomPoste) {
        this.nomPoste = nomPoste;
    }

    public boolean isaPostule() {
        return aPostule;
    }

    public void setaPostule(boolean aPostule) {
        this.aPostule = aPostule;
    }

    public PosteDetails getDetails() {
        return details;
    }

    public void setDetails(PosteDetails details) {
        this.details = details;
    }
}
