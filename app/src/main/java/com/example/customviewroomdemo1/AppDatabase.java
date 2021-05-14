package com.example.customviewroomdemo1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Place.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
        public abstract PlaceDao placeDao();
}
