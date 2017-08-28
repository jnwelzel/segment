package com.jonwelzel.segment.presentation.controllers;

import com.jonwelzel.segment.application.SegmentationService;
import com.jonwelzel.segment.domain.models.Segmentation;
import com.jonwelzel.segment.domain.models.SegmentationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/segmentations")
public class SegmentationsController {

    @Autowired
    private SegmentationService segmentationService;

    @RequestMapping(method = RequestMethod.POST)
    public Segmentation add(@RequestBody Segmentation segmentation) {
        return segmentationService.save(segmentation);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Segmentation> getAll() {
        return segmentationService.findAll();
    }

    @RequestMapping(value = "/{segmentationId}", method = RequestMethod.GET)
    public SegmentationDTO getOneWithContacts(@PathVariable Long segmentationId) {
        return segmentationService.getSegmentationWithContacts(segmentationId);
    }
}
