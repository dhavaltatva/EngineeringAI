package com.example.engineering_ai;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRepo {
    public static String BASE_URL = " https://hn.algolia.com/api/v1/";

    public static ApiInteface create(){
        return create(BASE_URL);
    }


    public static ApiInteface create(String url){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        return new Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInteface.class);
    }
}
