package com.ktpt.surmoon.service.survey.adapter.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AuthController {

    @GetMapping("/hi")
    public ResponseEntity<String> login(@AuthenticationPrincipal OAuth2User user) {
      log.error(">>>" + user.toString());
        return ResponseEntity.ok(user.getName());
    }
}
