package ca.etsmtl.applets.seemobile.view;

import java.util.List;

import ca.etsmtl.applets.seemobile.model.Postulation;

/**
 * Created by gnut3ll4 on 20/12/15.
 */
public interface PostulationView {

    void showProgress();

    void hideProgress();

    void setItems(List<Postulation> postulations);

    void showMessage(String message);
}
