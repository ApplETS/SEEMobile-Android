package ca.etsmtl.applets.seemobile.model;

/**
 * Created by gnut3ll4 on 29/12/15.
 */
public class BaseModel {

    Erreur erreur;

    public BaseModel(Erreur erreur) {
        this.erreur = erreur;
    }

    public Erreur getErreur() {
        return erreur;
    }

    public void setErreur(Erreur erreur) {
        this.erreur = erreur;
    }
}
