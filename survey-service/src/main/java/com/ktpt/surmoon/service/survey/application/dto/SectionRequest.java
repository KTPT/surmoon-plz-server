package com.ktpt.surmoon.service.survey.application.dto;

import javax.validation.constraints.NotNull;

import com.ktpt.surmoon.service.survey.application.dto.validation.SurveyShouldExist;
import com.ktpt.surmoon.service.survey.domain.model.section.Section;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SectionRequest {
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

    public Section toEntity(Long previousSectionId) {
        return new Section(null, surveyId, previousSectionId, title, description, null, null);
    }
}
