package com.ktpt.surmoon.service.survey.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenResponse {
    private static final String BEARER = "bearer";

    private final String type;
    private final String token;

    private TokenResponse() {
        this(null, null);
    }

    public static TokenResponse from(String token) {
        return new TokenResponse(BEARER, token);
    }
}
