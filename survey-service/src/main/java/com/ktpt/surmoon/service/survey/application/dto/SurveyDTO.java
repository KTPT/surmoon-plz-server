package com.ktpt.surmoon.service.survey.application.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.ktpt.surmoon.service.survey.domain.model.survey.Survey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class SurveyDTO {
    @Getter
    @RequiredArgsConstructor
    public static class SurveyCreateResponse {
        private final Long id;
        private final String title;
        private final Long creatorId;
        private final LocalDateTime createdDate;
        private final LocalDateTime lastModifiedDate;
        private final ThemeDTO.ThemeResponse themeResponse;

        private SurveyCreateResponse() {
            this(null, null, null, null, null, null);
        }

        public static SurveyCreateResponse of(Survey survey, ThemeDTO.ThemeResponse themeResponse) {
            return new SurveyCreateResponse(survey.getId(), survey.getTitle(), survey.getCreatorId(),
                survey.getCreatedDate(), survey.getLastModifiedDate(), themeResponse);
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class SurveyCreateRequest {
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

    @Getter
    @RequiredArgsConstructor
    public static class SurveyResponse {
        private final Long id;
        private final String title;
        private final Long creatorId;
        private final LocalDateTime createdDate;
        private final LocalDateTime lastModifiedDate;

        private SurveyResponse() {
            this(null, null, null, null, null);
        }

        public static SurveyResponse from(Survey survey) {
            return new SurveyResponse(survey.getId(), survey.getTitle(), survey.getCreatorId(), survey.getCreatedDate(),
                survey.getLastModifiedDate());
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class SurveyUpdateRequest {
        @NotBlank
        private final String title;

        private SurveyUpdateRequest() {
            this(null);
        }
    }
}
