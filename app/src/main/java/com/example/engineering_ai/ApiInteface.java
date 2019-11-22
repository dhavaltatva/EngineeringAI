package com.example.engineering_ai;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInteface {

    @GET("search_by_date?tags=story&page={page}")
    Call<EnggModel>  getMovieList(@Path("page") String page);

}
