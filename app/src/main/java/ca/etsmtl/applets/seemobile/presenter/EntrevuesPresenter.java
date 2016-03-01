package ca.etsmtl.applets.seemobile.presenter;

import android.accounts.AccountManager;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import ca.etsmtl.applets.seemobile.Injector;
import ca.etsmtl.applets.seemobile.model.Entrevue;
import ca.etsmtl.applets.seemobile.model.ListeEntrevuesResult;
import ca.etsmtl.applets.seemobile.model.ListePostulationsResult;
import ca.etsmtl.applets.seemobile.model.Poste;
import ca.etsmtl.applets.seemobile.model.Session;
import ca.etsmtl.applets.seemobile.service.DatabaseHelper;
import ca.etsmtl.applets.seemobile.service.SEEService;
import ca.etsmtl.applets.seemobile.utils.AuthenticationInterceptor;
import ca.etsmtl.applets.seemobile.utils.Constants;
import ca.etsmtl.applets.seemobile.utils.Synchronizer;
import ca.etsmtl.applets.seemobile.view.EntrevuesView;
import ca.etsmtl.applets.seemobile.view.StagesView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by gnut3ll4 on 20/12/15.
 */
public class EntrevuesPresenter implements IEntrevuesPresenter {

    private EntrevuesView entrevuesView;

    @Inject
    SEEService seeService;

    @Inject
    DatabaseHelper databaseHelper;

    @Inject
    AccountManager accountManager;

    @Inject
    AuthenticationInterceptor authenticationInterceptor;

//    private Synchronizer<Poste> postesSynchronizer;
//    private Dao<Poste, ?> posteDao;

    public EntrevuesPresenter(EntrevuesView entrevuesView) {
        this.entrevuesView = entrevuesView;
        Injector.INSTANCE.getServiceComponent().inject(this);

//        try {
//            posteDao = databaseHelper.getDao(Poste.class);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        postesSynchronizer = new Synchronizer<>(posteDao);

    }

    @Override
    public void onResume() {

        entrevuesView.showProgress();

        getListeEntrevues()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(listeEntrevues -> Observable.just(listeEntrevues.getEntrevues()))
//                .doOnNext(postesSynchronizer::synchronize)
                .subscribe(new Observer<List<Entrevue>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("EntrevuesPresenter", "e:" + e);
                        entrevuesView.hideProgress();
                    }

                    @Override
                    public void onNext(List<Entrevue> entrevues) {
                        entrevuesView.setItems(entrevues);
                        entrevuesView.hideProgress();
                    }
                });


    }

    @Override
    public void onItemClicked(int position) {
        entrevuesView.showMessage(String.format("Position %d clicked", position + 1));
    }

    public Observable<ListeEntrevuesResult> getListeEntrevues() {

        return Observable.zip(
                seeService.getApi()
                        .getEntrevuesAConfirmer()
                        .flatMap(listeEntrevues -> {
                            if (listeEntrevues.getErreur().getCode() != 1000) {
                                accountManager.invalidateAuthToken(Constants.ACCOUNT_TYPE, authenticationInterceptor.getAuthToken());
                                return Observable.error(new Exception("Request didn't succeed"));
                            } else {
                                return Observable.just(listeEntrevues);
                            }
                        })
                        .retry(1),

                seeService.getApi()
                        .getEntrevuesAVenir()
                        .flatMap(listeEntrevues -> {
                            if (listeEntrevues.getErreur().getCode() != 1000) {
                                accountManager.invalidateAuthToken(Constants.ACCOUNT_TYPE, authenticationInterceptor.getAuthToken());
                                return Observable.error(new Exception("Request didn't succeed"));
                            } else {
                                return Observable.just(listeEntrevues);
                            }
                        })
                        .retry(1),
                (listeEntrevues1, listeEntrevues2) -> {
                    listeEntrevues1.addAll(listeEntrevues2);
                    return listeEntrevues1;
                });

    }


}
