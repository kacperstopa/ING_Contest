package org.kstopa;

import okhttp3.Response;
import org.junit.jupiter.api.Test;
import io.javalin.testtools.JavalinTest;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpServerTest {

    @Test
    public void shouldHandleAtmserviceRequestsCorrectly() throws IOException {
        testEndpoint("/atmservice_request_1.json", "/atmservice_response_1.json", "/atms/calculateOrder");
        testEndpoint("/atmservice_request_2.json", "/atmservice_response_2.json", "/atms/calculateOrder");
    }

    @Test
    public void shouldHandleOnlinegameRequestCorrectly() throws IOException {
        testEndpoint("/onlinegame_request_1.json", "/onlinegame_response_1.json", "/onlinegame/calculate");
    }

    @Test
    public void shouldHandleTransactionsRequestCorrectly() throws IOException {
        testEndpoint("/transactions_request_1.json", "/transactions_response_1.json", "/transactions/report");
    }


    private void testEndpoint(String requestFileName, String responseFileName, String endpoint) throws IOException {
        String request = new String(this.getClass().getResourceAsStream(requestFileName).readAllBytes());
        String expectedResponse = new String(this.getClass().getResourceAsStream(responseFileName).readAllBytes());
        JavalinTest.test(new HttpServer().server(), (server, client) -> {
            final Response response = client.post(endpoint, request);
            assertEquals(200, response.code());
            JSONAssert.assertEquals(expectedResponse, response.body().string(), true);
        });
    }

}
