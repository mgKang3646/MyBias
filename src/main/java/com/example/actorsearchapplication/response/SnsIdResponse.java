package com.example.actorsearchapplication.response;

import com.example.actorsearchapplication.models.SNSIdModel;

public class SnsIdResponse {

    private int id;
    private String imdb_id;
    private String facebook_id;
    private String instagram_id;
    private String twitter_id;

    public SNSIdModel getSNSId(){
        return new SNSIdModel(id, imdb_id, facebook_id, instagram_id, twitter_id);
    }

}
