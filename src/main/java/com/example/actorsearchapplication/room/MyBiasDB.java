package com.example.actorsearchapplication.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {DibsDto.class}, version = 1, exportSchema = false)
public abstract class MyBiasDB extends RoomDatabase {
    public abstract DibsDao dibsDao();
}
