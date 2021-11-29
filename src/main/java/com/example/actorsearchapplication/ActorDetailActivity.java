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

import com.example.actorsearchapplication.adapters.ActorDetailViewAdapter;
import com.example.actorsearchapplication.adapters.FilmographyRecyclerAdapter;
import com.example.actorsearchapplication.observer.ActorDetailObserver;
import com.example.actorsearchapplication.observer.FilmographyObserver;
import com.example.actorsearchapplication.viewmodels.ActorDetailViewModel;
import com.example.actorsearchapplication.viewutil.ButtonClickHandler;
import com.example.actorsearchapplication.viewutil.IntentUtil;
import com.example.actorsearchapplication.viewutil.MainViewUtil;
import com.example.actorsearchapplication.viewutil.RecyclerViewUtil;
import com.example.actorsearchapplication.viewutil.StatusBar;
import com.example.actorsearchapplication.viewutil.TabLayoutHandler;

public class ActorDetailActivity extends AppCompatActivity implements ActivityClickListener {
    ImageButton backButton;
    TextView title;
    RecyclerView recyclerView_filmography;

    private int id;
    private ActorDetailViewModel actorDetailViewModel;
    private LinearLayout layout_parent_actor_detail;
    private IntentUtil intentUtil;
    private MainViewUtil mainViewUtil;
    private RecyclerViewUtil recyclerViewUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBar.setStatusBar(this);
        setContentView(R.layout.activity_actor_detail);

        onBindViewComponents();
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

    private void getIntentValue(){
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1); // 예외처리 해야됨
    }

    private void onBindViewComponents(){
        backButton = findViewById(R.id.backButton);
        title = findViewById(R.id.tv_Title);
        recyclerView_filmography = findViewById(R.id.recyclerView_filmography);
        layout_parent_actor_detail = findViewById(R.id.layout_parent_actor_detail);
    }

    private void setViewEvent(){
        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(backButton);
        buttonClickHandler.setOnClickEvent(title);
    }

    private void setIntentUtil(){
        intentUtil = new IntentUtil(this);
    }

    private void setMainViewUtil(){
        mainViewUtil = new MainViewUtil();
        mainViewUtil.inflate(getApplicationContext(),R.layout.layout_actor_detail,layout_parent_actor_detail);
        mainViewUtil.createActorDetailViewAdapter();
    }

    private void setRecyclerViewUtil(){
        recyclerViewUtil = new RecyclerViewUtil(recyclerView_filmography,new FilmographyRecyclerAdapter(this));
        recyclerViewUtil.setLayoutManagerHorizontal(getApplicationContext());
    }

    private void setViewModel(){
        actorDetailViewModel = new ActorDetailViewModel();
        actorDetailViewModel.getActorDetail().observe(this,new ActorDetailObserver(mainViewUtil.getMainViewAdapter()));
        actorDetailViewModel.getFilmography().observe(this,new FilmographyObserver(recyclerViewUtil.getRecyclerViewAdapter()));
        actorDetailViewModel.requestActorDetail(id);
        actorDetailViewModel.requestFilmography(id);
    }

}