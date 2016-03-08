package ca.etsmtl.applets.seemobile.view.activity;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ca.etsmtl.applets.seemobile.Injector;
import ca.etsmtl.applets.seemobile.R;
import ca.etsmtl.applets.seemobile.utils.Constants;
import ca.etsmtl.applets.seemobile.view.fragment.EntrevuesFragment;
import ca.etsmtl.applets.seemobile.view.fragment.PostulationFragment;
import ca.etsmtl.applets.seemobile.view.fragment.StagesFragment;

public class MainActivity extends AppCompatActivity {

    @Inject
    Application application;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    AccountManager accountManager;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.nvView)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Injector.INSTANCE.getServiceComponent().inject(this);

        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (!isLoggedIn) {
            final AccountManagerFuture<Bundle> future = accountManager.addAccount(Constants.ACCOUNT_TYPE, Constants.AUTH_TOKEN_TYPE, null, null, MainActivity.this, new AccountManagerCallback<Bundle>() {
                @Override
                public void run(AccountManagerFuture<Bundle> future) {
                    //Login successful
                }
            }, null);
        }

        setSupportActionBar(toolbar);
        setupDrawerContent(navigationView);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        FragmentManager fragmentManager = getSupportFragmentManager();
        try {
            fragmentManager.beginTransaction().replace(R.id.flContent, PostulationFragment.class.newInstance()).addToBackStack(null).commit();
            fragmentManager.executePendingTransactions();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position
        Fragment fragment = null;

        Class fragmentClass;
        switch (menuItem.getItemId()) {

            case R.id.nav_postulations:
                fragmentClass = PostulationFragment.class;
                break;

            case R.id.nav_liste_stages:
                fragmentClass = StagesFragment.class;
                break;

            case R.id.nav_liste_entrevues:
                fragmentClass = EntrevuesFragment.class;
                break;

            default:
                fragmentClass = PostulationFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment, fragmentClass.getName()).addToBackStack(null).commit();
        fragmentManager.executePendingTransactions();

        // Highlight the selected item, update the title, and close the drawer
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
