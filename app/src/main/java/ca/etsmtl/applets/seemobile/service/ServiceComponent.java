package ca.etsmtl.applets.seemobile.service;

import javax.inject.Singleton;

import ca.etsmtl.applets.seemobile.AppModule;
import ca.etsmtl.applets.seemobile.MainActivity;
import ca.etsmtl.applets.seemobile.postulations.FindPostulationsInteractor;
import ca.etsmtl.applets.seemobile.postulations.IFindPostulationsInteractor;
import ca.etsmtl.applets.seemobile.postulations.PostulationFragment;
import dagger.Component;

/**
 * Created by nicolas on 23/12/15.
 */


@Singleton
@Component(modules={AppModule.class, ServiceModule.class})
public interface ServiceComponent {
    void inject(SEEService seeService);
    void inject(FindPostulationsInteractor interactor);
}
