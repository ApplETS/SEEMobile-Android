package ca.etsmtl.applets.seemobile.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import ca.etsmtl.applets.seemobile.model.Poste;
import ca.etsmtl.applets.seemobile.view.PosteView;
import ca.etsmtl.applets.seemobile.view.fragment.DescriptionPosteFragment;
import ca.etsmtl.applets.seemobile.view.fragment.InformationsPosteFragment;
import ca.etsmtl.applets.seemobile.view.fragment.MissionEntrepriseFragment;

/**
 * Created by gnut3ll4 on 31/12/15.
 */
public class PostePagerAdapter extends FragmentStatePagerAdapter {

    private Poste poste;

    public PostePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        switch (position) {
            case 0:
                return InformationsPosteFragment.newInstance();
            case 1:
                return DescriptionPosteFragment.newInstance();
            case 2:
                return MissionEntrepriseFragment.newInstance();
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
                return "Description du stage";
            case 2:
                return "Mission de l'entreprise";
        }
        return null;
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof PosteView) {
            ((PosteView) object).setPoste(poste);
        }
        //don't return POSITION_NONE, avoid fragment recreation.
        return super.getItemPosition(object);
    }
}