package ca.etsmtl.applets.seemobile;

import android.app.Application;

/**
 * Created by nicolas on 23/12/15.
 */

public class SEEApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.INSTANCE.initializeServiceComponent(this);
    }

}
