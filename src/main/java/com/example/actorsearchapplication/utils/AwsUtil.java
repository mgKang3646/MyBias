package com.example.actorsearchapplication.utils;

import android.util.Log;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.model.Celebrity;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.RecognizeCelebritiesRequest;
import com.amazonaws.services.rekognition.model.RecognizeCelebritiesResult;
import com.example.actorsearchapplication.models.ActorModel;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class AwsUtil {

    private AmazonRekognition amazonRekognitionClient;
    private RecognizeCelebritiesResult result;


    public List<ActorModel> getSearchedActors(ByteBuffer imageBuffer) {
        setAWSClient();
        requestAWSResult(imageBuffer);
        return getListSearchedActors();
    }

    private void setAWSClient(){
        AWSCredentials credentials = new BasicAWSCredentials("AKIAVU53PT5HRYNVS2FX", "pSWWg00rsWFKXIFJgsOmNp2TwFoWvjCFNrm31Pg0");
        amazonRekognitionClient = new AmazonRekognitionClient(credentials);
        amazonRekognitionClient.setRegion(Region.getRegion(Regions.AP_NORTHEAST_2));
    }

    private void requestAWSResult(ByteBuffer imageBuffer){
        result = amazonRekognitionClient.recognizeCelebrities(new RecognizeCelebritiesRequest().withImage(new Image().withBytes(imageBuffer)));
    }

    private List<ActorModel> getListSearchedActors(){
        List<Celebrity> celebs = result.getCelebrityFaces();
        List<ActorModel> awsSearchedActors = null;
        if (celebs.size() != 0) {
            awsSearchedActors = new ArrayList<ActorModel>();
            for (Celebrity celebrity : celebs) {
                ActorModel searchedActor = new ActorModel();
                searchedActor.setName(celebrity.getName());
                searchedActor.setMatchConfidence(celebrity.getMatchConfidence());
                awsSearchedActors.add(searchedActor);
            }
        }
        return awsSearchedActors;
    }
}
