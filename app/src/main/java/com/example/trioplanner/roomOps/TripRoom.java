package com.example.trioplanner.roomOps;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "trips")
public class TripRoom {
    @PrimaryKey()
    private String id; // will add to trip by firebase {getKey}
    private String name;
    private String startLoc;
    private String endLoc;
    private String date;
    private String time;
    private String type;
    private String notes;
    //  name - startLoc -  endLoc -  date -  time -  type -  notes
    // canceld - finished - or waiting
    private String status;
    private String fromOnline;

    public TripRoom(String id, String name, String startLoc, String endLoc, String date, String time, String type, String notes, String status,String fromOnline) {
        if(id.isEmpty()) {
            this.id = UUID.randomUUID().toString();
        } else{
            this.id = id;
        }
        this.name = name;
        this.startLoc = startLoc;
        this.endLoc = endLoc;
        this.date = date;
        this.time = time;
        this.type = type;
        this.notes = notes;
        this.status = status;
        this.fromOnline = fromOnline;
    }
    @Ignore
    public TripRoom() {
        id = UUID.randomUUID().toString();
        this.name = "";
        this.startLoc = "";
        this.endLoc = "";
        this.date = "";
        this.time = "";
        this.type = "";
        this.notes = "";
        this.status = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartLoc(String startLoc) {
        this.startLoc = startLoc;
    }

    public void setEndLoc(String endLoc) {
        this.endLoc = endLoc;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartLoc() {
        return startLoc;
    }

    public String getEndLoc() {
        return endLoc;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getNotes() {
        return notes;
    }

    public String getStatus() {
        return status;
    }
}
