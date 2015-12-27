package ca.etsmtl.applets.seemobile.utils;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by gnut3ll4 on 27/12/15.
 */
public class AuthenticationInterceptor implements Interceptor {

    private String authToken;

    public AuthenticationInterceptor() {
        authToken = "";
    }

    public void clearAuthValue() {
        authToken = null;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Request request = builder.addHeader("Cookie", authToken)
                .build();

        return chain.proceed(request);
    }
}