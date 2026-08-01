package com.example.contact;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;


@Controller
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @Get(uri = "/contacts")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        return "Hello World";
    }

    @Post(uri = "/contact")
    @Produces(MediaType.APPLICATION_JSON)
    public String postContact() {
        return "Hello World";
    }

    @Delete(uri = "/contact")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteContact() {
        return "Hello World";
    }
}