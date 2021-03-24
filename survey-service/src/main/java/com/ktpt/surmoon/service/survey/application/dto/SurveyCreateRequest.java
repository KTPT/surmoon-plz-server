package com.ktpt.surmoon.service.survey.application.dto;

import com.ktpt.surmoon.service.survey.domain.model.survey.Survey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class SurveyCreateRequest {
    @NotBlank
    private final String title;
    @NotBlank
    private final String thumbnail;
    @NotBlank
    private final String mainColor;
    @NotBlank
    private final String backgroundColor;
    @NotBlank
    private final String fontStyle;

    private SurveyCreateRequest() {
        this(null, null, null, null, null);
    }

    public Survey toSurvey(Long creatorId) {
        return new Survey(null, title, creatorId, null, null);
    }
}
