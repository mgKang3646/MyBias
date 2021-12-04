package com.example.actorsearchapplication;

import static android.os.Environment.DIRECTORY_PICTURES;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.actorsearchapplication.viewutil.ButtonClickHandler;
import com.example.actorsearchapplication.viewutil.IntentUtil;
import com.example.actorsearchapplication.viewutil.StatusBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SearchImageActivity extends AppCompatActivity {

    public static final int REQUEST_GET_IMAGE_FROM_GALLERY = 1;
    ImageButton cameraButton, galleryButton;
    FloatingActionButton closeButton;

    IntentUtil intentUtil;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_image);
        cameraButton = findViewById(R.id.btn_camera);
        galleryButton = findViewById(R.id.btn_gallery);
        closeButton = findViewById(R.id.btn_floating_search_close);

        intentUtil = new IntentUtil(this);

        // 권한 체크
        TedPermission.with(getApplicationContext())
                .setPermissionListener(permissionListener)
                .setRationaleMessage("카메라 권한이 필요합니다.")
                .setDeniedMessage("거부하셨습니다.")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();


        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(cameraButton);
        buttonClickHandler.setOnClickEvent(galleryButton);
        buttonClickHandler.setOnClickEvent(closeButton);
    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(getApplicationContext(), "권한이 거부됨",Toast.LENGTH_SHORT).show();
            intentUtil.finish();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_GET_IMAGE_FROM_GALLERY && resultCode == RESULT_OK){
                intentUtil.moveToSelectedImageActivity(data.getData());
        }
    }


}