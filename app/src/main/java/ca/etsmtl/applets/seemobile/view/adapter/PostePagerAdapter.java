package ca.etsmtl.applets.seemobile.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ca.etsmtl.applets.seemobile.view.fragment.DescriptionPosteFragment;
import ca.etsmtl.applets.seemobile.view.fragment.InformationsPosteFragment;
import ca.etsmtl.applets.seemobile.view.fragment.MissionEntrepriseFragment;

/**
 * Created by gnut3ll4 on 31/12/15.
 */
public class PostePagerAdapter extends FragmentPagerAdapter {

    public PostePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                return InformationsPosteFragment.newInstance("", "");
            case 1:
                return MissionEntrepriseFragment.newInstance("","");
            case 2:
                return DescriptionPosteFragment.newInstance("","");
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Informations sur le stage";
            case 1:
                return "Mission de l'entreprise";
            case 2:
                return "Description du stage";
        }
        return null;
    }
}