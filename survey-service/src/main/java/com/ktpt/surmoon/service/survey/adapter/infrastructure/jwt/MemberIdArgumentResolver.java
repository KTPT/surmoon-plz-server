package com.ktpt.surmoon.service.survey.adapter.infrastructure.jwt;

import static org.springframework.http.HttpHeaders.*;

import java.util.Objects;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.ktpt.surmoon.service.survey.adapter.presentation.LoginMemberId;
import com.ktpt.surmoon.service.survey.domain.model.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MemberIdArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String SP = " ";
    private static final int TOKEN_INDEX = 1;
    private static final int TYPE_INDEX = 0;
    private static final String BEARER = "bearer";

    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginMemberId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String[] authorizations = Objects.requireNonNull(webRequest.getHeader(AUTHORIZATION)).split(SP);
        String type = authorizations[TYPE_INDEX];
        String token = authorizations[TOKEN_INDEX];

        if (!type.equalsIgnoreCase(BEARER)) {
            throw new InvalidTokenException("지원하지 않는 토큰 타입입니다. type : " + type);
        }

        Long memberId = tokenProvider.getMemberId(token);
        if (memberRepository.existsById(memberId)) {
            return memberId;
        }

        throw new InvalidTokenException("토큰에 해당하는 member가 없습니다.");
    }
}
