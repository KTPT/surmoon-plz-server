package com.ktpt.surmoon.service.survey.integration;

import com.ktpt.surmoon.service.survey.adapter.presentation.ThemeController;
import com.ktpt.surmoon.service.survey.application.dto.ThemeRequest;
import com.ktpt.surmoon.service.survey.application.dto.ThemeResponse;
import com.ktpt.surmoon.service.survey.domain.model.survey.Survey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ThemeIntegrationTest extends IntegrationTest {
    @DisplayName("테마 생성")
    @Test
    void createTheme() {
        // given
        Survey survey = findAnySurvey();

        // when
        ThemeRequest request = new ThemeRequest(survey.getId(), "thumbnail", "mainColor", "backgroundColor", "fontStyle");
        ThemeResponse response = post(request, ThemeController.THEME_URI, ThemeResponse.class);

        // then
        assertAll(
                () -> assertThat(response.getId()).isNotNull(),
                () -> assertThat(response.getSurveyId()).isEqualTo(request.getSurveyId()),
                () -> assertThat(response.getMainColor()).isEqualTo(request.getMainColor()),
                () -> assertThat(response.getFontStyle()).isEqualTo(request.getFontStyle()),
                () -> assertThat(response.getBackgroundColor()).isEqualTo(request.getBackgroundColor()),
                () -> assertThat(response.getThumbnail()).isEqualTo(request.getThumbnail())
        );
    }
}
