package ca.etsmtl.applets.seemobile;

import android.app.Application;

import ca.etsmtl.applets.seemobile.component.DaggerServiceComponent;
import ca.etsmtl.applets.seemobile.component.ServiceComponent;
import ca.etsmtl.applets.seemobile.module.AppModule;
import ca.etsmtl.applets.seemobile.module.ServiceModule;
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

    Injector(){
    }

    void initializeServiceComponent(Application application) {

        String seeApiBaseUrl = application.getString(R.string.see_api_base_url);

        this.serviceComponent = DaggerServiceComponent.builder()
                .appModule(new AppModule(application))
                .serviceModule(new ServiceModule(seeApiBaseUrl))
                .build();
    }

    public ServiceComponent getServiceComponent() {
        return serviceComponent;
    }
}
