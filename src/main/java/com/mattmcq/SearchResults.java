package com.mattmcq;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

/**
 * Created by mjmc on 2/12/16.
 */
public class SearchResults {

    @JsonProperty("Search")
    @JsonDeserialize(contentAs = Movie.class)
    private List<Movie> moviesList;

    @JsonProperty("totalResults")
    private int totalResults;


    public List<Movie> getMoviesList() {
        return moviesList;
    }

    public int getTotalResults() {
        return totalResults;
    }


}
