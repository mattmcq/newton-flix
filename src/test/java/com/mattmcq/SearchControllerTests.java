package com.mattmcq;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


/**
 * Created by mjmc on 2/10/16.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SearchControllerTests {


    private static final String MOCK_URL = "baseUrl";
    private static final String RESULTS1 = "<div class=\"container\">\n" +
            "    <h1>Newton Flix</h1>\n" +
            "    <p><a class=\"btn btn-primary btn-lg\" href=\"/newtonflix/search/\" role=\"button\">Search for Newton</a></p>\n" +
            "    <div>\n" +
            "        <h2>Movie's with 'Newton' in the title:</h2>\n" +
            "        <table class=\"table table-bordered table-hover\">\n" +
            "            <tr>\n" +
            "                <th>Title</th>\n" +
            "                <th>Year</th>\n" +
            "                <th>Imdb Link</th>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>The Newton Boys</td>\n" +
            "                <td>1998</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0120769\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>A Huey P. Newton Story</td>\n" +
            "                <td>2001</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0278490\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "        </table>\n" +
            "    </div>\n" +
            "</div>";
    private static String RESULTS2 = "<div class=\"container\">\n" +
            "    <h1>Newton Flix</h1>\n" +
            "    <p><a class=\"btn btn-primary btn-lg\" href=\"/newtonflix/search/\" role=\"button\">Search for Newton</a></p>\n" +
            "    <div>\n" +
            "        <h2>Movie's with 'Newton' in the title:</h2>\n" +
            "        <table class=\"table table-bordered table-hover\">\n" +
            "            <tr>\n" +
            "                <th>Title</th>\n" +
            "                <th>Year</th>\n" +
            "                <th>Imdb Link</th>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>The Newton Boys</td>\n" +
            "                <td>1998</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0120769\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>A Huey P. Newton Story</td>\n" +
            "                <td>2001</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0278490\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Me &amp; Isaac Newton</td>\n" +
            "                <td>1999</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0218433\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Helmut Newton: Frames from the Edge</td>\n" +
            "                <td>1989</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0097382\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Olivia Newton-John: Let&#39;s Get Physical</td>\n" +
            "                <td>1982</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0283520\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Isaac Newton: The Last Magician</td>\n" +
            "                <td>2013</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt2878302\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>A Special Olivia Newton-John</td>\n" +
            "                <td>1976</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0262014\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Olivia Newton-John: Twist of Fate</td>\n" +
            "                <td>1984</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0283517\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Olivia Newton-John: Hollywood Nights</td>\n" +
            "                <td>1980</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0330718\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Baby Einstein: Baby Newton Discovering Shapes</td>\n" +
            "                <td>2002</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0488489\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Olivia Newton-John: Video Gold II</td>\n" +
            "                <td>2005</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0478713\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>The Best of Bert Newton</td>\n" +
            "                <td>2002</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0337866\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Newton</td>\n" +
            "                <td>1996–</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0231037\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>La pomme de Newton</td>\n" +
            "                <td>2005</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0442417\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Newton: A Tale of Two Isaacs</td>\n" +
            "                <td>1997</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0217687\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Olivia Newton-John: Video Gold I</td>\n" +
            "                <td>2005</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0478712\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Olivia Newton-John and the Sydney Symphony Orchestra: Live at the Sydney Opera House</td>\n" +
            "                <td>2007</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt1183689\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Newton&#39;s 3rd Law</td>\n" +
            "                <td>2011</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt1823172\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>20th Century Masters: The Best of Olivia Newton-John - The DVD Collection</td>\n" +
            "                <td>2004</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0409735\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Newton: The Dark Heretic</td>\n" +
            "                <td>2003</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt1176444\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Isaac Newton Son of Philipose</td>\n" +
            "                <td>2013</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt2622698\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Olivia Newton-John: Warm and Tender</td>\n" +
            "                <td>1989</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0283518\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Newton</td>\n" +
            "                <td>2003</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0366822\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Newton - Neues aus der Welt der Wissenschaft</td>\n" +
            "                <td>2006–</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt1173322\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>La prima legge di Newton</td>\n" +
            "                <td>2012</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt2536574\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>The Wayne Newton Special</td>\n" +
            "                <td>1982</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0356190\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Tonight with Bert Newton</td>\n" +
            "                <td>1984</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0401989\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Newton&#39;s Law</td>\n" +
            "                <td>2003</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0340868\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Newton Dee: Portrait of a Community</td>\n" +
            "                <td>2010</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt2630532\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Newton&#39;s Grace</td>\n" +
            "                <td>2015</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt2791334\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Disks of Newton</td>\n" +
            "                <td>2010</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt2150217\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Pig Newton</td>\n" +
            "                <td>1983</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt2241425\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Groovemonster Tony Newton</td>\n" +
            "                <td>2012</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt2255821\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Kent Newton and the Surrogate Rebuttal</td>\n" +
            "                <td>2011</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt2361301\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Three Boys from Pasadena: A Tribute to Helmut Newton</td>\n" +
            "                <td>2012</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt2428854\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Take It Away: Adam Newton</td>\n" +
            "                <td>2008</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt1399318\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Dr. Newton: Professional Psychiatrist</td>\n" +
            "                <td>2005</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt1428023\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Newton</td>\n" +
            "                <td>2001</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt0810430\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Newton&#39;s Law</td>\n" +
            "                <td>2005</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt1028582\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>The Truth About Billy Newton</td>\n" +
            "                <td>1961</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt2926964\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>The Bert Newton Show</td>\n" +
            "                <td>1959–1960</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt3041592\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Newton Garth</td>\n" +
            "                <td>2013</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt3605314\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Newton</td>\n" +
            "                <td>2015</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt3891986\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>Untitled Huey P. Newton Story</td>\n" +
            "                <td>2011</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt4467794\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>The Great Foods of Time: Isaac Newton and the Dirty Apple Bunch</td>\n" +
            "                <td>2015</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt4770422\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>No Sunday West of Newton</td>\n" +
            "                <td>2016</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt5076990\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>J. Newton</td>\n" +
            "                <td>2016</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt5167354\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "                <td>La tercera ley de Newton</td>\n" +
            "                <td>2016</td>\n" +
            "                <td><a href=\"http://www.imdb.com/title/tt5175426\">View IMDB</a></td>\n" +
            "\n" +
            "\n" +
            "            </tr>\n" +
            "        </table>\n" +
            "    </div>\n" +
            "</div>";
    MockRestServiceServer mockServer;
    @Mock
    RestTemplate mockRestTemplate;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Bean
    public MovieService getMovieService() {
        return Mockito.mock(MovieService.class);
    }

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(new SearchController()).build();
        Mockito.reset(getMovieService());

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockRestTemplate = new RestTemplate();

        mockServer = MockRestServiceServer.createServer(mockRestTemplate);

        ReflectionTestUtils.setField(MovieServiceImpl.class, "restTemplate",
                mockRestTemplate);
        ReflectionTestUtils.setField(MovieServiceImpl.class, "urlForQuery",
                MOCK_URL);
    }

    @Test
    public void testIndex() throws Exception {
        this.mockMvc.perform(get("/newtonflix/").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk());
    }


//    @Test
//    public void testSearch_MoviesFound_ShouldReturnFoundMovieEntries() throws Exception {
//        MvcResult mvcResult = this.mockMvc.perform(get("/newtonflix/search/").accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("text/html;charset=UTF-8"))
//                .andDo(print())
//                .andReturn();
//        assertThat(mvcResult.getResponse().getContentAsString(), containsString("Groovemonster"));
//    }

    @Test
    public void testSearch_MoviesFound_ShouldReturnFound2MovieEntries() throws Exception {
        mockServer.expect(requestTo(MOCK_URL + "&page=1")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                        "{\"Search\":[{\"Title\":\"The Newton Boys\",\"Year\":\"1998\",\"imdbID\":\"tt0120769\",\"Type\":\"movie\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTcwNTg1MjgzMl5BMl5BanBnXkFtZTcwNDMzMzYyMQ@@._V1_SX300.jpg\"},{\"Title\":\"A Huey P. Newton Story\",\"Year\":\"2001\",\"imdbID\":\"tt0278490\",\"Type\":\"movie\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTg3OTI1MTI2N15BMl5BanBnXkFtZTcwOTIzNDQyMQ@@._V1_SX300.jpg\"}],\"totalResults\":\"2\",\"Response\":\"True\"}"
                        , MediaType.APPLICATION_JSON));

        MvcResult mvcResult = this.mockMvc.perform(get("/newtonflix/search/").accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andDo(print())
                .andReturn();
        System.out.println("mvcResult.getResponse().getContentAsString() = " + mvcResult.getResponse().getContentAsString());
        assertThat(mvcResult.getResponse().getContentAsString(), containsString(RESULTS1));

    }

    @Test
    public void testSearch_MoviesFound_ShouldReturnFound48MovieEntries() throws Exception {
        mockServer.expect(requestTo(MOCK_URL + "&page=1")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                        "{\"Search\":[{\"Title\":\"The Newton Boys\",\"Year\":\"1998\",\"imdbID\":\"tt0120769\",\"Type\":\"movie\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTcwNTg1MjgzMl5BMl5BanBnXkFtZTcwNDMzMzYyMQ@@._V1_SX300.jpg\"},{\"Title\":\"A Huey P. Newton Story\",\"Year\":\"2001\",\"imdbID\":\"tt0278490\",\"Type\":\"movie\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTg3OTI1MTI2N15BMl5BanBnXkFtZTcwOTIzNDQyMQ@@._V1_SX300.jpg\"},{\"Title\":\"Me & Isaac Newton\",\"Year\":\"1999\",\"imdbID\":\"tt0218433\",\"Type\":\"movie\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTc4NDU0MjcxMl5BMl5BanBnXkFtZTcwMzczNzAzMQ@@._V1_SX300.jpg\"},{\"Title\":\"Helmut Newton: Frames from the Edge\",\"Year\":\"1989\",\"imdbID\":\"tt0097382\",\"Type\":\"movie\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTI4MjgwMjk3OV5BMl5BanBnXkFtZTcwMjA0NzUxMQ@@._V1_SX300.jpg\"},{\"Title\":\"Olivia Newton-John: Let's Get Physical\",\"Year\":\"1982\",\"imdbID\":\"tt0283520\",\"Type\":\"movie\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTgwODQxMDYxMF5BMl5BanBnXkFtZTcwMjA1NTYyMQ@@._V1_SX300.jpg\"},{\"Title\":\"Isaac Newton: The Last Magician\",\"Year\":\"2013\",\"imdbID\":\"tt2878302\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"A Special Olivia Newton-John\",\"Year\":\"1976\",\"imdbID\":\"tt0262014\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Olivia Newton-John: Twist of Fate\",\"Year\":\"1984\",\"imdbID\":\"tt0283517\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Olivia Newton-John: Hollywood Nights\",\"Year\":\"1980\",\"imdbID\":\"tt0330718\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Baby Einstein: Baby Newton Discovering Shapes\",\"Year\":\"2002\",\"imdbID\":\"tt0488489\",\"Type\":\"movie\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTc3OTE0MDgxMV5BMl5BanBnXkFtZTcwMjI2Njc0MQ@@._V1_SX300.jpg\"}],\"totalResults\":\"48\",\"Response\":\"True\"}"
                        , MediaType.APPLICATION_JSON));
        mockServer.expect(requestTo(MOCK_URL + "&page=2")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                        "{\"Search\":[{\"Title\":\"Olivia Newton-John: Video Gold II\",\"Year\":\"2005\",\"imdbID\":\"tt0478713\",\"Type\":\"movie\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTk0NDcxMjc1NF5BMl5BanBnXkFtZTcwNzU4OTAzMQ@@._V1_SX300.jpg\"},{\"Title\":\"The Best of Bert Newton\",\"Year\":\"2002\",\"imdbID\":\"tt0337866\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Newton\",\"Year\":\"1996–\",\"imdbID\":\"tt0231037\",\"Type\":\"series\",\"Poster\":\"N/A\"},{\"Title\":\"La pomme de Newton\",\"Year\":\"2005\",\"imdbID\":\"tt0442417\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Newton: A Tale of Two Isaacs\",\"Year\":\"1997\",\"imdbID\":\"tt0217687\",\"Type\":\"movie\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTM3NjI3NTkwOV5BMl5BanBnXkFtZTcwNDE3MzcxMQ@@._V1_SX300.jpg\"},{\"Title\":\"Olivia Newton-John: Video Gold I\",\"Year\":\"2005\",\"imdbID\":\"tt0478712\",\"Type\":\"movie\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMjE0NDQyMDIxOF5BMl5BanBnXkFtZTcwNzYzODAzMQ@@._V1_SX300.jpg\"},{\"Title\":\"Olivia Newton-John and the Sydney Symphony Orchestra: Live at the Sydney Opera House\",\"Year\":\"2007\",\"imdbID\":\"tt1183689\",\"Type\":\"movie\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTYwOTk5MTI2NV5BMl5BanBnXkFtZTcwMTgyMjE2MQ@@._V1_SX300.jpg\"},{\"Title\":\"Newton's 3rd Law\",\"Year\":\"2011\",\"imdbID\":\"tt1823172\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"20th Century Masters: The Best of Olivia Newton-John - The DVD Collection\",\"Year\":\"2004\",\"imdbID\":\"tt0409735\",\"Type\":\"movie\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTQ0OTU5NjMwN15BMl5BanBnXkFtZTcwOTQxNjM0Mg@@._V1_SX300.jpg\"},{\"Title\":\"Newton: The Dark Heretic\",\"Year\":\"2003\",\"imdbID\":\"tt1176444\",\"Type\":\"movie\",\"Poster\":\"N/A\"}],\"totalResults\":\"48\",\"Response\":\"True\"}"
                        , MediaType.APPLICATION_JSON));
        mockServer.expect(requestTo(MOCK_URL + "&page=3")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                        "{\"Search\":[{\"Title\":\"Isaac Newton Son of Philipose\",\"Year\":\"2013\",\"imdbID\":\"tt2622698\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Olivia Newton-John: Warm and Tender\",\"Year\":\"1989\",\"imdbID\":\"tt0283518\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Newton\",\"Year\":\"2003\",\"imdbID\":\"tt0366822\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Newton - Neues aus der Welt der Wissenschaft\",\"Year\":\"2006–\",\"imdbID\":\"tt1173322\",\"Type\":\"series\",\"Poster\":\"N/A\"},{\"Title\":\"La prima legge di Newton\",\"Year\":\"2012\",\"imdbID\":\"tt2536574\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"The Wayne Newton Special\",\"Year\":\"1982\",\"imdbID\":\"tt0356190\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Tonight with Bert Newton\",\"Year\":\"1984\",\"imdbID\":\"tt0401989\",\"Type\":\"series\",\"Poster\":\"N/A\"},{\"Title\":\"Newton's Law\",\"Year\":\"2003\",\"imdbID\":\"tt0340868\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Newton Dee: Portrait of a Community\",\"Year\":\"2010\",\"imdbID\":\"tt2630532\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Newton's Grace\",\"Year\":\"2015\",\"imdbID\":\"tt2791334\",\"Type\":\"movie\",\"Poster\":\"N/A\"}],\"totalResults\":\"48\",\"Response\":\"True\"}"
                        , MediaType.APPLICATION_JSON));
        mockServer.expect(requestTo(MOCK_URL + "&page=4")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                        "{\"Search\":[{\"Title\":\"Disks of Newton\",\"Year\":\"2010\",\"imdbID\":\"tt2150217\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Pig Newton\",\"Year\":\"1983\",\"imdbID\":\"tt2241425\",\"Type\":\"game\",\"Poster\":\"N/A\"},{\"Title\":\"Groovemonster Tony Newton\",\"Year\":\"2012\",\"imdbID\":\"tt2255821\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Kent Newton and the Surrogate Rebuttal\",\"Year\":\"2011\",\"imdbID\":\"tt2361301\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Three Boys from Pasadena: A Tribute to Helmut Newton\",\"Year\":\"2012\",\"imdbID\":\"tt2428854\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Take It Away: Adam Newton\",\"Year\":\"2008\",\"imdbID\":\"tt1399318\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Dr. Newton: Professional Psychiatrist\",\"Year\":\"2005\",\"imdbID\":\"tt1428023\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Newton\",\"Year\":\"2001\",\"imdbID\":\"tt0810430\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Newton's Law\",\"Year\":\"2005\",\"imdbID\":\"tt1028582\",\"Type\":\"N/A\",\"Poster\":\"N/A\"},{\"Title\":\"The Truth About Billy Newton\",\"Year\":\"1961\",\"imdbID\":\"tt2926964\",\"Type\":\"movie\",\"Poster\":\"N/A\"}],\"totalResults\":\"48\",\"Response\":\"True\"}"
                        , MediaType.APPLICATION_JSON));
        mockServer.expect(requestTo(MOCK_URL + "&page=5")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                        "{\"Search\":[{\"Title\":\"The Bert Newton Show\",\"Year\":\"1959–1960\",\"imdbID\":\"tt3041592\",\"Type\":\"series\",\"Poster\":\"N/A\"},{\"Title\":\"Newton Garth\",\"Year\":\"2013\",\"imdbID\":\"tt3605314\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Newton\",\"Year\":\"2015\",\"imdbID\":\"tt3891986\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"Untitled Huey P. Newton Story\",\"Year\":\"2011\",\"imdbID\":\"tt4467794\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"The Great Foods of Time: Isaac Newton and the Dirty Apple Bunch\",\"Year\":\"2015\",\"imdbID\":\"tt4770422\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"No Sunday West of Newton\",\"Year\":\"2016\",\"imdbID\":\"tt5076990\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"J. Newton\",\"Year\":\"2016\",\"imdbID\":\"tt5167354\",\"Type\":\"movie\",\"Poster\":\"N/A\"},{\"Title\":\"La tercera ley de Newton\",\"Year\":\"2016\",\"imdbID\":\"tt5175426\",\"Type\":\"movie\",\"Poster\":\"N/A\"}],\"totalResults\":\"48\",\"Response\":\"True\"}"
                        , MediaType.APPLICATION_JSON));


        MvcResult mvcResult = this.mockMvc.perform(get("/newtonflix/search/").accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andDo(print())
                .andReturn();
        System.out.println("mvcResult.getResponse().getContentAsString() = " + mvcResult.getResponse().getContentAsString());
        assertThat(mvcResult.getResponse().getContentAsString(), containsString(RESULTS2));

    }
}