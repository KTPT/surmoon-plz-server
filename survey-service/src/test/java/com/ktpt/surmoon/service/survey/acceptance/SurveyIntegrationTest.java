package com.ktpt.surmoon.service.survey.acceptance;

import com.ktpt.surmoon.service.survey.adapter.presentation.SurveyController;
import com.ktpt.surmoon.service.survey.adapter.presentation.advice.ErrorResponse;
import com.ktpt.surmoon.service.survey.application.dto.SurveyRequest;
import com.ktpt.surmoon.service.survey.application.dto.SurveyResponse;
import com.ktpt.surmoon.service.survey.domain.model.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class SurveyIntegrationTest extends IntegrationTest {
    @DisplayName("설문을 생성한다.")
    @Test
    void createSurvey() {
        // given
        Member creator = createMember();

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
}
