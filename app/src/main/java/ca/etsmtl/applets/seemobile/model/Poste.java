package ca.etsmtl.applets.seemobile.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

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

    @DatabaseField
    private String codePostal;
    @DatabaseField
    private String coordonnateur;
    @DatabaseField
    private Date datePostulationLimite;
    @DatabaseField
    private String description;
    @DatabaseField
    private String langueCv;
    @DatabaseField
    private String missionEmployeur;
    @DatabaseField
    private String siteWebEmployeur;
    @DatabaseField
    private String typeEmployeur;

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

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getCoordonnateur() {
        return coordonnateur;
    }

    public void setCoordonnateur(String coordonnateur) {
        this.coordonnateur = coordonnateur;
    }

    public Date getDatePostulationLimite() {
        return datePostulationLimite;
    }

    public void setDatePostulationLimite(Date datePostulationLimite) {
        this.datePostulationLimite = datePostulationLimite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLangueCv() {
        return langueCv;
    }

    public void setLangueCv(String langueCv) {
        this.langueCv = langueCv;
    }

    public String getMissionEmployeur() {
        return missionEmployeur;
    }

    public void setMissionEmployeur(String missionEmployeur) {
        this.missionEmployeur = missionEmployeur;
    }

    public String getSiteWebEmployeur() {
        return siteWebEmployeur;
    }

    public void setSiteWebEmployeur(String siteWebEmployeur) {
        this.siteWebEmployeur = siteWebEmployeur;
    }

    public String getTypeEmployeur() {
        return typeEmployeur;
    }

    public void setTypeEmployeur(String typeEmployeur) {
        this.typeEmployeur = typeEmployeur;
    }
}
