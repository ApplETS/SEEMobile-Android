package ca.etsmtl.applets.seemobile.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ca.etsmtl.applets.seemobile.Injector;
import ca.etsmtl.applets.seemobile.model.Poste;
import ca.etsmtl.applets.seemobile.model.Postulation;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by gnut3ll4 on 22/12/15.
 */
public class SEEService {

    // Purée, je vais t'en mettre une injection !!!
    @Inject @Named("retrofit") Retrofit client;

    private SEEApi mSEEApi;

    String baseUrl;

    public SEEService() {

        //TODO use string from xml
        baseUrl = "http://dummy-api-stages.herokuapp.com";

        // Injection let's do it!!!!!!
        Injector.INSTANCE.getServiceComponent().inject(this);

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
