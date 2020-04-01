package com.example.trioplanner.roomOps;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TripDAO {
    @Query("SELECT * FROM trips")
    public List<TripRoom> loadAllTrips();
    @Query("SELECT * FROM trips where fromOnline = 'f'")
    public List<TripRoom> loadAllTripsOffline();
    @Query("DELETE FROM trips")
    public void deleteAllTrips();
    @Update
    public void updateTrip(TripRoom trip);
    @Insert
    public void insertTrip(TripRoom trip);
}
