package com.example.actorsearchapplication.utils;

import static android.os.Environment.DIRECTORY_PICTURES;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageFileMaker {
    File imageFolder;
    File imageFile;
    String imageFileName;
    String imageFilePath;

    public void createImageFolder() {
        imageFolder = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES),"MyBiasFolder");
        if(!imageFolder.exists()){
            imageFolder.mkdir();
        }
    }
    public void createImageFile() throws IOException {
        String timestamp = new SimpleDateFormat(("yyyyMMdd HHmmss")).format(new Date());
        imageFileName = "MYBIAS_"+timestamp+"_";
        imageFile = File.createTempFile(imageFileName,".jpg",imageFolder);
        imageFilePath = imageFile.getAbsolutePath();
    }

    public File getImageFolder() {
        return imageFolder;
    }

    public File getImageFile() {
        return imageFile;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }
}
