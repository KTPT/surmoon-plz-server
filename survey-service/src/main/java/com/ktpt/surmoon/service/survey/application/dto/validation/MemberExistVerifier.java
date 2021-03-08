package com.ktpt.surmoon.service.survey.application.dto.validation;

import com.ktpt.surmoon.service.survey.domain.model.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.MessageFormat;

@RequiredArgsConstructor
@Component
public class MemberExistVerifier implements ConstraintValidator<MemberShouldExist, Long> {
    private final MemberRepository memberRepository;

    @Override
    public void initialize(MemberShouldExist constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        boolean isValid = memberRepository.existsById(id);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MessageFormat.format("Member with id: {0} does not exists!", id))
                    .addConstraintViolation();
        }
        return isValid;
    }
}
