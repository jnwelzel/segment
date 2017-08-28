package com.jonwelzel.segment.application;

import com.jonwelzel.segment.domain.enums.NumberOption;
import com.jonwelzel.segment.domain.enums.TextOption;
import com.jonwelzel.segment.domain.models.Contact;
import com.jonwelzel.segment.domain.models.ContactDTO;
import com.jonwelzel.segment.domain.models.Segmentation;
import com.jonwelzel.segment.domain.models.SegmentationDTO;
import com.jonwelzel.segment.infrastructure.ContactRepository;
import com.jonwelzel.segment.infrastructure.SegmentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class SegmentationService {

    @Autowired
    private SegmentationRepository segmentationRepository;

    @Autowired
    private ContactRepository contactRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Segmentation save(Segmentation segmentation) {
        return segmentationRepository.saveAndFlush(segmentation);
    }

    public List<Segmentation> findAll() {
        return segmentationRepository.findAll();
    }

    public SegmentationDTO getSegmentationWithContacts(Long segmentationId) {
        Segmentation segmentation = segmentationRepository.getOne(segmentationId);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> query = criteriaBuilder.createQuery(Contact.class);
        Root<Contact> c = query.from(Contact.class);

        if (segmentation.getAgeValue() != null) {
            query.where(getNumberPredicateFromOperator(c.get("age"), segmentation.getAgeOperator(), criteriaBuilder, segmentation.getAgeValue()));
        }
        if (segmentation.getEmailValue() != null && !segmentation.getEmailValue().equals("")) {
            query.where(getTextPredicateFromOperator(c.get("email"), segmentation.getEmailOperator(), criteriaBuilder, segmentation.getEmailValue()));
        }
        if (segmentation.getNameValue() != null && !segmentation.getNameValue().equals("")) {
            query.where(getTextPredicateFromOperator(c.get("name"), segmentation.getNameOperator(), criteriaBuilder, segmentation.getNameValue()));
        }
        if (segmentation.getStateValue() != null && !segmentation.getStateValue().equals("")) {
            query.where(getTextPredicateFromOperator(c.get("state"), segmentation.getStateOperator(), criteriaBuilder, segmentation.getStateValue()));
        }
        if (segmentation.getJobTitleValue() != null && !segmentation.getJobTitleValue().equals("")) {
            query.where(getTextPredicateFromOperator(c.get("jobTitle"), segmentation.getJobTitleOperator(), criteriaBuilder, segmentation.getJobTitleValue()));
        }

        TypedQuery<Contact> typedQuery = entityManager.createQuery(query);
        List<ContactDTO> contacts = new ArrayList<>();
        for (Contact contact : typedQuery.getResultList()) {
            contacts.add(contact.toDTO());
        }
        segmentation.setContacts(contacts);

        return segmentation.toDTO();
    }

    private Predicate getNumberPredicateFromOperator(Path attribute, NumberOption operator, CriteriaBuilder criteriaBuilder, Integer value) {
        Predicate result = null;

        if (operator == NumberOption.EQUALS_TO) {
            result = criteriaBuilder.equal(attribute, value);
        }
        if (operator == NumberOption.GREATER_THAN) {
            result = criteriaBuilder.gt(attribute, value);
        }
        if (operator == NumberOption.GREATER_THAN_EQUAL) {
            result = criteriaBuilder.greaterThanOrEqualTo(attribute, value);
        }
        if (operator == NumberOption.LESS_THAN) {
            result = criteriaBuilder.lt(attribute, value);
        }
        if (operator == NumberOption.LESS_THAN_EQUAL) {
            result = criteriaBuilder.lessThanOrEqualTo(attribute, value);
        }

        return result;
    }

    private Predicate getTextPredicateFromOperator(Path attribute, TextOption operator, CriteriaBuilder criteriaBuilder, String value) {
        Predicate result = null;

        if (operator == TextOption.EQUALS_TO) {
            result = criteriaBuilder.equal(attribute, value);
        }
        if (operator == TextOption.CONTAINS) {
            result = criteriaBuilder.like(attribute, "%" + value + "%");
        }
        if (operator == TextOption.ENDS_WITH) {
            result = criteriaBuilder.like(attribute, "%" + value);
        }
        if (operator == TextOption.STARTS_WITH) {
            result = criteriaBuilder.like(attribute, value + "%");
        }

        return result;
    }

}
