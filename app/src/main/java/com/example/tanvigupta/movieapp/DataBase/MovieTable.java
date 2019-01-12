package com.example.tanvigupta.movieapp.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class MovieTable {

    @PrimaryKey(autoGenerate = true)
    private int TableId;

  private long id;

  private String PosterPath;

  private String Title;

  private boolean checked;


    public int getTableId() {
        return TableId;
    }

    public long getId() {
        return id;
    }

    public String getPosterPath() {
        return PosterPath;
    }

    public String getTitle() {
        return Title;
    }

    public void setTableId(int tableId) {
        TableId = tableId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {

        return checked;
    }
}
