package typicodetests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.example.TypicodeUser;
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


}
