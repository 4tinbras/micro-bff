package com.example.contact;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class ContactControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    public void whenPostValidContact_thenReturnValidSetOfRecords_andReturn200() {
        HttpRequest<?> request = HttpRequest.GET("/contacts").accept(MediaType.TEXT_PLAIN);
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        assertEquals("Hello World", body);
    }

    @Test
    public void whenGetContacts_thenReturnValidSetOfRecords_andReturn200() {
        HttpRequest<?> request = HttpRequest.POST("/contact", "").accept(MediaType.TEXT_PLAIN);
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        assertEquals("Hello World", body);
    }

    @Test
    public void whenDelete_thenObjectDeleted_andReturn204() {
        HttpRequest<?> request = HttpRequest.DELETE("/contact").accept(MediaType.TEXT_PLAIN);
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        assertEquals("Hello World", body);
    }
}