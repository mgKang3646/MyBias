package com.example.actorsearchapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.adapters.MainRecyclerViewAdapter;
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
import com.example.actorsearchapplication.viewutil.TabLayoutHandler;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;


public class MainActivity extends AppCompatActivity implements ActivityViewListener {


    // 뷰 컴포넌트
    TabLayout tab;
    TabItem tabItem_popularActor, tabItem_trend, tabItem_myActor;
    ImageButton search_button;
    Button category_button;
    RecyclerView recyclerView;
    View selectedView;
    LinearLayout layout_parent_selected;
    //어댑터
    MainRecyclerViewAdapter mainRecyclerViewAdapter;
    SelectedViewAdapter selectedViewAdapter;
    ArrayAdapter<String> stringArrayAdapter;
    // 뷰모델
    ListViewModel listViewModel;
    SelectedViewModel selectedViewModel;

    String[] items = {"영화", "시리즈"};

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101){ // 카테고리 설정
            int mode = data.getIntExtra("category",-1); // -1은 그냥 종료한 경우
            if(mode == MainRecyclerViewAdapter.MODE_MOVIE) listViewModel.requestMovies();
            else if(mode == MainRecyclerViewAdapter.MODE_TV) listViewModel.requestTvs();
        }
    }
    @Override
    public void requestSwitchSelectedActor(int position) {
        selectedViewModel.switchSelectedActor(position);
    }
    @Override
    public void requestSwitchSelectedMovie(int position){
        selectedViewModel.switchSelectedMovie(position);
    }
    @Override
    public void requestSwitchSelectedTv(int position) {
        selectedViewModel.switchSelectedTv(position);
    }
    @Override
    public void moveActorDetailPage(int id) {
        Intent intent = new Intent(getApplicationContext(), ActorDetailActivity.class);
        intent.putExtra("actor_id",id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    @Override
    public void moveMovieDetailPage(int id) {
        Intent intent = new Intent(getApplicationContext(), MovieDetailActivity.class);
        intent.putExtra("movie_id",id);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    @Override
    public void moveTVDetailPage(int id) {

    }

    @Override
    public ListViewModel getListViewModel(){
        return listViewModel;
    }

    @Override
    public Button getCategoryButton(){
        return category_button;
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

        layout_parent_selected = findViewById(R.id.layout_parent_selected);
        recyclerView = findViewById(R.id.recyclerView);
        selectedView = View.inflate(getApplicationContext(),R.layout.layout_recycler_selected,layout_parent_selected);

        tab = findViewById(R.id.tab);
        tabItem_myActor = findViewById(R.id.tabItem_myActor);
        tabItem_popularActor = findViewById(R.id.tabItem_popularActor);
        tabItem_trend = findViewById(R.id.tabItem_trend);
        search_button = findViewById(R.id.search_button);
        category_button = findViewById(R.id.category_button);
        category_button.setVisibility(View.INVISIBLE);
    }

    private void createAdapter(){
        mainRecyclerViewAdapter = new MainRecyclerViewAdapter(this);
        selectedViewAdapter = new SelectedViewAdapter(this,selectedView);
    }

    private void onBindViewAndAdapter(){
        stringArrayAdapter = new ArrayAdapter<>(this,R.layout.trend_list,items);
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
        listViewModel.getMovies().observe(this,new MovieObserver(mainRecyclerViewAdapter));
        listViewModel.getTvs().observe(this,new TvObserver(mainRecyclerViewAdapter));
        selectedViewModel.getSelectedActor().observe(this,new SelectedActorObserver(selectedViewAdapter));
        selectedViewModel.getSelectedMovie().observe(this,new SelectedMovieObserver(selectedViewAdapter));
        selectedViewModel.getSelectedTv().observe(this,new SelectedTvObserver(selectedViewAdapter));
    }
    private void requestDefaultProcessToViewModel(){
        UrlModel.setPage("1"); // 분리시켜야함
        listViewModel.requestPopularActors();
    }

    private void setViewEvent(){
        tab.addOnTabSelectedListener(new TabLayoutHandler(this));
        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(search_button);
        buttonClickHandler.setOnClickEvent(category_button);
    }



}