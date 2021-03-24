package com.ktpt.surmoon.service.survey.adapter.presentation.web;

import com.ktpt.surmoon.service.survey.adapter.presentation.LoginMemberId;
import com.ktpt.surmoon.service.survey.application.SurveyService;
import com.ktpt.surmoon.service.survey.application.dto.SurveyCreateRequest;
import com.ktpt.surmoon.service.survey.application.dto.SurveyCreateResponse;
import com.ktpt.surmoon.service.survey.application.dto.SurveyRequest;
import com.ktpt.surmoon.service.survey.application.dto.SurveyResponse;
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
    public ResponseEntity<SurveyCreateResponse> save(@RequestBody @Valid SurveyCreateRequest request, @LoginMemberId Long creatorId) {
        SurveyCreateResponse saved = surveyService.save(request, creatorId);
        return ResponseEntity.created(URI.create(SURVEY_URI + "/" + saved.getId()))
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SurveyResponse> update(@PathVariable Long id, @RequestBody @Valid SurveyRequest request, @LoginMemberId Long creatorId) {
        SurveyResponse saved = surveyService.update(id, request, creatorId);
        return ResponseEntity.ok(saved);
    }
}
