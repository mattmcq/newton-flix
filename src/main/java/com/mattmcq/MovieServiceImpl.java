package com.mattmcq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    int numberOfPages = 0;


    public List<Movie> getAllMovies() {
        // get first page of movies and set numberOfPages
        fullList = getMoviesFromPage(1);

        // now get movies from pages > 1
        if (numberOfPages > 1) {
            for (int p = 2; p < 1 + numberOfPages; p++) {
                fullList.addAll(getMoviesFromPage(p));
            }
        }
        log.info("fullList = " + fullList);
        return fullList;

    }


    public List<Movie> getMoviesFromPage(int pageNum) {
        SearchResults req = restTemplate.getForObject(urlForQuery.concat("&page=").concat(String.valueOf(pageNum)), SearchResults.class);

        // always set the number of pages on first page request
        if (pageNum == 1) {
            setNumberOfPages(1 + (req.getTotalResults() / 10));
        }
        return req.getMoviesList();
    }


    private void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
        log.info("numberOfPages = " + numberOfPages);
    }

}
