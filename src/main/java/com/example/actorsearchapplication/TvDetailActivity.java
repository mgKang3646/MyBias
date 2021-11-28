package com.example.actorsearchapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.actorsearchapplication.adapters.CastingRecyclerAdapter;
import com.example.actorsearchapplication.adapters.TvDetailViewAdapter;
import com.example.actorsearchapplication.observer.CastingObserver;
import com.example.actorsearchapplication.observer.TvDetailObserver;
import com.example.actorsearchapplication.viewmodels.TvDetailViewModel;
import com.example.actorsearchapplication.viewutil.ButtonClickHandler;

public class TvDetailActivity extends AppCompatActivity implements ActivityClickListener {

    private ImageButton backButton;
    private LinearLayout layout_parent_tv_detail;
    private TvDetailViewModel tvDetailViewModel;
    private TvDetailObserver tvDetailObserver;
    private TvDetailViewAdapter tvDetailViewAdapter;
    private CastingRecyclerAdapter castingRecyclerAdapter;
    private RecyclerView recyclerView_casting_tv;
    private View tvDetailView;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);
        createStatusBar();

        backButton = findViewById(R.id.backButton);
        layout_parent_tv_detail = findViewById(R.id.layout_parent_tv_detail);
        recyclerView_casting_tv = findViewById(R.id.recyclerView_casting_tv);
        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(backButton);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1); // 예외처리 해야됨

        tvDetailView = View.inflate(getApplicationContext(),R.layout.layout_tv_detail,layout_parent_tv_detail);

        tvDetailViewModel = new TvDetailViewModel();
        tvDetailViewAdapter = new TvDetailViewAdapter(tvDetailView);
        castingRecyclerAdapter = new CastingRecyclerAdapter(this);

        recyclerView_casting_tv.setAdapter(castingRecyclerAdapter);
        recyclerView_casting_tv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        tvDetailViewModel.getTvDetail().observe(this, new TvDetailObserver(tvDetailViewAdapter));
        tvDetailViewModel.getCastingTv().observe(this,new CastingObserver(castingRecyclerAdapter));

        tvDetailViewModel.requestTvDetail(id);
        tvDetailViewModel.requestCastingTv(id);

    }

    private void createStatusBar(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));
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
}