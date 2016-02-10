package com.mattmcq;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by mjmc on 2/9/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    private String title;
    private String year;
    private String imdbID;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }
}

