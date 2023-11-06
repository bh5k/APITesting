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
    /*
    TODO Create a test that validates the status code for a known user.  Users 1 - 10 are known users
    TODO Create a test that validates the status code for an unknown user.  Unknown users have ID's > 10
    TODO Create a test that validates the default MIME type
     */
}
