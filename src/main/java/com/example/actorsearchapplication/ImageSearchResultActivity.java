package com.example.actorsearchapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.actorsearchapplication.adapters.ImageSearchRecyclerViewAdapter;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.viewutil.ButtonClickHandler;
import com.example.actorsearchapplication.viewutil.IntentUtil;
import com.example.actorsearchapplication.viewutil.RecyclerViewUtil;
import com.example.actorsearchapplication.viewutil.StatusBar;

import java.util.ArrayList;
import java.util.List;

public class ImageSearchResultActivity extends AppCompatActivity implements ActivityClickListener {
    private List<ActorModel> searchedActors;
    private RecyclerView recyclerView;
    private IntentUtil intentUtil;

    private TextView titleView;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBar.setStatusBar(this);
        setContentView(R.layout.activity_image_search_result);
        recyclerView = findViewById(R.id.recyclerView_image_search);
        titleView = findViewById(R.id.tv_Title);
        backButton = findViewById(R.id.backButton);
        intentUtil = new IntentUtil(this);

        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(titleView);
        buttonClickHandler.setOnClickEvent(backButton);

        Intent intent = getIntent();
        searchedActors = (ArrayList<ActorModel>)intent.getSerializableExtra("actorList");

        RecyclerViewUtil recyclerViewUtil = new RecyclerViewUtil(recyclerView,new ImageSearchRecyclerViewAdapter(this));
        recyclerViewUtil.setLayoutManagerVertical(getApplicationContext());
        ((ImageSearchRecyclerViewAdapter)recyclerViewUtil.getRecyclerViewAdapter()).setSearchedActors(searchedActors);
        recyclerViewUtil.getRecyclerViewAdapter().notifyDataSetChanged();

    }

    @Override
    public void moveDetailPage(Class className, int id) {
        intentUtil.moveToDetailActivity(className,id);
    }
}