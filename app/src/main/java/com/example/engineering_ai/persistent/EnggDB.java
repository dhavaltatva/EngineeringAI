package com.example.engineering_ai.persistent;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class EnggDB extends RoomDatabase {

    public static EnggDB crate(Context context)  {
        return Room.databaseBuilder(context, EnggDB.class, "reddit.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build();
    }

    public abstract EnggDao enggDao();
}
