package com.ktpt.surmoon.service.survey.integration;

import com.ktpt.surmoon.service.survey.adapter.presentation.advice.ErrorResponse;
import com.ktpt.surmoon.service.survey.adapter.presentation.web.SurveyController;
import com.ktpt.surmoon.service.survey.application.dto.SurveyCreateRequest;
import com.ktpt.surmoon.service.survey.application.dto.SurveyCreateResponse;
import com.ktpt.surmoon.service.survey.application.dto.SurveyRequest;
import com.ktpt.surmoon.service.survey.application.dto.SurveyResponse;
import com.ktpt.surmoon.service.survey.domain.model.member.Member;
import com.ktpt.surmoon.service.survey.domain.model.survey.Survey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class SurveyIntegrationTest extends IntegrationTest {

    @DisplayName("설문 생성")
    @Test
    void createSurvey() {
        // given
        Member creator = findAnyMember();

        // when
        SurveyCreateRequest request = new SurveyCreateRequest("title", "thumbnail", "mainColor", "backgroundColor", "fontStyle");
        SurveyCreateResponse response = postWithLogin(request, SurveyController.SURVEY_URI, SurveyCreateResponse.class, creator.getId());

        // then
        assertAll(
                () -> assertThat(response.getId()).isNotNull(),
                () -> assertThat(response.getTitle()).isEqualTo(request.getTitle()),
                () -> assertThat(response.getCreatorId()).isEqualTo(creator.getId()),
                () -> assertThat(response.getCreatedDate()).isNotNull(),
                () -> assertThat(response.getLastModifiedDate()).isNotNull(),
                () -> assertThat(response.getThemeResponse().getSurveyId()).isNotNull()
        );
    }

    @DisplayName("설문 생성 실패")
    @Test
    void createSurveyFails() {
        // when
        SurveyCreateRequest request = new SurveyCreateRequest("", "", "", "", "");
        ErrorResponse response = postFailsWithLogin(request, SurveyController.SURVEY_URI, -1L);

        // then
        assertThat(response.getMessages())
                .containsOnly("must not be blank");
        assertThat(response.getMessages().size()).isEqualTo(5);
    }

    @DisplayName("설문 수정")
    @Test
    void updateSurvey() {
        // given
        Survey saved = findAnySurvey();

        // when
        SurveyRequest request = new SurveyRequest("changed");
        SurveyResponse response = putWithLogin(request, SurveyController.SURVEY_URI, saved.getId(), SurveyResponse.class, saved.getCreatorId());

        // then
        assertAll(
                () -> assertThat(response.getId()).isEqualTo(saved.getId()),
                () -> assertThat(response.getTitle()).isEqualTo(request.getTitle()),
                () -> assertThat(response.getCreatorId()).isEqualTo(saved.getCreatorId()),
                () -> assertThat(response.getCreatedDate()).isEqualTo(saved.getCreatedDate()),
                () -> assertThat(response.getLastModifiedDate().isAfter(saved.getLastModifiedDate())).isTrue()
        );
    }

    @DisplayName("설문 수정 실패")
    @TestFactory
    Stream<DynamicTest> updateSurveyFails() {
        // given
        Survey saved = findAnySurvey();
        Member noCreator = saveMember(new Member(null, null));

        return Stream.of(
                DynamicTest.dynamicTest("같은 title로 수정시 BadRequest", () -> {
                    // when
                    SurveyRequest request = new SurveyRequest(saved.getTitle());
                    ErrorResponse response = putFailsWithLogin(request, SurveyController.SURVEY_URI + "/" + saved.getId(), saved.getCreatorId());

                    // then
                    assertThat(response.getMessages()).containsExactly("같은 title로 수정할 수 없습니다.");
                }),
                DynamicTest.dynamicTest("creatorId가 다를때 BadRequest", () -> {
                    // when
                    SurveyRequest request = new SurveyRequest("changed");
                    ErrorResponse response = putFailsWithLogin(request, SurveyController.SURVEY_URI + "/" + saved.getId(), noCreator.getId());

                    // then
                    assertThat(response.getMessages()).containsExactly("수정 권한이 없는 사용자, id : " + noCreator.getId());
                })
        );
    }
}
