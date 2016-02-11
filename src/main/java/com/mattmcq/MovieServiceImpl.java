package com.mattmcq;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by mjmc on 2/9/16.
 */
@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);

    private static final String urlForQuery = "http://www.omdbapi.com/?s=newton";
    private static RestTemplate restTemplate;

    static {
        restTemplate = new RestTemplate();
    }

    @JsonProperty("Search")
    private List<Object> moviesList;
    @JsonProperty("totalResults")
    private int totalResults;

    public List<Movie> getAllMovies() {

        Movie movie;
        List<Movie> fullList = new ArrayList<>();
        List<Object> resultsList = new ArrayList<>();

        for (int p = 1; p <= getNumberOfPages(); p++) {

            resultsList = getMoviesFromSpecificPage(p);

            for (int i = 0; i < resultsList.size(); i++) {

                System.out.println("movie.getTitle() = " + ((java.util.LinkedHashMap) resultsList.get(i)).get("Title"));
                movie = new Movie();
                movie.setTitle(((LinkedHashMap) resultsList.get(i)).get("Title").toString());
                movie.setYear(((LinkedHashMap) resultsList.get(i)).get("Year").toString());
                movie.setImdbID(((LinkedHashMap) resultsList.get(i)).get("imdbID").toString());
                fullList.add(movie);
            }
        }
        return fullList;
    }

    public List<Object> getMoviesFromSpecificPage(int pageNum) {
        List<Object> resultsList = restTemplate.getForObject(urlForQuery.concat("&page=").concat(String.valueOf(pageNum)), MovieServiceImpl.class).getMoviesList();
        return resultsList;
    }

    public int getNumberOfPages() {
        return 1 + (restTemplate.getForObject(urlForQuery, MovieServiceImpl.class).getTotalResults() / 10);
    }

    @Override
    public List<Object> getMoviesList() {
        return moviesList;
    }

    @Override
    public void setMoviesList(List<Object> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public int getTotalResults() {
        return totalResults;
    }

    @Override
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
