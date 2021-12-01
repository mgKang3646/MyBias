package com.example.actorsearchapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class CaptureResultActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_result);
        imageView = findViewById(R.id.iv_capture_result);

        Intent intent = getIntent();
        byte[] bytes = intent.getByteArrayExtra("CapturedImageBytes");
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);

        imageView.setImageBitmap(bitmap);

    }
}