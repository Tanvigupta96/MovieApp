
package com.example.tanvigupta.movieapp.model.Tvdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class LastEpisodeToAir {

    @SerializedName("air_date")
    private String airDate;
    @SerializedName("episode_number")
    private Long episodeNumber;
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private String overview;
    @SerializedName("production_code")
    private Object productionCode;
    @SerializedName("season_number")
    private Long seasonNumber;
    @SerializedName("show_id")
    private Long showId;
    @SerializedName("still_path")
    private String stillPath;
    @SerializedName("vote_average")
    private Long voteAverage;
    @SerializedName("vote_count")
    private Long voteCount;

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public Long getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Long episodeNumber) {
        this.episodeNumber = episodeNumber;
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

    public Object getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(Object productionCode) {
        this.productionCode = productionCode;
    }

    public Long getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Long seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public String getStillPath() {
        return stillPath;
    }

    public void setStillPath(String stillPath) {
        this.stillPath = stillPath;
    }

    public Long getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Long voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

}
