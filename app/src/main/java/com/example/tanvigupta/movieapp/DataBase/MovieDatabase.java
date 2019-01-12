package com.example.tanvigupta.movieapp.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {MovieTable.class,TvTable.class}, version = 1)


public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao getDao();


}
