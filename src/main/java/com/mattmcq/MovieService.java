package com.mattmcq;

import java.util.List;

/**
 * Created by mjmc on 2/10/16.
 */
public interface MovieService {
    List<Object> getMoviesList();

    List<Movie> getAllMovies();

    void setMoviesList(List<Object> movies);

    int getTotalResults();

    void setTotalResults(int totalResults);
}
