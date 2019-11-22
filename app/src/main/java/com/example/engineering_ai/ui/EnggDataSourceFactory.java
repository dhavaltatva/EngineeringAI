package com.example.engineering_ai.ui;

import com.example.engineering_ai.model.Hit;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class EnggDataSourceFactory extends  DataSource.Factory<String, Hit> {

    public MutableLiveData<PageKeyedEnggDataSource> dataSource = new MutableLiveData<PageKeyedEnggDataSource>();

    @NonNull
    @Override
    public DataSource<String, Hit> create() {
        PageKeyedEnggDataSource pageKeyedEnggDataSource = new PageKeyedEnggDataSource();
        dataSource.postValue(pageKeyedEnggDataSource);
        return pageKeyedEnggDataSource;
    }
}
