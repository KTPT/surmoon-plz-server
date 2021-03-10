package com.ktpt.surmoon.service.survey.application.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MemberExistVerifier.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MemberShouldExist {
    String message() default "member does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
