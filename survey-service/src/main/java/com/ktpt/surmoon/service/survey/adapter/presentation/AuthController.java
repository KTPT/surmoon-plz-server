package com.ktpt.surmoon.service.survey.adapter.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktpt.surmoon.service.survey.security.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final CustomOAuth2UserService customOAuth2UserService;

    @GetMapping("/hi")
    public ResponseEntity<String> login(@AuthenticationPrincipal OAuth2User user) {
        log.error(">>>" + user.toString());
        return ResponseEntity.ok(user.getName());
    }

    @GetMapping("/login-success")
    public ResponseEntity<String> success(@AuthenticationPrincipal OAuth2User user) {
        String name = (String)user.getAttributes().get("name");
        log.error("!!!!" + name);
        return ResponseEntity.ok(user.getName());
    }
}
