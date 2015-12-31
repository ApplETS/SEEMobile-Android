package ca.etsmtl.applets.seemobile.view;

import java.util.List;

import ca.etsmtl.applets.seemobile.model.Postulation;

/**
 * Created by gnut3ll4 on 31/12/15.
 */
public interface PosteView {

    void showProgress();

    void hideProgress();

    void setItems(List<Postulation> postulations);

}
