package ca.etsmtl.applets.seemobile.postulations;

import android.os.Handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.etsmtl.applets.seemobile.model.Postulation;

/**
 * Created by gnut3ll4 on 20/12/15.
 */
public class FindPostulationsInteractor implements IFindPostulationsInteractor {


    @Override
    public void findPostulations(final OnFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(createPostulationList());
            }
        }, 2000);
    }

    private List<Postulation> createPostulationList() {
        ArrayList<Postulation> postulations = new ArrayList<>();

        postulations.add(
                new Postulation(
                        "d01bac8c-01e7-40b9-b122-a42600a16abf",
                        "COTE-1",
                        "20152VIDEO003",
                        "Stagiaire Analyste-programmeur",
                        "VIDÉOTRON",
                        "Poste annulé", true));
        postulations.add(
                new Postulation(
                        "d01bac8c-01e7-40b9-b122-a42600a16bbf",
                        "JAT",
                        "20152NURUN001",
                        "Développeur Mobile",
                        "NURUN INC.",
                        "Non gagnant", false));
        postulations.add(
                new Postulation(
                        "d01bac8c-01e7-40b9-b122-a42600a16cbf",
                        "COTE-3",
                        "20152SOLUT002",
                        "développeur mobile",
                        "SOLUTIONS MEDIAS 360 INC.",
                        "Non retenu", true));
        postulations.add(
                new Postulation(
                        "d01bac8c-01e7-40b9-b122-a42600a16dbf",
                        "COTE-3",
                        "20152CGI__001",
                        "Développeur de services et d’interfaces web dans le centre d’expertise en applications mobiles",
                        "CGI",
                        "Poste annulé", false));

        return postulations;
    }
}
