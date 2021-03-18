package com.ktpt.surmoon.service.survey.oauth;

import java.util.Optional;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.ktpt.surmoon.service.survey.domain.model.member.Member;
import com.ktpt.surmoon.service.survey.domain.model.member.MemberRepository;
import com.ktpt.surmoon.service.survey.oauth.token.TokenProvider;
import com.ktpt.surmoon.service.survey.oauth.user.OAuth2UserInfo;
import com.ktpt.surmoon.service.survey.oauth.user.OAuth2UserInfoFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OAuth2MemberService {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public OAuth2MemberService(MemberRepository memberRepository, TokenProvider tokenProvider) {
        this.memberRepository = memberRepository;
        this.tokenProvider = tokenProvider;
    }

    public String createToken(OAuth2User oAuthUser) {
        Member member = processOAuth2User(oAuthUser);
        return tokenProvider.createToken(member.getId());
    }

    private Member processOAuth2User(OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getGoogleOAuth2UserInfo(oAuth2User.getAttributes());
        if (oAuth2UserInfo.getEmail().isEmpty()) {
            throw new IllegalArgumentException("OAuth2 provider에 이메일이 없습니다.");
        }

        Optional<Member> userOptional = memberRepository.findByEmail(oAuth2UserInfo.getEmail());
        if (userOptional.isPresent()) {
            Member member = userOptional.get();
            return updateExistingMember(member, oAuth2UserInfo);
        }
        return registerNewMember(oAuth2UserInfo);
    }

    private Member updateExistingMember(Member existingMember, OAuth2UserInfo oAuth2UserInfo) {
        existingMember.rename(oAuth2UserInfo.getName());
        return memberRepository.save(existingMember);
    }

    private Member registerNewMember(OAuth2UserInfo oAuth2UserInfo) {
        Member member = new Member(oAuth2UserInfo.getName(), oAuth2UserInfo.getEmail());
        return memberRepository.save(member);
    }
}
