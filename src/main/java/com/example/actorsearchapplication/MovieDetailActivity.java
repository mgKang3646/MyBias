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

import com.example.actorsearchapplication.adapters.CastingRecyclerAdapter;
import com.example.actorsearchapplication.adapters.MovieDetailViewAdapter;
import com.example.actorsearchapplication.observer.CastingObserver;
import com.example.actorsearchapplication.observer.MovieDetailObserver;
import com.example.actorsearchapplication.viewmodels.MovieDetailViewModel;
import com.example.actorsearchapplication.viewutil.ButtonClickHandler;

public class MovieDetailActivity extends AppCompatActivity implements ActivityClickListener {

    private ImageButton backButton;
    private LinearLayout layout_parent_movie_detail;
    private View movieDetailView;
    private RecyclerView recyclerViewCasting;
    private CastingRecyclerAdapter castingRecyclerAdapter;
    private MovieDetailViewModel movieDetailViewModel;
    private MovieDetailViewAdapter movieDetailViewAdapter;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        createStatusBar();

        backButton = findViewById(R.id.backButton);
        layout_parent_movie_detail = findViewById(R.id.layout_parent_movie_detail);
        recyclerViewCasting = findViewById(R.id.recycler_casting_movie);

        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(backButton);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1); // 예외처리 해야됨

        movieDetailView = View.inflate(getApplicationContext(),R.layout.layout_movie_detail,layout_parent_movie_detail);

        movieDetailViewModel = new MovieDetailViewModel();
        movieDetailViewAdapter = new MovieDetailViewAdapter(movieDetailView);
        castingRecyclerAdapter = new CastingRecyclerAdapter(this);

        recyclerViewCasting.setAdapter(castingRecyclerAdapter);
        recyclerViewCasting.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        movieDetailViewModel.getMovieDetail().observe(this,new MovieDetailObserver(movieDetailViewAdapter));
        movieDetailViewModel.getCasting().observe(this,new CastingObserver(castingRecyclerAdapter));

        movieDetailViewModel.requestMovieDetail(id);
        movieDetailViewModel.requestCasting(id);

    }

    @Override
    public void moveActorDetailPage(int id) {
        Intent intent = new Intent(getApplicationContext(),ActorDetailActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
    @Override
    public void moveMovieDetailPage(int id) {}
    @Override
    public void moveTvDetailPage(int id) {}

    private void createStatusBar(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));
    }


}