package com.example.contact;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;


@Controller
public class ContactController {
    @Get(uri = "/contacts")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAll() {
        return "Hello World";
    }

    @Post(uri = "/contact")
    @Produces(MediaType.TEXT_PLAIN)
    public String postContact() {
        return "Hello World";
    }

    @Delete(uri = "/contact")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteContact() {
        return "Hello World";
    }
}