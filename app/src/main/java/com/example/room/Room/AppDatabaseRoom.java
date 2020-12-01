package com.example.room.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Alumn.class}, version = 1)
public abstract class AppDatabaseRoom extends RoomDatabase {
    public abstract AlumnDAO alumnDAO();
}
