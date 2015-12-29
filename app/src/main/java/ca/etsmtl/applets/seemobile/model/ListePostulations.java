package ca.etsmtl.applets.seemobile.model;

import java.util.List;

/**
 * Created by gnut3ll4 on 29/12/15.
 */
public class ListePostulations extends BaseModel {

    List<Postulation> postulationList;

    public ListePostulations(Erreur erreur, List<Postulation> postulationList) {
        super(erreur);
        this.postulationList = postulationList;
    }

    public List<Postulation> getPostulationList() {
        return postulationList;
    }

    public void setPostulationList(List<Postulation> postulationList) {
        this.postulationList = postulationList;
    }

    public void addAll(ListePostulations listePostulations) {
        this.postulationList.addAll(listePostulations.getPostulationList());
    }
}
