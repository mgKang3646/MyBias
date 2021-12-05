package com.example.actorsearchapplication.utils;

import android.widget.ImageView;

import com.example.actorsearchapplication.R;

public class SNSUtil {
    public static final int IDMB = 0;
    public static final int FACEBOOK = 1;
    public static final int INSTARGRAM = 2;
    public static final int TWITTER = 3;

    private static String instargramURL = "https://www.instagram.com/";
    private static String facebookURL = "https://ko-kr.facebook.com/";
    private static String twitterURL = "https://twitter.com/";
    private static String imdbURL = "https://www.imdb.com/name/";


    public static String getHomeUrl(int sns){
        switch (sns){
            case SNSUtil.IDMB: return imdbURL;
            case SNSUtil.FACEBOOK: return facebookURL;
            case SNSUtil.INSTARGRAM: return instargramURL;
            case SNSUtil.TWITTER: return twitterURL;
            default: return null;
        }
    }

    public static void setSNSIconInImageView(ImageView imageView, int sns){
        switch (sns){
            case SNSUtil.IDMB: imageView.setImageResource(R.drawable.ic_imdb);break;
            case SNSUtil.FACEBOOK: imageView.setImageResource(R.drawable.ic_facebook);break;
            case SNSUtil.INSTARGRAM: imageView.setImageResource(R.drawable.ic_instagram);break;
            case SNSUtil.TWITTER: imageView.setImageResource(R.drawable.ic_twitter); break;
        }
    }
}
