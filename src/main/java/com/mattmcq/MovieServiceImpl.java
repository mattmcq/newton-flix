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

    List<Movie> fullList = new ArrayList<>();
    int numberOfPages;
    @JsonProperty("Search")
    private List<Object> moviesList;
    @JsonProperty("totalResults")
    private int totalResults;

    public List<Movie> getAllMovies() {
        // get first page of movies and set numberOfPages
        fullList = getMoviesFromPage(1);

        if (numberOfPages > 1) {
            for (int p = 2; p < 1 + numberOfPages; p++) {
                fullList.addAll(getMoviesFromPage(p));
            }
        }
        return fullList;

    }

    public List<Movie> getMoviesFromPage(int pageNum) {

        Movie movie;
        List<Object> resultsList = new ArrayList<>();
        List<Movie> singlePageOfMovies = new ArrayList<>();


        resultsList = getSinglePageResults(pageNum);

        for (int i = 0; i < resultsList.size(); i++) {

            log.info("movie.getTitle() = " + ((java.util.LinkedHashMap) resultsList.get(i)).get("Title"));
            movie = new Movie();
            movie.setTitle(((LinkedHashMap) resultsList.get(i)).get("Title").toString());
            movie.setYear(((LinkedHashMap) resultsList.get(i)).get("Year").toString());
            movie.setImdbID(((LinkedHashMap) resultsList.get(i)).get("imdbID").toString());
            singlePageOfMovies.add(movie);
        }

        return singlePageOfMovies;
    }

    public List<Object> getSinglePageResults(int pageNum) {
        MovieService req = restTemplate.getForObject(urlForQuery.concat("&page=").concat(String.valueOf(pageNum)), MovieServiceImpl.class);
        if (pageNum == 1)
            setNumberOfPages(1 + (req.getTotalResults() / 10)); // always set the number of pages on first page request
        return req.getMoviesList();
    }


    private void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
        log.info("numberOfPages = " + numberOfPages);
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
