package com.ktpt.surmoon.service.survey;

import com.ktpt.surmoon.service.survey.domain.model.member.Member;
import com.ktpt.surmoon.service.survey.domain.model.member.MemberRepository;
import com.ktpt.surmoon.service.survey.domain.model.survey.Survey;
import com.ktpt.surmoon.service.survey.domain.model.survey.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FixtureFactory implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final SurveyRepository surveyRepository;

    @Override
    public void run(ApplicationArguments args) {
        Member member = memberRepository.save(new Member(null));
        surveyRepository.save(new Survey(null, "title", member.getId(), null, null));
    }
}

