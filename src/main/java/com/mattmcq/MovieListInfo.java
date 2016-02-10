package com.mattmcq;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Created by mjmc on 2/9/16.
 */
public class MovieListInfo {


    @JsonProperty("Search")
    private List<Object> movies;

    @JsonProperty("totalResults")
    private int totalResults;


    public List<Object> getMovies() {
        return movies;
    }

    public void setMovies(List<Object> movies) {
        this.movies = movies;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
