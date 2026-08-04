package com.example.contact;

import com.example.persistence.ContactDetails;
import com.example.persistence.ContactDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class ContactServiceTest {

    private final ContactDetails validRecord = new ContactDetails(1L, "Tom", "Smith", "ts1@example.com", "1234567890");
    private final ContactDetails duplicateRecord = new ContactDetails(22L, "Secundus", "Smith", "ts1@example.com", "1234567890");
    private ContactDetailsRepository contactDetailsRepository;
    private ContactService subject;

    @BeforeEach
    void setup() {
        contactDetailsRepository = Mockito.mock(ContactDetailsRepository.class);

        when(contactDetailsRepository.save(eq(validRecord))).thenReturn(validRecord);

        subject = new ContactService(contactDetailsRepository);
    }

    @Test
    void whenSaveWithValidRecord_thenMessageEmitted_andSavedRecordReturned() {
        ContactDetails outcome = subject.save(validRecord);
        assertEquals(validRecord, outcome);
    }

    @Test
    void whenValidateWithDistinctUUID_thenReturnFalse() {
        assertFalse(subject.validateRecordToSave(duplicateRecord, validRecord));
    }

    @Test
    void whenValidateWithValidUUID_thenReturnFalse() {
        assertTrue(subject.validateRecordToSave(validRecord, validRecord));
    }

}