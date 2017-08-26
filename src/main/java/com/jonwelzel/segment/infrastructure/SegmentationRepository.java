package com.jonwelzel.segment.infrastructure;

import com.jonwelzel.segment.domain.models.Segmentation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "segmentations", path = "segmentations")
@CrossOrigin
public interface SegmentationRepository extends PagingAndSortingRepository<Segmentation, Long> {
}
