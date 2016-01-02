package ca.etsmtl.applets.seemobile.view;

import ca.etsmtl.applets.seemobile.model.Poste;

/**
 * Created by gnut3ll4 on 31/12/15.
 */
public interface PosteView {

    void showProgress();

    void hideProgress();

    void setPoste(Poste poste);

}
