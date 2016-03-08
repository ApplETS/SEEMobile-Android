package ca.etsmtl.applets.seemobile.service;

import com.squareup.okhttp.ResponseBody;

import ca.etsmtl.applets.seemobile.model.Credentials;
import ca.etsmtl.applets.seemobile.model.GuidPoste;
import ca.etsmtl.applets.seemobile.model.ListeEntrevuesResult;
import ca.etsmtl.applets.seemobile.model.ListePostesResult;
import ca.etsmtl.applets.seemobile.model.ListePostulationsResult;
import ca.etsmtl.applets.seemobile.model.PosteResult;
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
        Observable<PosteResult> getPoste(@Body GuidPoste guidPoste);

        @POST("/Services/SEEMobile/SEEMobile.svc/obtenirPostulations")
        Observable<ListePostulationsResult> getPostulations(@Body Session session);

        @POST("/Services/SEEMobile/SEEMobile.svc/obtenirPostesEnAffichage")
        Observable<ListePostesResult> getPostesEnAffichage();

        @POST("/Services/SEEMobile/SEEMobile.svc/obtenirEntrevuesAConfirmer")
        Observable<ListeEntrevuesResult> getEntrevuesAConfirmer();

        @POST("/Services/SEEMobile/SEEMobile.svc/obtenirEntrevuesAVenir")
        Observable<ListeEntrevuesResult> getEntrevuesAVenir();

    }

}
