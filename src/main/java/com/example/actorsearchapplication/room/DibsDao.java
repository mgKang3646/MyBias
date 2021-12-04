package com.example.actorsearchapplication.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DibsDao {

    @Query("SELECT * FROM dibs")
    List<DibsDto> getDibs();

    @Insert
    void insertActor(DibsDto dibsDto);

    @Delete
    void deleteActor(DibsDto dibsDto);

}
