
package com.example.tanvigupta.movieapp.model.EpisodeStills;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class EpisodeStillsResponse {

    @Expose
    private Long id;
    @Expose
    private List<Still> stills;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Still> getStills() {
        return stills;
    }

    public void setStills(List<Still> stills) {
        this.stills = stills;
    }

}
