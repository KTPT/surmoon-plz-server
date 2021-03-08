package com.ktpt.surmoon.service.survey.adapter.presentation;

import com.ktpt.surmoon.service.survey.application.SurveyService;
import com.ktpt.surmoon.service.survey.application.dto.SurveyRequest;
import com.ktpt.surmoon.service.survey.application.dto.SurveyResponse;
import com.ktpt.surmoon.service.survey.domain.model.survey.Survey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;


@RequiredArgsConstructor
@RequestMapping(SurveyController.SURVEY_URI)
@RestController
public class SurveyController {
    public static final String SURVEY_URI = "/surveys";

    private final SurveyService surveyService;

    @PostMapping
    public ResponseEntity<SurveyResponse> save(@RequestBody @Valid SurveyRequest request) {
        SurveyResponse saved = surveyService.save(request);
        return ResponseEntity.created(URI.create(SURVEY_URI + "/" + saved.getId()))
                .body(saved);
    }

    /**
     * @param request's title, creatorId will be verified by domain (NO @Valid)
     * @see Survey update()
     */
    @PutMapping("/{id}")
    public ResponseEntity<SurveyResponse> update(@PathVariable Long id, @RequestBody SurveyRequest request) {
        SurveyResponse saved = surveyService.update(id, request);
        return ResponseEntity.ok(saved);
    }
}
