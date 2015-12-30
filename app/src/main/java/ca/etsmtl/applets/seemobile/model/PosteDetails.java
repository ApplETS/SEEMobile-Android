package ca.etsmtl.applets.seemobile.model;

import java.util.Date;

/**
 * Created by gnut3ll4 on 30/12/15.
 */
public class PosteDetails {

    private String codePostal;
    private String coordonnateur;
    private Date datePostulationLimite;
    private String description;
    private String langueCv;
    private String missionEmployeur;
    private String siteWebEmployeur;
    private String typeEmployeur;

    public PosteDetails() {
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
