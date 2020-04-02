package com.example.trioplanner.data;

import androidx.annotation.NonNull;

public class Trip {

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


    public Trip() {
    }

    public Trip(String name, String startLoc, String endLoc, String date,
                String time, String type, String notes, String status) {
        this.name = name;
        this.startLoc = startLoc;
        this.endLoc = endLoc;
        this.date = date;
        this.time = time;
        this.type = type;
        this.notes = notes;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(String startLoc) {
        this.startLoc = startLoc;
    }

    public String getEndLoc() {
        return endLoc;
    }

    public void setEndLoc(String endLoc) {
        this.endLoc = endLoc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @NonNull
    @Override
    public String toString() {
        /*private String name;
    private String startLoc;
    private String endLoc;
    private String date;
    private String time;
    private String type;
    private String notes;
    private String status;
    */
        return ("\nname >>   " + name + "\n" +
                "startLoc >>  " + startLoc + "\n" +
                "endLoc >> " + endLoc + "\n" +
                "data >> " + date + "\n" +
                "type >> " + type + "\n" +
                "time >> " + time + "\n" +
                "notes >> " + notes + "\n" +
                "status >> " + status + "\n");

    }
}
