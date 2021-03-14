package com.ktpt.surmoon.service.survey.security.member;

import java.security.AuthProvider;
import java.util.Map;

public class OAuth2UserInfoFactory {
    private OAuth2UserInfoFactory() {
        throw new IllegalStateException("OAuth2UserInfoFactory의 인스턴스는 생성할 수 없습니다.");
    }

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        return new GoogleOAuth2UserInfo(attributes);
    }
}