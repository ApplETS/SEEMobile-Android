package ca.etsmtl.applets.seemobile;

import android.app.Application;

import ca.etsmtl.applets.seemobile.service.*;

/**
 * Created by nicolas on 23/12/15.
 */

/*****************
 * Injector callable in any files,
 * don't forget in called, to add in the component the
 * file where you have right to inject this component
 */
public enum Injector {
    INSTANCE;

    ServiceComponent serviceComponent;

    private Injector(){
    }

    void initializeServiceComponent(Application application) {
        ServiceComponent serviceComponent = DaggerServiceComponent.builder()
                .appModule(new AppModule(application))
                .serviceModule(new ServiceModule("http://dummy-api-stages.herokuapp.com"))
                .build();
        this.serviceComponent = serviceComponent;
    }

    public ServiceComponent getServiceComponent() {
        return serviceComponent;
    }
}
