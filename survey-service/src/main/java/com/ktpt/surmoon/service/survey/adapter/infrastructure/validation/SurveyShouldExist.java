package com.ktpt.surmoon.service.survey.adapter.infrastructure.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SurveyExistVerifier.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SurveyShouldExist {
    String message() default "survey does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
