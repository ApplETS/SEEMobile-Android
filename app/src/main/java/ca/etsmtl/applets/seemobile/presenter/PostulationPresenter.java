package ca.etsmtl.applets.seemobile.presenter;

import android.accounts.AccountManager;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import ca.etsmtl.applets.seemobile.Injector;
import ca.etsmtl.applets.seemobile.model.ListePostulationsResult;
import ca.etsmtl.applets.seemobile.model.Postulation;
import ca.etsmtl.applets.seemobile.model.Session;
import ca.etsmtl.applets.seemobile.service.DatabaseHelper;
import ca.etsmtl.applets.seemobile.service.SEEService;
import ca.etsmtl.applets.seemobile.utils.AuthenticationInterceptor;
import ca.etsmtl.applets.seemobile.utils.Constants;
import ca.etsmtl.applets.seemobile.utils.Synchronizer;
import ca.etsmtl.applets.seemobile.view.PostulationView;
import rx.Observable;
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

    @Inject
    DatabaseHelper databaseHelper;

    @Inject
    AccountManager accountManager;

    @Inject
    AuthenticationInterceptor authenticationInterceptor;

    private Synchronizer<Postulation> postulationSynchronizer;
    private Dao<Postulation, ?> postulationDao;

    public PostulationPresenter(PostulationView postulationView) {
        this.postulationView = postulationView;
        Injector.INSTANCE.getServiceComponent().inject(this);

        try {
            postulationDao = databaseHelper.getDao(Postulation.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        postulationSynchronizer = new Synchronizer<>(postulationDao);

    }

    @Override
    public void onResume() {

        try {
            postulationView.setItems(postulationDao.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        postulationView.showProgress();

        Session currentSession = new Session();

        Observable.zip(
                getListePostulations(currentSession),
                getListePostulations(currentSession.getSessionBefore()),
                (listePostulations1, listePostulations2) -> {
                    listePostulations1.addAll(listePostulations2);
                    return listePostulations1;
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(listePostulations ->
                        Observable.just(listePostulations.getPostulationList()))
                .doOnNext(postulationSynchronizer::synchronize)
                .subscribe(new Observer<List<Postulation>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("PostulationPresenter", "e:" + e);
                        postulationView.hideProgress();
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

    public Observable<ListePostulationsResult> getListePostulations(Session session) {
        return seeService.getApi()
                .getPostulations(session)
                .flatMap(listePostulations -> {
                    if (listePostulations.getErreur().getCode() != 1000) {
                        accountManager.invalidateAuthToken(Constants.ACCOUNT_TYPE, authenticationInterceptor.getAuthToken());
                        return Observable.error(new Exception("Request didn't succeed"));
                    } else {
                        return Observable.just(listePostulations);
                    }
                })
                .retry(1);
    }

}
