package com.example.actorsearchapplication.viewutil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;

import com.example.actorsearchapplication.ActorDetailActivity;
import com.example.actorsearchapplication.CameraCaptureActivity;
import com.example.actorsearchapplication.CategoryActivity;
import com.example.actorsearchapplication.ImageSearchResultActivity;
import com.example.actorsearchapplication.MainActivity;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.SearchActivity;
import com.example.actorsearchapplication.SearchImageActivity;
import com.example.actorsearchapplication.SelectedImageActivity;
import com.example.actorsearchapplication.adapters.MainRecyclerViewAdapter;
import com.example.actorsearchapplication.models.ActorModel;

import java.io.Serializable;
import java.util.List;

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

    public void returnCategoryResult(int result){
        Intent intent = new Intent();
        intent.putExtra("category",result);
        currentActivity.setResult(Activity.RESULT_OK,intent);
        currentActivity.finish();
    }

    public void moveToCategoryActivityForResult(){
        Intent intent = new Intent(currentActivity.getApplicationContext(), CategoryActivity.class);
        currentActivity.startActivityForResult(intent,101);
    }

    public void backToBeforeActivity(){
        currentActivity.finish();
        currentActivity.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public void moveToSearchActivity(){
        Intent intent = new Intent(currentActivity.getApplicationContext(), SearchActivity.class);
        currentActivity.startActivity(intent);
        currentActivity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void goHome(){
        Intent intent = new Intent(currentActivity, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 지금껏 열린 액티비티 청소하기
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        currentActivity.startActivity(intent);
        currentActivity.finish();
        currentActivity.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public void moveToSearchImageActivity(){
        Intent intent = new Intent(currentActivity.getApplicationContext(), SearchImageActivity.class);
        currentActivity.startActivity(intent);
    }

    public void moveToCameraCaptureActivity(){
        Intent intent = new Intent(currentActivity.getApplicationContext(), CameraCaptureActivity.class);
        currentActivity.startActivity(intent);
    }

    public void moveToGalleryForResult(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        currentActivity.startActivityForResult(intent,SearchImageActivity.REQUEST_GET_IMAGE_FROM_GALLERY);
    }

    public void moveToImageSearchResultActivity(List<ActorModel> awsSearchedActors){
        Intent intent = new Intent(currentActivity.getApplicationContext(), ImageSearchResultActivity.class);
        intent.putExtra("actorList", (Serializable) awsSearchedActors);
        currentActivity.startActivity(intent);
    }

    public void moveToSelectedImageActivity(Uri selectedImageUri){
        Intent intent = new Intent(currentActivity.getApplicationContext(), SelectedImageActivity.class);
        intent.putExtra("SelectedImageUri",selectedImageUri);
        currentActivity.startActivity(intent);
    }

    public void finish(){
        currentActivity.finish();
    }
}
