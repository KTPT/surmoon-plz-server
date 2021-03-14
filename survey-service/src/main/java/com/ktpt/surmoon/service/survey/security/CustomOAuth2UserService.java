package com.ktpt.surmoon.service.survey.security;

import java.util.Optional;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.ktpt.surmoon.service.survey.domain.model.member.Member;
import com.ktpt.surmoon.service.survey.domain.model.member.MemberRepository;
import com.ktpt.surmoon.service.survey.security.member.OAuth2UserInfo;
import com.ktpt.surmoon.service.survey.security.member.OAuth2UserInfoFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    public CustomOAuth2UserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

      log.error("hihihi");
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
            oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if (oAuth2UserInfo.getEmail().isEmpty()) {
            throw new RuntimeException("OAuth2 provider에 이메일이 없습니다.");
        }

        Optional<Member> userOptional = memberRepository.findByEmail(oAuth2UserInfo.getEmail());
        Member member;
        if (userOptional.isPresent()) {
            member = userOptional.get();
            member = updateExistingUser(member, oAuth2UserInfo);
        } else {
            member = registerNewUser(oAuth2UserInfo);
        }

        return UserPrincipal.create(member, oAuth2User.getAttributes());
    }

    private Member registerNewUser(OAuth2UserInfo oAuth2UserInfo) {
        Member member = new Member(oAuth2UserInfo.getName(), oAuth2UserInfo.getEmail());

        return memberRepository.save(member);
    }

    private Member updateExistingUser(Member existingMember, OAuth2UserInfo oAuth2UserInfo) {
        existingMember.rename(oAuth2UserInfo.getName());
        return memberRepository.save(existingMember);
    }
}