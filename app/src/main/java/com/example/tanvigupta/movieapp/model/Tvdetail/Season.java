
package com.example.tanvigupta.movieapp.model.Tvdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Season {

    @SerializedName("air_date")
    private Object airDate;
    @SerializedName("episode_count")
    private Long episodeCount;
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private String overview;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("season_number")
    private Long seasonNumber;

    public Object getAirDate() {
        return airDate;
    }

    public void setAirDate(Object airDate) {
        this.airDate = airDate;
    }

    public Long getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(Long episodeCount) {
        this.episodeCount = episodeCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Long getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Long seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    @Override
    public String toString() {
        return name;
    }
}
