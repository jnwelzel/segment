package com.jonwelzel.segment.application;

import com.jonwelzel.segment.domain.enums.NumberOption;
import com.jonwelzel.segment.domain.enums.TextOption;
import com.jonwelzel.segment.domain.models.Contact;
import com.jonwelzel.segment.domain.models.ContactDTO;
import com.jonwelzel.segment.domain.models.Segmentation;
import com.jonwelzel.segment.domain.models.SegmentationDTO;
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

    @PersistenceContext
    private EntityManager entityManager;

    public Segmentation save(Segmentation segmentation) {
        return segmentationRepository.saveAndFlush(segmentation);
    }

    public List<Segmentation> findAll() {
        return segmentationRepository.findAll();
    }

    public SegmentationDTO getSegmentationWithContacts(Long segmentationId) {
        Segmentation segmentation = segmentationRepository.findOne(segmentationId);
        if (segmentation == null) {
            return null;
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> query = criteriaBuilder.createQuery(Contact.class);
        Root<Contact> c = query.from(Contact.class);
        List<Predicate> predicates = new ArrayList<>();

        if (segmentation.getAgeValue() != null) {
            predicates.add(getNumberPredicateFromOperator(c.get("age"), segmentation.getAgeOperator(), criteriaBuilder, segmentation.getAgeValue()));
        }
        if (segmentation.getEmailValue() != null && !segmentation.getEmailValue().equals("")) {
            predicates.add(getTextPredicateFromOperator(c.get("email"), segmentation.getEmailOperator(), criteriaBuilder, segmentation.getEmailValue()));
        }
        if (segmentation.getNameValue() != null && !segmentation.getNameValue().equals("")) {
            predicates.add(getTextPredicateFromOperator(c.get("name"), segmentation.getNameOperator(), criteriaBuilder, segmentation.getNameValue()));
        }
        if (segmentation.getStateValue() != null && !segmentation.getStateValue().equals("")) {
            predicates.add(getTextPredicateFromOperator(c.get("state"), segmentation.getStateOperator(), criteriaBuilder, segmentation.getStateValue()));
        }
        if (segmentation.getJobTitleValue() != null && !segmentation.getJobTitleValue().equals("")) {
            predicates.add(getTextPredicateFromOperator(c.get("jobTitle"), segmentation.getJobTitleOperator(), criteriaBuilder, segmentation.getJobTitleValue()));
        }

        query.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Contact> typedQuery = entityManager.createQuery(query);

        segmentation.setContacts(toContactDTOList(typedQuery.getResultList()));

        return segmentation.toDTO();
    }

    private List<ContactDTO> toContactDTOList(List<Contact> contactsList) {
        List<ContactDTO> contactsDTOList = new ArrayList<>();

        for (Contact contact : contactsList) {
            contactsDTOList.add(contact.toDTO());
        }

        return contactsDTOList;
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
