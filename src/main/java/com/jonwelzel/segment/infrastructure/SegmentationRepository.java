package com.jonwelzel.segment.infrastructure;

import com.jonwelzel.segment.domain.models.Segmentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentationRepository extends JpaRepository<Segmentation, Long> {
}
