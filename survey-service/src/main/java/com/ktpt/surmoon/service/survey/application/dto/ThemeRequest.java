package com.ktpt.surmoon.service.survey.application.dto;

import com.ktpt.surmoon.service.survey.adapter.infrastructure.validation.SurveyShouldExist;
import com.ktpt.surmoon.service.survey.domain.model.theme.Theme;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ThemeRequest {
    @SurveyShouldExist
    private final Long surveyId;
    // TODO: 2021/03/13 Nullable?
    private final String thumbnail;
    private final String mainColor;
    private final String backgroundColor;
    private final String fontStyle;

    private ThemeRequest() {
        this(null, null, null, null, null);
    }

    public static ThemeRequest of(Long surveyId, SurveyCreateRequest request) {
        return new ThemeRequest(surveyId, request.getThumbnail(), request.getMainColor(), request.getBackgroundColor(), request.getFontStyle());
    }

    public Theme toEntity() {
        return new Theme(null, surveyId, thumbnail, mainColor, backgroundColor, fontStyle);
    }
}
