
package com.example.tanvigupta.movieapp.model.EpisodeWiseCast;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class CastResponse {

    @Expose
    private List<Cast> cast;
    @Expose
    private List<Crew> crew;
    @SerializedName("guest_stars")
    private List<GuestStar> guestStars;
    @Expose
    private Long id;

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    public List<GuestStar> getGuestStars() {
        return guestStars;
    }

    public void setGuestStars(List<GuestStar> guestStars) {
        this.guestStars = guestStars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
