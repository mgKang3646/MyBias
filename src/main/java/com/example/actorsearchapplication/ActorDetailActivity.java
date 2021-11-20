package com.example.actorsearchapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.actorsearchapplication.adapters.ActorDetailViewAdapter;
import com.example.actorsearchapplication.observer.ActorDetailObserver;
import com.example.actorsearchapplication.viewmodels.ActorDetailViewModel;
import com.example.actorsearchapplication.viewutil.ButtonClickHandler;

public class ActorDetailActivity extends AppCompatActivity {
    ImageButton imageButton;

    private int id;
    private ActorDetailViewModel actorDetailViewModel;
    private ActorDetailViewAdapter actorDetailViewAdapter;
    private View actorDetailView;
    private LinearLayout layout_parent_actor_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createStatusBar();
        setContentView(R.layout.activity_actor_detail);
        imageButton = findViewById(R.id.backButton_actor);
        layout_parent_actor_detail = findViewById(R.id.layout_parent_actor_detail);

        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(imageButton);

        Intent intent = getIntent();
        id = intent.getIntExtra("actor_id",-1); // 예외처리 해야됨

        actorDetailView = View.inflate(getApplicationContext(),R.layout.layout_actor_detail,layout_parent_actor_detail);
        actorDetailViewAdapter = new ActorDetailViewAdapter(actorDetailView);

        actorDetailViewModel = new ActorDetailViewModel();
        actorDetailViewModel.getActorDetail().observe(this,new ActorDetailObserver(actorDetailViewAdapter));
        actorDetailViewModel.requestActorDetail(id);
        actorDetailViewModel.requestFilmography(id);
    }

    private void createStatusBar(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));
    }
}