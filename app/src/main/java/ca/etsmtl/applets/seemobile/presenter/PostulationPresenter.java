package ca.etsmtl.applets.seemobile.presenter;

import java.util.List;

import javax.inject.Inject;

import ca.etsmtl.applets.seemobile.model.Postulation;
import ca.etsmtl.applets.seemobile.interactor.FindPostulationsInteractor;
import ca.etsmtl.applets.seemobile.utils.OnFinishedListener;
import ca.etsmtl.applets.seemobile.ui.PostulationView;

/**
 * Created by gnut3ll4 on 20/12/15.
 */
public class PostulationPresenter implements IPostulationPresenter, OnFinishedListener {

    private PostulationView postulationView;

    @Inject
    FindPostulationsInteractor findPostulationsInteractor;

    public PostulationPresenter(PostulationView postulationView) {
        this.postulationView = postulationView;
        findPostulationsInteractor = new FindPostulationsInteractor();
    }

    @Override public void onResume() {
        postulationView.showProgress();
        findPostulationsInteractor.findPostulations(this);
    }

    @Override public void onItemClicked(int position) {
        postulationView.showMessage(String.format("Position %d clicked", position + 1));
    }

    @Override public void onFinished(List<Postulation> postulations) {
        postulationView.setItems(postulations);
        postulationView.hideProgress();
    }
}
