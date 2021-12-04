package com.example.actorsearchapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.actorsearchapplication.adapters.DibsViewAdapter;
import com.example.actorsearchapplication.adapters.MainContentViewAdapter;
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
import com.example.actorsearchapplication.utils.TransitionUtil;
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


public class MainActivity extends AppCompatActivity {

    public static final int MODE_ACTOR = 0;
    public static final int MODE_MOVIE_TV = 1;
    public static final int MODE_DIBS = 2;
    public int currentMode;

    TabLayout tab;
    TabItem tabItem_popularActor, tabItem_trend, tabItem_myActor;
    ImageButton search_button,search_image_button;

    ViewGroup root;
    Scene mainScene;
    Scene dibsScene;
    View mainContentsView;
    View dibsView;

    MainContentViewAdapter mainContentViewAdapter;
    DibsViewAdapter dibsViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBar.setStatusBar(this);

        onBindViewComponents();
        setViewEvent();
        setMainContentsView();
        setDibsView();
        setScene();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(currentMode == MODE_ACTOR) clickActorEvent();
        else if(currentMode == MODE_MOVIE_TV) clickMovieTvEvent();
        else if(currentMode == MODE_DIBS) clickDibsEvent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101){ // 카테고리 설정
            int mode = data.getIntExtra("category",-1);
            mainContentViewAdapter.notifyCategoryChanged(mode);
        }
    }

    private void onBindViewComponents(){
        tab = findViewById(R.id.tab);
        tabItem_myActor = findViewById(R.id.tabItem_myActor);
        tabItem_popularActor = findViewById(R.id.tabItem_popularActor);
        tabItem_trend = findViewById(R.id.tabItem_trend);
        search_button = findViewById(R.id.search_button);
        search_image_button = findViewById(R.id.search_image_button);
        root = findViewById(R.id.layout_parent_main);
    }

    private void setViewEvent(){
        tab.addOnTabSelectedListener(new TabLayoutHandler(this));
        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(search_button);
        buttonClickHandler.setOnClickEvent(search_image_button);
    }

    private void setMainContentsView(){
        mainContentsView = View.inflate(getApplicationContext(),R.layout.layout_main_contents,null);
        mainContentViewAdapter = new MainContentViewAdapter(this,mainContentsView);
    }

    private void setDibsView(){
        dibsView = View.inflate(getApplicationContext(),R.layout.layout_dibs,null);
        dibsViewAdapter = new DibsViewAdapter(dibsView,this);
    }

    private void setScene(){
        mainScene = new Scene(root,mainContentsView);
        dibsScene = new Scene(root,dibsView);
        mainScene.enter();
    }

    public void clickActorEvent(){
        currentMode = MODE_ACTOR;
        mainContentViewAdapter.requestChangeMode(MODE_ACTOR);
        TransitionUtil.setSceneTransition(mainScene);
    }
    public void clickMovieTvEvent(){
        currentMode = MODE_MOVIE_TV;
        mainContentViewAdapter.requestChangeMode(MODE_MOVIE_TV);
        TransitionUtil.setSceneTransition(mainScene);
    }
    public void clickDibsEvent(){
        currentMode = MODE_DIBS;
        dibsViewAdapter.refresh();
        TransitionUtil.setSceneTransition(dibsScene);
    }
}