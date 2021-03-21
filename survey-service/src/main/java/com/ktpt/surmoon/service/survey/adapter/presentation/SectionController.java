package com.ktpt.surmoon.service.survey.adapter.presentation;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktpt.surmoon.service.survey.application.SectionService;
import com.ktpt.surmoon.service.survey.application.dto.SectionRequest;
import com.ktpt.surmoon.service.survey.application.dto.SectionResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SectionController.SECTION_URI)
@RequiredArgsConstructor
public class SectionController {
    public static final String SECTION_URI = "/api/sections";

    private final SectionService sectionService;

    @PostMapping
    public ResponseEntity<SectionResponse> create(@RequestBody @Valid SectionRequest request) {
        SectionResponse saved = sectionService.save(request);
        return ResponseEntity.created(URI.create(SECTION_URI + "/" + saved.getId()))
            .body(saved);
    }

    @PutMapping("/{id}/content")
    public ResponseEntity<SectionResponse> updateContent(@PathVariable Long id,
        @RequestBody @Valid SectionRequest request) {
        SectionResponse response = sectionService.updateContent(id, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/sequence")
    public ResponseEntity<SectionResponse> updateSequence(@PathVariable Long id,
        @RequestBody @Valid SectionRequest request) {
        SectionResponse response = sectionService.updateSequence(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sectionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
