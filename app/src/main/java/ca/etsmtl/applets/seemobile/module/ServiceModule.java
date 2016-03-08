package ca.etsmtl.applets.seemobile.module;

import android.accounts.AccountManager;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.sql.SQLException;

import javax.inject.Singleton;

import ca.etsmtl.applets.seemobile.model.ListeEntrevuesResult;
import ca.etsmtl.applets.seemobile.model.ListePostesResult;
import ca.etsmtl.applets.seemobile.model.ListePostulationsResult;
import ca.etsmtl.applets.seemobile.model.PosteResult;
import ca.etsmtl.applets.seemobile.model.Postulation;
import ca.etsmtl.applets.seemobile.service.DatabaseHelper;
import ca.etsmtl.applets.seemobile.service.SEEService;
import ca.etsmtl.applets.seemobile.utils.AuthenticationInterceptor;
import ca.etsmtl.applets.seemobile.utils.deserializers.EntrevuesDeserializer;
import ca.etsmtl.applets.seemobile.utils.deserializers.PosteDeserializer;
import ca.etsmtl.applets.seemobile.utils.deserializers.PostesDeserializer;
import ca.etsmtl.applets.seemobile.utils.deserializers.PostulationsDeserializer;
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
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {

        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(ListePostulationsResult.class, new PostulationsDeserializer())
                .registerTypeAdapter(PosteResult.class, new PosteDeserializer())
                .registerTypeAdapter(ListePostesResult.class, new PostesDeserializer())
                .registerTypeAdapter(ListeEntrevuesResult.class, new EntrevuesDeserializer());

        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    AuthenticationInterceptor provideAuthenticationInterceptor(AccountManager accountManager) {
        return new AuthenticationInterceptor(accountManager);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, AuthenticationInterceptor authenticationInterceptor) {
        OkHttpClient client = new OkHttpClient();
        client.setCache(cache);

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                Request request = builder
                        .addHeader("Accept", "application/json")
                        .addHeader("User-Agent", "applETS")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept-Charset", "UTF-8")
                        .build();

                return chain.proceed(request);
            }
        };

        client.interceptors().add(interceptor);
        client.interceptors().add(authenticationInterceptor);

        //TODO REMOVE LOG
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(loggingInterceptor);

        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    SEEService provideSEEService(Retrofit client) {
        return new SEEService(client);
    }

    @Provides
    @Singleton
    DatabaseHelper provideDatabaseHelper(Application application) {
        DatabaseHelper databaseHelper = new DatabaseHelper(application);
        try {
            databaseHelper.getDao(Postulation.class).queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return databaseHelper;
    }

}
