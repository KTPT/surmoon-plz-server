package com.ktpt.surmoon.service.survey.adapter.infrastructure.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = SurveyExistVerifier.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SurveyShouldExist {
    String message() default "survey does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
