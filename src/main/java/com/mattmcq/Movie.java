package com.mattmcq;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by mjmc on 2/9/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    private String Title;
    private String Year;
    private String imdbID;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }
}

