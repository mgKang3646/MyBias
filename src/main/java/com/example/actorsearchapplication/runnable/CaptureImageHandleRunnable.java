package com.example.actorsearchapplication.runnable;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.actorsearchapplication.CameraCaptureActivity;
import com.example.actorsearchapplication.viewutil.IntentUtil;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class CaptureImageHandleRunnable implements Runnable{
        private Image image;
        private String imagePath;
        private CameraCaptureActivity cameraCaptureActivity;
        private IntentUtil intentUtil;
        private byte[] bytes;

        public CaptureImageHandleRunnable(CameraCaptureActivity cameraCaptureActivity){
            this.cameraCaptureActivity = cameraCaptureActivity;
            this.image = cameraCaptureActivity.getCapturedImage();
            this.imagePath = cameraCaptureActivity.getCapturedImagePath();
            this.intentUtil = new IntentUtil(cameraCaptureActivity);
        }

        @Override
        public void run() {
            loadImageToByteArray();
            writeImageTOFile();
            handleIntentProcess();
        }

        private void loadImageToByteArray(){
            ByteBuffer byteBuffer = image.getPlanes()[0].getBuffer();
            bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes);
        }

        private void writeImageTOFile(){
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(imagePath);
                fileOutputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                image.close();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private void handleIntentProcess(){
            cameraCaptureActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    intentUtil.scanAndBroadCastImage(imagePath);
                    intentUtil.moveToSelectedImageActivity(path2uri(imagePath));
                }
            });

        }

        private Uri path2uri(String filePath) {
            Cursor cursor = cameraCaptureActivity.getApplicationContext().getContentResolver().query( MediaStore.Images.Media.EXTERNAL_CONTENT_URI ,
                    new String[] { MediaStore.Images.Media._ID } ,
                    MediaStore.Images.Media.DATA + "=? " ,
                    new String[] { filePath }, null);

            if (cursor != null && cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
                cursor.close();
                return Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI , Integer.toString(id));
            }
            else if (!filePath.isEmpty()) {
                ContentValues values = new ContentValues(); values.put(MediaStore.Images.Media.DATA, filePath);
                cursor.close();
                return cameraCaptureActivity.getApplicationContext().getContentResolver().insert( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            }
            else { return null; }
        }
    }

