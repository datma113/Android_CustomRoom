package com.example.customviewroomdemo1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlaceDao {
    @Query("SELECT * FROM Place")
    List<Place> getAllPlace();

    @Insert
    void insertPlace(Place... places);

    @Delete
    void deletePlace(Place place);
    @Query("UPDATE place set name = :name WHERE id = :id")
    void updatePlace(String name,int id);
}
