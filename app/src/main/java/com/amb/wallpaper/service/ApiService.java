package com.amb.wallpaper.service;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abarsode on 3/4/17.
 */

public class ApiService {

    private static ApiService sApiService = null;

    private FlickrService mFlickrService;
    private Retrofit retrofit;

    private ApiService() {
        // Add the interceptor to OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //// Can be Level.BASIC, Level.HEADERS, or Level.BODY
        //// See http://square.github.io/okhttp/3.x/logging-interceptor/ to see the options.
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);
        builder.interceptors().add(mInterceptor);
        OkHttpClient client = builder.build();

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .client(client)
                .build();
    }

    public static synchronized ApiService getInstance() {
        if (sApiService == null) {
            sApiService = new ApiService();
        }
        return sApiService;
    }

    public FlickrService getFlickrService() {
        if (mFlickrService == null) {
            mFlickrService = retrofit.create(FlickrService.class);
        }
        return mFlickrService;
    }

    private final Interceptor mInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder().
                    addQueryParameter("api_key", "81a7b722eeee55dabf8437a32b42f24b")
                    .addQueryParameter("format", "json")
                    .addQueryParameter("nojsoncallback", "1")
                    .build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        }
    };


}
