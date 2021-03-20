package com.ktpt.surmoon.service.survey.application.dto;

import com.ktpt.surmoon.service.survey.domain.model.section.Section;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SectionResponse {
    private final Long id;
    private final Long surveyId;
    private final Long previousSectionId;
    private final String title;
    private final String description;
    private final String createdDate;
    private final String lastModifiedDate;

    public SectionResponse() {
        this(null, null, null, null, null, null, null);
    }

    public static SectionResponse from(Section section) {
        return new SectionResponse(section.getId(), section.getSurveyId(), section.getPreviousSectionId(),
            section.getTitle(), section.getDescription(), section.getCreatedDate().toString(),
            section.getLastModifiedDate().toString());
    }
}
