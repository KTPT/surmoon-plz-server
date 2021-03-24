package com.ktpt.surmoon.service.survey.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class SurveyRequest {
    @NotBlank
    private final String title;

    private SurveyRequest() {
        this(null);
    }
}
