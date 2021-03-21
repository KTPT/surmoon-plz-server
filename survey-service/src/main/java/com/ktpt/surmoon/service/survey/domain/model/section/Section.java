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

    public Section updatePreviousSectionId(Long surveyId, Long previousSectionId) {
        verifySurveyId(surveyId);
        this.previousSectionId = previousSectionId;
        return this;
    }

    private void verifySurveyId(Long surveyId) {
        if (!this.surveyId.equals(surveyId)) {
            throw new IllegalArgumentException("해당 survey가 아닙니다, surveyId : " + surveyId);
        }
    }

    private void verifyContentsAreSame(String title, String description) {
        if (this.title.equals(title)) {
            throw new IllegalArgumentException("동일한 title로 변경할 수 없습니다.");
        }
        if (this.description.equals(description)) {
            throw new IllegalArgumentException("동일한 description으로 변경할 수 없습니다.");
        }
    }
}
