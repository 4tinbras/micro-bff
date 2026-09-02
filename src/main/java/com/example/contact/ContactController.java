package com.example.contact;

import com.example.persistence.ContactDetails;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.validation.Valid;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@Controller
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @Get(uri = "/contacts")
    @Produces(MediaType.APPLICATION_JSON)
    public MutableHttpResponse<List<ContactDetails>> getAll() {
        return HttpResponse.ok(contactService.findAll());
    }

    @Post(uri = "/contact")
    @Produces(MediaType.APPLICATION_JSON)
    public MutableHttpResponse<ContactDetails> postContact(@Body @Valid ContactDetails contactDetails) throws URISyntaxException {
        final ContactDetails foundDetails = contactService.findByEmail(contactDetails.getEmail()).orElse(null);
        if (foundDetails != null) {
            if (!contactService.validateRecordToSave(contactDetails, foundDetails)) {
                return HttpResponse.unprocessableEntity();
            }
            contactDetails.setUuid(foundDetails.getUuid());
        }

        final ContactDetails savedContact = contactService.save(contactDetails);
        return HttpResponse.created(new URI("/contacts/" + savedContact.getUuid().toString())).body(savedContact);
    }

    @Status(HttpStatus.NO_CONTENT)
    @Delete(uri = "/contact/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public void deleteContact(@Parameter(value = "id") @PathVariable("name") Long id) {
        contactService.deleteById(id);
    }
}