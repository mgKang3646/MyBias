package com.example.actorsearchapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.actorsearchapplication.adapters.MainRecyclerViewAdapter;
import com.example.actorsearchapplication.adapters.MainViewAdapter;
import com.example.actorsearchapplication.adapters.SelectedViewAdapter;
import com.example.actorsearchapplication.models.UrlModel;
import com.example.actorsearchapplication.observer.ActorPopularObserver;
import com.example.actorsearchapplication.observer.MovieObserver;
import com.example.actorsearchapplication.observer.SelectedActorObserver;
import com.example.actorsearchapplication.observer.SelectedMovieObserver;
import com.example.actorsearchapplication.observer.SelectedTvObserver;
import com.example.actorsearchapplication.observer.TvObserver;
import com.example.actorsearchapplication.viewmodels.ListViewModel;
import com.example.actorsearchapplication.viewmodels.SelectedViewModel;
import com.example.actorsearchapplication.viewutil.ButtonClickHandler;
import com.example.actorsearchapplication.viewutil.IntentUtil;
import com.example.actorsearchapplication.viewutil.MainViewUtil;
import com.example.actorsearchapplication.viewutil.RecyclerViewUtil;
import com.example.actorsearchapplication.viewutil.StatusBar;
import com.example.actorsearchapplication.viewutil.TabLayoutHandler;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity implements MainActivityViewListener {
    // 뷰 컴포넌트
    TabLayout tab;
    TabItem tabItem_popularActor, tabItem_trend, tabItem_myActor;
    ImageButton search_button,search_image_button;
    Button category_button;
    RecyclerView recyclerView;
    LinearLayout layout_parent_selected;
    //Util
    MainViewUtil mainViewUtil;
    RecyclerViewUtil recyclerViewUtil;
    IntentUtil intentUtil;
    // 뷰모델
    ListViewModel listViewModel;
    SelectedViewModel selectedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBar.setStatusBar(this);
        UrlModel.setPage("1");

        onBindViewComponents();
        setIntentUtil();
        setViewEvent();
        setMainView();
        setRecyclerView();
        setViewModel();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101){ // 카테고리 설정
            int mode = data.getIntExtra("category",-1); // -1은 그냥 종료한 경우
            if(mode == MainRecyclerViewAdapter.MODE_MOVIE) listViewModel.requestMovies();
            else if(mode == MainRecyclerViewAdapter.MODE_TV) listViewModel.requestTvs();

        }
    }
    @Override
    public void requestSwitchSelected(int mode, int position) { selectedViewModel.switchSelected(mode,position); }
    @Override
    public void moveDetailPage(Class className, int id) { intentUtil.moveToDetailActivity(className,id); }

    public ListViewModel getListViewModel(){
        return listViewModel;
    }
    public Button getCategoryButton(){
        return category_button;
    }

    private void onBindViewComponents(){
        layout_parent_selected = findViewById(R.id.layout_parent_selected);
        recyclerView = findViewById(R.id.recyclerView);
        tab = findViewById(R.id.tab);
        tabItem_myActor = findViewById(R.id.tabItem_myActor);
        tabItem_popularActor = findViewById(R.id.tabItem_popularActor);
        tabItem_trend = findViewById(R.id.tabItem_trend);
        search_button = findViewById(R.id.search_button);
        search_image_button = findViewById(R.id.search_image_button);
        category_button = findViewById(R.id.category_button);
        category_button.setVisibility(View.INVISIBLE);
    }

    private void setIntentUtil(){
        intentUtil = new IntentUtil(this);
    }

    private void setViewEvent(){
        tab.addOnTabSelectedListener(new TabLayoutHandler(this));
        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(search_button);
        buttonClickHandler.setOnClickEvent(search_image_button);
        buttonClickHandler.setOnClickEvent(category_button);
    }

    private void setMainView(){
        mainViewUtil = new MainViewUtil();
        mainViewUtil.inflate(getApplicationContext(),R.layout.layout_recycler_selected,layout_parent_selected);
        mainViewUtil.createSelectedViewAdapter(this);
    }

    private void setRecyclerView(){
        recyclerViewUtil = new RecyclerViewUtil(recyclerView,new MainRecyclerViewAdapter(this));
        recyclerViewUtil.setLayoutManagerHorizontal(getApplicationContext());
    }

    private void setViewModel(){
        selectedViewModel = new ViewModelProvider(this).get(SelectedViewModel.class);
        selectedViewModel.observe(this, mainViewUtil.getMainViewAdapter());
        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        listViewModel.observe(this,recyclerViewUtil.getRecyclerViewAdapter());
        listViewModel.requestPopularActors();
    }


}