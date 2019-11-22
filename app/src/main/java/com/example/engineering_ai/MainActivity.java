package com.example.engineering_ai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvListing;

    ItemAdapter adapter ;

    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModelSetup();
        viewSetup();
    }

    private void viewModelSetup() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
    }

    private void viewSetup() {
        adapter = new ItemAdapter(this);
        rvListing = findViewById(R.id.rvListing);
        rvListing.setAdapter(adapter);
        mainActivityViewModel.getData();
        mainActivityViewModel.itemPagedList.observe(this, new Observer<PagedList<Hit>>() {
            @Override
            public void onChanged(PagedList<Hit> hits) {
                adapter.submitList(hits);
            }
        });
    }
}
