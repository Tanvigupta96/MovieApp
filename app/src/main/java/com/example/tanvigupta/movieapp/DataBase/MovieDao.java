package com.example.tanvigupta.movieapp.DataBase;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert
    public void addMovie(MovieTable movieTable);

    @Query("SELECT checked from MovieTable where id=:id")
    public boolean getChecked(long id);


    @Query("select * from MovieTable")
    public List<MovieTable> getMovies();


    @Insert
    public void addTv(TvTable tvTable);


    @Query("select checked from TvTable where id=:id")
    public boolean getTvChecked(long id);

    @Query("Select * from TvTable")
    List<TvTable> getTv();

    @Query("Select id from MovieTable")
    public long getMovieId();

    @Query("Select id from TvTable")
    public long getTvId();


    @Query("Select TableId from MovieTable where id=:id")
    public int getMovieTableId(long id);

    @Delete
     void deleteMovie(MovieTable movieTable);


    @Query("Select TableId from TvTable where id=:id")
    public int getTvTableId(long id);

    @Delete
    void deleteTv(TvTable tvTable);


}
