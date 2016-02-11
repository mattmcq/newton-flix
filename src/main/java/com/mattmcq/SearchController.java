package com.mattmcq;

/**
 * Created by mjmc on 2/10/16.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {

    @Autowired
    private MovieService movieService;

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }


    @RequestMapping("/app/")
    String index() {
        return "search";
    }

    @RequestMapping("/app/search")
    public String search(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "search";
    }

}



