package com.example.actorsearchapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.adapters.MainRecyclerViewAdapter;
import com.example.actorsearchapplication.adapters.SelectedViewAdapter;
import com.example.actorsearchapplication.models.UrlModel;
import com.example.actorsearchapplication.observer.ActorPopularObserver;
import com.example.actorsearchapplication.observer.SelectedActorObserver;
import com.example.actorsearchapplication.observer.SelectedTrendObserver;
import com.example.actorsearchapplication.observer.TrendObserver;
import com.example.actorsearchapplication.viewmodels.ListViewModel;
import com.example.actorsearchapplication.viewmodels.SelectedViewModel;
import com.example.actorsearchapplication.viewutil.ButtonClickHandler;
import com.example.actorsearchapplication.viewutil.TabLayoutHandler;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
;

// 뷰 홀더 공유하기
public class MainActivity extends AppCompatActivity implements ActivityViewListener {

    LinearLayout selected_parent_layout;

    // 뷰 컴포넌트
    TabLayout tab;
    TabItem tabItem_popularActor, tabItem_trend, tabItem_myActor;
    ImageButton search_button;
    RecyclerView recyclerView;
    View selectedView;

    //어댑터
    MainRecyclerViewAdapter mainRecyclerViewAdapter;
    SelectedViewAdapter selectedViewAdapter;

    // 뷰모델
    ListViewModel listViewModel;
    SelectedViewModel selectedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createStatusBar();
        setContentView(R.layout.activity_main);

        onSettingView();
        onSettingViewModel();

        setViewEvent();

    }

    @Override
    public void requestSwitchSelectedActor(int position) {
        selectedViewModel.switchSelectedActor(position);
    }

    @Override
    public void requestSwitchSelectedTrend(int position){
        selectedViewModel.switchSelectedTrend(position);
    }

    @Override
    public void moveActorDetailPage(int id) {
        Intent intent = new Intent(getApplicationContext(), ActorDetailActivity.class);
        intent.putExtra("actor_id",id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void onSettingView(){
        onBindViewComponents();
        createAdapter();
        onBindViewAndAdapter();
    }

    private void onSettingViewModel(){
        createViewModel();
        doObserve();
        requestDefaultProcessToViewModel();
    }

    private void createStatusBar(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));
    }

    private void onBindViewComponents(){
        tab = findViewById(R.id.tab);
        tabItem_myActor = findViewById(R.id.tabItem_myActor);
        tabItem_popularActor = findViewById(R.id.tabItem_popularActor);
        tabItem_trend = findViewById(R.id.tabItem_trend);
        search_button = findViewById(R.id.search_button);
        recyclerView = findViewById(R.id.recyclerView);
        selected_parent_layout = findViewById(R.id.selected_parent_layout);
        selectedView = View.inflate(getApplicationContext(),R.layout.layout_recycler_selected,selected_parent_layout);
    }

    private void createAdapter(){
        mainRecyclerViewAdapter = new MainRecyclerViewAdapter(this);
        selectedViewAdapter = new SelectedViewAdapter(this,selectedView);
    }

    private void onBindViewAndAdapter(){
        recyclerView.setAdapter(mainRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        selectedViewAdapter.setSelectedView(selectedView);
    }

    private void createViewModel(){
        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        selectedViewModel = new ViewModelProvider(this).get(SelectedViewModel.class);
    }

    private void doObserve(){
        listViewModel.getPopularActors().observe(this,new ActorPopularObserver(mainRecyclerViewAdapter));
        listViewModel.getTrends().observe(this,new TrendObserver(mainRecyclerViewAdapter));
        selectedViewModel.getSelectedActor().observe(this,new SelectedActorObserver(selectedViewAdapter));
        selectedViewModel.getSelectedTrend().observe(this,new SelectedTrendObserver(selectedViewAdapter));
    }

    private void requestDefaultProcessToViewModel(){
        UrlModel.setPage("1"); // 분리시켜야함
        listViewModel.requestPopularActors();
    }

    private void setViewEvent(){
        tab.addOnTabSelectedListener(new TabLayoutHandler(listViewModel));
        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(search_button);
    }

}