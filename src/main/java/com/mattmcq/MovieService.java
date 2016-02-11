package com.mattmcq;

import java.util.List;

/**
 * Created by mjmc on 2/10/16.
 */
public interface MovieService {
    List<Object> getMoviesList();

    void setMoviesList(List<Object> movies);

    List<Movie> getAllMovies();

    int getTotalResults();

    void setTotalResults(int totalResults);
}
