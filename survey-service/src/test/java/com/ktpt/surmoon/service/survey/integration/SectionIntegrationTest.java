package com.ktpt.surmoon.service.survey.integration;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ktpt.surmoon.service.survey.adapter.presentation.SectionController;
import com.ktpt.surmoon.service.survey.adapter.presentation.advice.ErrorResponse;
import com.ktpt.surmoon.service.survey.application.dto.SectionRequest;
import com.ktpt.surmoon.service.survey.application.dto.SectionResponse;
import com.ktpt.surmoon.service.survey.domain.model.survey.Survey;

public class SectionIntegrationTest extends IntegrationTest {

    private Survey survey;

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        survey = findAnySurvey();
    }

    @DisplayName("섹션 생성")
    @Test
    public void createSection() {
        //given
        String title = "title";
        String description = "description";

        //when
        SectionRequest request = new SectionRequest(survey.getId(), null, title, description);
        SectionResponse response = post(request, SectionController.SECTION_URI, SectionResponse.class);

        //then
        assertThat(response.getId()).isNotNull();
        assertThat(response.getSurveyId()).isEqualTo(survey.getId());
        assertThat(response.getTitle()).isEqualTo(title);
        assertThat(response.getDescription()).isEqualTo(description);
        assertThat(response.getCreatedDate()).isNotNull();
        assertThat(response.getLastModifiedDate()).isNotNull();
    }

    @DisplayName("섹션 생성 실패")
    @Test
    public void failCreatingSection_WhenSurveyDoesNotExist() {
        //given
        Long surveyIdDoesNotExist = -1L;

        //when
        SectionRequest request = new SectionRequest(surveyIdDoesNotExist, null, "title", "description");
        ErrorResponse errorResponse = postFails(request, SectionController.SECTION_URI);

        //then
        assertThat(errorResponse.getMessages()).contains(
            "Survey with id: " + surveyIdDoesNotExist + " does not exists!");
    }

    @DisplayName("섹션 수정")
    @Test
    public void updateSection() {
        //given

        //when

        //then
    }

    @DisplayName("섹션 삭제")
    @Test
    public void deleteSection() {
        //given

        //when

        //then
    }
}
