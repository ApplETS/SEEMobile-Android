package ca.etsmtl.applets.seemobile.view;

import java.util.List;

import ca.etsmtl.applets.seemobile.model.Entrevue;
import ca.etsmtl.applets.seemobile.model.Poste;

/**
 * Created by gnut3ll4 on 29/02/16.
 */
public interface EntrevuesView {

    void showProgress();

    void hideProgress();

    void setItems(List<Entrevue> entrevues);

    void showMessage(String message);
}
