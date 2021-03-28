package com.ktpt.surmoon.service.survey.integration;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ktpt.surmoon.service.survey.adapter.presentation.advice.ErrorResponse;
import com.ktpt.surmoon.service.survey.adapter.presentation.web.SectionController;
import com.ktpt.surmoon.service.survey.application.dto.SectionDTO;
import com.ktpt.surmoon.service.survey.domain.model.section.Section;
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
    public void create() {
        //given
        String title = "title";
        String description = "description";
        Section section1 = createFixture(0L);
        Section section2 = createFixture(section1.getId());

        //when
        SectionDTO.SectionRequest request = new SectionDTO.SectionRequest(survey.getId(), section1.getId(), title,
            description);
        SectionDTO.SectionResponse response = post(request, SectionController.SECTION_URI,
            SectionDTO.SectionResponse.class);

        Section findSection1 = getById(section1.getId());
        Section findSection2 = getById(section2.getId());

        //then
        assertThat(response.getId()).isNotNull();
        assertThat(response.getSurveyId()).isEqualTo(survey.getId());
        assertThat(response.getTitle()).isEqualTo(title);
        assertThat(response.getDescription()).isEqualTo(description);
        assertThat(response.getCreatedDate()).isNotNull();
        assertThat(response.getLastModifiedDate()).isNotNull();

        assertThat(findSection1.getPreviousSectionId()).isEqualTo(0L);
        assertThat(response.getPreviousSectionId()).isEqualTo(section1.getId());
        assertThat(findSection2.getPreviousSectionId()).isEqualTo(response.getId());
    }

    @DisplayName("섹션 생성 실패")
    @Test
    public void failCreatingSection_WhenSurveyDoesNotExist() {
        //given
        Long surveyIdDoesNotExist = -1L;

        //when
        SectionDTO.SectionRequest request = new SectionDTO.SectionRequest(surveyIdDoesNotExist, 0L, "title",
            "description");
        ErrorResponse errorResponse = postFails(request, SectionController.SECTION_URI);

        //then
        assertThat(errorResponse.getMessages()).contains(
            "Survey with id: " + surveyIdDoesNotExist + " does not exists!");
    }

    @DisplayName("섹션 내용 수정")
    @Test
    public void updateContent() {
        //given
        String updatedTitle = "updatedTitle";
        String updatedDescription = "updatedDescription";
        Section section = getById(createFixture(0L).getId());

        //when
        SectionDTO.SectionUpdateContentRequest request = new SectionDTO.SectionUpdateContentRequest(survey.getId(),
            updatedTitle,
            updatedDescription);
        SectionDTO.SectionResponse response = patch(request, SectionController.SECTION_URI, section.getId(), "content",
            SectionDTO.SectionResponse.class);

        //then
        assertThat(response.getId()).isEqualTo(section.getId());
        assertThat(response.getSurveyId()).isEqualTo(section.getSurveyId());
        assertThat(response.getTitle()).isEqualTo(updatedTitle);
        assertThat(response.getDescription()).isEqualTo(updatedDescription);
        assertThat(response.getCreatedDate()).isEqualTo(section.getCreatedDate());
        assertThat(response.getLastModifiedDate().isAfter(section.getLastModifiedDate())).isTrue();
    }

    @DisplayName("마지막 섹션 순서 수정")
    @Test
    public void updateSequence_WhenMoveLastSection() {
        //given
        Section section1 = createFixture(0L);
        Section section2 = createFixture(section1.getId());
        Section section3 = createFixture(section2.getId());

        //when
        SectionDTO.SectionUpdateSequenceRequest request = new SectionDTO.SectionUpdateSequenceRequest(survey.getId(),
            section1.getId());
        SectionDTO.SectionResponse response = patch(request, SectionController.SECTION_URI, section3.getId(),
            "sequence",
            SectionDTO.SectionResponse.class);

        Section findSection1 = getById(section1.getId());
        Section findSection2 = getById(section2.getId());
        Section findSection3 = getById(section3.getId());

        //then
        assertThat(response.getId()).isEqualTo(section3.getId());
        assertThat(response.getSurveyId()).isEqualTo(section3.getSurveyId());
        assertThat(findSection3.getLastModifiedDate().isAfter(section3.getLastModifiedDate())).isTrue();

        assertThat(findSection1.getPreviousSectionId()).isEqualTo(0L);
        assertThat(response.getPreviousSectionId()).isEqualTo(section1.getId());
        assertThat(findSection2.getPreviousSectionId()).isEqualTo(section3.getId());
    }

    @DisplayName("중간 섹션 순서 수정")
    @Test
    public void updateSequence_WhenMoveSectionInTheMiddle() {
        //given
        Section section1 = createFixture(0L);
        Section section2 = createFixture(section1.getId());
        Section section3 = createFixture(section2.getId());
        Section section4 = createFixture(section3.getId());

        //when
        SectionDTO.SectionUpdateSequenceRequest request = new SectionDTO.SectionUpdateSequenceRequest(survey.getId(),
            section1.getId());
        SectionDTO.SectionResponse response = patch(request, SectionController.SECTION_URI, section3.getId(),
            "sequence",
            SectionDTO.SectionResponse.class);

        Section findSection1 = getById(section1.getId());
        Section findSection2 = getById(section2.getId());
        Section findSection3 = getById(section3.getId());
        Section findSection4 = getById(section4.getId());

        //then
        assertThat(response.getId()).isEqualTo(section3.getId());
        assertThat(response.getSurveyId()).isEqualTo(section3.getSurveyId());
        assertThat(findSection3.getLastModifiedDate().isAfter(section3.getLastModifiedDate())).isTrue();

        assertThat(findSection1.getPreviousSectionId()).isEqualTo(0L);
        assertThat(response.getPreviousSectionId()).isEqualTo(section1.getId());
        assertThat(findSection2.getPreviousSectionId()).isEqualTo(section3.getId());
        assertThat(findSection4.getPreviousSectionId()).isEqualTo(section2.getId());
    }

    @DisplayName("섹션 내용 수정 실패")
    @Test
    public void failUpdateContent() {
        //given
        Section section = getById(createFixture(0L).getId());

        //when
        SectionDTO.SectionUpdateContentRequest request = new SectionDTO.SectionUpdateContentRequest(survey.getId(),
            "title", "description");
        ErrorResponse response = patchFails(request,
            SectionController.SECTION_URI + "/" + section.getId() + "/content");

        //then
        assertThat(response.getMessages()).contains("동일한 title 과 description으로 변경할 수 없습니다.");
    }

    @DisplayName("섹션 삭제")
    @Test
    public void deleteSection() {
        //given
        Section section = createFixture(0L);

        //when
        delete(SectionController.SECTION_URI, section.getId());

        //then
        assertThat(findOptionalById(section.getId())).isNotPresent();
    }

    private Section createFixture(Long previousSectionId) {
        return saveSection(
            new Section(null, survey.getId(), previousSectionId, "title", "description", LocalDateTime.now(),
                LocalDateTime.now()));
    }

    private Section getById(Long id) {
        return findOptionalById(id).get();
    }
}
