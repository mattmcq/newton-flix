package com.mattmcq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.mattmcq"})
@PropertySource({"classpath:application.properties" })
public class NewtonflixApplication implements CommandLineRunner {


	private static final Logger log = LoggerFactory.getLogger(NewtonflixApplication.class);
//	private static final String urlForQuery = "http://www.omdbapi.com/?s=newton";

	@Autowired
	private Environment env;

	@Bean(name = "omdbApiUrl")
	public String omdbApiUrl() {
		return env.getProperty("api.omdbapi.url");
	}


	private Movie movie;

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

//rt.notify();
//        List<Movie> movies = restTemplate.getForObject(urlForQuery,MovieListInfo.class).getMovies();
		List<Object> resultsList = restTemplate.getForObject(urlForQuery,MovieListInfo.class).getMovies();

		List<Movie> fullList = new ArrayList<Movie>();


		for (int i = 0; i < resultsList.size(); i++) {

//			Movie movie = (Movie) movies.get(i);
			System.out.println("movie.getTitle() = " + ((java.util.LinkedHashMap)resultsList.get(i)).get("Title"));
			movie = new Movie();
			movie.setTitle(((java.util.LinkedHashMap)resultsList.get(i)).get("Title").toString());
			movie.setYear(((java.util.LinkedHashMap)resultsList.get(i)).get("Year").toString());
			movie.setImdbID(((java.util.LinkedHashMap)resultsList.get(i)).get("imdbID").toString());
			fullList.add(movie);
		}


		log.info(fullList.toString());

	}

	private List<Object> getMoviesFromSpecificPage(int pageNum){
		List<Object> resultsList = restTemplate.getForObject(urlForQuery.concat("&page=").concat(String.valueOf(pageNum)),MovieListInfo.class).getMovies();
		return resultsList;
	}

	private int getNumberOfPages(){

	}


}
