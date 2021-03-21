package com.ktpt.surmoon.service.survey.application.dto;

import javax.validation.constraints.NotNull;

import com.ktpt.surmoon.service.survey.application.dto.validation.SurveyShouldExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SectionUpdateContentRequest {
    @SurveyShouldExist
    private final Long surveyId;
    @NotNull
    private final String title;
    @NotNull
    private final String description;

    private SectionUpdateContentRequest() {
        this(null, null, null);
    }
}
