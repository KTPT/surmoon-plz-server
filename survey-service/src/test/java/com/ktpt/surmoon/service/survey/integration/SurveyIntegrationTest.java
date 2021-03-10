package com.ktpt.surmoon.service.survey.integration;

import com.ktpt.surmoon.service.survey.adapter.presentation.SurveyController;
import com.ktpt.surmoon.service.survey.adapter.presentation.advice.ErrorResponse;
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
        SurveyRequest request = new SurveyRequest("title", creator.getId());
        SurveyResponse response = post(request, SurveyController.SURVEY_URI, SurveyResponse.class);

        // then
        assertAll(
                () -> assertThat(response.getId()).isNotNull(),
                () -> assertThat(response.getTitle()).isEqualTo(request.getTitle()),
                () -> assertThat(response.getCreatorId()).isEqualTo(request.getCreatorId()),
                () -> assertThat(response.getCreatedDate()).isNotNull(),
                () -> assertThat(response.getLastModifiedDate()).isNotNull()
        );
    }

    @DisplayName("설문 생성 실패")
    @Test
    void createSurveyFails() {
        // when
        SurveyRequest request = new SurveyRequest("", -1L);
        ErrorResponse response = postFails(request, SurveyController.SURVEY_URI);

        // then
        assertThat(response.getMessages())
                .containsExactlyInAnyOrder("must not be blank", "Member with id: -1 does not exists!");
    }

    @DisplayName("설문 수정")
    @Test
    void updateSurvey() {
        // given
        Survey saved = findAnySurvey();

        // when
        SurveyRequest request = new SurveyRequest("changed", saved.getCreatorId());
        SurveyResponse response = put(request, SurveyController.SURVEY_URI + "/" + saved.getId(), SurveyResponse.class);

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

        return Stream.of(
                DynamicTest.dynamicTest("같은 title로 수정시 BadRequest", () -> {
                    // when
                    SurveyRequest request = new SurveyRequest(saved.getTitle(), saved.getCreatorId());
                    ErrorResponse response = putFails(request, SurveyController.SURVEY_URI + "/" + saved.getId());

                    // then
                    assertThat(response.getMessages()).containsExactly("같은 title로 수정할 수 없습니다.");
                }),
                DynamicTest.dynamicTest("creatorId가 다를때 BadRequest", () -> {
                    // when
                    SurveyRequest request = new SurveyRequest("changed", -1L);
                    ErrorResponse response = putFails(request, SurveyController.SURVEY_URI + "/" + saved.getId());

                    // then
                    assertThat(response.getMessages()).containsExactly("수정 권한이 없는 사용자, id : " + -1);
                })
        );
    }
}
