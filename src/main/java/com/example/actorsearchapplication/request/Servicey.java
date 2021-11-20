package com.example.actorsearchapplication.request;

import android.util.Log;

import com.example.actorsearchapplication.utils.RestAPI;
import com.example.actorsearchapplication.utils.Credentials;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicey {

    private static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor()).build();
    private static Retrofit.Builder retrofitBuilder
            = new Retrofit.Builder().baseUrl(Credentials.BASE_URL)
            .client(client).addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();
    private static RestAPI restApi = retrofit.create(RestAPI.class);

    public static RestAPI getRestApi(){
        return restApi;
    }

    private static HttpLoggingInterceptor httpLoggingInterceptor(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                android.util.Log.e("TMDB API :", message + "");
            }
        });

        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
