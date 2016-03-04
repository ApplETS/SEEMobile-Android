package ca.etsmtl.applets.seemobile.presenter;

import android.accounts.AccountManager;
import android.content.Intent;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import ca.etsmtl.applets.seemobile.Injector;
import ca.etsmtl.applets.seemobile.model.ListePostulationsResult;
import ca.etsmtl.applets.seemobile.model.Poste;
import ca.etsmtl.applets.seemobile.model.Postulation;
import ca.etsmtl.applets.seemobile.model.Session;
import ca.etsmtl.applets.seemobile.service.DatabaseHelper;
import ca.etsmtl.applets.seemobile.service.SEEService;
import ca.etsmtl.applets.seemobile.utils.AuthenticationInterceptor;
import ca.etsmtl.applets.seemobile.utils.Constants;
import ca.etsmtl.applets.seemobile.utils.Synchronizer;
import ca.etsmtl.applets.seemobile.view.StagesView;
import ca.etsmtl.applets.seemobile.view.activity.PosteActivity;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by gnut3ll4 on 20/12/15.
 */
public class StagesPresenter implements IStagesPresenter {

    private StagesView stagesView;

    @Inject
    SEEService seeService;

    @Inject
    DatabaseHelper databaseHelper;

    @Inject
    AccountManager accountManager;

    @Inject
    AuthenticationInterceptor authenticationInterceptor;

    private Synchronizer<Poste> postesSynchronizer;
    private Dao<Poste, ?> posteDao;

    public StagesPresenter(StagesView stagesView) {
        this.stagesView = stagesView;
        Injector.INSTANCE.getServiceComponent().inject(this);

        try {
            posteDao = databaseHelper.getDao(Poste.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        postesSynchronizer = new Synchronizer<>(posteDao);

    }

    @Override
    public void onResume() {

        try {
            stagesView.setItems(posteDao.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stagesView.showProgress();

        Session currentSession = new Session();

        seeService.getApi()
                .getPostesEnAffichage()
                .flatMap(listeStages -> {
                    if (listeStages.getErreur().getCode() != 1000) {
                        accountManager.invalidateAuthToken(Constants.ACCOUNT_TYPE, authenticationInterceptor.getAuthToken());
                        return Observable.error(new Exception("Request didn't succeed"));
                    } else {
                        return Observable.just(listeStages);
                    }
                })
                .retry(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(listeStages -> Observable.just(listeStages.getPostesList()))
                .doOnNext(postesSynchronizer::synchronize)
                .subscribe(new Observer<List<Poste>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("PostulationPresenter", "e:" + e);
                        stagesView.hideProgress();
                    }

                    @Override
                    public void onNext(List<Poste> postes) {
                        stagesView.setItems(postes);
                        stagesView.hideProgress();
                    }
                });


    }

    @Override
    public void onItemClicked(int position) {




        stagesView.showMessage(String.format("Position %d clicked", position + 1));
    }



}
