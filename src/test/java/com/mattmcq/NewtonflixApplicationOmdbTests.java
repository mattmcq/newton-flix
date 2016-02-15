package com.mattmcq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class NewtonflixApplicationOmdbTests {

    RestTemplate template = new TestRestTemplate();

    @Test
    public void contextLoads() {
    }

    @Test
    public void testOmdbAvailable() throws Exception {
        HttpStatus httpStatus = template.getForEntity("http://www.omdbapi.com/?s=gleaming the cube", String.class).getStatusCode();
        assertThat(httpStatus.toString(), containsString("200"));
    }

    @Test
    public void testOmdbWorking() throws Exception {
        String body = template.getForEntity("http://www.omdbapi.com/?s=point break", String.class).getBody();
        assertThat(body, containsString("tt0102685"));
    }


}
