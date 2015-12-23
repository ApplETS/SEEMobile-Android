package ca.etsmtl.applets.seemobile.postulations;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncStatusObserver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ca.etsmtl.applets.seemobile.R;
import ca.etsmtl.applets.seemobile.model.Postulation;

/**
 * A placeholder fragment containing a simple view.
 */
public class PostulationFragment extends Fragment implements PostulationView, AdapterView.OnItemClickListener {

    @Bind(R.id.listview_postulations)
    ListView listView;
    @Bind(R.id.progressbar)
    ProgressBar progressBar;

    private PostulationPresenter presenter;
    private PostulationAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_postulation, container, false);

        ButterKnife.bind(this, view);
        presenter = new PostulationPresenter(this);

        listView.setOnItemClickListener(this);

        Bundle settings = new Bundle();
        settings.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settings.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
//        Account mAccount = new Account("SEEMobile", "ca.etsmtl.applets.seemobile");
        ContentResolver.requestSync(CreateSyncAccount(getActivity()), "ca.etsmtl.applets.seemobile.provider", settings);
        getActivity().getContentResolver().registerContentObserver(
                Uri.parse("content://ca.etsmtl.applets.seemobile.provider"),
                false,
                new ContentObserver(new Handler(Looper.getMainLooper())) {
                    public void onChange(boolean selfChange) {
                        int i = 0;
                    }
                });
        return view;


    }

    // The authority for the sync adapter's content provider
    public static final String AUTHORITY = "ca.etsmtl.applets.seemobile.provider";
    // An account type, in the form of a domain name
    public static final String ACCOUNT_TYPE = "ca.etsmtl.applets.seemobile";
    // The account name
    public static final String ACCOUNT = "SEEMobile";

    /**
     * Create a new dummy account for the sync adapter
     *
     * @param context The application context
     */
    public static Account CreateSyncAccount(Context context) {
        // Create the account type and default account
        Account newAccount = new Account(
                ACCOUNT, ACCOUNT_TYPE);
        // Get an instance of the Android account manager
        AccountManager accountManager = AccountManager.get(context);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
        } else {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
        }
        return newAccount;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        presenter.onItemClicked(position);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<Postulation> postulations) {
        adapter = new PostulationAdapter(getActivity(), R.layout.row_postulation, postulations);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        ;
    }
}
