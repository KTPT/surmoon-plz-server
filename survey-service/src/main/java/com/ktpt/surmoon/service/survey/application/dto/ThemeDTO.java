package com.ktpt.surmoon.service.survey.application.dto;

import com.ktpt.surmoon.service.survey.adapter.infrastructure.validation.SurveyShouldExist;
import com.ktpt.surmoon.service.survey.domain.model.theme.Theme;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

public class ThemeDTO {
    @Getter
    @RequiredArgsConstructor
    public static class ThemeRequest {
        @SurveyShouldExist
        private final Long surveyId;
        @NotBlank
        private final String thumbnail;
        @NotBlank
        private final String mainColor;
        @NotBlank
        private final String backgroundColor;
        @NotBlank
        private final String fontStyle;

        private ThemeRequest() {
            this(null, null, null, null, null);
        }

        public static ThemeRequest of(Long surveyId, SurveyDTO.SurveyCreateRequest request) {
            return new ThemeRequest(surveyId, request.getThumbnail(), request.getMainColor(), request.getBackgroundColor(), request.getFontStyle());
        }

        public Theme toEntity() {
            return new Theme(null, surveyId, thumbnail, mainColor, backgroundColor, fontStyle);
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class ThemeResponse {
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
}
