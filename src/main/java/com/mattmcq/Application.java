package com.mattmcq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
//public class Application extends SpringBootServletInitializer {


    private static final Logger log = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        log.info("starting app ...");
        SpringApplication.run(Application.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Application.class);
//    }
}
