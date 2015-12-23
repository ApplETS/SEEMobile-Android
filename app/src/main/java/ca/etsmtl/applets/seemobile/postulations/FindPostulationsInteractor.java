package ca.etsmtl.applets.seemobile.postulations;

import java.util.ArrayList;
import java.util.List;

import ca.etsmtl.applets.seemobile.model.Postulation;
import ca.etsmtl.applets.seemobile.service.SEEService;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by gnut3ll4 on 20/12/15.
 */
public class FindPostulationsInteractor implements IFindPostulationsInteractor {


    @Override
    public void findPostulations(final OnFinishedListener listener) {

        SEEService seeService = new SEEService();

        seeService.getApi()
                .getPostulations()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Postulation>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Postulation> postulations) {

                        listener.onFinished(postulations);
                    }
                });

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
