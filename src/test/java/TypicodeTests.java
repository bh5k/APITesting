import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TypicodeTests {
    int userId;

    @Test
    public void testResponseCodeWithVerifiedUser() throws IOException {
        userId = 1;

        HttpUriRequest request = new HttpGet("https://jsonplaceholder.typicode.com/users/" + userId);

        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse httpResponse = client.execute(request);

        assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void testResponseCodeWithUnverifiedUser() throws IOException {
        userId = 20;

        HttpUriRequest request = new HttpGet("https://jsonplaceholder.typicode.com/users/" + userId);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse httpResponse = client.execute(request);

        assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void testDefaultMimeEncodingWithVerifiedUser() throws IOException {
        userId = 2;
        String expectedMimeType = "application/json";

        HttpUriRequest request = new HttpGet("https://jsonplaceholder.typicode.com/users/" + userId);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse httpResponse = client.execute(request);

        String mimeType = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();

        assertEquals(expectedMimeType, mimeType);
    }

    @Test
    public void testUserHasCorrectName() throws IOException {
        userId = 2;
        String expectedName = "Ervin Howell";
        String expectedUserName = "Antonette";

        HttpUriRequest request = new HttpGet("https://jsonplaceholder.typicode.com/users/" + userId);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse httpResponse = client.execute(request);

        ObjectMapper mapper = new ObjectMapper();

        TypicodeUser user = mapper.readValue(httpResponse.getEntity().getContent(), TypicodeUser.class);
        String actualName = user.getName();
        String actualUserName = user.getUsername();

        assertAll("user",
                () -> assertEquals(expectedName, actualName),
                () -> assertEquals(expectedUserName, actualUserName )
            );
    }

    @Test
    public void noOneLivesInWilloughby() throws IOException {
        String emptyCity = "Willoughby";

        HttpUriRequest request = new HttpGet("https://jsonplaceholder.typicode.com/users/" );
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse httpResponse = client.execute(request);

        ObjectMapper mapper = new ObjectMapper();

        TypicodeUser[] users = mapper.readValue(httpResponse.getEntity().getContent(), TypicodeUser[].class);

        for(TypicodeUser user : users) {
            assertFalse(emptyCity.equals(user.getAddress().getCity()));
        }
    }
}
