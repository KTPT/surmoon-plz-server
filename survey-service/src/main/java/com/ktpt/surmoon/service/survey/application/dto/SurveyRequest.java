package com.ktpt.surmoon.service.survey.application.dto;

import com.ktpt.surmoon.service.survey.application.dto.validation.MemberShouldExist;
import com.ktpt.surmoon.service.survey.domain.model.survey.Survey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class SurveyRequest {
    @NotBlank
    private final String title;
    // TODO: 2021/03/09 extracted by header (token)
    @MemberShouldExist
    private final Long creatorId;

    private SurveyRequest() {
        this(null, null);
    }

    public Survey toEntity() {
        return new Survey(null, title, creatorId, null, null);
    }
}
