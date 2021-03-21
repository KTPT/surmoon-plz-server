package com.ktpt.surmoon.service.survey.application.dto;

import javax.validation.constraints.NotNull;

import com.ktpt.surmoon.service.survey.application.dto.validation.SurveyShouldExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SectionUpdateSequenceRequest {
    @SurveyShouldExist
    private final Long surveyId;
    @NotNull
    private final Long previousSectionId;

    private SectionUpdateSequenceRequest() {
        this(null, null);
    }
}
