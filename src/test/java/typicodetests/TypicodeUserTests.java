package typicodetests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.example.TypicodeUser;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TypicodeUserTests {
    int userId;

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
