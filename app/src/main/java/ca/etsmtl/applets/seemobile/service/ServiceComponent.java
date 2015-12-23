package ca.etsmtl.applets.seemobile.service;

import javax.inject.Singleton;

import ca.etsmtl.applets.seemobile.AppModule;
import ca.etsmtl.applets.seemobile.MainActivity;
import ca.etsmtl.applets.seemobile.postulations.PostulationFragment;
import dagger.Component;

/**
 * Created by nicolas on 23/12/15.
 */


@Singleton
@Component(modules={AppModule.class, ServiceModule.class})
public interface ServiceComponent {
    // Injecte moi ça dans ta face
    void inject(SEEService fragment);
}
