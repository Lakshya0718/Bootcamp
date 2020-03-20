package com.example.retrofitasses.Network;

import com.example.retrofitasses.Model.Data;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    private static DataService service;

    static Retrofit getInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://storage.googleapis.com/network-security-conf-codelab.appspot.com/v2/").client(client)
                    .addConverterFactory(GsonConverterFactory.create());

            retrofit = builder.build();


        }
        return retrofit;
    }

    public static DataService getDataService() {
        if (service == null) {
            service = getInstance().create(DataService.class);

        }
        return service;
    }
}
