package com.ktpt.surmoon.service.survey.application.dto;

import com.ktpt.surmoon.service.survey.domain.model.survey.Survey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class SurveyCreateResponse {
    private final Long id;
    private final String title;
    private final Long creatorId;
    private final LocalDateTime createdDate;
    private final LocalDateTime lastModifiedDate;
    private final ThemeResponse themeResponse;

    private SurveyCreateResponse() {
        this(null, null, null, null, null, null);
    }

    public static SurveyCreateResponse of(Survey survey, ThemeResponse themeResponse) {
        return new SurveyCreateResponse(survey.getId(), survey.getTitle(), survey.getCreatorId(), survey.getCreatedDate(), survey.getLastModifiedDate(), themeResponse);
    }
}
