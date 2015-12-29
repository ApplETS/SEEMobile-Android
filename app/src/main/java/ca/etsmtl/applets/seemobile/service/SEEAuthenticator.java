package ca.etsmtl.applets.seemobile.service;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import ca.etsmtl.applets.seemobile.Injector;
import ca.etsmtl.applets.seemobile.utils.AuthenticationInterceptor;
import ca.etsmtl.applets.seemobile.utils.Constants;

import static android.accounts.AccountManager.KEY_BOOLEAN_RESULT;

/**
 * Created by gnut3ll4 on 28/12/15.
 */
public class SEEAuthenticator extends AbstractAccountAuthenticator {

    private final Class loginActivity;

    @Inject
    SEEService seeService;

    @Inject
    AccountManager accountManager;

    @Inject
    Application application;

    @Inject
    AuthenticationInterceptor authenticationInterceptor;

    public SEEAuthenticator(Context context, Class loginActivity) {
        super(context);

        Injector.INSTANCE.getServiceComponent().inject(this);
        this.loginActivity = loginActivity;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse, String s) {
        return null;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
        final Intent intent = new Intent(application, loginActivity);
        intent.putExtra(Constants.KEY_ACCOUNT_TYPE, accountType);
        intent.putExtra(Constants.KEY_AUTH_TYPE, authTokenType);
        intent.putExtra(Constants.KEY_IS_ADDING_NEW_ACCOUNT, true);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);

        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, Bundle bundle) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse authenticatorResponse, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        // Extract the username and password from the Account Manager, and ask
        // the server for an appropriate AuthToken.

        String authToken = accountManager.peekAuthToken(account, authTokenType);

        // Lets give another try to authenticate the user
        if (TextUtils.isEmpty(authToken)) {
            final String password = accountManager.getPassword(account);
            final String username = account.name;


            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n    \"codeAccesUniversel\":\"" + username + "\",\n    \"motPasse\":\"" + password + "\"\n}");
            Request request = new Request.Builder()
                    .url("https://see-preprod.etsmtl.ca/Services/SEEMobile/SEEMobile.svc/authentifierEtudiant")
                    .post(body)
                    .addHeader("user-agent", "applETS")
                    .addHeader("accept", "application/json")
                    .addHeader("content-type", "application/json")
                    .addHeader("accept-charset", "UTF-8")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                List<String> cookies = response.headers().values("Set-Cookie");

                for (String cookie : cookies) {
                    if (cookie.contains("ASP.NET_SessionId")) {
                        authToken = cookie;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        // If we get an authToken - we return it
        if (!TextUtils.isEmpty(authToken)) {
            final Bundle result = new Bundle();
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            result.putString(AccountManager.KEY_AUTHTOKEN, authToken);
            return result;
        } else {
            final Bundle result = new Bundle();
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            result.putString(AccountManager.KEY_AUTHTOKEN, null);
            return result;
        }

    }

    @Override
    public String getAuthTokenLabel(String s) {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse
                                            accountAuthenticatorResponse, Account account, String s, Bundle bundle) throws
            NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse
                                      accountAuthenticatorResponse, Account account, String[] strings) throws NetworkErrorException {
        final Bundle result = new Bundle();
        result.putBoolean(KEY_BOOLEAN_RESULT, false);
        return result;
    }
}