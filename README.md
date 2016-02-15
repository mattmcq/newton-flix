# newton-flix

## clone the repo; then build with gradle
```gradle
git clone https://github.com/mattmcq/newton-flix ; cd newton-flix
./gradlew clean
./gradlew war
cp build/libs/newtonflix-2.0.0-SNAPSHOT.war /YOUR/TOMCAT/webapps/newtonflix.war
/YOUR/TOMCAT/bin/startup.sh

```

1. Wait for startup to appear finished, then open a browser.
2. Load the base url in a browser:  http://localhost:8080/newtonflix/
3. Click the button to search

#### Notes:
   - I tried building war's with maven but tomcat refused to load thymeleaf templates. I just tried gradle and it seems to have built a more complete war.
   - The omdbapi seems to be hard limited at 10 entries per response. I could not find a way around this. I did correct the previous inefficiencies so at most there should only be one request per 10 Movie results.
   - I was able to mock out the service to some static json responses. This made those tests in NewtonflixApplicationTests run fast. The tests in NewtonflixApplicationOmdbTests are not mocked and therefore hit the network to check if omdbapi is really up.
   - I had the most trouble with writing the tests. I really would like to see how the more experienced devs would have architected testing for this exercise and how to prioritize what gets tested / and how granular.



