package com.example.actorsearchapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.actorsearchapplication.adapters.MainRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoryActivity extends AppCompatActivity {

    TextView tv_category_movie, tv_category_series;
    FloatingActionButton floatingActionButton;

    // error : 이전 버튼을 클릭하면 꺼진다

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        tv_category_movie = findViewById(R.id.tv_category_movie);
        tv_category_series = findViewById(R.id.tv_category_series);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        tv_category_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_category_movie.setBackgroundColor(Color.parseColor("#E6FFFFFF"));
                Intent intent = new Intent();
                intent.putExtra("category", MainRecyclerViewAdapter.MODE_MOVIE);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

        tv_category_series.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_category_series.setBackgroundColor(Color.parseColor("#E6FFFFFF"));
                Intent intent = new Intent();
                intent.putExtra("category", MainRecyclerViewAdapter.MODE_TV);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("category", -1);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}