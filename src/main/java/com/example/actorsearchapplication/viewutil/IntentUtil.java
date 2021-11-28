package com.example.actorsearchapplication.viewutil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.actorsearchapplication.ActorDetailActivity;
import com.example.actorsearchapplication.R;

public class IntentUtil {

    AppCompatActivity currentActivity;

    public IntentUtil(AppCompatActivity currentActivity){
        this.currentActivity = currentActivity;
    }

    public void moveToDetailActivity(Class detailActivity, int id){
        Intent intent = new Intent(currentActivity.getApplicationContext(),detailActivity);
        intent.putExtra("id",id);
        currentActivity.startActivity(intent);
        currentActivity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}
