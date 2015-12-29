package ca.etsmtl.applets.seemobile.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import ca.etsmtl.applets.seemobile.view.activity.LoginActivity;

/**
 * Created by gnut3ll4 on 28/12/15.
 */
public class SEEAuthenticatorService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        SEEAuthenticator authenticator = new SEEAuthenticator(this, LoginActivity.class);
        return authenticator.getIBinder();
    }
}
