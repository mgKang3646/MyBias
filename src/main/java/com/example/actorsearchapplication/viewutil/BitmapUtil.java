package com.example.actorsearchapplication.viewutil;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class BitmapUtil {

    public static ByteBuffer changeBitmapToByteBufferInPNG(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray() ;
        return ByteBuffer.wrap(byteArray);
    }

    public static ByteBuffer changeBitmapToByteBufferInJPEG(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] byteArray = stream.toByteArray() ;
        return ByteBuffer.wrap(byteArray);
    }
}
