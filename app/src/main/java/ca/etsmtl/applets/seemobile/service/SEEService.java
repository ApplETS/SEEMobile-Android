package ca.etsmtl.applets.seemobile.service;

import com.squareup.okhttp.ResponseBody;

import ca.etsmtl.applets.seemobile.model.Credentials;
import ca.etsmtl.applets.seemobile.model.ListePostulationsResult;
import ca.etsmtl.applets.seemobile.model.Poste;
import ca.etsmtl.applets.seemobile.model.Session;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Body;
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

        @POST("/Services/SEEMobile/SEEMobile.svc/authentifierEtudiant")
        Observable<Response<ResponseBody>> authenticate(@Body Credentials credentials);

        @POST("/Services/SEEMobile/SEEMobile.svc/obtenirPoste")
        Observable<Poste> getPoste();

        @POST("/Services/SEEMobile/SEEMobile.svc/obtenirPostulations")
        Observable<ListePostulationsResult> getPostulations(@Body Session session);
    }

}
