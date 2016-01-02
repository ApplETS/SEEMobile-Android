package ca.etsmtl.applets.seemobile.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ca.etsmtl.applets.seemobile.R;
import ca.etsmtl.applets.seemobile.model.Poste;
import ca.etsmtl.applets.seemobile.view.PosteView;

public class DescriptionPosteFragment extends Fragment implements PosteView {

    @Bind(R.id.webview_description)
    WebView webViewDescription;

    public DescriptionPosteFragment() {
        // Required empty public constructor
    }

    public static DescriptionPosteFragment newInstance() {
        DescriptionPosteFragment fragment = new DescriptionPosteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_description_poste, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setPoste(Poste poste) {
        webViewDescription.loadDataWithBaseURL(null, poste.getDescription(), "text/html", "utf-8", null);
    }
}
