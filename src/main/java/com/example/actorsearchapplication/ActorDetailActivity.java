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

import com.example.actorsearchapplication.adapters.ActorDetailViewAdapter;
import com.example.actorsearchapplication.adapters.FilmographyRecyclerAdapter;
import com.example.actorsearchapplication.observer.ActorDetailObserver;
import com.example.actorsearchapplication.observer.FilmographyObserver;
import com.example.actorsearchapplication.viewmodels.ActorDetailViewModel;
import com.example.actorsearchapplication.viewutil.ButtonClickHandler;

public class ActorDetailActivity extends AppCompatActivity implements ActivityClickListener {
    ImageButton backButton;
    RecyclerView recyclerView_filmography;

    private int id;
    private ActorDetailViewModel actorDetailViewModel;
    private ActorDetailViewAdapter actorDetailViewAdapter;
    private FilmographyRecyclerAdapter filmographyRecyclerAdapter;
    private View actorDetailView;
    private LinearLayout layout_parent_actor_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createStatusBar();
        setContentView(R.layout.activity_actor_detail);
        backButton = findViewById(R.id.backButton);
        recyclerView_filmography = findViewById(R.id.recyclerView_filmography);
        layout_parent_actor_detail = findViewById(R.id.layout_parent_actor_detail);

        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(backButton);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1); // 예외처리 해야됨

        actorDetailView = View.inflate(getApplicationContext(),R.layout.layout_actor_detail,layout_parent_actor_detail);

        actorDetailViewAdapter = new ActorDetailViewAdapter(actorDetailView);
        filmographyRecyclerAdapter = new FilmographyRecyclerAdapter(this);
        actorDetailViewModel = new ActorDetailViewModel();

        recyclerView_filmography.setAdapter(filmographyRecyclerAdapter);
        recyclerView_filmography.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        actorDetailViewModel.getActorDetail().observe(this,new ActorDetailObserver(actorDetailViewAdapter));
        actorDetailViewModel.getFilmography().observe(this,new FilmographyObserver(filmographyRecyclerAdapter));

        actorDetailViewModel.requestActorDetail(id);
        actorDetailViewModel.requestFilmography(id);
    }

    @Override
    public void moveActorDetailPage(int id) {
    }

    @Override
    public void moveMovieDetailPage(int id) {
        Intent intent = new Intent(getApplicationContext(),MovieDetailActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    @Override
    public void moveTvDetailPage(int id) {
        Intent intent = new Intent(getApplicationContext(),TvDetailActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }


    private void createStatusBar(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));
    }



}