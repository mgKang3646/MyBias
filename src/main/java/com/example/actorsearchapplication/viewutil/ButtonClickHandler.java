package com.example.actorsearchapplication.viewutil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.actorsearchapplication.CategoryActivity;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.SearchActivity;
import com.example.actorsearchapplication.SelectedImageActivity;
import com.example.actorsearchapplication.runnable.AwsImageAnalysisRunnable;
import com.example.actorsearchapplication.utils.MVVMFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ButtonClickHandler {

    private AppCompatActivity activity;
    private IntentUtil intentUtil;

    public ButtonClickHandler(AppCompatActivity activity){
        this.activity = activity;
        this.intentUtil = new IntentUtil(activity);
    }

    public void setOnClickEvent(ImageButton button){
        setOnClick(button);
    }
    public void setOnClickEvent(Button button){
        setOnClick(button);
    }
    public void setOnClickEvent(TextView textView) { setOnClick(textView);}

    private void setOnClick(ImageButton button){ // + FloatingButton
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEachButtonClickEvent(button);
            }
        });
    }
    private void setOnClick(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEachButtonClickEvent(button);
            }
        });
    }
    private void setOnClick(TextView textView){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEachButtonClickEvent(textView);
            }
        });
    }

    private void setEachButtonClickEvent(ImageButton button){
        if(button.getId() == R.id.search_button) setSearchButtonClickEvent();
        else if(button.getId() == R.id.backButton) setBackButtonClickEvent();
        else if(button.getId() == R.id.search_image_button) setSearchImageButtonClickEvent();
        else if(button.getId() == R.id.btn_image_search_back) setImageSearchBackButtonEvent();
        else if(button.getId() == R.id.btn_camera) setCameraButtonEvent();
        else if(button.getId() == R.id.btn_gallery) setGalleryButtonEvent();
    }

    private void setEachButtonClickEvent(Button button){
        if(button.getId() == R.id.category_button) setCategoryButtonClickEvent();
    }

    private void setEachButtonClickEvent(TextView textView){
        if(textView.getId() == R.id.tv_Title) setTitleTextViewClickEvent();
    }

    private void setSearchImageButtonClickEvent(){ intentUtil.moveToSearchImageActivity(); }
    private void setSearchButtonClickEvent(){ intentUtil.moveToSearchActivity(); }
    private void setBackButtonClickEvent(){ intentUtil.backToBeforeActivity(); }
    private void setCategoryButtonClickEvent(){ intentUtil.moveToCategoryActivityForResult(); }
    private void setTitleTextViewClickEvent(){ intentUtil.goHome();}
    private void setImageSearchBackButtonEvent() { intentUtil.finish(); }
    private void setCameraButtonEvent(){ intentUtil.moveToCameraCaptureActivity();}
    private void setGalleryButtonEvent() { intentUtil.moveToGalleryForResult();}

    public void setImageAnalysisButtonEvent(ImageButton analysisButton, Bitmap bitmap, int mimeType){
        analysisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setImageAnalysisButtonEvent(bitmap,mimeType);
            }
        });
    }

    private void setImageAnalysisButtonEvent(Bitmap bitmap,int mimeType){
        SelectedImageActivity selectedImageActivity = (SelectedImageActivity)activity;
        selectedImageActivity.onAnalysisUi();
        MVVMFactory.getRequestExecutor().getScheduledExecutorService()
                .submit(new AwsImageAnalysisRunnable(selectedImageActivity,bitmap,mimeType));
    }

}
