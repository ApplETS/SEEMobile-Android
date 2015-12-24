package ca.etsmtl.applets.seemobile.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by gnut3ll4 on 21/12/15.
 */
@DatabaseTable(tableName = "postulation")
public class Postulation {

    @DatabaseField(id = true)
    private String guidPoste;

    @DatabaseField
    private String typePlacement;

    @DatabaseField
    private String numeroPoste;

    @DatabaseField
    private String nomPoste;

    @DatabaseField
    private String nomEmployeur;

    @DatabaseField
    private String statut;

    @DatabaseField
    private boolean active;

    public Postulation() {
    }

    public Postulation(String guidPoste, String typePlacement, String numeroPoste, String nomPoste, String nomEmployeur, String statut, boolean active) {
        this.guidPoste = guidPoste;
        this.typePlacement = typePlacement;
        this.numeroPoste = numeroPoste;
        this.nomPoste = nomPoste;
        this.nomEmployeur = nomEmployeur;
        this.statut = statut;
        this.active = active;
    }

    public String getGuidPoste() {
        return guidPoste;
    }

    public void setGuidPoste(String guidPoste) {
        this.guidPoste = guidPoste;
    }

    public String getTypePlacement() {
        return typePlacement;
    }

    public void setTypePlacement(String typePlacement) {
        this.typePlacement = typePlacement;
    }

    public String getNumeroPoste() {
        return numeroPoste;
    }

    public void setNumeroPoste(String numeroPoste) {
        this.numeroPoste = numeroPoste;
    }

    public String getNomPoste() {
        return nomPoste;
    }

    public void setNomPoste(String nomPoste) {
        this.nomPoste = nomPoste;
    }

    public String getNomEmployeur() {
        return nomEmployeur;
    }

    public void setNomEmployeur(String nomEmployeur) {
        this.nomEmployeur = nomEmployeur;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
