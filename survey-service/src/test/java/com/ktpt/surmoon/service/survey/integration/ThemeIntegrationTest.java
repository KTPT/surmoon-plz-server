package com.ktpt.surmoon.service.survey.integration;

import com.ktpt.surmoon.service.survey.adapter.presentation.web.ThemeController;
import com.ktpt.surmoon.service.survey.application.dto.ThemeDTO;
import com.ktpt.surmoon.service.survey.domain.model.theme.Theme;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ThemeIntegrationTest extends IntegrationTest {

    @DisplayName("테마 수정")
    @Test
    void updateTheme() {
        // given
        Theme theme = findAnyTheme();

        // when
        ThemeDTO.ThemeRequest request = new ThemeDTO.ThemeRequest(theme.getSurveyId(), "test", "test", "test", "test");
        ThemeDTO.ThemeResponse response = put(request, ThemeController.THEME_URI, theme.getId(), ThemeDTO.ThemeResponse.class);

        // then
        assertAll(
                () -> assertThat(response.getId()).isEqualTo(theme.getId()),
                () -> assertThat(response.getSurveyId()).isEqualTo(request.getSurveyId()),
                () -> assertThat(response.getMainColor()).isEqualTo(request.getMainColor()),
                () -> assertThat(response.getFontStyle()).isEqualTo(request.getFontStyle()),
                () -> assertThat(response.getBackgroundColor()).isEqualTo(request.getBackgroundColor()),
                () -> assertThat(response.getThumbnail()).isEqualTo(request.getThumbnail())
        );
    }
}