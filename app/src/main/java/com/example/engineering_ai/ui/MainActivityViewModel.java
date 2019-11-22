package com.example.engineering_ai.ui;

import com.example.engineering_ai.model.Hit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class MainActivityViewModel extends ViewModel {


    LiveData<PagedList<Hit>> itemPagedList;

    MutableLiveData<Boolean> progress = new MutableLiveData<Boolean>();
    MutableLiveData<Boolean> initialLoad = new MutableLiveData<Boolean>();

    void getData(){
        EnggDataSourceFactory enggDataSourceFactory = new EnggDataSourceFactory();
        PageKeyedEnggDataSource dataSourceValue = enggDataSourceFactory.dataSource.getValue();
        //Getting PagedList config
        PagedList.Config pagedListConfig =
            (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(10).build();

        //Building the paged list
        itemPagedList = (new LivePagedListBuilder(enggDataSourceFactory, pagedListConfig))
            .build();
    }

}
