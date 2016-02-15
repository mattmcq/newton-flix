package com.mattmcq;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

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

    public static Builder getBuilder(String title) {
        return new Builder(title);
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


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public static class Builder {

        private Movie built;

        public Builder(String theTitle) {
            built = new Movie();
            built.title = theTitle;
        }

        public Movie build() {
            return built;
        }

        public Builder year(String theYear) {
            built.year = theYear;
            return this;
        }
    }
}

