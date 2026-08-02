package com.example.persistence;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, String> {

    ContactDetails findByEmail(String email);
}
