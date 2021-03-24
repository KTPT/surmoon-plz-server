package com.ktpt.surmoon.service.survey.adapter.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktpt.surmoon.service.survey.application.dto.TokenResponse;
import com.ktpt.surmoon.service.survey.application.OAuth2MemberService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class OAuthController {
    private final OAuth2MemberService oAuth2MemberService;

    @GetMapping("/login-success")
    public ResponseEntity<TokenResponse> loginSuccess(@AuthenticationPrincipal OAuth2User user) {
        String token = oAuth2MemberService.createToken(user);
        return ResponseEntity.ok(TokenResponse.from(token));
    }
}
