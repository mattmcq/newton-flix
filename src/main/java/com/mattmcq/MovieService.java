package com.mattmcq;

import java.util.List;

/**
 * Created by mjmc on 2/10/16.
 */
public interface MovieService {


    List<Movie> getAllMovies();

    List<Movie> getMoviesFromPage(int pageNum);

    int getNumberOfPages();
}
