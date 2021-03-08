package com.ktpt.surmoon.service.survey.adapter.presentation;

import com.ktpt.surmoon.service.survey.application.SurveyService;
import com.ktpt.surmoon.service.survey.application.dto.SurveyRequest;
import com.ktpt.surmoon.service.survey.application.dto.SurveyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
