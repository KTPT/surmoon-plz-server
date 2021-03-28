package com.ktpt.surmoon.service.survey.adapter.presentation.web;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktpt.surmoon.service.survey.adapter.presentation.LoginMemberId;
import com.ktpt.surmoon.service.survey.application.SurveyService;
import com.ktpt.surmoon.service.survey.application.dto.SurveyDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(SurveyController.SURVEY_URI)
@RestController
public class SurveyController {
    public static final String SURVEY_URI = "/api/surveys";

    private final SurveyService surveyService;

    @PostMapping
    public ResponseEntity<SurveyDTO.SurveyCreateResponse> save(
        @RequestBody @Valid SurveyDTO.SurveyCreateRequest request, @LoginMemberId Long creatorId) {
        SurveyDTO.SurveyCreateResponse saved = surveyService.save(request, creatorId);
        return ResponseEntity.created(URI.create(SURVEY_URI + "/" + saved.getId()))
            .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SurveyDTO.SurveyResponse> update(@PathVariable Long id,
        @RequestBody @Valid SurveyDTO.SurveyUpdateRequest request, @LoginMemberId Long creatorId) {
        SurveyDTO.SurveyResponse saved = surveyService.update(id, request, creatorId);
        return ResponseEntity.ok(saved);
    }
}
