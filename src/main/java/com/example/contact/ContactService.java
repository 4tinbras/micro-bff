package com.example.contact;

import com.example.persistence.ContactDetails;
import com.example.persistence.ContactDetailsRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class ContactService {

    private final ContactDetailsRepository contactRepository;

    public ContactService(final ContactDetailsRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<ContactDetails> findAll() {
        return contactRepository.findAll();
    }

    public ContactDetails save(final ContactDetails toBeSaved) {

        final ContactDetails returnedRecord = contactRepository.save(toBeSaved);
        return returnedRecord;
    }

    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    public Optional<ContactDetails> findByEmail(String email) {
        return contactRepository.findByEmail(email);
    }

    public boolean validateRecordToSave(final ContactDetails contactDetails, final ContactDetails foundDetails) {
        if (contactDetails.getUuid() != null && !foundDetails.getUuid().equals(contactDetails.getUuid())) {
//            log.error("Provided email is already associated with another account.");
            return false;
        }

        return true;
    }
}
