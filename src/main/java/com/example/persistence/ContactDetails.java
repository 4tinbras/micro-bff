package com.example.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Table(name = "contact_details")
public class ContactDetails {

    //TODO: change to a proper UUID
    @Id
    @GeneratedValue
    private Long uuid;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    @Digits(integer = 15, fraction = 0)
    private String phoneNo;

}