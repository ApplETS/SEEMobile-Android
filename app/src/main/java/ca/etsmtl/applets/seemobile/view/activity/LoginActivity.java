package ca.etsmtl.applets.seemobile.view.activity;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.ResponseBody;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ca.etsmtl.applets.seemobile.Injector;
import ca.etsmtl.applets.seemobile.R;
import ca.etsmtl.applets.seemobile.model.Credentials;
import ca.etsmtl.applets.seemobile.service.SEEService;
import ca.etsmtl.applets.seemobile.utils.Constants;
import ca.etsmtl.applets.seemobile.utils.Utils;
import retrofit.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by gnut3ll4 on 26/12/15.
 */
public class LoginActivity extends AccountAuthenticatorActivity {

    @Bind(R.id.edittext_username)
    EditText editTextUsername;
    @Bind(R.id.edittext_password)
    EditText editTextPassword;

    @Bind(R.id.btn_sign_in)
    Button loginButton;

    @Bind(R.id.login_status)
    View statusView;
    @Bind(R.id.login_form)
    View loginFormView;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    SEEService seeService;

    @Inject
    AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Injector.INSTANCE.getServiceComponent().inject(this);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    @OnClick(R.id.btn_sign_in)
    void attemptLogin() {

        // Reset errors.
        editTextUsername.setError(null);
        editTextPassword.setError(null);

        // Store values at the time of the login attempt.
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError(getString(R.string.error_field_required));
            focusView = editTextUsername;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            editTextPassword.setError(getString(R.string.error_field_required));
            focusView = editTextPassword;
            cancel = true;
        } else if (!Utils.isUsernameValid(username)) {
            editTextUsername.setError(getString(R.string.error_invalid_username));
            focusView = editTextUsername;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            seeService.getApi()
                    .authenticate(new Credentials(username, password))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onCompleted() {
                            showProgress(false);
                        }

                        @Override
                        public void onError(Throwable e) {
                            editTextPassword.setError(getString(R.string.error_incorrect_password));
                            editTextPassword.requestFocus();
                        }

                        @Override
                        public void onNext(Response<ResponseBody> response) {

                            String authToken = "";
                            List<String> cookies = response.headers().values("Set-Cookie");
                            for (String cookie : cookies) {
                                if (cookie.contains("ASP.NET_SessionId")) {
                                    authToken = cookie;
                                }
                            }

                            final Account account = new Account(username, Constants.ACCOUNT_TYPE);

                            if (getIntent().getBooleanExtra(Constants.KEY_IS_ADDING_NEW_ACCOUNT, false)) {
                                accountManager.addAccountExplicitly(account, password, null);
                            } else {
                                accountManager.setPassword(account, password);
                            }

                            accountManager.setAuthToken(account, Constants.AUTH_TOKEN_TYPE, authToken);

                            Intent intent = new Intent();
                            intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, username);
                            intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, Constants.ACCOUNT_TYPE);

                            setAccountAuthenticatorResult(intent.getExtras());
                            setResult(RESULT_OK, intent);

                            sharedPreferences.edit().putBoolean("isLoggedIn", true).commit();
                            finish();
                        }
                    });
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            loginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            statusView.setVisibility(show ? View.VISIBLE : View.GONE);
            statusView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    statusView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            statusView.setVisibility(show ? View.VISIBLE : View.GONE);
            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}