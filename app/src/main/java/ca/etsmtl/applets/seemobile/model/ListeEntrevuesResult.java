package ca.etsmtl.applets.seemobile.model;

import java.util.List;

/**
 * Created by gnut3ll4 on 29/12/15.
 */
public class ListeEntrevuesResult extends BaseModel {

    List<Entrevue> entrevues;

    public ListeEntrevuesResult(Erreur erreur, List<Entrevue> entrevues) {
        super(erreur);
        this.entrevues = entrevues;
    }

    public List<Entrevue> getEntrevues() {
        return entrevues;
    }

    public void setEntrevues(List<Entrevue> entrevues) {
        this.entrevues = entrevues;
    }

    public void addAll(ListeEntrevuesResult listeEntrevues) {
        this.entrevues.addAll(listeEntrevues.getEntrevues());
    }
}
