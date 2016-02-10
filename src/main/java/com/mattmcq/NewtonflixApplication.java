package com.mattmcq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class NewtonflixApplication implements CommandLineRunner {


	private static final Logger log = LoggerFactory.getLogger(NewtonflixApplication.class);
	private static final String urlForQuery = "http://www.omdbapi.com/?s=newton";

	public static void main(String[] args) {
		SpringApplication.run(NewtonflixApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
//        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
//        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(urlGETList, Object[].class);

//        ResponseEntity<Object[]> results = restTemplate.getForObject(urlForQuery,Object[].class);
//        ResponseEntity<Object[]> results = restTemplate.getForEntity(urlForQuery, Object[].class);
//        Object[] forNow = restTemplate.getForObject(urlForQuery, Object[].class);
		log.info("mattmmmmmmmm");

//        List<Object> results = Arrays.asList(forNow);
//        ResponseEntity<List<Movie>> queryResults = restTemplate.exchange(urlForQuery, HttpMethod.GET, null, new ParameterizedTypeReference<List<Movie>>() {});
//        ResponseEntity queryResults = restTemplate.getForEntity(urlForQuery, Object[].class);


//        List<Movie> movies = restTemplate.getForObject(urlForQuery,MovieListInfo.class).getMovies();
		List<Object> movies = restTemplate.getForObject(urlForQuery,MovieListInfo.class).getMovies();

		for (int i = 0; i < movies.size(); i++) {

			Movie movie = (Movie) movies.get(i);
			System.out.println("movie.getTitle() = " + movie.getTitle());
		}


		log.info(movies.toString());

	}
}
