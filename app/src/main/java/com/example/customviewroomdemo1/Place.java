package com.example.customviewroomdemo1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Place {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public Place(String name) {
        this.name = name;
    }

    public Place() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
