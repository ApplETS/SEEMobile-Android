package ca.etsmtl.applets.seemobile.view;

import java.util.List;

import ca.etsmtl.applets.seemobile.model.Poste;

/**
 * Created by gnut3ll4 on 29/02/16.
 */
public interface StagesView {

    void showProgress();

    void hideProgress();

    void setItems(List<Poste> postes);

    void showMessage(String message);
}
