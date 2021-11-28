package com.example.actorsearchapplication.viewutil;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.R;

public class GlideUtil {
    public static void loadPosterImage(Context context, String imageURL, ImageView imageView){
        if(imageURL != null){
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w300/"+imageURL).into(imageView);
        }else{
            imageView.setImageResource(R.drawable.default_image);
        }
    }

    public static void loadProfileImage(Context context, String imageURL, ImageView imageView){
        if(imageURL != null){
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w300/"+imageURL).into(imageView);
        }else{
            imageView.setImageResource(R.drawable.default_image);
        }
    }

    public static void loadProductionImage(Context context, String imageURL, ImageView imageView){
        Glide.with(context).load("https://image.tmdb.org/t/p/w300/"+imageURL).into(imageView);
    }

    public static void loadSeasonImage(Context context, String imageURL, String tmpImageUrl, ImageView imageView){
        if(imageURL != null){
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+imageURL).into(imageView);
        }else if(tmpImageUrl != null){
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+tmpImageUrl).into(imageView);
        }else{
            imageView.setImageResource(R.drawable.default_image);
        }
    }
}
