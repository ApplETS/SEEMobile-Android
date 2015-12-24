package ca.etsmtl.applets.seemobile.component;

import javax.inject.Singleton;

import ca.etsmtl.applets.seemobile.module.AppModule;
import ca.etsmtl.applets.seemobile.module.ServiceModule;
import ca.etsmtl.applets.seemobile.presenter.PostulationPresenter;
import ca.etsmtl.applets.seemobile.service.SEEService;
import dagger.Component;

/**
 * Created by nicolas on 23/12/15.
 */


@Singleton
@Component(modules = {AppModule.class, ServiceModule.class})
public interface ServiceComponent {
    void inject(PostulationPresenter presenter);
}
