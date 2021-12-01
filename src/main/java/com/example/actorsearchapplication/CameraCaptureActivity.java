package com.example.actorsearchapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.actorsearchapplication.viewutil.StatusBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class CameraCaptureActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION_RESULT = 0;
    private static final int STATE_PREVIEW = 0;
    private static final int STATE_WAIT_LOCK = 1;
    private int mCaptureState = STATE_PREVIEW;
    private ImageButton mStillImageButton;

    private TextureView textureView;
    private CameraDevice mCameraDevice;

    //UI 처리는 Main 스레드만 가능, But HandlerThread를 생성하고 Handler에게 Runnable을 던지면 UI 처리가 가능
    private HandlerThread mBackgroundHandlerThread;
    private Handler mBackgroundHandler;
    private String mCameraId;
    private CameraCaptureSession mPreviewCaptureSession;
    private int mTotalRotation;
    private CaptureRequest.Builder mCaptureRequestBuilder;
    private Size mPreViewSize;
    private Size mImageSize;
    private ImageReader mImageReader;


    private File mImageFolder;
    private String mImageFileName;

    private byte[] bytes;


    private static SparseIntArray ORIENTAITIONS = new SparseIntArray();
    static { // 키 : value
        ORIENTAITIONS.append(Surface.ROTATION_0, 0);
        ORIENTAITIONS.append(Surface.ROTATION_90, 90);
        ORIENTAITIONS.append(Surface.ROTATION_180, 180);
        ORIENTAITIONS.append(Surface.ROTATION_270, 270);
    }

    private static class CompareSizeByArea implements Comparator<Size>{
        @Override
        public int compare(Size size, Size t1) {
            return Long.signum((long) size.getWidth()*size.getHeight() / (long)t1.getWidth()*t1.getHeight());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_capture);
        createImageFolder();
        textureView = (TextureView)findViewById(R.id.textureView);
        mStillImageButton = findViewById(R.id.capture_button);
        mStillImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStillCaptureRequest();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        //BackgroundHandler부터 생성
        startBackgroundThread();
        textureView.setSurfaceTextureListener(textureViewListener);
//        if(textureView.isAvailable()){
//            setUpCamera(textureView.getWidth(),textureView.getHeight());
//            connectCamera();
//        }else{
//
//        }
    }

    @Override
    protected void onPause() {
        closeCamera();
        stopBackGroundThread();
        super.onPause();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if(hasFocus){
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }

    TextureView.SurfaceTextureListener textureViewListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int width, int height) {
            setUpCamera(width,height);
            connectCamera();
        }

        @Override
        public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {

        }
    };

    // Camera Device State가 변할 때 마다 호출되는 메소드.
    private CameraDevice.StateCallback mCameraStateCallback = new CameraDevice.StateCallback() {
        //Camera가 Open될 때
        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            mCameraDevice = cameraDevice;
            startPreview(); // TextureView에 실시간으로 PreView가 뜨드록 요청
            Toast.makeText(getApplicationContext(),"Camera connection made!",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            cameraDevice.close();
            mCameraDevice = null;
        }

        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
            cameraDevice.close();
            mCameraDevice = null;
        }
    };

    private void setUpCamera(int width,int height){ // TextureView의 width와 height
        //getSystemService() : 시스템 레벨의 기능에 접근할 수 있는 Manager 객체 가져오기
        CameraManager cameraManager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try {
            for(String cameraId : cameraManager.getCameraIdList()){ // 전후면 카메라 id 갖고 오기
                //특정 카메라의 각종 정보를 갖고 오기
                CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId);

                //전면 카메라는 거르기
                //LENS_FACING_FRONT : 전면 , LENS_FACING_BACK : 후면
                if(cameraCharacteristics.get(CameraCharacteristics.LENS_FACING) ==  CameraCharacteristics.LENS_FACING_FRONT){
                    continue;
                }

                // CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP 키는
                // get에 넣으면
                // StreamConfigurationMap 객체 정보를 return 한다.
                // StreamConfigurationMap 이미지 사이즈에 대한 정보를 갖고 있다.
                StreamConfigurationMap map = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);

                int deviceOrientation = getWindowManager().getDefaultDisplay().getRotation();
                mTotalRotation = sensorToDeviceRotation(cameraCharacteristics,deviceOrientation);
                boolean swapRoatation = mTotalRotation == 90 || mTotalRotation == 270;

                //TextureView의 width와 height
                int rotateWidth = width;
                int rotateHeight = height;

                if(swapRoatation){
                    rotateWidth = height;
                    rotateHeight =width;
                }

                //map.getOutputSizes를 통해 제공되는 사이즈들 중 TextureView의 사이즈와 적합한 최적의 사이즈를 선택하는 메소드
                mPreViewSize = chooseOptimalSize(map.getOutputSizes(SurfaceTexture.class),rotateWidth,rotateWidth);
                mImageSize = chooseOptimalSize(map.getOutputSizes(ImageFormat.JPEG),rotateWidth,rotateHeight);
                // 이미지 포맷 정의
                mImageReader = ImageReader.newInstance(mImageSize.getWidth(),mImageSize.getHeight(),ImageFormat.JPEG,1);
                //
                mImageReader.setOnImageAvailableListener(mOnImageAvailableListener,mBackgroundHandler);
                mCameraId = cameraId;
                return;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void connectCamera(){
        CameraManager cameraManager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    cameraManager.openCamera(mCameraId,mCameraStateCallback,mBackgroundHandler);
                }else{
                    if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                        Toast.makeText(this,"Video App requires Access to Camera",Toast.LENGTH_SHORT).show();
                    }
                    requestPermissions(new String[] { Manifest.permission.CAMERA},REQUEST_CAMERA_PERMISSION_RESULT);
                }
            }else{
                cameraManager.openCamera(mCameraId,mCameraStateCallback,mBackgroundHandler);
            }
        }catch (CameraAccessException e){
            e.printStackTrace();
        }

    }

    private void startPreview(){
        // Surface : 화면 컴퍼지터가 관리하는 원시 버퍼를 제어하는 객체, 파이프 라인은 대변
        // 소비자 : TextureView
        // 생산자 : CameraDevice
        SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
        surfaceTexture.setDefaultBufferSize(mPreViewSize.getWidth(),mPreViewSize.getHeight());
        Surface previewSurface = new Surface(surfaceTexture);

        try {
            //카메라 PREVIEW를 요청하는 Request Builder 생성
            mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            //Request의 결과를 받을 Target Buffer로 previewSurface를 선정
            mCaptureRequestBuilder.addTarget(previewSurface);

            //CaptureSession은 Device와 APP 간의 인터페이스, CaptureRequest를 유지
            mCameraDevice.createCaptureSession(Arrays.asList(previewSurface,mImageReader.getSurface()),
                    new CameraCaptureSession.StateCallback() {
                //CaptureSession이 구성되는 단계
                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    mPreviewCaptureSession = cameraCaptureSession;
                    try {
                        //끊임없이 request를 요청하여 Preview에 카메라가 이동할때마다 이미지가 전달
                        mPreviewCaptureSession.setRepeatingRequest(mCaptureRequestBuilder.build(),
                                null,mBackgroundHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                        Toast.makeText(getApplicationContext(),"Unable to setUp Camera preview ",Toast.LENGTH_SHORT).show();
                }
            },null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    //Still Capture 정지된 촬영
    private void startStillCaptureRequest(){
        try {
            Toast.makeText(getApplicationContext(),"startStillCaptureRequest 실행",Toast.LENGTH_SHORT ).show();
            mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            mCaptureRequestBuilder.addTarget(mImageReader.getSurface());
            mCaptureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION,mTotalRotation);
            CameraCaptureSession.CaptureCallback stillCaptureCallback = new CameraCaptureSession.CaptureCallback() {

                // 파이프라인 식으로 동작하므로 중강중간 Callback으로 동작을 알림
                @Override
                public void onCaptureStarted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, long timestamp, long frameNumber) {
                    super.onCaptureStarted(session, request, timestamp, frameNumber);
                    try {
                        Toast.makeText(getApplicationContext(),"onCaptureStarted 실행",Toast.LENGTH_SHORT ).show();
                        createImageFileName();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
                    super.onCaptureCompleted(session, request, result);
                    moveCaptureResultActivity();
                }

            };
            //사진 캡처 요청하기 Submit a request for an image to be captured by the camera device.
            mPreviewCaptureSession.capture(mCaptureRequestBuilder.build(),stillCaptureCallback,null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"startStillCaptureRequest 에러발생",Toast.LENGTH_SHORT ).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CAMERA_PERMISSION_RESULT){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"Application is not Run without camera services",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void closeCamera(){
        if(mCameraDevice != null){
            mCameraDevice.close();
            mCameraDevice = null;
        }
    }

    private void startBackgroundThread(){
        mBackgroundHandlerThread = new HandlerThread("Camera2VideoImage");
        mBackgroundHandlerThread.start();
        mBackgroundHandler = new Handler(mBackgroundHandlerThread.getLooper());
    }

    private void stopBackGroundThread(){
        try {
            mBackgroundHandlerThread.quitSafely();
            mBackgroundHandlerThread.join();
            mBackgroundHandlerThread =null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int sensorToDeviceRotation(CameraCharacteristics cameraCharacteristics,int deviceOrientation){
        // 카메라의 센서 방향 갖고 오기
        int sensorOrientation= cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
        // device의 방향의 value 갖고 오기
        deviceOrientation = ORIENTAITIONS.get(deviceOrientation);
        return ( sensorOrientation + deviceOrientation + 360)%360;
    }

    //최적의 이미지 사이즈 찾기
    private static Size chooseOptimalSize(Size[] choices,int width, int height ){
        List<Size> bigEnough = new ArrayList<Size>();
        for(Size option : choices){
            if(option.getHeight() == option.getWidth()*height/width && option.getWidth() >= width
                    && option.getHeight()>= height){
                bigEnough.add(option); // 제공되는 사이즈 중 TextureView의 width와 height가 큰 size를 리스트에 저장
            }
        }
        if(bigEnough.size() > 0){
            return Collections.min(bigEnough, new CompareSizeByArea());  // 큰 것들 중 가장 최소 사이즈 리턴턴
        }else{
           return choices[0]; // 없으면 가장 첫 번째 사이즈 리턴
        }
    }

    private final ImageReader.OnImageAvailableListener mOnImageAvailableListener = new ImageReader.OnImageAvailableListener() {
        @Override //카메라로부터 이미지를 받은 경우
        public void onImageAvailable(ImageReader imageReader) {
                //ImageReader가 acquire한 최신이미지를 ImageSaver Runnable이 Handler에 post되어서 처리된다.
                mBackgroundHandler.post(new ImageSaver(imageReader.acquireLatestImage()));
        }
    };

//    private CameraCaptureSession.CaptureCallback mPreviewCaptureCallback = new CameraCaptureSession.CaptureCallback() {
//
//        private void process(CaptureResult captureResult){
//            switch (mCaptureState){
//                case STATE_PREVIEW : break;
//                case STATE_WAIT_LOCK:
//                    mCaptureState = STATE_PREVIEW;
//                    Integer afState = captureResult.get(CaptureResult.CONTROL_AF_STATE);
//                    Toast.makeText(getApplicationContext(),"AF Locked Before!",Toast.LENGTH_SHORT).show();
//
//                    if(afState == CaptureResult.CONTROL_AF_STATE_FOCUSED_LOCKED ||
//                            afState == CaptureResult.CONTROL_AF_STATE_NOT_FOCUSED_LOCKED ){
//                        Toast.makeText(getApplicationContext(),"AF Locked!",Toast.LENGTH_SHORT).show();
//                        startStillCaptureRequest();
//                    }
//                    break;
//            }
//        }
//        @Override
//        public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
//            super.onCaptureCompleted(session, request, result);
//            process(result);
//        }
//    };
//    private void lockFocus(){
//        mCaptureState = STATE_WAIT_LOCK; // STATE 변환
//        mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER,CaptureRequest.CONTROL_AF_TRIGGER_START); // SYSTEM에 Capture를 요청할 Request를 Build
//
//        try {
//            mPreviewCaptureSession.capture(mCaptureRequestBuilder.build(),mPreviewCaptureCallback,mBackgroundHandler);
//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//        }
//    }

    ///File 생성
    private void createImageFolder() {
        File imageFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        mImageFolder = new File(imageFile,"actorSearchImage");
        if(!mImageFolder.exists()){
            mImageFolder.mkdir();
        }
    }

    private File createImageFileName() throws IOException{
        String timestamp = new SimpleDateFormat(("yyyyMMdd HHmmss")).format(new Date());
        String prepend = "IMAGE_"+timestamp+"_";
        File imageFile = File.createTempFile(prepend,".jpg",mImageFolder);
        mImageFileName = imageFile.getAbsolutePath();
        return imageFile;
    }

    private void moveCaptureResultActivity(){
        Intent intent = new Intent(getApplicationContext(),CaptureResultActivity.class);
        intent.putExtra("CapturedImageBytes",bytes);
        startActivity(intent);
    }

    // Background Handler에서 처리되어 질 Runnable
    private class ImageSaver implements Runnable{

        private final Image mImage;

        public ImageSaver(Image image){
            mImage = image;
        }

        @Override
        public void run() {
            ByteBuffer byteBuffer = mImage.getPlanes()[0].getBuffer();
            bytes = new byte[byteBuffer.remaining()];
            ///byteBuffer.get(bytes);

         //   FileOutputStream fileOutputStream = null;
//            try {
//                fileOutputStream = new FileOutputStream(mImageFileName);
//                fileOutputStream.write(bytes);
//                Toast.makeText(getApplicationContext(),"ImageSaver : mImageFileName로 전달",Toast.LENGTH_SHORT).show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }finally{
//                mImage.close();
//                if(fileOutputStream != null){
//                    try {
//                        fileOutputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }

        }
    }
}