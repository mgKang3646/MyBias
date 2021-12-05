package com.example.actorsearchapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.actorsearchapplication.models.SNSModel;
import com.example.actorsearchapplication.utils.SNSUtil;
import com.example.actorsearchapplication.viewutil.ButtonClickHandler;
import com.example.actorsearchapplication.viewutil.StatusBar;

public class WebViewActivity extends AppCompatActivity {

    ImageButton backButton;
    TextView titleView;
    private WebView webView;
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        StatusBar.setStatusBar(this);

        webView = findViewById(R.id.web_view);
        backButton = findViewById(R.id.backButton);
        titleView = findViewById(R.id.tv_Title);

        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(this);
        buttonClickHandler.setOnClickEvent(backButton);
        buttonClickHandler.setOnClickEvent(titleView);

        Intent intent = getIntent();
        SNSModel snsModel = (SNSModel) intent.getSerializableExtra("SNSUrl");

        url += SNSUtil.getHomeUrl(snsModel.getSort())+snsModel.getId();

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClientClass());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}