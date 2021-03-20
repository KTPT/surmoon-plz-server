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

    public Section updateContent(Long surveyId, String title, String description) {
        verifyUpdate(surveyId);
        this.title = title;
        this.description = description;
        return this;
    }

    public Section updatePreviousSectionId(Long surveyId, Long previousSectionId) {
        verifyUpdate(surveyId);
        this.previousSectionId = previousSectionId;
        return this;
    }

    private void verifyUpdate(Long surveyId) {
        if (!this.surveyId.equals(surveyId)) {
            throw new IllegalArgumentException("해당 survey가 아닙니다, surveyId : " + surveyId);
        }
    }
}
