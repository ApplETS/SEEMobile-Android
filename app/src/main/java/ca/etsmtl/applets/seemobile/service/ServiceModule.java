package ca.etsmtl.applets.seemobile.service;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import ca.etsmtl.applets.seemobile.model.Poste;
import ca.etsmtl.applets.seemobile.model.Postulation;
import ca.etsmtl.applets.seemobile.service.PosteDeserializer;
import ca.etsmtl.applets.seemobile.service.PostulationsDeserializer;
import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by nicolas on 23/12/15.
 */
@Module
public class ServiceModule {

    String mBaseUrl;

    // Constructor needs one parameter to instantiate.
    public ServiceModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    // Application reference must come from AppModule.class
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Named("Cache")
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Named("gsonbuilder")
    @Singleton
    Gson  provideGson() {

        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<List<Postulation>>() {
                }.getType(), new PostulationsDeserializer())
                .registerTypeAdapter(Poste.class, new PosteDeserializer());

        return gsonBuilder.create();
    }

     @Provides
     @Named("okhttpclient")
     @Singleton
     OkHttpClient provideOkHttpClient(@Named("Cache") Cache cache) {
         OkHttpClient client = new OkHttpClient();
         client.setCache(cache);

         Interceptor interceptor = new Interceptor() {
             @Override
             public Response intercept(Chain chain) throws IOException {
                 Request.Builder builder = chain.request().newBuilder();
                 Request request = builder.addHeader("Accept", "application/json")
                         .build();

                 return chain.proceed(request);
             }
         };

         client.interceptors().add(interceptor);

         return client;
    }

    @Provides
    @Named("retrofit")
    @Singleton
    Retrofit provideRetrofit(@Named("gsonbuilder") Gson gson, @Named("okhttpclient") OkHttpClient okHttpClient) {

        Retrofit client = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return client;
    }
}
