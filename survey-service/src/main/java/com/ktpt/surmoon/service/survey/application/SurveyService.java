package com.ktpt.surmoon.service.survey.application;

import com.ktpt.surmoon.service.survey.application.dto.*;
import com.ktpt.surmoon.service.survey.domain.model.survey.Survey;
import com.ktpt.surmoon.service.survey.domain.model.survey.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final ThemeService themeService;

    @Transactional
    public SurveyCreateResponse save(SurveyCreateRequest request) {
        Survey saved = surveyRepository.save(request.toSurvey());
        ThemeResponse themeResponse = themeService.save(ThemeRequest.of(saved.getId(), request));
        return SurveyCreateResponse.of(saved, themeResponse);
    }

    public SurveyResponse update(Long id, SurveyRequest request) {
        Survey survey = findById(id);
        survey.changeTitleWhoCreator(request.getTitle(), request.getCreatorId());

        return SurveyResponse.from(surveyRepository.save(survey));
    }

    private Survey findById(Long id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 survey, id : " + id));
    }
}
