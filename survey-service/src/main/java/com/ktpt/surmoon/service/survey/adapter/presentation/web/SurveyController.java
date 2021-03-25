package com.ktpt.surmoon.service.survey.adapter.presentation.web;

import com.ktpt.surmoon.service.survey.adapter.presentation.LoginMemberId;
import com.ktpt.surmoon.service.survey.application.SurveyService;
import com.ktpt.surmoon.service.survey.application.dto.SurveyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;


@RequiredArgsConstructor
@RequestMapping(SurveyController.SURVEY_URI)
@RestController
public class SurveyController {
    public static final String SURVEY_URI = "/api/surveys";

    private final SurveyService surveyService;

    @PostMapping
    public ResponseEntity<SurveyDTO.SurveyCreateResponse> save(@RequestBody @Valid SurveyDTO.SurveyCreateRequest request, @LoginMemberId Long creatorId) {
        SurveyDTO.SurveyCreateResponse saved = surveyService.save(request, creatorId);
        return ResponseEntity.created(URI.create(SURVEY_URI + "/" + saved.getId()))
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SurveyDTO.SurveyResponse> update(@PathVariable Long id, @RequestBody @Valid SurveyDTO.SurveyUpdateRequest request, @LoginMemberId Long creatorId) {
        SurveyDTO.SurveyResponse saved = surveyService.update(id, request, creatorId);
        return ResponseEntity.ok(saved);
    }
}
