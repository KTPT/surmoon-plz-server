package com.ktpt.surmoon.service.survey.domain.model.section;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long surveyId;
    private Long previousSectionId;
    private String title;
    private String description;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public void updateContent(Long surveyId, String title, String description) {
        verifySurveyId(surveyId);
        verifyContentsAreSame(title, description);
        this.title = title;
        this.description = description;
    }

    public Section updatePreviousSectionId(Long previousSectionId, Long surveyId) {
        verifySurveyId(surveyId);
        verifyPreviousSectionIdsAreSame(previousSectionId);
        this.previousSectionId = previousSectionId;
        return this;
    }

    private void verifySurveyId(Long surveyId) {
        if (!this.surveyId.equals(surveyId)) {
            throw new IllegalArgumentException(
                "수정할 survey와 일치하지 않습니다, 수정할 surveyId: " + this.surveyId + "입력 받은 surveyId : " + surveyId);
        }
    }

    private void verifyContentsAreSame(String title, String description) {
        if (this.title.equals(title) && this.description.equals(description)) {
            throw new IllegalArgumentException("동일한 title 과 description으로 변경할 수 없습니다.");
        }
    }

    private void verifyPreviousSectionIdsAreSame(Long previousSectionId) {
        if (this.previousSectionId.equals(previousSectionId)) {
            throw new IllegalArgumentException("동일한 previousSectionId로 변경할 수 없습니다.");
        }
    }
}
