package com.example.trioplanner.roomOps;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TripRoom.class}, version = 1)
public abstract class TripDB extends RoomDatabase {
    public abstract TripDAO tripDAO();

}
