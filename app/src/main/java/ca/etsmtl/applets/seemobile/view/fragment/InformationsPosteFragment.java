package ca.etsmtl.applets.seemobile.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import ca.etsmtl.applets.seemobile.R;
import ca.etsmtl.applets.seemobile.model.Poste;
import ca.etsmtl.applets.seemobile.view.PosteView;

public class InformationsPosteFragment extends Fragment implements PosteView {

    @Bind(R.id.tv_numeroPoste)
    TextView textViewNumeroPoste;
    @Bind(R.id.tv_nomPoste)
    TextView textViewNomPoste;
    @Bind(R.id.tv_nomEmployeur)
    TextView textViewNomEmployeur;
    @Bind(R.id.tv_duree)
    TextView textViewDuree;
    @Bind(R.id.tv_lieu)
    TextView textViewLieu;
    @Bind(R.id.tv_datePostulationLimite)
    TextView textViewDatePostulationLimite;
    @Bind(R.id.tv_coordonnateur)
    TextView textViewCoordonnateur;
    @Bind(R.id.tv_langueCv)
    TextView textViewLangueCV;

    @Bind(R.id.action_postuler)
    FloatingActionButton fabActionPostuler;
//    @Bind(R.id.action_question)
//    FloatingActionButton fabActionQuestion;
    @Bind(R.id.action_site_web)
    FloatingActionButton fabActionSiteWeb;
    @Bind(R.id.action_localiser)
    FloatingActionButton fabActionLocaliser;


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
        ButterKnife.bind(this, v);

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

        textViewNumeroPoste.setText(poste.getNumeroPoste());
        textViewNomPoste.setText(poste.getNomPoste());
        String nomEmployeur = getString(R.string.employeur_poste, poste.getNomEmployeur(), poste.getTypeEmployeur());
        textViewNomEmployeur.setText(nomEmployeur);
        String duree = getString(R.string.duree_poste, poste.getDuree());
        textViewDuree.setText(duree);
        textViewLieu.setText(poste.getLieu());
        textViewDatePostulationLimite.setText(poste.getDatePostulationLimite().toString());
        textViewCoordonnateur.setText(poste.getCoordonnateur());
        textViewLangueCV.setText(poste.getLangueCv());


        fabActionPostuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
//        fabActionQuestion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",poste.getCoordonnateur(), null));
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
//                startActivity(Intent.createChooser(emailIntent, "Send email..."));
//            }
//        });
        fabActionSiteWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = poste.getSiteWebEmployeur();
                if(!url.startsWith("www.")&& !url.startsWith("http://")){
                    url = "www."+url;
                }
                if(!url.startsWith("http://")){
                    url = "http://"+url;
                }
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
        fabActionLocaliser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?z=18&q=" + Uri.encode(poste.getCodePostal().replace(" ",""))));
                getActivity().startActivity(intent);
            }
        });


    }
}
