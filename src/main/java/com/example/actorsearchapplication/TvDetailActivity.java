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
import android.widget.TextView;

import com.example.actorsearchapplication.adapters.CastingRecyclerAdapter;
import com.example.actorsearchapplication.adapters.TvDetailViewAdapter;
import com.example.actorsearchapplication.observer.CastingObserver;
import com.example.actorsearchapplication.observer.TvDetailObserver;
import com.example.actorsearchapplication.viewmodels.TvDetailViewModel;
import com.example.actorsearchapplication.viewutil.ButtonClickHandler;
import com.example.actorsearchapplication.viewutil.IntentUtil;
import com.example.actorsearchapplication.viewutil.MainViewUtil;
import com.example.actorsearchapplication.viewutil.RecyclerViewUtil;
import com.example.actorsearchapplication.viewutil.StatusBar;

public class TvDetailActivity extends AppCompatActivity implements ActivityClickListener {

    private ImageButton backButton;
    private TextView titleTextView;
    private LinearLayout layout_parent_tv_detail;
    private TvDetailViewModel tvDetailViewModel;
    private RecyclerView recyclerView_casting_tv;

    private int id;

    private IntentUtil intentUtil;
    private MainViewUtil mainViewUtil;
    private RecyclerViewUtil recyclerViewUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);
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
        layout_parent_tv_detail = findViewById(R.id.layout_parent_tv_detail);
        recyclerView_casting_tv = findViewById(R.id.recyclerView_casting_tv);
    }

    private void getIntentValue(){
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1); // 예외처리 해야됨
    }

    private void setViewEvent(){
        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(backButton);
        buttonClickHandler.setOnClickEvent(titleTextView);
    }

    private void setIntentUtil(){
        intentUtil = new IntentUtil(this);
    }

    private void setMainViewUtil(){
        mainViewUtil = new MainViewUtil();
        mainViewUtil.inflate(getApplicationContext(),R.layout.layout_tv_detail,layout_parent_tv_detail);
        mainViewUtil.createTvDetailViewAdapter();
    }

    private void setRecyclerViewUtil(){
        recyclerViewUtil = new RecyclerViewUtil(recyclerView_casting_tv,new CastingRecyclerAdapter(this));
        recyclerViewUtil.setLayoutManagerHorizontal(getApplicationContext());
    }

    private void setViewModel(){
        tvDetailViewModel = new TvDetailViewModel();
        tvDetailViewModel.getTvDetail().observe(this, new TvDetailObserver(mainViewUtil.getMainViewAdapter()));
        tvDetailViewModel.getCastingTv().observe(this,new CastingObserver(recyclerViewUtil.getRecyclerViewAdapter()));
        tvDetailViewModel.requestTvDetail(id);
        tvDetailViewModel.requestCastingTv(id);
    }
}