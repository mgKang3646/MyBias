package com.example.actorsearchapplication.utils;

import android.content.Context;

import androidx.room.Room;

import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.room.DibsDto;
import com.example.actorsearchapplication.room.MyBiasDB;

import java.util.ArrayList;
import java.util.List;

public class RoomUtil {

    private MyBiasDB myBiasDB;

    public RoomUtil(Context context){
        myBiasDB = Room.databaseBuilder(context,MyBiasDB.class,"mybias2").allowMainThreadQueries().build();
    }

    public List<ActorModel> getDibs(){
        List<DibsDto> dibsDtoes = myBiasDB.dibsDao().getDibs();
        List<ActorModel> actorModels = new ArrayList<>();

        for(DibsDto dibsDto: dibsDtoes){
                ActorModel actorModel = new ActorModel(dibsDto);
                actorModels.add(actorModel);
        }

        return actorModels;
    }

    public void insertActor(ActorModel actorModel){
        DibsDto dibsDto = new DibsDto();
        dibsDto.changeActorModelToDto(actorModel);
        myBiasDB.dibsDao().insertActor(dibsDto);
    }

    public void deleteActor(ActorModel actorModel){
        DibsDto dibsDto = new DibsDto();
        dibsDto.changeActorModelToDto(actorModel);
        myBiasDB.dibsDao().deleteActor(dibsDto);
    }

    public boolean isDibsActor(int id){
        List<DibsDto> dibsDtoes = myBiasDB.dibsDao().getDibs();
        for(DibsDto dibsDto : dibsDtoes ){
            if( dibsDto.getId() == id ) return true;
        }
        return false;
    }
}
