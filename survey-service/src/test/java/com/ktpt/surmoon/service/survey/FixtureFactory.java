package com.ktpt.surmoon.service.survey;

import com.ktpt.surmoon.service.survey.domain.model.member.Member;
import com.ktpt.surmoon.service.survey.domain.model.member.MemberRepository;
import com.ktpt.surmoon.service.survey.domain.model.survey.Survey;
import com.ktpt.surmoon.service.survey.domain.model.survey.SurveyRepository;
import com.ktpt.surmoon.service.survey.domain.model.theme.Theme;
import com.ktpt.surmoon.service.survey.domain.model.theme.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FixtureFactory implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final SurveyRepository surveyRepository;
    private final ThemeRepository themeRepository;

    @Override
    public void run(ApplicationArguments args) {
        Member member = memberRepository.save(new Member(null, null));
        Survey survey = surveyRepository.save(new Survey(null, "title", member.getId(), null, null));
        themeRepository.save(new Theme(null, survey.getId(), "thumbnail", "mainColor", "backgroundColor", "fontStyle"));
    }
}

