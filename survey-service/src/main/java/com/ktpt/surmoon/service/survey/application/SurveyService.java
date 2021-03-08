package com.ktpt.surmoon.service.survey.application;

import com.ktpt.surmoon.service.survey.application.dto.SurveyRequest;
import com.ktpt.surmoon.service.survey.application.dto.SurveyResponse;
import com.ktpt.surmoon.service.survey.domain.model.survey.Survey;
import com.ktpt.surmoon.service.survey.domain.model.survey.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;

    public SurveyResponse save(SurveyRequest request) {
        Survey saved = surveyRepository.save(request.toEntity());
        return SurveyResponse.from(saved);
    }

    public SurveyResponse update(Long id, SurveyRequest request) {
        Survey survey = findById(id);
        survey.updateWhoCreator(request.getTitle(), request.getCreatorId());

        return SurveyResponse.from(surveyRepository.save(survey));
    }

    private Survey findById(Long id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 survey, id : " + id));
    }
}
