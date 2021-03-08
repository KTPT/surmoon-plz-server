package com.ktpt.surmoon.service.survey.application;

import com.ktpt.surmoon.service.survey.application.dto.SurveyRequest;
import com.ktpt.surmoon.service.survey.application.dto.SurveyResponse;
import com.ktpt.surmoon.service.survey.domain.model.survey.Survey;
import com.ktpt.surmoon.service.survey.domain.model.survey.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;

    @Transactional
    public SurveyResponse save(SurveyRequest request) {
        Survey saved = surveyRepository.save(request.toEntity());
        return SurveyResponse.from(saved);
    }
}
