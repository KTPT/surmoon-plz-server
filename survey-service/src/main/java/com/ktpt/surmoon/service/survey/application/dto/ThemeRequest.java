package com.ktpt.surmoon.service.survey.application.dto;

import com.ktpt.surmoon.service.survey.domain.model.theme.Theme;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ThemeRequest {
    private final Long surveyId;
    private final String thumbnail;
    private final String mainColor;
    private final String backgroundColor;
    private final String fontStyle;

    private ThemeRequest() {
        this(null, null, null, null, null);
    }

    public Theme toEntity() {
        return new Theme(null, surveyId, thumbnail, mainColor, backgroundColor, fontStyle);
    }
}
