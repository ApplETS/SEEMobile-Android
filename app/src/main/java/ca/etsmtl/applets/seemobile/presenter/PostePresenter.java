package ca.etsmtl.applets.seemobile.presenter;

import android.accounts.AccountManager;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ca.etsmtl.applets.seemobile.Injector;
import ca.etsmtl.applets.seemobile.model.GuidPoste;
import ca.etsmtl.applets.seemobile.model.Poste;
import ca.etsmtl.applets.seemobile.service.DatabaseHelper;
import ca.etsmtl.applets.seemobile.service.SEEService;
import ca.etsmtl.applets.seemobile.utils.AuthenticationInterceptor;
import ca.etsmtl.applets.seemobile.utils.Constants;
import ca.etsmtl.applets.seemobile.utils.Synchronizer;
import ca.etsmtl.applets.seemobile.view.PosteView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by gnut3ll4 on 31/12/15.
 */
public class PostePresenter implements IPostePresenter {

    @Inject
    SEEService seeService;

    @Inject
    AccountManager accountManager;
    @Inject
    AuthenticationInterceptor authenticationInterceptor;

    @Inject
    DatabaseHelper databaseHelper;
    private Dao<Poste, ?> posteDao;
    private Synchronizer<Poste> posteSynchronizer;

    private String guidPoste;


    private PosteView posteView;


    public PostePresenter(PosteView posteView, String guidPoste) {
        this.posteView = posteView;
        Injector.INSTANCE.getServiceComponent().inject(this);
        this.guidPoste = guidPoste;

    }

    @Override
    public void onResume() {

        try {
            posteDao = databaseHelper.getDao(Poste.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        posteSynchronizer = new Synchronizer<>(posteDao);

        seeService.getApi()
                .getPoste(new GuidPoste(guidPoste))
                .flatMap(result -> {
                    if (result.getErreur().getCode() != 1000) {
                        accountManager.invalidateAuthToken(Constants.ACCOUNT_TYPE, authenticationInterceptor.getAuthToken());
                        return Observable.error(new Exception("Request didn't succeed"));
                    } else {
                        return Observable.just(result);
                    }
                })
                .retry(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(posteResult ->
                        Observable.just(new ArrayList<Poste>() {{
                            add(posteResult.getPoste());
                        }}))
                .doOnNext(posteSynchronizer::synchronize)
                .subscribe(new Observer<List<Poste>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("PostulationPresenter", "e:" + e);
                    }

                    @Override
                    public void onNext(List<Poste> postes) {
                        posteView.setPoste(postes.get(0));
                    }
                });


    }
}
