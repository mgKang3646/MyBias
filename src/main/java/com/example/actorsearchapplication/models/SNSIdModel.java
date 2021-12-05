package com.example.actorsearchapplication.models;

import com.example.actorsearchapplication.adapters.SNSRecyclerViewAdapter;
import com.example.actorsearchapplication.utils.SNSUtil;

import java.util.ArrayList;
import java.util.List;

public class SNSIdModel {

    private int id;
    private String imdb_id;
    private String facebook_id;
    private String instagram_id;
    private String twitter_id;

    public SNSIdModel(int id, String imdb_id, String facebook_id, String instagram_id, String twitter_id) {
        this.id = id;
        this.imdb_id = imdb_id;
        this.facebook_id = facebook_id;
        this.instagram_id = instagram_id;
        this.twitter_id = twitter_id;
    }

    public int getId() {
        return id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public String getInstagram_id() {
        return instagram_id;
    }

    public String getTwitter_id() {
        return twitter_id;
    }

    public List<SNSModel> getSnsModel(){
        List<SNSModel> snsModels = new ArrayList<>();
        if(imdb_id != null) snsModels.add(new SNSModel(SNSUtil.IDMB,imdb_id));
        if(facebook_id != null) snsModels.add(new SNSModel(SNSUtil.FACEBOOK,facebook_id));
        if(instagram_id != null) snsModels.add(new SNSModel(SNSUtil.INSTARGRAM,instagram_id));
        if(twitter_id != null) snsModels.add(new SNSModel(SNSUtil.TWITTER,twitter_id));
        return snsModels;
    }
}
