package ca.etsmtl.applets.seemobile.component;

import javax.inject.Singleton;

import ca.etsmtl.applets.seemobile.SEEApplication;
import ca.etsmtl.applets.seemobile.module.AppModule;
import ca.etsmtl.applets.seemobile.module.ServiceModule;
import ca.etsmtl.applets.seemobile.presenter.EntrevuesPresenter;
import ca.etsmtl.applets.seemobile.presenter.PostePresenter;
import ca.etsmtl.applets.seemobile.presenter.PostulationPresenter;
import ca.etsmtl.applets.seemobile.presenter.StagesPresenter;
import ca.etsmtl.applets.seemobile.service.SEEAuthenticator;
import ca.etsmtl.applets.seemobile.service.SEEService;
import ca.etsmtl.applets.seemobile.view.activity.LoginActivity;
import ca.etsmtl.applets.seemobile.view.activity.MainActivity;
import dagger.Component;

/**
 * Created by nicolas on 23/12/15.
 */


@Singleton
@Component(modules = {AppModule.class, ServiceModule.class})
public interface ServiceComponent {
    void inject(SEEApplication application);
    void inject(PostulationPresenter presenter);
    void inject(LoginActivity loginActivity);
    void inject(MainActivity mainActivity);
    void inject(SEEAuthenticator seeAuthenticator);
    void inject(PostePresenter postePresenter);
    void inject(StagesPresenter stagesPresenter);
    void inject(EntrevuesPresenter entrevuesPresenter);
}
