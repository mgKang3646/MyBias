package com.example.actorsearchapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.actorsearchapplication.adapters.MovieDetailViewAdapter;
import com.example.actorsearchapplication.observer.MovieDetailObserver;
import com.example.actorsearchapplication.viewmodels.MovieDetailViewModel;
import com.example.actorsearchapplication.viewutil.ButtonClickHandler;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageButton backButton;
    private LinearLayout layout_parent_movie_detail;
    private View movieDetailView;
    private int id;

    private MovieDetailViewModel movieDetailViewModel;
    private MovieDetailViewAdapter movieDetailViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createStatusBar();
        setContentView(R.layout.activity_movie_detail);
        backButton = findViewById(R.id.backButton);
        layout_parent_movie_detail = findViewById(R.id.layout_parent_movie_detail);
        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(backButton);

        Intent intent = getIntent();
        id = intent.getIntExtra("movie_id",-1); // 예외처리 해야됨

        movieDetailView = View.inflate(getApplicationContext(),R.layout.layout_movie_detail,layout_parent_movie_detail);

        movieDetailViewModel = new MovieDetailViewModel();
        movieDetailViewAdapter = new MovieDetailViewAdapter(movieDetailView);
        movieDetailViewModel.getMovieDetail().observe(this,new MovieDetailObserver(movieDetailViewAdapter));

        movieDetailViewModel.requestMovieDetail(id);

    }

    private void createStatusBar(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));
    }
}