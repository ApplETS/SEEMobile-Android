package ca.etsmtl.applets.seemobile.model;

import java.util.List;

/**
 * Created by gnut3ll4 on 29/02/16.
 */
public class ListePostesResult extends BaseModel {

    List<Poste> postesList;

    public ListePostesResult(Erreur erreur, List<Poste> postesList) {
        super(erreur);
        this.postesList = postesList;
    }

    public List<Poste> getPostesList() {
        return postesList;
    }

    public void setPostesList(List<Poste> postesList) {
        this.postesList = postesList;
    }

    public void addAll(ListePostesResult listePostesResult) {
        this.postesList.addAll(listePostesResult.getPostesList());
    }

}
