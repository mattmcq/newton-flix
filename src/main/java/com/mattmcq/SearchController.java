package com.mattmcq;

/**
 * Created by mjmc on 2/10/16.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
public class SearchController {

    @Autowired
    private MovieService movieService;

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }


    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/newtonflix/")
    String index() {
        return "search";
    }

    @RequestMapping("/newtonflix/search")
    public String search(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "search";
    }

}



