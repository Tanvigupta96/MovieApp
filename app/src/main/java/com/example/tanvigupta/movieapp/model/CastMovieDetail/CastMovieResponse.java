
package com.example.tanvigupta.movieapp.model.CastMovieDetail;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class CastMovieResponse {

    @Expose
    private List<Cast> cast;
    @Expose
    private List<Object> crew;
    @Expose
    private Long id;

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Object> getCrew() {
        return crew;
    }

    public void setCrew(List<Object> crew) {
        this.crew = crew;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
