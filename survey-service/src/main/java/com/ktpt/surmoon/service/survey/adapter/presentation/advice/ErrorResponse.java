package com.ktpt.surmoon.service.survey.adapter.presentation.advice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    private final List<String> messages;

    private ErrorResponse() {
        this(null);
    }
}
