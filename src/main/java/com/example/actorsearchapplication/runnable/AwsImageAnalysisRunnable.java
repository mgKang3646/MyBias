package com.example.actorsearchapplication.runnable;

import android.graphics.Bitmap;
import android.widget.Toast;

import com.example.actorsearchapplication.SelectedImageActivity;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.utils.AwsUtil;
import com.example.actorsearchapplication.utils.MVVMFactory;
import com.example.actorsearchapplication.viewutil.BitmapUtil;
import com.example.actorsearchapplication.viewutil.IntentUtil;

import java.nio.ByteBuffer;
import java.util.List;

public class AwsImageAnalysisRunnable implements Runnable{

    private SelectedImageActivity selectedImageActivity;
    private List<ActorModel> searchedActors;
    private ByteBuffer imageBuffer;
    private Bitmap bitmap;
    private AwsUtil awsUtil;
    private IntentUtil intentUtil;

    public AwsImageAnalysisRunnable(SelectedImageActivity selectedImageActivity, Bitmap bitmap, int mimeType){
        this.selectedImageActivity = selectedImageActivity;
        this.bitmap = bitmap;
        this.imageBuffer = getImageBuffer(mimeType);
        this.awsUtil = new AwsUtil();
        this.intentUtil = new IntentUtil(selectedImageActivity);
    }

    @Override
    public void run() {
        if(imageBuffer != null){
            searchedActors = awsUtil.getSearchedActors(imageBuffer);
            if(searchedActors != null) {
                SelectedImageActivity.NumForSearch = searchedActors.size();
                waitSettingProcess();
                onCompleted();
            }else { // 검색된 유명인이 없는 경우
                onPause("검색된 유명인이 없습니다.");
            }
        }
    }

    private ByteBuffer getImageBuffer(int mimeType){
        if(mimeType == SelectedImageActivity.MIME_TYPE_JPEG) return BitmapUtil.changeBitmapToByteBufferInJPEG(bitmap);
        else if(mimeType == SelectedImageActivity.MIME_TYPE_PNG) return BitmapUtil.changeBitmapToByteBufferInPNG(bitmap);
        else{
            onPause("PNG, JPEG 형식의 이미지만 분석 가능합니다.");
            return null;
        }
    }

    private void waitSettingProcess() {
        while (SelectedImageActivity.NumForSearch > 0) {
            for (int i = 0; i < searchedActors.size(); i++) {
                MVVMFactory.getClientAPI().setSearchedActor(searchedActors.get(i));
            }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void onCompleted() {
        selectedImageActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                selectedImageActivity.onAnalysisCompletedUi();
                intentUtil.moveToImageSearchResultActivity(searchedActors);
            }
        });
    }

    private void onPause(String msg){
        selectedImageActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            Toast.makeText(selectedImageActivity.getApplicationContext(),msg,Toast.LENGTH_LONG).show();
            selectedImageActivity.finish();
            }
        });
    }
}
