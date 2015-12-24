package ca.etsmtl.applets.seemobile.ui;

import java.util.List;

import ca.etsmtl.applets.seemobile.model.Postulation;

/**
 * Created by gnut3ll4 on 20/12/15.
 */
public interface PostulationView {

    public void showProgress();

    public void hideProgress();

    public void setItems(List<Postulation> postulations);

    public void showMessage(String message);
}
