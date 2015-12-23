package ca.etsmtl.applets.seemobile;

import android.app.Application;

import ca.etsmtl.applets.seemobile.service.ServiceComponent;
import ca.etsmtl.applets.seemobile.service.ServiceModule;

/**
 * Created by nicolas on 23/12/15.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.INSTANCE.initializeServiceComponent(this);
    }

}
