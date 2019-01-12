
package com.example.tanvigupta.movieapp.model.CastPhotos;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class CastPhotosResponse {

    @Expose
    private Long id;
    @Expose
    private List<Profile> profiles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

}
