package com.jonwelzel.segment.infrastructure;

import com.jonwelzel.segment.domain.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
