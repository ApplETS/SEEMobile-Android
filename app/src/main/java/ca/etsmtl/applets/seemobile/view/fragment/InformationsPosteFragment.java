package ca.etsmtl.applets.seemobile.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.etsmtl.applets.seemobile.R;
import ca.etsmtl.applets.seemobile.model.Poste;
import ca.etsmtl.applets.seemobile.view.PosteView;

public class InformationsPosteFragment extends Fragment implements PosteView {

    public InformationsPosteFragment() {
        // Required empty public constructor
    }

    public static InformationsPosteFragment newInstance() {
        InformationsPosteFragment fragment = new InformationsPosteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_informations_poste, container, false);

        return v;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setPoste(Poste poste) {

    }
}
