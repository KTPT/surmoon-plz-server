package com.ktpt.surmoon.service.survey.application.dto;

import com.ktpt.surmoon.service.survey.domain.model.theme.Theme;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ThemeResponse {
    private final Long id;
    private final Long surveyId;
    private final String thumbnail;
    private final String mainColor;
    private final String backgroundColor;
    private final String fontStyle;

    private ThemeResponse() {
        this(null, null, null, null, null, null);
    }

    public static ThemeResponse from(Theme theme) {
        return new ThemeResponse(theme.getId(), theme.getSurveyId(), theme.getThumbnail(), theme.getMainColor(), theme.getBackgroundColor(), theme.getFontStyle());
    }
}
