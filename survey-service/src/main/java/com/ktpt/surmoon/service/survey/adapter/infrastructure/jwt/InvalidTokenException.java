package com.ktpt.surmoon.service.survey.adapter.infrastructure.jwt;

public class InvalidTokenException extends IllegalArgumentException {
    public InvalidTokenException(String s) {
        super(s);
    }
}
