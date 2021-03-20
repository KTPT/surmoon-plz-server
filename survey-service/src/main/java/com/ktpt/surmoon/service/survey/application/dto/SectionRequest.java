package com.ktpt.surmoon.service.survey.application.dto;

import com.ktpt.surmoon.service.survey.application.dto.validation.SurveyShouldExist;
import com.ktpt.surmoon.service.survey.domain.model.section.Section;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SectionRequest {
    @SurveyShouldExist
    private final Long surveyId;
    private final Long previousSectionId;
    private final String title;
    private final String description;

    private SectionRequest() {
        this(null, null, null, null);
    }

    public Section toEntity() {
        return new Section(null, surveyId, previousSectionId, title, description, null, null);
    }
}
