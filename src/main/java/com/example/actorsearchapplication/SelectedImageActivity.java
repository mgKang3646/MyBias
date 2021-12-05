package com.example.actorsearchapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieAnimationView;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.model.Celebrity;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.RecognizeCelebritiesRequest;
import com.amazonaws.services.rekognition.model.RecognizeCelebritiesResult;
import com.example.actorsearchapplication.models.AWSCelebrity;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.runnable.AwsImageAnalysisRunnable;
import com.example.actorsearchapplication.utils.AwsUtil;
import com.example.actorsearchapplication.utils.MVVMFactory;
import com.example.actorsearchapplication.viewutil.BitmapUtil;
import com.example.actorsearchapplication.viewutil.ButtonClickHandler;
import com.example.actorsearchapplication.viewutil.IntentUtil;
import com.example.actorsearchapplication.viewutil.StatusBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class SelectedImageActivity extends AppCompatActivity {

    public static int NumForSearch = 0;
    public static final int MIME_TYPE_PNG = 0;
    public static final int MIME_TYPE_JPEG = 1;
    public static final int MIME_TYPE_FAULT = -1;
    TextView titleView;
    ImageView imageView,loading_background;
    FloatingActionButton imageSearchButton;
    FloatingActionButton backButton;
    LottieAnimationView loadingView;
    String mimeType;

    Bitmap bitmap;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBar.setStatusBar(this);
        setContentView(R.layout.activity_selected_image);

        onBindViewById();
        getImageUri();
        setImageView();
        changeUriToBitmap();
        setButtonClickEvent();
    }

    private void onBindViewById(){
        titleView = findViewById(R.id.tv_Title);
        imageView = findViewById(R.id.iv_result);
        imageSearchButton = findViewById(R.id.btn_image_analysis);
        backButton = findViewById(R.id.btn_image_search_back);
        loadingView = findViewById(R.id.lottie_loading);
        loading_background = findViewById(R.id.loading_background);
    }

    private void getImageUri(){
        Intent intent = getIntent();
        imageUri = intent.getParcelableExtra("SelectedImageUri");
        Log.v("Tag",imageUri.toString());
        checkMimeType();
    }

    private void setImageView(){
        imageView.setImageURI(imageUri);
    }

    private void changeUriToBitmap(){
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setButtonClickEvent(){
        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(backButton);
        buttonClickHandler.setOnClickEvent(titleView);
        if(mimeType.equals("jpg")) buttonClickHandler.setImageAnalysisButtonEvent(imageSearchButton,bitmap,MIME_TYPE_JPEG);
        else if(mimeType.equals("png")) buttonClickHandler.setImageAnalysisButtonEvent(imageSearchButton,bitmap,MIME_TYPE_PNG);
        else buttonClickHandler.setImageAnalysisButtonEvent(imageSearchButton,bitmap,MIME_TYPE_FAULT);
    }

    private void checkMimeType(){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        mimeType = mime.getExtensionFromMimeType(cR.getType(imageUri));
        Log.v("Tag","Mime Type : " + mimeType);
    }

    public void onAnalysisUi(){
        loadingView.playAnimation();
        loadingView.setVisibility(View.VISIBLE);
        loading_background.setVisibility(View.VISIBLE);
    }

    public void onAnalysisCompletedUi(){
        loadingView.cancelAnimation();
        loading_background.setVisibility(View.INVISIBLE);
        loadingView.setVisibility(View.INVISIBLE);
    }

}