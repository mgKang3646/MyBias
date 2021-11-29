package com.example.actorsearchapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.actorsearchapplication.adapters.SearchRecyclerViewAdapter;
import com.example.actorsearchapplication.models.UrlModel;
import com.example.actorsearchapplication.observer.SearchObserver;
import com.example.actorsearchapplication.viewmodels.SearchViewModel;
import com.example.actorsearchapplication.viewutil.ButtonClickHandler;
import com.example.actorsearchapplication.viewutil.IntentUtil;
import com.example.actorsearchapplication.viewutil.RecyclerViewUtil;
import com.example.actorsearchapplication.viewutil.StatusBar;

public class SearchActivity extends AppCompatActivity implements ActivityClickListener {
    SearchView searchView;
    TextView titleTextView;
    ImageButton backButton;
    RecyclerView recyclerViewSearch;

    IntentUtil intentUtil;
    RecyclerViewUtil recyclerViewUtil;
    SearchViewModel searchViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_search);
        StatusBar.setStatusBar(this);
        UrlModel.setPage("1");

        onBindViewById();
        setViewEvent();
        setIntentUtil();
        setRecyclerViewUtil();
        setViewModelUtil();
    }

    @Override
    public void moveDetailPage(Class className, int id) {
        intentUtil.moveToDetailActivity(className,id);
    }

    private void onBindViewById(){
        titleTextView = findViewById(R.id.tv_Title);
        searchView = findViewById(R.id.searchView);
        backButton = findViewById(R.id.backButton);
        recyclerViewSearch = findViewById(R.id.recyclerView_search);
    }

    private void setViewEvent(){
        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(backButton);
        buttonClickHandler.setOnClickEvent(titleTextView);
        searchView.onActionViewExpanded();
        setSearchViewClickEvent();
    }

    private void setIntentUtil(){
        intentUtil = new IntentUtil(this);
    }

    private void setRecyclerViewUtil(){
        recyclerViewUtil = new RecyclerViewUtil(recyclerViewSearch,new SearchRecyclerViewAdapter(this));
        recyclerViewUtil.setLayoutManagerVertical(getApplicationContext());
    }

    private void setViewModelUtil(){
        searchViewModel = new SearchViewModel();
        searchViewModel.getSearchedActors().observe(this,new SearchObserver(recyclerViewUtil.getRecyclerViewAdapter()));
    }

    private void setSearchViewClickEvent(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String name) {
                searchViewModel.requestSearchActor(name);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String name) {
                searchViewModel.requestSearchActor(name);
                return true;
            }
        });
    }

}