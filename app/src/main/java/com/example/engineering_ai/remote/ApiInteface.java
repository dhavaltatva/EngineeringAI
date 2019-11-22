package com.example.engineering_ai.remote;

import com.example.engineering_ai.model.EnggModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInteface {

    @GET("search_by_date?tags=story")
    Call<EnggModel>  getMovieList(@Query("page") String page);

}
