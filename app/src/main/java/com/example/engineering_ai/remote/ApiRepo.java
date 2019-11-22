package com.example.engineering_ai.remote;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRepo {

    public static ApiInteface create(){
        return create("https://hn.algolia.com/api/v1/");
    }


    public static ApiInteface create(String url){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        return new Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInteface.class);
    }
}
