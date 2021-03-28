package com.ktpt.surmoon.service.survey.adapter.presentation.web;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktpt.surmoon.service.survey.application.SectionService;
import com.ktpt.surmoon.service.survey.application.dto.SectionDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SectionController.SECTION_URI)
@RequiredArgsConstructor
public class SectionController {
    public static final String SECTION_URI = "/api/sections";

    private final SectionService sectionService;

    @PostMapping
    public ResponseEntity<SectionDTO.SectionResponse> create(@RequestBody @Valid SectionDTO.SectionRequest request) {
        SectionDTO.SectionResponse saved = sectionService.save(request);
        return ResponseEntity.created(URI.create(SECTION_URI + "/" + saved.getId()))
            .body(saved);
    }

    @PatchMapping("/{id}/content")
    public ResponseEntity<SectionDTO.SectionResponse> updateContent(@PathVariable Long id,
        @RequestBody @Valid SectionDTO.SectionUpdateContentRequest request) {
        SectionDTO.SectionResponse response = sectionService.updateContent(id, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/sequence")
    public ResponseEntity<SectionDTO.SectionResponse> updateSequence(@PathVariable Long id,
        @RequestBody @Valid SectionDTO.SectionUpdateSequenceRequest request) {
        SectionDTO.SectionResponse response = sectionService.updateSequence(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sectionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
