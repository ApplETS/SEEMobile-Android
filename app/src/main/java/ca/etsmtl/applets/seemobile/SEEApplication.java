package ca.etsmtl.applets.seemobile;

import android.app.Application;

import javax.inject.Inject;

import ca.etsmtl.applets.seemobile.service.DatabaseHelper;

/**
 * Created by nicolas on 23/12/15.
 */

public class SEEApplication extends Application {

    @Inject
    DatabaseHelper databaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.INSTANCE.initializeServiceComponent(this);

        // Creates database tables in advance to avoid heavy processing during login
        Injector.INSTANCE.getServiceComponent().inject(this);
    }

}
