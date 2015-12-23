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

    private SEEApi mSEEApi;

    String baseUrl;

    public SEEService() {

        //TODO use string from xml
        baseUrl = "http://dummy-api-stages.herokuapp.com";

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                Request request = builder.addHeader("Accept", "application/json")
                        .build();

                return chain.proceed(request);
            }
        };

        OkHttpClient okClient = new OkHttpClient();
        okClient.interceptors().add(interceptor);


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<List<Postulation>>() {
                }.getType(), new PostulationsDeserializer())
                .registerTypeAdapter(Poste.class, new PosteDeserializer())
                .create();

        Retrofit client = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

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
