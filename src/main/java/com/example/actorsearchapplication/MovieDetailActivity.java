package com.example.actorsearchapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.actorsearchapplication.adapters.CastingRecyclerAdapter;
import com.example.actorsearchapplication.adapters.MovieDetailViewAdapter;
import com.example.actorsearchapplication.observer.CastingObserver;
import com.example.actorsearchapplication.observer.MovieDetailObserver;
import com.example.actorsearchapplication.viewmodels.MovieDetailViewModel;
import com.example.actorsearchapplication.viewutil.ButtonClickHandler;
import com.example.actorsearchapplication.viewutil.IntentUtil;
import com.example.actorsearchapplication.viewutil.MainViewUtil;
import com.example.actorsearchapplication.viewutil.RecyclerViewUtil;
import com.example.actorsearchapplication.viewutil.StatusBar;

public class MovieDetailActivity extends AppCompatActivity implements ActivityClickListener {

    private ImageButton backButton;
    private TextView titleTextView;
    private LinearLayout layout_parent_movie_detail;
    private RecyclerView recyclerViewCasting;

    private IntentUtil intentUtil;
    private MainViewUtil mainViewUtil;
    private RecyclerViewUtil recyclerViewUtil;

    private MovieDetailViewModel movieDetailViewModel;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        StatusBar.setStatusBar(this);

        onBindViewById();
        getIntentValue();
        setIntentUtil();
        setViewEvent();
        setMainViewUtil();
        setRecyclerViewUtil();
        setViewModel();
    }

    @Override
    public void moveDetailPage(Class className, int id) {
        intentUtil.moveToDetailActivity(className,id);
    }

    private void onBindViewById(){
        backButton = findViewById(R.id.backButton);
        titleTextView = findViewById(R.id.tv_Title);
        layout_parent_movie_detail = findViewById(R.id.layout_parent_movie_detail);
        recyclerViewCasting = findViewById(R.id.recycler_casting_movie);
    }

    private void setViewEvent(){
        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(backButton);
        buttonClickHandler.setOnClickEvent(titleTextView);
    }

    private void setIntentUtil(){
        intentUtil = new IntentUtil(this);
    }
    private void getIntentValue(){
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1); // 예외처리 해야됨
    }

    private void setMainViewUtil(){
        mainViewUtil = new MainViewUtil();
        mainViewUtil.inflate(getApplicationContext(),R.layout.layout_movie_detail,layout_parent_movie_detail);
        mainViewUtil.createMovieDetailViewAdapter();
    }

    private void setRecyclerViewUtil(){
        recyclerViewUtil = new RecyclerViewUtil(recyclerViewCasting,new CastingRecyclerAdapter(this));
        recyclerViewUtil.setLayoutManagerHorizontal(getApplicationContext());
    }

    private void setViewModel(){
        movieDetailViewModel = new MovieDetailViewModel();
        movieDetailViewModel.getMovieDetail().observe(this,new MovieDetailObserver(mainViewUtil.getMainViewAdapter()));
        movieDetailViewModel.getCasting().observe(this,new CastingObserver(recyclerViewUtil.getRecyclerViewAdapter()));
        movieDetailViewModel.requestMovieDetail(id);
        movieDetailViewModel.requestCasting(id);
    }

}