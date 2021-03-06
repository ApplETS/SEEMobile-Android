package ca.etsmtl.applets.seemobile.module;

import android.accounts.AccountManager;
import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nicolas on 23/12/15.
 */

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    AccountManager providesAccountManager(Application application) {
        return AccountManager.get(application);
    }
}
