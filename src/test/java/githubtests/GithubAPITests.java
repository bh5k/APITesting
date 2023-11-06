package githubtests;

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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GithubAPITests {
    @Test
    public void testResponseCodeWithVerifiedUser() throws IOException {
        String userName = "BackpackTrainer";

        HttpUriRequest request = new HttpGet("https://api.github.com/users/" + userName);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse httpResponse = client.execute(request);

        assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }
    @Test
    public void testResponseCodeWithUnverifiedUser() throws IOException {
        String userName = RandomStringUtils.randomAlphabetic(16);

        HttpUriRequest request = new HttpGet("https://api.github.com/users/" + userName);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse httpResponse = client.execute(request);

        assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }
    @Test
    public void testDefaultMimeEncodingWithVerifiedUser() throws IOException {
        String userName = "BackpackTrainer";
        String expectedMimeType = "application/json";

        HttpUriRequest request = new HttpGet("https://api.github.com/users/" + userName);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse httpResponse = client.execute(request);

        String mimeType = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();

        assertEquals(expectedMimeType, mimeType);
    }
}
