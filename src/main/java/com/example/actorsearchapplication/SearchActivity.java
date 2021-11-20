package com.example.actorsearchapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.example.actorsearchapplication.viewutil.ButtonClickHandler;

public class SearchActivity extends AppCompatActivity {
    SearchView searchView;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));
        setContentView(R.layout.activitiy_search);

        searchView = findViewById(R.id.searchView);
        backButton = findViewById(R.id.backButton);

        searchView.onActionViewExpanded();
        setViewEvent();


    }

    private void setViewEvent(){
        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(backButton);
    }
}