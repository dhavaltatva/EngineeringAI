package com.example.engineering_ai.ui;

import com.example.engineering_ai.R;
import com.example.engineering_ai.model.Hit;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements ItemAdapter.CallBack {

    RecyclerView rvListing;

    ItemAdapter adapter;

    MainActivityViewModel mainActivityViewModel;

    ProgressBar progressBar;

    TextView tvCount;

    Toolbar toolBar;

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
        adapter = new ItemAdapter(this,this);
        rvListing = findViewById(R.id.rvListing);
        progressBar = findViewById(R.id.progressBar);
        toolBar = findViewById(R.id.toolBar);
        tvCount = findViewById(R.id.tvCount);
        rvListing.setAdapter(adapter);
        mainActivityViewModel.getData();
        setSupportActionBar(toolBar);
        mainActivityViewModel.itemPagedList.observe(this, new Observer<PagedList<Hit>>() {
            @Override
            public void onChanged(PagedList<Hit> hits) {
                adapter.submitList(hits);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void OnSwitch(PagedList<Hit> cHitPagedList) {
        int count = 0;
        for (Hit hit : cHitPagedList) {
            if(hit.getOn()){
                count++;
            }
        }
        tvCount.setText(String.valueOf(count));
    }
}
