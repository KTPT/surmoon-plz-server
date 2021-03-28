package com.ktpt.surmoon.service.survey.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktpt.surmoon.service.survey.application.dto.SurveyDTO;
import com.ktpt.surmoon.service.survey.application.dto.ThemeDTO;
import com.ktpt.surmoon.service.survey.domain.model.survey.Survey;
import com.ktpt.surmoon.service.survey.domain.model.survey.SurveyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final ThemeService themeService;

    @Transactional
    public SurveyDTO.SurveyCreateResponse save(SurveyDTO.SurveyCreateRequest request, Long creatorId) {
        Survey saved = surveyRepository.save(request.toSurvey(creatorId));
        ThemeDTO.ThemeResponse themeResponse = themeService.save(ThemeDTO.ThemeRequest.of(saved.getId(), request));
        return SurveyDTO.SurveyCreateResponse.of(saved, themeResponse);
    }

    public SurveyDTO.SurveyResponse update(Long id, SurveyDTO.SurveyUpdateRequest request, Long creatorId) {
        Survey survey = findById(id);
        survey.changeTitleWhoCreator(request.getTitle(), creatorId);

        return SurveyDTO.SurveyResponse.from(surveyRepository.save(survey));
    }

    private Survey findById(Long id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 survey, id : " + id));
    }
}
