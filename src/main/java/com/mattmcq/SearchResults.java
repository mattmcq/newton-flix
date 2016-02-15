package com.mattmcq;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

/**
 * Created by mjmc on 2/14/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResults {

    @JsonProperty("Search")
    private List<Object> moviesList;
    @JsonProperty("totalResults")
    private int totalResults;


    @JsonDeserialize(contentAs = Movie.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public void setMovieList(final List<Movie> movieResults) {
        this.moviesList = moviesList;
    }

    @JsonDeserialize(contentAs = Movie.class)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public List<Object> getMoviesList() {
        return moviesList;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public String toString() {
        return "SearchResults [MovieList=" + moviesList + "]";
    }
}
