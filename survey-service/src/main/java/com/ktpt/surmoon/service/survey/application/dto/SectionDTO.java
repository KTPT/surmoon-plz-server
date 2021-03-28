package com.ktpt.surmoon.service.survey.application.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.ktpt.surmoon.service.survey.adapter.infrastructure.validation.SurveyShouldExist;
import com.ktpt.surmoon.service.survey.domain.model.section.Section;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class SectionDTO {
    @Getter
    @RequiredArgsConstructor
    public static class SectionRequest {
        @SurveyShouldExist
        private final Long surveyId;
        @NotNull
        private final Long previousSectionId;
        @NotNull
        private final String title;
        @NotNull
        private final String description;

        private SectionRequest() {
            this(null, null, null, null);
        }

        public Section toEntity() {
            return new Section(null, surveyId, previousSectionId, title, description, null, null);
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class SectionResponse {
        private final Long id;
        private final Long surveyId;
        private final Long previousSectionId;
        private final String title;
        private final String description;
        private final LocalDateTime createdDate;
        private final LocalDateTime lastModifiedDate;

        public SectionResponse() {
            this(null, null, null, null, null, null, null);
        }

        public static SectionResponse from(Section section) {
            return new SectionResponse(section.getId(), section.getSurveyId(), section.getPreviousSectionId(),
                section.getTitle(), section.getDescription(), section.getCreatedDate(), section.getLastModifiedDate());
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class SectionUpdateContentRequest {
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

    @Getter
    @RequiredArgsConstructor
    public static class SectionUpdateSequenceRequest {
        @SurveyShouldExist
        private final Long surveyId;
        @NotNull
        private final Long previousSectionId;

        private SectionUpdateSequenceRequest() {
            this(null, null);
        }
    }
}
