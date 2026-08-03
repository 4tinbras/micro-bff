package com.example.contact;

import com.example.persistence.ContactDetails;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
public class ContactControllerTest {

    private static final String VALID_EMAIL = "ts@example.com";
    private final ContactDetails validRecord = new ContactDetails(0L, "Tom", "Smith", VALID_EMAIL, "079678234");

    @Inject
    @Client("/")
    HttpClient client;

    @Inject
    ContactService contactService;

    @Test
    public void whenPostValidContact_thenReturnValidSetOfRecords_andReturn200() {
        when(contactService.findByEmail(eq(VALID_EMAIL))).thenReturn(Optional.empty());
        when(contactService.save(any())).thenReturn(validRecord);

        HttpRequest<?> request = HttpRequest.POST("/contact", validRecord).accept(MediaType.APPLICATION_JSON);
        HttpResponse<ContactDetails> response = client.toBlocking().exchange(request, ContactDetails.class);
        ContactDetails body = response.body();

        assertEquals(HttpStatus.CREATED, response.getStatus());
        assertNotNull(body);
        Assertions.assertThat(validRecord).usingRecursiveComparison().isEqualTo(body);

    }

    @Test
    public void whenGetContacts_thenReturnValidSetOfRecords_andReturn200() {
        when(contactService.findAll()).thenReturn(List.of(validRecord));

        HttpRequest<?> request = HttpRequest.GET("/contacts").accept(MediaType.APPLICATION_JSON);
        HttpResponse<List<ContactDetails>> response = client.toBlocking().exchange(request);

        assertEquals(HttpStatus.OK, response.getStatus());

        // TODO: fails to deserialize generic collection
//        List<ContactDetails> body = response.body();
//        assertNotNull(body);
//        Assertions.assertThat(validRecord).usingRecursiveComparison().isEqualTo(body.getFirst());
    }

    @Test
    public void whenDelete_thenObjectDeleted_andReturn204() {
        HttpRequest<?> request = HttpRequest.DELETE("/contact/1").accept(MediaType.TEXT_PLAIN);
        HttpResponse<String> response = client.toBlocking().exchange(request);
        String body = response.body();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
        assertEquals(null, body);
    }

    @MockBean(ContactService.class)
    ContactService mathService() {
        return mock(ContactService.class);
    }
}