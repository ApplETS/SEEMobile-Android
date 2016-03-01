package ca.etsmtl.applets.seemobile.model;

import java.util.Date;
import java.util.List;

/**
 * Created by gnut3ll4 on 22/12/15.
 */
public class Entrevue {

    private String guid;
    private String nomEmployeur;
    private String numeroPoste;
    private boolean posteAnnule;
    private String type;

    private Date dateEntrevue;

    private String lieu;
    private List<String> interviewers;
    private String remarque;
    private List<String> superviseurs;

    public Entrevue() {
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getNomEmployeur() {
        return nomEmployeur;
    }

    public void setNomEmployeur(String nomEmployeur) {
        this.nomEmployeur = nomEmployeur;
    }

    public String getNumeroPoste() {
        return numeroPoste;
    }

    public void setNumeroPoste(String numeroPoste) {
        this.numeroPoste = numeroPoste;
    }

    public boolean isPosteAnnule() {
        return posteAnnule;
    }

    public void setPosteAnnule(boolean posteAnnule) {
        this.posteAnnule = posteAnnule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateEntrevue() {
        return dateEntrevue;
    }

    public void setDateEntrevue(Date dateEntrevue) {
        this.dateEntrevue = dateEntrevue;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public List<String> getInterviewers() {
        return interviewers;
    }

    public void setInterviewers(List<String> interviewers) {
        this.interviewers = interviewers;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public List<String> getSuperviseurs() {
        return superviseurs;
    }

    public void setSuperviseurs(List<String> superviseurs) {
        this.superviseurs = superviseurs;
    }
}
