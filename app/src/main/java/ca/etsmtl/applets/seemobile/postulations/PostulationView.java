package ca.etsmtl.applets.seemobile.postulations;

import java.util.List;

/**
 * Created by gnut3ll4 on 20/12/15.
 */
public interface PostulationView {

    public void showProgress();

    public void hideProgress();

    public void setItems(List<String> items);

    public void showMessage(String message);
}
