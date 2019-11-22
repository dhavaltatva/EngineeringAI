package com.example.engineering_ai;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

public class MainActivityViewModel extends ViewModel {


    LiveData<PagedList<Hit>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, Hit>> liveDataSource;

    void getData(){
        EnggDataSourceFactory enggDataSourceFactory = new EnggDataSourceFactory();
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
