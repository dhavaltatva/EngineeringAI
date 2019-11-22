package com.example.engineering_ai.ui;

import com.example.engineering_ai.model.EnggModel;
import com.example.engineering_ai.model.Hit;
import com.example.engineering_ai.remote.ApiRepo;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageKeyedEnggDataSource extends PageKeyedDataSource<String, Hit> {

    public MutableLiveData<Boolean> networkState = new MutableLiveData<>();
    public MutableLiveData<Boolean> initialLoad  = new MutableLiveData<>();
    public MutableLiveData<String> error  = new MutableLiveData<>();


    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params,
        @NonNull final LoadInitialCallback<String, Hit> callback) {
        initialLoad.postValue(true);
        ApiRepo.create().getMovieList("1").enqueue(new Callback<EnggModel>() {
            @Override
            public void onResponse(Call<EnggModel> call, Response<EnggModel> response) {
                initialLoad.postValue(false);
                if (response.isSuccessful()) {
                    EnggModel body = response.body();
                    callback.onResult(body.getHits(),"",body.getPage().toString());
                }else{
                    error.postValue(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<EnggModel> call, Throwable t) {
                initialLoad.postValue(false);
                error.postValue(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params,
        @NonNull LoadCallback<String, Hit> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params,
        @NonNull final LoadCallback<String, Hit> callback) {
        networkState.postValue(true);
        ApiRepo.create().getMovieList(params.key).enqueue(new Callback<EnggModel>() {
            @Override
            public void onResponse(Call<EnggModel> call, Response<EnggModel> response) {
                networkState.postValue(false);
                if (response.isSuccessful()) {
                    EnggModel body = response.body();
                    callback.onResult(body.getHits(),body.getPage().toString());
                }else{
                    error.postValue(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<EnggModel> call, Throwable t) {
                initialLoad.postValue(false);
                error.postValue(t.getLocalizedMessage());
            }
        });
    }
}
