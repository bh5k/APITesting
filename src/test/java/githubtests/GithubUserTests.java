package githubtests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.example.GitHubUser;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GithubUserTests {
    @Test
    public void testUserBackpackTrainerIsMe() throws IOException {
        String userName = "BackpackTrainer";
        String name = "Bill Fairfield";
        int repos = 46;

        HttpUriRequest request = new HttpGet("https://api.github.com/users/" + userName);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse httpResponse = client.execute(request);

        ObjectMapper mapper = new ObjectMapper();

        GitHubUser user = mapper.readValue(httpResponse.getEntity().getContent(), GitHubUser.class);

        assertAll("user",
                () -> assertEquals(name, user.getName()),
                () -> assertEquals(repos, user.getPublic_repos())
        );
    }
}
