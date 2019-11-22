package com.example.engineering_ai;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class EnggDataSourceFactory extends  DataSource.Factory<String, Hit> {

    MutableLiveData<PageKeyedEnggDataSource> dataSource = new MutableLiveData<PageKeyedEnggDataSource>();

    @NonNull
    @Override
    public DataSource<String, Hit> create() {
        PageKeyedEnggDataSource pageKeyedEnggDataSource = new PageKeyedEnggDataSource();
        dataSource.postValue(pageKeyedEnggDataSource);
        return pageKeyedEnggDataSource;
    }
}
