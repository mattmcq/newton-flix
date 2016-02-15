package com.mattmcq;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mjmc on 2/9/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String year;

    private String imdbID;


    public Movie(String title, String year, String imdbID) {
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String Year) {
        this.year = Year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }



}

