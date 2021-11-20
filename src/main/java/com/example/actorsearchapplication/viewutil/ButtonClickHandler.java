package com.example.actorsearchapplication.viewutil;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.SearchActivity;

public class ButtonClickHandler {

    private AppCompatActivity activity;

    public ButtonClickHandler(AppCompatActivity activity){
        this.activity = activity;
    }

    public void setOnClickEvent(ImageButton button){
        setOnClick(button);
    }

    private void setOnClick(ImageButton button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEachButtonClickEvent(button);
            }
        });
    }

    private void setEachButtonClickEvent(ImageButton button){
        if(button.getId() == R.id.search_button) setSearchButtonClickEvent();
        else if(button.getId() == R.id.backButton) setBackButtonClickEvent();
        else if(button.getId() == R.id.backButton_actor) setBackButtonActionClickEvent();
    }

    private void setSearchButtonClickEvent(){
        Intent intent = new Intent(activity.getApplicationContext(), SearchActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void setBackButtonClickEvent(){
        activity.finish();
        activity.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    private void setBackButtonActionClickEvent(){
        activity.finish();
        activity.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

}
