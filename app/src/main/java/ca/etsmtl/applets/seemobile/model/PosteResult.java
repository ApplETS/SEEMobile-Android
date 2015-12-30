package ca.etsmtl.applets.seemobile.model;

/**
 * Created by gnut3ll4 on 30/12/15.
 */
public class PosteResult extends BaseModel {

    Poste poste;

    public PosteResult(Erreur erreur, Poste poste) {
        super(erreur);
        this.poste = poste;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }
}