package ca.etsmtl.applets.seemobile.service;

import java.util.List;

import ca.etsmtl.applets.seemobile.model.Poste;
import ca.etsmtl.applets.seemobile.model.Postulation;
import retrofit.Retrofit;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by gnut3ll4 on 22/12/15.
 */
public class SEEService {

    private SEEApi mSEEApi;

    public SEEService(Retrofit client) {

        mSEEApi = client.create(SEEApi.class);

    }

    public SEEApi getApi() {

        return mSEEApi;
    }


    public interface SEEApi {

        @POST("/obtenirPoste")
        public Observable<Poste>
        getPoste();

        @POST("/obtenirPostulations")
        public Observable<List<Postulation>>
        getPostulations();
    }

}
