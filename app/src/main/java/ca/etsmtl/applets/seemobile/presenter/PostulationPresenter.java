package ca.etsmtl.applets.seemobile.presenter;

import java.util.List;

import javax.inject.Inject;

import ca.etsmtl.applets.seemobile.Injector;
import ca.etsmtl.applets.seemobile.model.Postulation;
import ca.etsmtl.applets.seemobile.service.SEEService;
import ca.etsmtl.applets.seemobile.view.PostulationView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by gnut3ll4 on 20/12/15.
 */
public class PostulationPresenter implements IPostulationPresenter {

    private PostulationView postulationView;

    @Inject
    SEEService seeService;

    public PostulationPresenter(PostulationView postulationView) {
        this.postulationView = postulationView;
        Injector.INSTANCE.getServiceComponent().inject(this);
    }

    @Override
    public void onResume() {
        postulationView.showProgress();

        seeService.getApi()
                .getPostulations()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Postulation>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Postulation> postulations) {
                        postulationView.setItems(postulations);
                        postulationView.hideProgress();
                    }
                });

    }

    @Override
    public void onItemClicked(int position) {
        postulationView.showMessage(String.format("Position %d clicked", position + 1));
    }

}
