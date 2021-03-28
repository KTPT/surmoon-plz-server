package com.ktpt.surmoon.service.survey.adapter.infrastructure.validation;

import java.text.MessageFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.ktpt.surmoon.service.survey.domain.model.survey.SurveyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SurveyExistVerifier implements ConstraintValidator<SurveyShouldExist, Long> {
    private final SurveyRepository surveyRepository;

    @Override
    public void initialize(SurveyShouldExist constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        boolean isValid = surveyRepository.existsById(id);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MessageFormat.format("Survey with id: {0} does not exists!", id))
                    .addConstraintViolation();
        }
        return isValid;
    }
}
